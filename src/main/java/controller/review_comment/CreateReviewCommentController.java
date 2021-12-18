package controller.review_comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review_Comment;
import model.service.ReviewCommentManager;

public class CreateReviewCommentController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

       String user_id = UserSessionUtils.getLoginUserId(request.getSession());
       int post_id = Integer.parseInt(request.getParameter("post_id"));
       
       ReviewCommentManager comment_manager = ReviewCommentManager.getInstance();

       Review_Comment review_comment = new Review_Comment(
          post_id, 
          user_id,
          1,
          request.getParameter("content")
         );   
       
       comment_manager.create(review_comment);
      return "redirect:/review/view?post_id=" + post_id;      
      
    } 
}