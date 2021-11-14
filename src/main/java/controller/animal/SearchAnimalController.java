package controller.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Animal;
import model.dao.AnimalDAO;
import model.service.AnimalManager;

public class SearchAnimalController implements Controller{
	private AnimalDAO animalDAO = new AnimalDAO();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 후에 category_id는 String으로 바꿔야함
		int category_id = (int) request.getAttribute("species");
		String type = (String) request.getAttribute("type");
		int matched = (int) request.getAttribute("matched");
	
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.searchAnimalList(category_id, type, matched);
		
		// animalList 객체를  request 객체에 저장하여 뷰에 전달
		request.setAttribute("searchAnimalList", animalList);						

		// 사용자 리스트 화면으로 이동(forwarding)
		return "/animal/list.jsp"; 
	}

}
