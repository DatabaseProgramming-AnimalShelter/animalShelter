package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.service.ReviewManager;

public class ListReviewController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * if (!UserSessionUtils.hasLogined(request.getSession())) { return
		 * "redirect:/user/login/form"; // login form 요청으로 redirect }
		 */
		ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewList = null;
		
		if(request.getParameter("user_id") != null) { // 마이페이지에서 사용자가 작성한 후기 리스트 볼 때	
			reviewList = manager.findUserReviewList(UserSessionUtils.getLoginUserId(request.getSession()));
		}
		else { // 모든 사람이 작성한 후기 리스트 볼 때
			reviewList = manager.findReviewList();
		}
		
		request.setAttribute("reviewList", reviewList);

		return "/review/list.jsp";
	}

}