package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.service.AdopterManager;
import model.service.ReviewManager;

public class DeleteReviewController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteReviewController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		int post_id = Integer.parseInt(request.getParameter("post_id"));

    	ReviewManager manager = ReviewManager.getInstance();
	
		manager.remove(post_id);
		
		return "redirect:/review/list";	// 사용자 보기 화면으로 이동 (forwarding)	
		
	}
}