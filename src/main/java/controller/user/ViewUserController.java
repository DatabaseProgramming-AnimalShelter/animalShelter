package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.AdoptApplyManager;
import model.service.AdopterManager;
import model.service.UserNotFoundException;
import model.AdoptApply;
import model.Adopter;

public class ViewUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// �α��� ���� Ȯ��

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/"; // login form ��û���� redirect
		}
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));

		AdopterManager manager = AdopterManager.getInstance();
		AdoptApplyManager apply_manager = AdoptApplyManager.getInstance();
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		System.out.println("-------------------user_id-----------------------" + user_id);
		Adopter user = null;
		List<AdoptApply> list = null;
		try {
			user = manager.findUser(user_id);
			list = apply_manager.findAdoptApplyResult(user_id);

		} catch (UserNotFoundException e) {
			System.out.println("사용자를 찾을 수 없습니다.");
			return "redirect:/";
			// return "/user/mypage.jsp";
		}
		request.setAttribute("user", user);
		request.setAttribute("AdoptApplyList", list);
		return "/user/mypage.jsp";
	}
}
