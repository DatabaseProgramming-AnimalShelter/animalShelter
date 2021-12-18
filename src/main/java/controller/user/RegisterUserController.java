package controller.user;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Adopter;
import model.service.ExistingUserException;
import model.service.AdopterManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {	
    		log.debug("RegisterForm Request");

			return "/user/registerForm.jsp";      	
	    }	

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
	        return "redirect:/";
	        
		} catch (ExistingUserException e) {	
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}
