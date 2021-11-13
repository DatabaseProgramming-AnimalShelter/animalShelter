package controller.adopt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Adopter;
import model.Animal;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

// 유기동물 입양 신청
public class ApplyAnimalController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub	

		Animal animal = (Animal) request.getAttribute("animal");
		HttpSession session = request.getSession();
		Adopter user = (Adopter) session.getAttribute(UserSessionUtils.USER_SESSION_KEY);
		
		try {
			AdoptApplyManager manager = AdoptApplyManager.getInstance();
			manager.create(adoptApply);
	        return "redirect:/animal/list";	// 성공 시 유기동물 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 입양신청 form으로 forwarding
            request.setAttribute("applyFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("adoptApply", adoptApply);
			return "/adopt/createApplyForm.jsp";
		}
	}

}
