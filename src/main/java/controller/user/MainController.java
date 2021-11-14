package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Adopter;
import model.service.AdopterManager;

public class MainController implements Controller {
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	
    	
		//  현재 로그인한 사용자 ID를 request에 저장하여 전달
    	if (UserSessionUtils.hasLogined(request.getSession())) {
    		String user_id=UserSessionUtils.getLoginUserId(request.getSession());
    		AdopterManager manager = AdopterManager.getInstance();
    		Adopter user = manager.findUser(user_id);
		request.setAttribute("user", 
				user);	
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
    	}
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/home/main.jsp";        
    }
}
