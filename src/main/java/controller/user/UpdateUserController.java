package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.AdopterManager;
import model.Adopter;

public class UpdateUserController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String update_id = UserSessionUtils.getLoginUserId(request.getSession());		
		log.debug("UpdateForm Request : {}", update_id);
		//String update_id = request.getParameter("user_id");
		Adopter user = new Adopter(
				update_id, 
				request.getParameter("password"), 
				request.getParameter("user_name"),
				request.getParameter("email"), 
				request.getParameter("phone")
				);

		log.debug("UpdateForm Request : {}", update_id, request.getParameter("password"),
				request.getParameter("user_name"), request.getParameter("email"), request.getParameter("phone"));

		HttpSession session = request.getSession();
		if (UserSessionUtils.isLoginUser(update_id, session) || UserSessionUtils.isLoginUser("admin", session)) {
			// 현재 로그인한 사용자가 수정 대상 사용자이거나 관리자인 경우 -> 수정 가능
			AdopterManager manager = AdopterManager.getInstance();
			int result = manager.update(user);
			
			user = manager.findUser(update_id); // 수정 후 사용자 정보 검색
			request.setAttribute("user", user);

			if (result < 0) { // 업데이트 실패
				request.setAttribute("updateFailed", true);
			}
		} else {
			// (수정 불가능한 경우) 사용자 보기 화면으로 오류 메세지를 전달
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", new IllegalStateException("타인의 정보는 수정할 수 없습니다."));
		}

		return "/user/mypage.jsp";
	}
}
