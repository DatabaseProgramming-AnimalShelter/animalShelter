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
	
		if ((UserSessionUtils.isLoginUser("admin", session) && 	// �α����� ����ڰ� �������̰� 	
			 !user_id.equals("admin"))							// ���� ����� �Ϲ� ������� ���, 
			   || 												// �Ǵ� 
			(!UserSessionUtils.isLoginUser("admin", session) &&  // �α����� ����ڰ� �����ڰ� �ƴϰ� 
			  UserSessionUtils.isLoginUser(user_id, session))) { // �α����� ����ڰ� ���� ����� ��� (�ڱ� �ڽ��� ����)
				
			manager.remove(user_id);				// ����� ���� ����
			if (UserSessionUtils.isLoginUser("admin", session))	// �α����� ����ڰ� ������ 	
				//return "redirect:/user/list";		// ����� ����Ʈ�� �̵�
				return "redirect:/main.jsp";
			else 									// �α����� ����ڴ� �̹� ������
				return "redirect:/user/logout";		// logout ó��
		}
		
		/* ������ �Ұ����� ��� */
		Adopter user = manager.findUser(user_id);	// ����� ���� �˻�
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser("admin", session)) 
				   ? "�ý��� ������ ������ ������ �� �����ϴ�."		
				   : "Ÿ���� ������ ������ �� �����ϴ�.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/user/mypage.jsp";
		//return "/user/view.jsp";		// ����� ���� ȭ������ �̵� (forwarding)	
	}
}
