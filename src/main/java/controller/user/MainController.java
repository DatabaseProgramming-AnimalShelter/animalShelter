package controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.Adopter;
import model.service.AdopterManager;

public class MainController implements Controller {
	// private static final int countPerPage = 100;	// �� ȭ�鿡 ����� ����� ��

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
	
    	
		//  ���� �α����� ����� ID�� request�� �����Ͽ� ����
    	if (UserSessionUtils.hasLogined(request.getSession())) {
    		String user_id=UserSessionUtils.getLoginUserId(request.getSession());
    		AdopterManager manager = AdopterManager.getInstance();
    		Adopter user = manager.findUser(user_id);
		request.setAttribute("user", 
				user);	
		request.setAttribute("curUserId", 
				UserSessionUtils.getLoginUserId(request.getSession()));		
    	}
		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/home/main.jsp";        
    }
}
