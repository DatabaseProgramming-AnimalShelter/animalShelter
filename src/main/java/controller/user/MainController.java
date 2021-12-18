package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Adopter;
import model.service.AdopterManager;

public class MainController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	
    	if (UserSessionUtils.hasLogined(request.getSession())) {
    		String user_id=UserSessionUtils.getLoginUserId(request.getSession());
    		AdopterManager manager = AdopterManager.getInstance();
    		Adopter user = manager.findUser(user_id);
		request.setAttribute("user", 
				user);	
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
    	}
		return "/home/main.jsp";        
    }
}
