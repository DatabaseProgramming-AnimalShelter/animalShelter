package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.service.ReviewManager;

public class ReviewCommentListController implements Controller {

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // TODO Auto-generated method stub
   
      ReviewManager manager = ReviewManager.getInstance();
      List<Review> reviewCommentList = null;
      
      reviewCommentList = manager.findReviewCommentList(UserSessionUtils.getLoginUserId(request.getSession()));
      request.setAttribute("reviewCommentList", reviewCommentList);

      return "/user/commentList.jsp";
   }

}