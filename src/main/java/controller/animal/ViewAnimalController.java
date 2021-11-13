package controller.animal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.AnimalManager;
import model.service.AnimalNotFoundException;
import controller.Controller;

import model.Animal;

public class ViewAnimalController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Animal animal = null;
    	try {
			animal = manager.findAnimal(animalId);	// ���⵿�� ���� �˻�  
		} catch (AnimalNotFoundException e) {				
	        return "redirect:/animal/list";
		}	
		
    	request.setAttribute("animal", animal);		// ���⵿�� ���� ����				
		return "/animal/view.jsp";				// ���⵿�� �󼼺��� ȭ������ �̵�*/
	}

}
