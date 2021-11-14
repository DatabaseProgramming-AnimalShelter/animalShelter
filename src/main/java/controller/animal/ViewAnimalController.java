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
		AnimalManager manager = AnimalManager.getInstance();
		int animal_id = (int) request.getAttribute("matched");
		
		try {
			animal = manager.findAnimal(animal_id);	// 유기동물 정보 검색  
		} catch (AnimalNotFoundException e) {				
	        return "redirect:/animal/list";
		}	
		
    	request.setAttribute("animal", animal);		// 유기동물 정보 저장				
		return "/animal/view.jsp";				// 유기동물 상세보기 화면으로 이동*/
	}

}
