package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.ReviewManager;
import model.service.ReviewNotFoundException;
import controller.Controller;

import model.Review;

public class ViewReviewController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Review review = null;
		ReviewManager manager = ReviewManager.getInstance();
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
//		try {
//			review = manager.findReview(post_id);	// 후기 정보 검색  
//		} catch (ReviewNotFoundException e) {				
//	        return "redirect:/review/list";
//		}	
		
		review = manager.findReview(post_id);  // 후기 정보 검색  
		
    	request.setAttribute("review", review);		// 후기 정보 저장		
    	
		return "/review/view.jsp";				// 후기 상세보기 화면으로 이동*/
	}

}
