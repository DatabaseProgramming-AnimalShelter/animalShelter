package controller.adopt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.AdoptApplyManager;
import model.AdoptApply;

//�Ծ��û������ & ��û
public class ViewApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	AdoptApply apply = null;
    	AdoptApplyManager manager = AdoptApplyManager.getInstance();
		int adopt_id = Integer.parseInt(request.getParameter("apply_id"));
		
		try {
			apply = manager.find
		}
		request.setAttribute("apply", apply);	// Ŀ�´�Ƽ ���� ����				
		return "/adopt/viewApplyForm.jsp";				// Ŀ�´�Ƽ ���� ȭ������ �̵�
  }
}

