package controller.animal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Animal;
import model.service.AnimalManager;

public class UpdateAnimalController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateAnimalController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

    	System.out.println("animal_id" + request.getParameter("animal_id") );
		int animal_id = Integer.parseInt(request.getParameter("animal_id"));

		
		if (request.getMethod().equals("GET")) {	
			AnimalManager manager = AnimalManager.getInstance();
			Animal animal = manager.findAnimal(animal_id);
			request.setAttribute("animal", animal);			
				
			return "/animal/updateForm.jsp"; 
	    }	
		
		System.out.println("gender" + request.getParameter("gender") );
		System.out.println("species" + request.getParameter("species") );
		System.out.println("etc" + request.getParameter("etc") );
		
		
		Animal animal = new Animal(
				animal_id,
				Integer.parseInt(request.getParameter("species")),
				Integer.parseInt(request.getParameter("age")),
				request.getParameter("location"),
				0,
				request.getParameter("image"),
				request.getParameter("gender"),
				request.getParameter("weight"),
				request.getParameter("etc")
		 );
		      
    	log.debug("Update Animal : {}", animal);

    	AnimalManager manager = AnimalManager.getInstance();
		manager.update(animal);	
		animal = manager.findAnimal(animal_id);
		request.setAttribute("animal", animal);
//		request.setAttribute("animal_id", animal_id);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + animal_id);
		return "/animal/view.jsp";
    }
}
