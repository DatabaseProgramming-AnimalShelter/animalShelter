package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Community;
import model.Adopter;
import model.service.ExistingUserException;
import model.service.AdopterManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("RegisterForm Request");

			return "/user/registerForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     	
	    }	

    	// POST request (회원정보가 parameter로 전송됨)
       	Adopter user = new Adopter(
			request.getParameter("user_id"),
			request.getParameter("password"),
			request.getParameter("user_name"),
			request.getParameter("email"),
			request.getParameter("phone")
			);
		
        log.debug("Create User : {}", user);

		try {
			AdopterManager manager = AdopterManager.getInstance();
			manager.create(user);
	        return "redirect:/";	// 성공 시 사용자 리스트 화면으로 redirect
	        
		} catch (ExistingUserException e) {	// 예외 발생 시 회원가입 form으로 forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}
