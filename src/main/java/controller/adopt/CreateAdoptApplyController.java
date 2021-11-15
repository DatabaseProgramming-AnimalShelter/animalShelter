package controller.adopt;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.adopt.CreateAdoptApplyController;
import model.AdoptApply;
import model.service.UserManager;

public class CreateAdoptApplyController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// POST request (form�� �Էµ����Ͱ� parameter�� ���۵�)
		AdoptApply adopt = new AdoptApply(
				Integer.parseInt(request.getParameter("user_id")),
				Integer.parseInt(request.getParameter("animal_id")),
				// ���� ��¥ ���ϱ� (�ý��� �ð�, �ý��� Ÿ����)
				LocalDate.now(),
				0,//�Ծ�³��ź� ����-0
				request.getParameter("content"),
				request.getParameter("living_environment"),
				request.getParameter("have_pets")
				);
		try {
			UserManager manager = UserManager.getInstance();
			manager.createAdoptApply(adopt);
			
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
