package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;
import model.Review_Comment;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Review_Comment 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */

public class ReviewCommentDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReviewCommentDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
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
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = {"comment_id"};	// PK 컬럼의 이름     
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		review_comment.setPost_id(generatedKey); 	// id필드에 저장  
		   	}
		   	return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	

	/**
	 * 댓글 수정
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
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	
	/**
	 * 주어진 ID에 해당하는 커뮤니티 정보를 삭제.
	 */
	public int remove(int comment_id) throws SQLException {
		String sql = "DELETE FROM Review_Comment WHERE comment_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {comment_id});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	//review detail
	public Review_Comment findReviewComment(int post_id) throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
     		   + "FROM Review " 
     		  + "WHERE post_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Review review = new Review(		// User 객체를 생성하여 학생 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<Review_Comment> findReviewCommentList(int post_id) throws SQLException {
        String sql = "SELECT comment_id, post_id, user_id, creationDate, parent, content "
        		   + "FROM Review_Comment " 
          		   + "WHERE post_id=?"
        		   + "ORDER BY comment_id ";        

		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Review_Comment> reviewCommentList = new ArrayList<Review_Comment>();	// review_comment 들의 리스트 생성
			while (rs.next()) {
				Review_Comment review_comment = new Review_Comment(
						rs.getInt("comment_id"), 
						rs.getInt("post_id"),
						rs.getString("user_id"), 
						rs.getDate("creationDate"),
						rs.getInt("parent"),
						rs.getString("content")
						);
				reviewCommentList.add(review_comment);	// List에 review_comment 객체 저장
			}		
			return reviewCommentList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	


	

}