package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.service.ReviewManager;

public class ListReviewController implements Controller{
	
	
	// ListReviewController가 필요한가?? 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	
    	// Controller와 Manager를 분리한 방법
		ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewList = manager.findReviewList();

		// animalList 객체를  request 객체에 저장하여 뷰에 전달
		request.setAttribute("reviewList", reviewList);						

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/review/list.jsp";        
	}

}
