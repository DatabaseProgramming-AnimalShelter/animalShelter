package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Review_Comment;
import model.dao.ReviewCommentDAO;

public class ReviewCommentManager {
   private static ReviewCommentManager reviewCommentMan = new ReviewCommentManager();
   private ReviewCommentDAO reviewCommentDAO;

   public ReviewCommentDAO getReviewCommentDAO() {
      return this.reviewCommentDAO;
   }
   
   private ReviewCommentManager() {
      try {
         reviewCommentDAO = new ReviewCommentDAO();
      } catch (Exception e) {
         e.printStackTrace();
      }         
   }
   
   public static ReviewCommentManager getInstance() {
      return reviewCommentMan;
   }
   
   public int create(Review_Comment review_comment) throws SQLException, ExistingUserException {
      return reviewCommentDAO.create(review_comment);
   }

   public int remove(int comment_id) throws SQLException, UserNotFoundException {
      return reviewCommentDAO.remove(comment_id);
   }
   
   public List<Review_Comment> findReviewCommentList(int post_id) throws SQLException {
         return reviewCommentDAO.findReviewCommentList(post_id);
   }
   
   
}