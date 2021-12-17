package controller.user;

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
			AdopterManager manager = AdopterManager.getInstance();
			int result = manager.update(user);
			
			user = manager.findUser(update_id); 
			request.setAttribute("user", user);

			if (result < 0) { 
				request.setAttribute("updateFailed", true);
			}
		} else {
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", new IllegalStateException("수정을 할 수 없습니다."));
		}

		return "/user/mypage.jsp";
	}
}
