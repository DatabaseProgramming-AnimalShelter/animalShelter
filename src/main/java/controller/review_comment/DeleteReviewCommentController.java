package controller.review_comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.ReviewCommentManager;

public class DeleteReviewCommentController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		int comment_id = Integer.parseInt(request.getParameter("comment_id"));

		ReviewCommentManager comment_manager = ReviewCommentManager.getInstance();
	
		comment_manager.remove(comment_id);
		return "redirect:/review/view?post_id=" + post_id;		
		
	}
}
