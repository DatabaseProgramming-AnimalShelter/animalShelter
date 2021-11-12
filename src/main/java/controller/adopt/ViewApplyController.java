package controller.adopt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.AdoptApply;
import model.Community;

//�Ծ��û������ & ��û
public class ViewApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	AdoptApply apply = null;
		UserManager manager = UserManager.getInstance();
		int adopt_id = Integer.parseInt(request.getParameter("user_id"));
//		comm = manager.findCommunity(commId);		// Ŀ�´�Ƽ ���� �˻�			
		//adopt = manager.findAdoptApply(user_id);
		request.setAttribute("apply", apply);	// Ŀ�´�Ƽ ���� ����				
		return "/adopt/viewApplyForm.jsp";				// Ŀ�´�Ƽ ���� ȭ������ �̵�
  }
}

