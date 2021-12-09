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
		/*
		 * if (!UserSessionUtils.hasLogined(request.getSession())) { return
		 * "redirect:/user/login/form"; // login form ��û���� redirect }
		 */
		ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewCommnetList = null;
		
		reviewCommnetList = manager.findReviewCommnetList(UserSessionUtils.getLoginUserId(request.getSession()));
		request.setAttribute("reviewCommnetList", reviewCommnetList);

		return "/user/commentList.jsp";
	}

}
