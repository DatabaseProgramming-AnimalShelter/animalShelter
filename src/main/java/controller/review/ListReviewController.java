package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.service.ReviewManager;

public class ListReviewController implements Controller{
	
	
	// ListReviewController�� �ʿ��Ѱ�?? 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	
    	// Controller�� Manager�� �и��� ���
		ReviewManager manager = ReviewManager.getInstance();
		List<Review> reviewList = manager.findReviewList();

		// animalList ��ü��  request ��ü�� �����Ͽ� �信 ����
		request.setAttribute("reviewList", reviewList);						

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/review/list.jsp";        
	}

}
