package controller.adopt;

import java.io.File;
import java.time.LocalDate;

import javax.servlet.ServletContext;
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
import model.service.AdopterManager;
import model.service.AnimalManager;
import model.service.ExistingUserException;

public class CreateAdoptApplyController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		int animal_id = Integer.parseInt(request.getParameter("animal_id"));

		Animal animal = null;
		Adopter adopter = null;

		AnimalManager animalManager = AnimalManager.getInstance();
		AdopterManager adopterManager = AdopterManager.getInstance();

		animal = animalManager.findAnimal(animal_id);
		adopter = adopterManager.findUser(user_id);

		if (request.getMethod().equals("GET")) {
			log.debug("RegisterForm Request");

			AdoptApply apply_default = new AdoptApply(user_id, animal.getAnimal_id(), animal.getImage(),
					adopter.getUser_name(), animal.getAnimal_type(), animal.getSpecies());

			request.setAttribute("apply", apply_default);

			return "/adopt/createApplyForm.jsp";
		}

		AdoptApply apply = new AdoptApply(user_id, Integer.parseInt(request.getParameter("animal_id")),
				request.getParameter("content"), request.getParameter("living_conditions"),
				request.getParameter("have_pets"), animal.getImage(), adopter.getUser_name(), animal.getAnimal_type(),
				animal.getSpecies());

		try {
			AdoptApplyManager manager = AdoptApplyManager.getInstance();
			manager.create(apply);

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