package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Review;
import model.dao.ReviewDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */

public class ReviewManager {
	private static ReviewManager reviewMan = new ReviewManager();
	private ReviewDAO reviewDAO;

	public ReviewDAO getReviewDAO() {
		return this.reviewDAO;
	}
	
	private ReviewManager() {
		try {
			reviewDAO = new ReviewDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static ReviewManager getInstance() {
		return reviewMan;
	}
	
	public int create(Review review) throws SQLException, ExistingUserException {
		return reviewDAO.create(review);
	}

	public int update(Review review) throws SQLException, UserNotFoundException {
		return reviewDAO.update(review);
	}	

	public int remove(int post_id) throws SQLException, UserNotFoundException {

		return reviewDAO.remove(post_id);
	}

	/*public Adopter findUser(String user_id)
		throws SQLException, UserNotFoundException {
		Adopter user = adopterDAO.findUser(user_id);
		
		if (user == null) {
			throw new UserNotFoundException(user_id + "는 존재하지 않는 아이디입니다.");
		}		
		return user;
	}*/

	public List<Review> findReviewList() throws SQLException {
			return reviewDAO.findReviewList();
	}
	

	public Review findReview(int post_id) throws SQLException, AnimalNotFoundException {
		// TODO Auto-generated method stub
		Review review = reviewDAO.findReview(post_id);
		
		if(review == null) {
			throw new AnimalNotFoundException(post_id + "는 존재하지 않는 후기입니다.");
		}
		
		return review;
	}
	
	
}
