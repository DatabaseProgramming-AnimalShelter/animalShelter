package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.AdopterManager;
import model.service.UserNotFoundException;
import model.AdoptApply;
import model.Adopter;

public class ViewUserController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/"; 
		}
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));

		AdopterManager manager = AdopterManager.getInstance();
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		System.out.println("-------------------user_id-----------------------" + user_id);

		Adopter user = null;

		List<AdoptApply> list = null;
		try {
			user = manager.findUser(user_id);
		} catch (UserNotFoundException e) {
			return "redirect:/";
		}
		request.setAttribute("user", user);
		
		return "/user/mypage.jsp";
	}

}
