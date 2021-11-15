package controller.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Animal;
import model.service.AnimalManager;

public class ListAnimalController implements Controller{
	
	
	// ListAnimalController가 필요한가?? 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	
    	// Controller와 Manager를 분리한 방법
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.findAnimalList();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-"+animalList);
		// animalList 객체를  request 객체에 저장하여 뷰에 전달
		request.setAttribute("animalList", animalList);						

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/animal/list.jsp";        
	}

}
