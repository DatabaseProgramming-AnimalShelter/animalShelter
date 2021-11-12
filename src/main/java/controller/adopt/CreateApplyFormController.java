package controller.adopt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Animal;
import model.User;
import model.service.ExistingUserException;
import model.service.UserManager;

// ���⵿�� �Ծ� ��û
public class CreateApplyFormController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub	

    	// POST request (ȸ�������� parameter�� ���۵�)
       	AdoptApply adoptApply = new AdoptApply( // �Ծ� ��û ���̺� �ٲ�� �ҵ�???
			request.getParameter(""),
			request.getParameter(""),
			request.getParameter(""),
			request.getParameter(""),
			request.getParameter(""),
			Integer.parseInt(request.getParameter("")));
	
		try {
			AdoptApplyManager manager = AdoptApplyManager.getInstance();
			manager.create(adoptApply);
	        return "redirect:/animal/list";	// ���� �� ���⵿�� ����Ʈ ȭ������ redirect
	        
		} catch (ExistingUserException e) {	// ���� �߻� �� �Ծ��û form���� forwarding
            request.setAttribute("applyFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("adoptApply", adoptApply);
			return "/adopt/createApplyForm.jsp";
		}
	}

}
