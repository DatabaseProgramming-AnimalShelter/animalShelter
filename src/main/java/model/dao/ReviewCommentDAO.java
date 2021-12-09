package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;
import model.Review_Comment;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * Review_Comment ���̺��� Ŀ�´�Ƽ ������ �߰�, ����, ����, �˻� ���� 
 */

public class ReviewCommentDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReviewCommentDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
/**
CREATE TABLE Review_Comment
(
	comment_id           INTEGER NOT NULL ,
	post_id              INTEGER NOT NULL ,
	user_id              VARCHAR2(20) NOT NULL ,
	creationDate         DATE NULL ,
	parent               INTEGER NULL ,
	content              VARCHAR2(20) NULL ,
 	PRIMARY KEY (comment_id),
	FOREIGN KEY (user_id) REFERENCES Adopter (user_id),
	FOREIGN KEY (post_id) REFERENCES Review (post_id)
);
*/
	
	public int create(Review_Comment review_comment) throws SQLException {
		String sql = "INSERT INTO Review_Comment "
				+ "VALUES (comment_id_seq.nextval, ?, ?, SYSDATE, ?, ?)";		
		Object[] param = new Object[] {
				review_comment.getPost_id(),
				review_comment.getUser_id(),
				review_comment.getParent(),
				review_comment.getContent()
				};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = {"comment_id"};	// PK �÷��� �̸�     
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		review_comment.setPost_id(generatedKey); 	// id�ʵ忡 ����  
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
	 * ��� ����
	 */
	public int update(Review_Comment review_comment) throws SQLException {
		String sql = "UPDATE Review "
					+ "SET  title=?, content=? "
					+ "WHERE post_id=?";
		Object[] param = new Object[] {
				review_comment.getTitle(), 
				review_comment.getContent(),
				review_comment.getPost_id()
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
	public int remove(int comment_id) throws SQLException {
		String sql = "DELETE FROM Review_Comment WHERE comment_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {comment_id});	// JDBCUtil�� delete���� �Ű� ���� ����

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
	public Review_Comment findReviewComment(int post_id) throws SQLException {
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
	
	public List<Review_Comment> findReviewCommentList(int post_id) throws SQLException {
        String sql = "SELECT comment_id, post_id, user_id, creationDate, parent, content "
        		   + "FROM Review_Comment " 
          		   + "WHERE post_id=?"
        		   + "ORDER BY comment_id ";        

		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Review_Comment> reviewCommentList = new ArrayList<Review_Comment>();	// review_comment ���� ����Ʈ ����
			while (rs.next()) {
				String user_id = rs.getString("user_id");
		        if(user_id == null) { 
		        	user_id = "(알 수 없음)";
		        }
				Review_Comment review_comment = new Review_Comment(
						rs.getInt("comment_id"), 
						rs.getInt("post_id"),
						user_id,
						rs.getDate("creationDate"),
						rs.getInt("parent"),
						rs.getString("content")
						);
				reviewCommentList.add(review_comment);	// List�� review_comment ��ü ����
			}		
			return reviewCommentList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	


	

}
