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
import model.Animal;
import model.service.AdoptApplyManager;
import model.service.AnimalManager;
import model.service.ExistingUserException;

// view.jsp���� ���� ���� �޾Ƽ� createApplyForm���� ����
public class CreateAdoptApplyController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		if (request.getMethod().equals("GET")) {
//			AnimalManager manager = AnimalManager.getInstance();
//			int animal_id = Integer.parseInt(request.getParameter("animal_id"));
//			Animal animal = manager.findAnimal(animal_id);
//			request.setAttribute("user_id", user_id);
//			request.setAttribute("animal", animal);
    		log.debug("RegisterForm Request");
			return "/adopt/createApplyForm.jsp";   // �˻��� ����� ������ update form���� ����     	
	    }	
		
		AdoptApply apply = new AdoptApply(
				user_id,
				Integer.parseInt(request.getParameter("animal_id")),
				request.getParameter("content"),
				request.getParameter("living_environment"),
				request.getParameter("have_pets"),
				Integer.parseInt(request.getParameter("apply_matched")),
				request.getParameter("apply_date"),
				request.getParameter("approval_date"),
				request.getParameter("image"),
				request.getParameter("user_name"),
				request.getParameter("animal_type"),
				request.getParameter("species")
				);
		try {
			AdoptApplyManager manager = AdoptApplyManager.getInstance();
			manager.create(apply);
			
	    	log.debug("Create Adopt : {}", apply);
	        return "redirect:/";	// ���� �� adopt form���� redirect
	        
		} catch (Exception e) {		// ���� �߻� �� �Է� form���� forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("apply", apply);
			return "/adopt/createApplyForm.jsp";
		}
	}
}
