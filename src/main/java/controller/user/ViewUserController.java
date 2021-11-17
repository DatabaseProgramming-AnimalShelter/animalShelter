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
    	// 占싸깍옙占쏙옙 占쏙옙占쏙옙 확占쏙옙
    
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/";		// login form 占쏙옙청占쏙옙占쏙옙 redirect
        }
    			
    	AdopterManager manager = AdopterManager.getInstance();
    	 String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		System.out.println("-------------------user_id-----------------------"+user_id);
		Adopter user = null;
    	try {
			user = manager.findUser(user_id);	// 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占싯삼옙
		} catch (UserNotFoundException e) {	
			System.out.println("占싱곤옙占쏙옙??");
			return "redirect:/";
			//return "/user/mypage.jsp";
		}	
    	request.setAttribute("user", user);		
			// 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙				
    	return "/user/mypage.jsp";
    }
}
