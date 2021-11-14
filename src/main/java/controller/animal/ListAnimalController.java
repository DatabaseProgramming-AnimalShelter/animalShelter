package controller.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Animal;
import model.service.AnimalManager;

public class ListAnimalController implements Controller{
	
	
	// ListAnimalController�� �ʿ��Ѱ�?? 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	
    	// Controller�� Manager�� �и��� ���
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.findAnimalList();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-"+animalList);
		// animalList ��ü��  request ��ü�� �����Ͽ� �信 ����
		request.setAttribute("animalList", animalList);						

		// ����� ����Ʈ ȭ������ �̵�(forwarding)
		return "/animal/list.jsp";        
	}

}
