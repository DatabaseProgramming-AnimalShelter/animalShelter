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
		// Controller�� Manager�� ������ ���
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
    	 
		// animalList ��ü��  request ��ü�� �����Ͽ� �信 ����
		request.setAttribute("animalList", animalList);						

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/animal/list.jsp"; 
	}

}
