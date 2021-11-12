package controller.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class SearchAnimalController implements Controller{
	private AnimalDAO animalDAO = new AnimalDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// Controller와 Manager를 통합한 방법
		List animalList = null;
		String search = (String) request.getAttribute("search");
		
		if(search.equals("all")) {
			animalList = animalDAO.findAnimalList();
		}
		else if(search.equals("dog")) {
			animalList = animalDAO.findDogList();
		}
		else if(search.equals("cat")) {
			animalList = animalDAO.findCatList();
		}
    	 
		// animalList 객체를  request 객체에 저장하여 뷰에 전달
		request.setAttribute("animalList", animalList);						

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/animal/list.jsp"; 
	}

}
