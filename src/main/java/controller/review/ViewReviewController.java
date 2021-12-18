package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ReviewCommentManager;
import model.service.ReviewManager;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.Review_Comment;

public class ViewReviewController implements Controller{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      int post_id = Integer.parseInt(request.getParameter("post_id"));

      Review review = null;
      ReviewManager manager = ReviewManager.getInstance();
      ReviewCommentManager comment_manager = ReviewCommentManager.getInstance();
      List<Review_Comment> reviewCommentList = null;
      
      review = manager.findReview(post_id);    
      reviewCommentList = comment_manager.findReviewCommentList(post_id);
      
      request.setAttribute("user_id", UserSessionUtils.getLoginUserId(request.getSession()));
       request.setAttribute("review", review);            
      request.setAttribute("reviewCommentList", reviewCommentList);

      return "/review/view.jsp";            
   }

}