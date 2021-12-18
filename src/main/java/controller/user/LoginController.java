package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.service.AdopterManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String userId = request.getParameter("user_id");
		String password = request.getParameter("password");
		
       	if (request.getMethod().equals("GET")) {	
    		log.debug("LoginForm Request");

			return "/user/loginForm.jsp";   //     	
	    }	

		try {
			AdopterManager manager = AdopterManager.getInstance();
			manager.login(userId, password);
	
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/";			
		} catch (Exception e) {
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}	
    }
}
