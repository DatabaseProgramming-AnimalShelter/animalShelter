package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import model.AdoptApply;
import model.Adopter;

public class AdoptApplyDAO {

	private JDBCUtil jdbcUtil = null;
	AdoptApply adoptApply = new AdoptApply();
	
	public AdoptApplyDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 媛앹껜 �깮�꽦
	}
	
	public int create(AdoptApply adoptApply) throws SQLException {
		String sql = "INSERT INTO AdoptApply VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {adoptApply.getApply_id(), adoptApply.getUser_id(), 
				adoptApply.getAnimal_id(), adoptApply.getContent(), adoptApply.getLiving_environment(),
				adoptApply.getHave_pets(), adoptApply.getApply_matched(), adoptApply.getApply_date(), adoptApply.getApproval_date()}; 
						//(user.getCommId()!=0) ? user.getCommId() : null };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;			
	}


	// �엯�뼇 �떊泥� �닔�씫�븯硫� matched->1, �듅�씤�궇吏� 蹂�寃�
	public int update(AdoptApply adoptApply) throws SQLException {
		String sql = "UPDATE AdoptApply "
					+ "SET  matched=?, approval_date=? "
					+ "WHERE apply_id=?";

		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		try {		
			Date date = new Date(df.parse(adoptApply.getApproval_date()).getTime());
			// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			Object[] param = new Object[] { 1, date, adoptApply.getApply_id()};				
			jdbcUtil.setSqlAndParameters(sql, param);
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}

	// �엯�뼇�떊泥� �닔�씫 �쟾 �떊泥�由ъ뒪�듃 李얘린
	public AdoptApply findAdoptApply(String apply_id) throws SQLException {
        String sql = "SELECT apply_id, user_id, animal_id, content, living_environment, have_pets, apply_matched, apply_date "
        			+ "FROM AdoptApply "
        			+ "WHERE apply_matched=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {0});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {
				Date apply_date = new Date(rs.getDate("apply_date").getTime());
				String apply_dateString = df.format(apply_date);
				
				adoptApply = new AdoptApply(		// User 媛앹껜瑜� �깮�꽦�븯�뿬 �븰�깮 �젙蹂대�� ���옣
					rs.getInt("apply_id"),
					rs.getString("user_id"),
					rs.getInt("animal_id"),
					rs.getString("content"),
					rs.getString("living_environment"),
					rs.getString("have_pets"),
					rs.getInt("apply_matched"),
					apply_dateString);
				return adoptApply;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	// �엯�뼇�떊泥� �닔�씫 �썑 �떊泥�由ъ뒪�듃 李얘린
		public AdoptApply findAdoptApplyResult(String apply_id) throws SQLException {
	        String sql = "SELECT apply_id, user_id, animal_id, content, living_environment, have_pets, apply_matched, apply_date "
	        			+ "FROM AdoptApply "
	        			+ "WHERE apply_matched IN (?,?) ";              
			jdbcUtil.setSqlAndParameters(sql, new Object[] {-1, 1});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			
			
			try {
				ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
				if (rs.next()) {
					Date apply_date = new Date(rs.getDate("apply_date").getTime());
					String apply_dateString = df.format(apply_date);
					
					Date approval_date = new Date(rs.getDate("approval_date").getTime());
					String approval_dateString = df.format(approval_date);
					
					adoptApply = new AdoptApply(		// User 媛앹껜瑜� �깮�꽦�븯�뿬 �븰�깮 �젙蹂대�� ���옣
						rs.getInt("apply_id"),
						rs.getString("user_id"),
						rs.getInt("animal_id"),
						rs.getString("content"),
						rs.getString("living_environment"),
						rs.getString("have_pets"),
						rs.getInt("apply_matched"),
						apply_dateString,
						approval_dateString);
					return adoptApply;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 諛섑솚
			}
			return null;
		}

}


