package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * Review ���̺��� Ŀ�´�Ƽ ������ �߰�, ����, ����, �˻� ���� 
 */
public class ReviewDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReviewDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
/**
CREATE TABLE Review
(
   post_id              INTEGER NOT NULL ,
   title                VARCHAR2(40) NOT NULL ,
   content              VARCHAR2(40) NULL ,
   creationDate         DATE NULL ,
   image                VARCHAR2(40) NULL ,
   writer               VARCHAR2(20) NOT NULL ,
   animal_id            INTEGER NOT NULL 
);

	*/
	public int create(Review review) throws SQLException {
		String sql = "INSERT INTO Review "
				+ "VALUES (post_id_seq.nextval, ?, ?, SYSDATE, ?, ?, ?)";		
		Object[] param = new Object[] {
				review.getTitle(),
				review.getContent(),
				review.getImage(),
				review.getWriter(),
				review.getAnimal_id()
				};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = {"post_id"};	// PK �÷��� �̸�     
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		review.setPost_id(generatedKey); 	// id�ʵ忡 ����  
		   	}
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
	

	/**
	 * ������ ���� ������ ����
	 */
	public int update(Review review) throws SQLException {
		String sql = "UPDATE Review "
					+ "SET  title=?, content=? "
					+ "WHERE post_id=?";
		Object[] param = new Object[] {
				review.getTitle(), 
				review.getContent(),
				review.getPost_id()
				};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	
	/**
	 * �־��� ID�� �ش��ϴ� Ŀ�´�Ƽ ������ ����.
	 */
	public int remove(int post_id) throws SQLException {
		String sql = "DELETE FROM Review WHERE post_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	//review detail
	public Review findReview(int post_id) throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
     		   + "FROM Review " 
     		  + "WHERE post_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				Review review = new Review(		// User ��ü�� �����Ͽ� �л� ������ ����
					rs.getInt("post_id"),
					rs.getInt("animal_id"),
					rs.getString("writer"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getDate("creationDate"),
					rs.getString("image")
					);
				return review;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	
	/**
	 * ��ü Ŀ�´�Ƽ ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 
	public List<Review> findReviewList() throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
        		   + "FROM Review " 
        		   + "ORDER BY post_id ";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Review> animalList = new ArrayList<Review>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Review animal = new Review(
						rs.getInt("post_id"), 
						rs.getString("animal_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("creationDate"),
						rs.getString("image"));
					animalList.add(animal);				// List�� Community ��ü ����
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	*/
	
	public List<Review> findReviewList() throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate "
        		   + "FROM Review " 
        		   + "ORDER BY post_id ";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Review> animalList = new ArrayList<Review>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Review animal = new Review(
						rs.getInt("post_id"), 
						rs.getString("title"),
						rs.getString("content"), 
						rs.getDate("creationDate"),
						rs.getString("writer"),
						rs.getInt("animal_id")
						);
					animalList.add(animal);				// List�� Community ��ü ����
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public List<Review> findUserReviewList(String user_id) throws SQLException {
        String sql = "SELECT post_id, animal_id, title, content, creationDate "
        		   + "FROM Review "
        		   + "WHERE writer=? "
        		   + "ORDER BY post_id ";        
        			
        
		jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Review> animalList = new ArrayList<Review>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Review animal = new Review(
						rs.getInt("post_id"), 
						rs.getString("title"),
						rs.getString("content"), 
						rs.getDate("creationDate"),
						user_id,
						rs.getInt("animal_id")
						);
					animalList.add(animal);				// List�� Community ��ü ����
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
//	
//	public List<Review> searchReviewList(int post_id) throws SQLException {
//		String sql = null;
//		// list.jsp���� species�� 0�̸�  type�� ��ü�� �����Ѱ��̴� category_id=?�� �ָ� �ȵ�
//		String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
//     		   + "FROM Review " 
//     		   + "ORDER BY post_id ";   
//		
//        Object[] param = new Object[] { category_id, animal_type, matched};	// JDBCUtil�� update���� �Ű� ���� ����
//        jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
//        
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
//			List<Animal> animalList = new ArrayList<Animal>();	// Community���� ����Ʈ ����
//			while (rs.next()) {
//				Animal animal = new Animal(
//						rs.getInt("animal_id"), 
//						rs.getInt("category_id"),
//						rs.getInt("age"),
//						rs.getString("location"),
//						rs.getInt("matched"),
//						rs.getString("image"),
//						rs.getString("species"),
//						rs.getString("animal_type"));
//
//				animalList.add(animal);				// List�� Community ��ü ����
//			}		
//			return animalList;					
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource ��ȯ
//		}
//		return null;
//	}
	

}
