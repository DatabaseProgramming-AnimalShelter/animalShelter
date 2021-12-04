package controller.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Animal;
import model.service.AnimalManager;

public class ListAnimalController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/*if (request.getMethod().equals("POST")) {

			String type = request.getParameter("type");
			System.out.println("type" + type);
			int category_id;
			int matched;
			String location= request.getParameter("location");
			if (request.getParameter("species") == null) {
				category_id = 0;
			} else {
				category_id = Integer.parseInt(request.getParameter("species"));
			}
			System.out.println("category_id" + category_id);
			matched = Integer.parseInt(request.getParameter("matched"));
			System.out.println("matched" + matched);
			AnimalManager manager = AnimalManager.getInstance();
			List<Animal> animalList = manager.searchAnimalList(type,category_id, matched,location);
			System.out.println("animalList");
			request.setAttribute("animalList", animalList);					
			request.setAttribute("type", type);		
			request.setAttribute("category_id", category_id);
			request.setAttribute("matched", matched);
			request.setAttribute("location", location);
			request.setAttribute("ispost", 1);
			System.out.println(animalList);
			return "/animal/list.jsp";
		}*/
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.findAnimalList();

		request.setAttribute("animalList", animalList);

		return "/animal/list.jsp";
	}

}
