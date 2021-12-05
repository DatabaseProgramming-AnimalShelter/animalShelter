package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.ReviewCommentManager;
import model.service.ReviewManager;
import model.service.ReviewNotFoundException;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.Review_Comment;

public class ViewReviewController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		int post_id = Integer.parseInt(request.getParameter("post_id"));

		//후기글
		Review review = null;
		ReviewManager manager = ReviewManager.getInstance();
		//댓글
		ReviewCommentManager comment_manager = ReviewCommentManager.getInstance();
		List<Review_Comment> reviewCommentList = null;

		
//		try {
//			review = manager.findReview(post_id);	// 후기 정보 검색  
//		} catch (ReviewNotFoundException e) {				
//	        return "redirect:/review/list";
//		}	
		
		review = manager.findReview(post_id);  // 후기 정보 검색  
		//댓글
		reviewCommentList = comment_manager.findReviewCommentList(post_id);
		
		request.setAttribute("user_id", UserSessionUtils.getLoginUserId(request.getSession()));
    	request.setAttribute("review", review);		// 후기 정보 저장		
    	//댓글
		request.setAttribute("reviewCommentList", reviewCommentList);

    	
		return "/review/view.jsp";				// 후기 상세보기 화면으로 이동
	}

}
