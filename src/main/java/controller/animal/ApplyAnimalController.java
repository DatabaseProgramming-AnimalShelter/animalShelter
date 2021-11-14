package controller.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Animal;
import model.service.ExistingUserException;
import model.service.AnimalManager;

public class ApplyAnimalController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(ApplyAnimalController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("ApplyForm Request");
			return "/animal/applyForm.jsp";   
	    }	
    	System.out.println("@@@@@@@@@@@@Create Animal :---------------------"+request.getParameter("gender"));
       	Animal animal = new Animal(
			Integer.parseInt(request.getParameter("animal_id")),
			
			Integer.parseInt(request.getParameter("category_id")),
			Integer.parseInt(request.getParameter("age")),
			request.getParameter("location"),
			Integer.parseInt(request.getParameter("matched")),
			request.getParameter("image"),
			request.getParameter("gender"),
			request.getParameter("weight"),
			request.getParameter("etc"),
			request.getParameter("species"),
			request.getParameter("animal_type")
			);
		System.out.println("Create Animal :---------------------"+animal);
        log.debug("Create Animal : {}", animal);

		try {
			AnimalManager manager = AnimalManager.getInstance();
			manager.create(animal);
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+animal);
	        return "redirect:/";	
	        
		} catch (ExistingUserException e) {	
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("animal", animal);
			return "/animal/applyForm.jsp";
		}
    }
}
