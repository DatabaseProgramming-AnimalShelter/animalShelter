package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Review;
import model.dao.ReviewDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
			throw new UserNotFoundException(user_id + "�� �������� �ʴ� ���̵��Դϴ�.");
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
			throw new AnimalNotFoundException(post_id + "�� �������� �ʴ� �ı��Դϴ�.");
		}
		
		return review;
	}
	
	public List<Review> findUserReviewList(String user_id) throws SQLException {
		return reviewDAO.findUserReviewList(user_id);
	}
	
}
