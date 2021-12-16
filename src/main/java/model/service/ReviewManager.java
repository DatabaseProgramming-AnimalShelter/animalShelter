package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Review;
import model.dao.ReviewDAO;

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

	public int remove(int comment_id) throws SQLException, UserNotFoundException {
		return reviewDAO.remove(comment_id);
	}

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
	
	public Review findUserReview(String user_id, int animal_id)throws SQLException {
		return reviewDAO.findUserReview(user_id, animal_id);
	}
	
	public List<Review> findUserReviewList(String user_id) throws SQLException {
		return reviewDAO.findUserReviewList(user_id);
	}
	
	public List<Review> findReviewCommnetList(String user_id) throws SQLException {
		return reviewDAO.findReviewCommnetList(user_id);
	}
}
