package controller.animal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.animal.DeleteAnimalController;
import model.service.AnimalManager;


public class DeleteAnimalController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteAnimalController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	Integer animal_id = Integer.parseInt(request.getParameter("animal_id"));
		
    	AnimalManager manager = AnimalManager.getInstance();
	
		manager.remove(animal_id);
		
		return "redirect:/animal/list";	//삭제하면 animal_list로 이동
	}
}