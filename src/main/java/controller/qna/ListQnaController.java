package controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Qna;
import model.Review;
import model.service.QnaManager;
import model.service.ReviewManager;

public class ListQnaController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
		
		QnaManager manager = QnaManager.getInstance();
		List<Qna> reviewList = null;
		
		if(request.getParameter("user_id") != null) { // �������������� ����ڰ� �ۼ��� ���� ����Ʈ �� ��	
			reviewList = manager.findUserQnaList(UserSessionUtils.getLoginUserId(request.getSession()));
		}
		else { // ��� ����� �ۼ��� ���� ����Ʈ �� ��
			reviewList = manager.findQnaList();
		}		

		request.setAttribute("reviewList", reviewList);

		return "/review/list.jsp";
	}

}
