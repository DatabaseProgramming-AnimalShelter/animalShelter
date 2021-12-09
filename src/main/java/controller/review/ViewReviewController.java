package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.ReviewManager;
import model.service.ReviewNotFoundException;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;

public class ViewReviewController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Review review = null;
		ReviewManager manager = ReviewManager.getInstance();
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
//		try {
//			review = manager.findReview(post_id);	// �ı� ���� �˻�  
//		} catch (ReviewNotFoundException e) {				
//	        return "redirect:/review/list";
//		}	
		
		review = manager.findReview(post_id);  // �ı� ���� �˻�  
		
		request.setAttribute("user_id", UserSessionUtils.getLoginUserId(request.getSession()));
    	request.setAttribute("review", review);		// �ı� ���� ����		
    	
		return "/review/view.jsp";				// �ı� �󼼺��� ȭ������ �̵�*/
	}

}
