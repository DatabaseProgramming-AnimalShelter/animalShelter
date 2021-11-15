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
    	// 로그인 여부 확인
    
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/";		// login form 요청으로 redirect
        }
    			

    			
    	       
    	request.setAttribute("curUserId", 
    					UserSessionUtils.getLoginUserId(request.getSession()));
    	    	
    	String curUserId=UserSessionUtils.getLoginUserId(request.getSession());
    	AdopterManager manager = AdopterManager.getInstance();
		System.out.println("-------------------user_id-----------------------"+curUserId);
		Adopter user = null;
    	try {
			user = manager.findUser(curUserId);	// 사용자 정보 검색
		} catch (UserNotFoundException e) {	
			return "redirect:/";
			//return "/user/mypage.jsp";
		}	
    	request.setAttribute("user", user);		
			// 사용자 정보 저장				
    	return "/user/mypage.jsp";
    }
}
