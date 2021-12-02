package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.adopt.CreateAdoptApplyController;

import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import model.AdoptApply;
import model.Adopter;
import model.Animal;

public class AdoptApplyDAO {
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);
	
	private JDBCUtil jdbcUtil = null;
	AdoptApply adoptApply = new AdoptApply();

	public AdoptApplyDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(AdoptApply adoptApply) throws SQLException {

		String sql = "INSERT INTO AdoptApply VALUES (apply_id_seq.nextval, ?, ?, ?, ?, ?, ?, SYSDATE, null)";
		Object[] param = new Object[] { adoptApply.getUser_id(), adoptApply.getAnimal_id(), adoptApply.getContent(),
				adoptApply.getLiving_environment(), 0, adoptApply.getHave_pets() };
		jdbcUtil.setSqlAndParameters(sql, param);

		String key[] = { "apply_id" };
		int generatedKey = 0;
		try {
			jdbcUtil.executeUpdate(key);
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1);
				adoptApply.setAnimal_id(generatedKey);
			}
			return generatedKey;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	// 승인 -> AdoptApply의 apply_matched : 1, approval_date : SYSDATE & Animal의 animal_matched : 1
	// 거절 -> AdoptApply의 apply_matched : 1, approval_date : SYSDATE
	
	// 승인과 거절 합친 메소드
	public int apply_result(AdoptApply adoptApply, int apply_result) throws SQLException {
		int result = 0;
		
		// 승인, 거절 모두 해당
		try {
			String sql = "UPDATE AdoptApply " + "SET apply_matched=? , approval_date=SYSDATE " + "WHERE apply_id=? ";
			Object[] param = new Object[] { 1, adoptApply.getApply_id() };
			jdbcUtil.setSqlAndParameters(sql, param);
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}

		// 승인 됐을 경우
		if (apply_result == 1) {
			
			try {
				String sql2 = "UPDATE Animal " + "SET  animal_matched=? " + "WHERE animal_id=? ";
				Object[] param2 = new Object[] { 1, adoptApply.getAnimal_id() };
				jdbcUtil.setSqlAndParameters(sql2, param2);
				result += jdbcUtil.executeUpdate();
				
				/*
				 * // 한 동물에 대해 다른 입양 신청이 존재 할 경우 다른 신청들 모두 거절 처리 String sql3 = "UPDATE Animal "
				 * + "SET  animal_matched=? " + "WHERE animal_id=? ";
				 * 
				 * Iterator<AdoptApply> iter = adoptApplyList.iterator(); while(iter.hasNext())
				 * { check = true; AdoptApply apply = iter.next(); Object[] param3 = new
				 * Object[] { 0, apply.getAnimal_id() }; jdbcUtil.setSqlAndParameters(sql2,
				 * param2); jdbcUtil.executeUpdate(); jdbcUtil.commit(); jdbcUtil.close(); }
				 */
				
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();
			}
		}

		return result;
	}

	// adoptapply의 matched 값: 1 + animal의 matched: 1
	public int approval(AdoptApply adoptApply) throws SQLException {

		String sql = "UPDATE AdoptApply " + "SET apply_matched=? , approval_date=SYSDATE " + "WHERE apply_id=? ";
		Object[] param = new Object[] { 1, adoptApply.getApply_id() };

		String sql2 = "UPDATE Animal " + "SET  animal_matched=? " + "WHERE animal_id=? ";
		Object[] param2 = new Object[] { 1, adoptApply.getAnimal_id() };
		// DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		try {
			// Date date = new Date(df.parse(adoptApply.getApproval_date()).getTime());
			jdbcUtil.setSqlAndParameters(sql, param);

			jdbcUtil.executeUpdate();
			jdbcUtil.commit();
			jdbcUtil.close();
			jdbcUtil.setSqlAndParameters(sql2, param2);
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 獄쏆꼹 넎
		}
		return 0;
	}

	// adoptapply의 matched 값만 1
	public int decline(AdoptApply adoptApply) throws SQLException {

		String sql = "UPDATE AdoptApply " + "SET  apply_matched=?, approval_date=SYSDATE " + "WHERE apply_id=?";
		Object[] param = new Object[] { 1, adoptApply.getApply_id() };

		// DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			// Date date = new Date(df.parse(adoptApply.getApproval_date()).getTime());
			jdbcUtil.setSqlAndParameters(sql, param);
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	// view
	public AdoptApply findAdoptApply(int apply_id) throws SQLException {
		String sql = "SELECT adp.apply_id, adp.user_id, adp.animal_id, adp.content, adp.living_environment, adp.have_pets, adp.apply_matched, adp.apply_date, a.image, u.user_name, c.animal_type, c.species  "
				+ "FROM AdoptApply adp , Adopter u , Animal a ,Category c "
				+ "WHERE adp.user_id = u.user_id and a.animal_id = adp.animal_id and a.category_id = c.category_id and adp.apply_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { apply_id });

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				Date apply_date = new Date(rs.getDate("apply_date").getTime());
				String apply_dateString = df.format(apply_date);
				adoptApply = new AdoptApply(rs.getInt("apply_id"), rs.getString("user_id"), rs.getInt("animal_id"),
						rs.getString("content"), rs.getString("living_environment"), rs.getString("have_pets"),
						rs.getInt("apply_matched"), apply_dateString, rs.getString("image"), rs.getString("user_name"),
						rs.getString("animal_type"), rs.getString("species"));
				return adoptApply;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	// 관리자 입장에서 입양신청의 리스트를 보여주는 페이지
	public List<AdoptApply> findAdoptApplyList() throws SQLException {
		String sql = "SELECT adp.apply_id, adp.user_id,  a.user_name, adp.animal_id,adp.apply_matched, adp.apply_date "
				+ "FROM AdoptApply adp JOIN Adopter a ON adp.user_id = a.user_id " + "WHERE adp.apply_matched=0 "
				+ "ORDER BY apply_id ";
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		jdbcUtil.setSqlAndParameters(sql, null);
		System.out.println("1ddddddd");
		try {
			System.out.println("2ddddddd");
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쎈뼄占쎈뻬
			List<AdoptApply> adoptApplyList = new ArrayList<AdoptApply>();
			while (rs.next()) {
				Date apply_date = new Date(rs.getDate("apply_date").getTime());
				String apply_dateString = df.format(apply_date);
				AdoptApply adoptApply = new AdoptApply(rs.getInt("apply_id"), rs.getString("user_id"),
						rs.getInt("animal_id"), rs.getInt("apply_matched"), apply_dateString,
						rs.getString("user_name"));
				adoptApplyList.add(adoptApply);
			}
			return adoptApplyList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹 넎
		}
		return null;
	}

	// 입양결과를 다 보여주는 페이지 ( 관리자가 승인 거부 이후)
	public List<AdoptApply> findAdoptApplyResultList() throws SQLException {
		String sql = "SELECT adp.apply_id, adp.user_id,  a.user_name, adp.animal_id,adp.apply_matched, adp.apply_date, adp.approval_date ,an.animal_matched "
				+ "FROM AdoptApply adp, Adopter a ,Animal an "
				+ "WHERE apply_matched = ? and adp.user_id = a.user_id  and adp.animal_id = an.animal_id "
				+ "ORDER BY adp.apply_id ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { 1 });

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<AdoptApply> adoptApplyList = new ArrayList<AdoptApply>();
			while (rs.next()) {
				Date apply_date = new Date(rs.getDate("apply_date").getTime());
				Date approval_date = new Date(rs.getDate("approval_date").getTime());
				String apply_dateString = df.format(apply_date);
				String approval_dateString = df.format(approval_date);

				AdoptApply adoptApply = new AdoptApply(rs.getInt("apply_id"), rs.getString("user_id"),
						rs.getInt("animal_id"), rs.getInt("apply_matched"), apply_dateString, rs.getString("user_name"),
						approval_dateString, rs.getInt("animal_matched"));

				adoptApplyList.add(adoptApply);
			}
			return adoptApplyList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹 넎
		}
		return null;
	}
	
	// 마이페이지에서 보여질 입양 리스트
	public List<AdoptApply> findAdoptApplyResult(String user_id) throws SQLException {
		String sql = "SELECT adp.apply_id, adp.animal_id, adp.apply_matched, adp.apply_date, adp.approval_date, a.animal_matched,  "
				+ "FROM AdoptApply adp JOIN Animal a ON adp.animal_id = a.animal_id "
				+ "WHERE apply_matched = ? and adp.user_id = ? "
				+ "ORDER BY adp.apply_id ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { 1, user_id});

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<AdoptApply> adoptApplyList = new ArrayList<AdoptApply>();
			while (rs.next()) {
				Date apply_date = new Date(rs.getDate("apply_date").getTime());
				Date approval_date = new Date(rs.getDate("approval_date").getTime());
				String apply_dateString = df.format(apply_date);
				String approval_dateString = df.format(approval_date);

				AdoptApply adoptApply = new AdoptApply(
						rs.getInt("apply_id"),
						rs.getInt("animal_id"), 
						rs.getInt("animal_matched"),
						rs.getInt("apply_matched"), 
						apply_dateString,
						approval_dateString
				);
				adoptApplyList.add(adoptApply);
			}
			return adoptApplyList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 獄쏆꼹 넎
		}
		return null;
	}
	
	public List<AdoptApply> findUserAdoptList(String user_id) throws SQLException {	
		String sql = "SELECT adp.apply_id, adp.animal_id, adp.apply_matched, a.animal_matched, adp.approval_date, c.animal_type, c.species  "
				+ "FROM AdoptApply adp , Animal a ,Category c "
				+ "WHERE a.animal_id = adp.animal_id and a.category_id = c.category_id and adp.user_id=? "
				+ "ORDER BY adp.apply_id ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});		
			
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			ResultSet rs = jdbcUtil.executeQuery();						
			List<AdoptApply> userAdoptApplyList = new ArrayList<AdoptApply>();	
			while (rs.next()) {
				int apply_matched = rs.getInt("apply_matched");
				String approval_dateString;
				
				if(apply_matched == 0) {
					approval_dateString = "null";
				}
				else {
					Date approval_date = new Date(rs.getDate("approval_date").getTime());
					approval_dateString = df.format(approval_date);
				}
				AdoptApply userAdoptApply = new AdoptApply(			
						rs.getInt("apply_id"), 
						rs.getInt("animal_id"),
						apply_matched, 
						rs.getInt("animal_matched"),
						approval_dateString,
						rs.getString("animal_type"), 
						rs.getString("species")
						);
				userAdoptApplyList.add(userAdoptApply);				
			}		
			return userAdoptApplyList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public List<AdoptApply> findAnimalAdoptList(int animal_id) throws SQLException {	
		String sql = "SELECT apply_id, user_id  "
				+ "FROM AdoptApply "
				+ "WHERE animal_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});		
			
		try {
			ResultSet rs = jdbcUtil.executeQuery();						
			List<AdoptApply> userAdoptApplyList = new ArrayList<AdoptApply>();	
			while (rs.next()) {
				AdoptApply userAdoptApply = new AdoptApply(			
						rs.getInt("apply_id"), 
						rs.getString("user_id")
						);
				userAdoptApplyList.add(userAdoptApply);				
			}		
			return userAdoptApplyList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
}