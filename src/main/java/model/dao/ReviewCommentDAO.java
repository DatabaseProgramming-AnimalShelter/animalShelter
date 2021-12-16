package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;
import model.Review_Comment;

/**
 * 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쌜억옙占쏙옙 占쏙옙占쏙옙占싹댐옙 DAO 클占쏙옙占쏙옙
 * Review_Comment 占쏙옙占싱븝옙占쏙옙 커占승댐옙티 占쏙옙占쏙옙占쏙옙 占쌩곤옙, 占쏙옙占쏙옙, 占쏙옙占쏙옙, 占싯삼옙 占쏙옙占쏙옙 
 */

public class ReviewCommentDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReviewCommentDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 占쏙옙체 占쏙옙占쏙옙
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
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쏙옙 insert占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
						
		String key[] = {"comment_id"};	// PK 占시뤄옙占쏙옙 占싱몌옙     
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert 占쏙옙 占쏙옙占쏙옙
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 占쏙옙占쏙옙占쏙옙 PK 占쏙옙
		   		review_comment.setPost_id(generatedKey); 	// id占십드에 占쏙옙占쏙옙  
		   	}
		   	return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;			
	}
	

	/**
	 * 占쏙옙占� 占쏙옙占쏙옙
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
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쏙옙 update占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
	}

	
	/**
	 * 占쌍억옙占쏙옙 ID占쏙옙 占쌔댐옙占싹댐옙 커占승댐옙티 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙.
	 */
	public int remove(int comment_id) throws SQLException {
		String sql = "DELETE FROM Review_Comment WHERE comment_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {comment_id});	// JDBCUtil占쏙옙 delete占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
	}

	//review detail
	public Review_Comment findReviewComment(int post_id) throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
     		   + "FROM Review " 
     		  + "WHERE post_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {						// 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
				Review review = new Review(		// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占싻삼옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
					rs.getInt("post_id"),
					rs.getInt("animal_id"),
					rs.getString("writer"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getDate("creationDate"),
					rs.getString("image")
					);
				//return review;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	
	public List<Review_Comment> findReviewCommentList(int post_id) throws SQLException {
        String sql = "SELECT comment_id, post_id, user_id, creationDate, parent, content "
        		   + "FROM Review_Comment " 
          		   + "WHERE post_id=?"
        		   + "ORDER BY comment_id ";        

		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쏙옙占쏙옙			
			List<Review_Comment> reviewCommentList = new ArrayList<Review_Comment>();	// review_comment 占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				String user_id = rs.getString("user_id");
		        if(user_id == null) { 
		        	user_id = "(�븣 �닔 �뾾�쓬)";
		        }
				Review_Comment review_comment = new Review_Comment(
						rs.getInt("comment_id"), 
						rs.getInt("post_id"),
						user_id,
						rs.getDate("creationDate"),
						rs.getInt("parent"),
						rs.getString("content")
						);
				reviewCommentList.add(review_comment);	// List占쏙옙 review_comment 占쏙옙체 占쏙옙占쏙옙
			}		
			return reviewCommentList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	


	

}
