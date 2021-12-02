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
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/"; 
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
			//list = apply_manager.findAdoptApplyResult(user_id);
		} catch (UserNotFoundException e) {
			System.out.println("�궗�슜�옄瑜� 李얠쓣 �닔 �뾾�뒿�땲�떎.");
			return "redirect:/";
		}
		request.setAttribute("user", user);
		//request.setAttribute("AdoptApplyList", list);
		//�엯�뼇�떊泥� 紐⑸줉 - �듅�씤寃곌낵
		return "/user/mypage.jsp";
	}

}
