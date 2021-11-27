package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Review 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class ReviewDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReviewDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
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
				"imageimage",
				review.getWriter(),
				review.getAnimal_id()
				};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = {"post_id"};	// PK 컬럼의 이름     
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		review.setPost_id(generatedKey); 	// id필드에 저장  
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
	 * 기존의 동물 정보를 수정
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
	public int remove(int post_id) throws SQLException {
		String sql = "DELETE FROM Review WHERE post_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil에 delete문과 매개 변수 설정

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
	public Review findReview(int post_id) throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
     		   + "FROM Review " 
     		  + "WHERE post_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Review review = new Review(		// User 객체를 생성하여 학생 정보를 저장
					rs.getInt("post_id"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getDate("creationDate"),
					rs.getString("writer"),
					rs.getInt("animal_id")
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
	
	
	/**
	 * 전체 커뮤니티 정보를 검색하여 List에 저장 및 반환
	 
	public List<Review> findReviewList() throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
        		   + "FROM Review " 
        		   + "ORDER BY post_id ";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Review> animalList = new ArrayList<Review>();	// Community들의 리스트 생성
			while (rs.next()) {
				Review animal = new Review(
						rs.getInt("post_id"), 
						rs.getString("animal_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("creationDate"),
						rs.getString("image"));
					animalList.add(animal);				// List에 Community 객체 저장
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	*/
	
	public List<Review> findReviewList() throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate "
        		   + "FROM Review " 
        		   + "ORDER BY post_id ";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Review> animalList = new ArrayList<Review>();	// Community들의 리스트 생성
			while (rs.next()) {
				Review animal = new Review(
						rs.getInt("post_id"), 
						rs.getString("title"),
						rs.getString("content"), 
						rs.getDate("creationDate"),
						rs.getString("writer"),
						rs.getInt("animal_id")
						);
					animalList.add(animal);				// List에 Community 객체 저장
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
//	
//	public List<Review> searchReviewList(int post_id) throws SQLException {
//		String sql = null;
//		// list.jsp에서 species가 0이면  type을 전체로 선택한것이니 category_id=?를 주면 안됨
//		String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
//     		   + "FROM Review " 
//     		   + "ORDER BY post_id ";   
//		
//        Object[] param = new Object[] { category_id, animal_type, matched};	// JDBCUtil에 update문과 매개 변수 설정
//        jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
//        
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
//			List<Animal> animalList = new ArrayList<Animal>();	// Community들의 리스트 생성
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
//				animalList.add(animal);				// List에 Community 객체 저장
//			}		
//			return animalList;					
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return null;
//	}
	

}
