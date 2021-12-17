package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Adopter;
import model.service.AdopterManager;


public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());		
    	log.debug("Delete User : {}", user_id);

		AdopterManager manager = AdopterManager.getInstance();
		HttpSession session = request.getSession();	
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	
			 !user_id.equals("admin"))							
			   || 												
			(!UserSessionUtils.isLoginUser("admin", session) &&  
			  UserSessionUtils.isLoginUser(user_id, session))) { 
				
			manager.remove(user_id);				
			if (UserSessionUtils.isLoginUser("admin", session))		
				return "redirect:/main.jsp";
			else 									
				return "redirect:/user/logout";		
		}
		
		Adopter user = manager.findUser(user_id);	
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser("admin", session)) 
				   ? "회원 삭제에 실패 하였습니다."		
				   : "탈퇴에 실패 하였습니다.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/user/mypage.jsp";
	}
}
