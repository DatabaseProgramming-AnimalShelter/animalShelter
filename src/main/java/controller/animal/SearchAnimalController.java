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
		// �Ŀ� category_id�� String���� �ٲ����
		int category_id = (int) request.getAttribute("species");
		String type = (String) request.getAttribute("type");
		int matched = (int) request.getAttribute("matched");
	
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.searchAnimalList(category_id, type, matched);
		
		// animalList ��ü��  request ��ü�� �����Ͽ� �信 ����
		request.setAttribute("searchAnimalList", animalList);						

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/animal/list.jsp"; 
	}

}
