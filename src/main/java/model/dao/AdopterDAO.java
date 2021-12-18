package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Adopter;

public class AdopterDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AdopterDAO() {			
		jdbcUtil = new JDBCUtil();	
	}

	public int create(Adopter user) throws SQLException {
		String sql = "INSERT INTO adopter VALUES (?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUser_id(), user.getPassword(), 
						user.getUser_name(), user.getEmail(), user.getPhone()}; 
						
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
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

	public boolean existingUser(String user_id) throws SQLException {
		String sql = "SELECT count(*) FROM adopter WHERE user_id=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}

}
