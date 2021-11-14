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

public class RegisterAnimalController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterAnimalController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
    	if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("ApplyForm Request");
			return "/animal/registerForm.jsp";   
	    }	
    	System.out.println("@@@@@@@@@@@@Create Animal :---------------------"+request.getParameter("species"));
       	Animal animal = new Animal(
			Integer.parseInt(request.getParameter("species")),
			Integer.parseInt(request.getParameter("age")),
			request.getParameter("location"),
			request.getParameter("image"),
			request.getParameter("gender"),
			request.getParameter("weight"),
			request.getParameter("etc"),
			0
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
			return "/animal/registerForm.jsp";
		}
    }
}
