package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.AdopterManager;
import model.service.UserNotFoundException;
import model.Adopter;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// �α��� ���� Ȯ��
    
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/";		// login form ��û���� redirect
        }
    			

    			
    	       
    	request.setAttribute("curUserId", 
    					UserSessionUtils.getLoginUserId(request.getSession()));
    	    	
    	String curUserId=UserSessionUtils.getLoginUserId(request.getSession());
    	AdopterManager manager = AdopterManager.getInstance();
		System.out.println("-------------------user_id-----------------------"+curUserId);
		Adopter user = null;
    	try {
			user = manager.findUser(curUserId);	// ����� ���� �˻�
		} catch (UserNotFoundException e) {	
			return "redirect:/";
			//return "/user/mypage.jsp";
		}	
    	request.setAttribute("user", user);		
			// ����� ���� ����				
    	return "/user/mypage.jsp";
    }
}
