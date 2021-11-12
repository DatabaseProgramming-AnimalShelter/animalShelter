package controller.adopt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Animal;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

// 유기동물 입양 신청
public class CreateApplyFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub	

    	// POST request (회원정보가 parameter로 전송됨)
       	AdoptApply adoptApply = new AdoptApply( // 입양 신청 테이블 바꿔야 할듯???
			request.getParameter(""),
			request.getParameter(""),
			request.getParameter(""),
			request.getParameter(""),
			request.getParameter(""),
			Integer.parseInt(request.getParameter("")));
	
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
