package controller.adopt;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.AdoptApplyManager;
import model.service.AnimalManager;
import model.AdoptApply;
import model.Animal;

public class ViewApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	AdoptApply apply = null;
    	AdoptApplyManager manager = AdoptApplyManager.getInstance();
		int adopt_id = Integer.parseInt(request.getParameter("apply_id"));
		
		request.setAttribute("apply", apply);	// Ŀ�´�Ƽ ���� ����				
		return "/adopt/viewApplyForm.jsp";				// Ŀ�´�Ƽ ���� ȭ������ �̵�
  }
}

