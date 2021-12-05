package controller.review_comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.Review_Comment;
import model.service.ReviewCommentManager;
import model.service.ReviewManager;

public class CreateReviewCommentController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
    	int post_id = Integer.parseInt(request.getParameter("post_id"));
    	
    	ReviewManager manager = ReviewManager.getInstance();
    	ReviewCommentManager comment_manager = ReviewCommentManager.getInstance();

    	System.out.println("@@@@@@@@@@@@@@@@comment post id: =--=----"+ post_id);
    	System.out.println("@@@@@@@@@@@@@@@@comment content: =--=----"+ request.getParameter("content"));
    	
    	Review_Comment review_comment = new Review_Comment(
    		post_id, 
    		user_id,
    		1,
    		request.getParameter("content")
			);	
    	
    	comment_manager.create(review_comment);

    	// redirect 할때 파라미터 값 넘기는 방법
    	//ex) "redirect:/foo/bar?param1=" + value     
		return "redirect:/review/view?post_id=" + post_id;		
		
    }
    
	
}
