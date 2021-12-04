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
            return "redirect:/user/login/form";		// login form 占쏙옙청占쏙옙占쏙옙 redirect
        }
		
		QnaManager manager = QnaManager.getInstance();
		List<Qna> reviewList = null;
		
		if(request.getParameter("user_id") != null) { // 마이페이지에서 사용자가 작성한 문의 리스트 볼 때	
			reviewList = manager.findUserQnaList(UserSessionUtils.getLoginUserId(request.getSession()));
		}
		else { // 모든 사람이 작성한 문의 리스트 볼 때
			reviewList = manager.findQnaList();
		}		

		request.setAttribute("reviewList", reviewList);

		return "/review/list.jsp";
	}

}
