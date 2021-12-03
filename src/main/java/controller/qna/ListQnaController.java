package controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Qna;
import model.service.QnaManager;

public class ListQnaController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
		
		QnaManager manager = QnaManager.getInstance();
		List<Qna> reviewList = manager.findQnaList();

		request.setAttribute("reviewList", reviewList);

		return "/review/list.jsp";
	}

}
