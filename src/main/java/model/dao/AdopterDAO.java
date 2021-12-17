package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Adopter;

public class AdopterDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AdopterDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}

	public int create(Adopter user) throws SQLException {
		String sql = "INSERT INTO adopter VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUser_id(), user.getPassword(), 
						user.getUser_name(), user.getEmail(), user.getPhone()}; 
						//(user.getCommId()!=0) ? user.getCommId() : null };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	public int update(Adopter user) throws SQLException {
		String sql = "UPDATE adopter "
					+ "SET  password=?, user_name=?, email=?, phone=? "
					+ "WHERE user_id=?";
		Object[] param = new Object[] {
				user.getPassword(), user.getUser_name(), 
					user.getEmail(), user.getPhone(),user.getUser_id()};				
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}

	public int remove(String user_id) throws SQLException {
		String sql = "DELETE FROM adopter WHERE user_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});	

		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}

	public Adopter findUser(String user_id) throws SQLException {
        String sql = "SELECT password, user_name, email, phone "
        			+ "FROM adopter "
        			+ "WHERE user_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {						
				Adopter user = new Adopter(		
					user_id,
					rs.getString("password"),
					rs.getString("user_name"),
					rs.getString("email"),
					rs.getString("phone"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 
	public List<User> findUserList() throws SQLException {
        String sql = "SELECT userId, name, email, NVL(commId,0) AS commId, cName " 
        		   + "FROM adopter u LEFT OUTER JOIN Community c ON u.commId = c.cId "
        		   + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<User> userList = new ArrayList<User>();	// User���� ����Ʈ ����
			while (rs.next()) {
				User user = new User(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("userId"),
					null,
					rs.getString("name"),
					rs.getString("email"),
					null,
					rs.getInt("commId"),
					rs.getString("cName"));
				userList.add(user);				// List�� User ��ü ����
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	*/
	
	/**
	 * ��ü ����� ������ �˻��� �� ���� �������� �������� ����� ����� ���� �̿��Ͽ�
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 
	public List<User> findUserList(int currentPage, int countPerPage) throws SQLException {
		String sql = "SELECT userId, name, email, NVL(commId, 0), cName " 
					+ "FROM adopter u LEFT OUTER JOIN Community c ON u.commId = c.cId "
					+ "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<User> userList = new ArrayList<User>();	// User���� ����Ʈ ����
				do {
					User user = new User(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
						rs.getString("userId"),
						null,
						rs.getString("name"),
						rs.getString("email"),
						null,
						rs.getInt("commId"),
						rs.getString("cName"));
					userList.add(user);							// ����Ʈ�� User ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return userList;							
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}*/

	/**
	 * Ư�� Ŀ�´�Ƽ�� ���� ����ڵ��� �˻��Ͽ� List�� ���� �� ��ȯ
	
	public List<User> findUsersInCommunity(int communityId) throws SQLException {
        String sql = "SELECT userId, name, email, phone FROM adopter "
     				+ "WHERE commId = ?";                         
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			List<User> memList = new ArrayList<User>();	// member���� ����Ʈ ����
			while (rs.next()) {
				User member = new User(			// User ��ü�� �����Ͽ� ���� ���� ������ ����
					rs.getString("userId"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("phone"));
				memList.add(member);			// List�� Community ��ü ����
			}		
			return memList;					
				
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	} */
	
	/**
	 * Ư�� Ŀ�´�Ƽ�� ���� ����ڵ��� ���� count�Ͽ� ��ȯ
	
	public int getNumberOfUsersInCommunity(int communityId) {
		String sql = "SELECT COUNT(userId) FROM adopter "
     				+ "WHERE commId = ?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil�� query���� �Ű� ���� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			rs.next();										
			return rs.getInt(1);			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return 0;
	}
	 */
	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingUser(String user_id) throws SQLException {
		String sql = "SELECT count(*) FROM adopter WHERE user_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}

}
