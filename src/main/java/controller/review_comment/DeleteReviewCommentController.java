package controller.review_comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.Review_Comment;
import model.service.ReviewCommentManager;
import model.service.ReviewManager;

public class DeleteReviewCommentController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteReviewCommentController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		int comment_id = Integer.parseInt(request.getParameter("comment_id"));

		ReviewCommentManager comment_manager = ReviewCommentManager.getInstance();
	
		comment_manager.remove(comment_id);
		
		// redirect 할때 파라미터 값 넘기는 방법
    	//ex) "redirect:/foo/bar?param1=" + value     
		return "redirect:/review/view?post_id=" + post_id;		
		
	}
}
