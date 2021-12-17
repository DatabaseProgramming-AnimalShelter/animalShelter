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
		ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewList = null;
		
		if(request.getParameter("user_id") != null) { 
			reviewList = manager.findUserReviewList(UserSessionUtils.getLoginUserId(request.getSession()));
		}
		else { 
			reviewList = manager.findReviewList();
		}
		
		request.setAttribute("reviewList", reviewList);

		return "/review/list.jsp";
	}

}
