package controller.adopt;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.adopt.CreateAdoptApplyController;
import controller.user.UserSessionUtils;
import model.AdoptApply;
import model.Adopter;
import model.service.AdoptApplyManager;
import model.service.AdopterManager;
import model.service.ExistingUserException;
import model.service.UserManager;


// view.jsp���� ���� ���� �޾Ƽ� createApplyForm���� ����
public class CreateAdoptApplyController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// POST request (form�� �Էµ����Ͱ� parameter�� ���۵�)
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: ȸ������ ��� form ��û	
    		log.debug("RegisterForm Request");

			return "/adopt/createApplyForm.jsp";   // �˻��� ����� ������ update form���� ����     	
	    }	
		
		AdoptApply adopt = new AdoptApply(
				request.getParameter("apply_id"),
				user_id,
				request.getParameter("animal_id"),
				request.getParameter("content"),
				request.getParameter("living_environment"),
				request.getParameter("have_pets"),
				request.getParameter("apply_matched"),
				request.getParameter("apply_date"),
				request.getParameter("approval_date"),
				request.getParameter("image"),
				request.getParameter()
				// ���� ��¥ ���ϱ� (�ý��� �ð�, �ý��� Ÿ����)
				LocalDate.now(),
				0,//�Ծ�³��ź� ����-0
				);
		try {
			AdoptApplyManager manager = AdoptApplyManager.getInstance();
			manager.create(adopt);
			
	    	log.debug("Create Adopt : {}", adopt);
	        return "redirect:/adopt/applyList";	// ���� �� adopt form���� redirect
	        
		} catch (Exception e) {		// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("adopt", adopt);
			return "/adopt/createApplyForm.jsp";
		}
	}
}
