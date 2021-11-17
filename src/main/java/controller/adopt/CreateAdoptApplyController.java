package controller.adopt;

import java.io.File;
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
import model.service.AdopterManager;

public class CreateAdoptApplyController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
//		System.out.println(user_id);
//		System.out.println(request.getParameter("content"));
		if (request.getMethod().equals("GET")) {
			AnimalManager manager = AnimalManager.getInstance();

			int animal_id = Integer.parseInt(request.getParameter("animal_id"));
			Animal animal = manager.findAnimal(animal_id);
			
			AdopterManager adopterManager = AdopterManager.getInstance();
			Adopter adopter = adopterManager.findUser(user_id);
			File dir = new File("C:/Temp");
			request.setAttribute("dir", dir);
			request.setAttribute("adopter", adopter);
			request.setAttribute("animal", animal);
    		log.debug("RegisterForm Request");
			return "/adopt/createApplyForm.jsp";  
	    }	
		System.out.println(request.getParameter("animal_id"));
		System.out.println(request.getParameter("user_id"));
		AdoptApply apply = new AdoptApply(
				request.getParameter("user_id"),
				Integer.parseInt(request.getParameter("animal_id")),
				request.getParameter("content"),
				request.getParameter("living_environment"),
				request.getParameter("have_pets"),
				0//생성될때는 manager가 승낙하기 전
			);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		try {
			AdoptApplyManager manager = AdoptApplyManager.getInstance();
			manager.create(apply);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	    	log.debug("Create Adopt : {}", apply);
	        return "redirect:/";
	        
		} catch (Exception e) {
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("apply", apply);
			return "/adopt/createApplyForm.jsp";
		}
	}
}
