package controller.animal;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Animal;
import model.service.AnimalManager;

public class ListAnimalController implements Controller{
	
	
	// ListAnimalController占쏙옙 占십울옙占싼곤옙?? 
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		if (request.getMethod().equals("POST")) {	
			
			/*request.setAttribute("animalList", request.getParameter("searchAnimalList"));	
			System.out.println( request.getParameter("searchAnimalList"));
			return "/animal/list.jsp";   */
			String type =  request.getParameter("type");System.out.println("type"+type);
			int category_id ;
			int matched ;
			if (request.getParameter("species") == null) {
				category_id=0;
			}
			else {
			category_id = Integer.parseInt( request.getParameter("species"));}
			System.out.println("category_id"+category_id);
			matched = Integer.parseInt( request.getParameter("matched"));
			System.out.println("matched"+matched);
			AnimalManager manager = AnimalManager.getInstance();
			List<Animal> animalList = manager.searchAnimalList(type,category_id, matched);
			System.out.println("animalList");
			// animalList 占쏙옙체占쏙옙  request 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占썰에 占쏙옙占쏙옙
			request.setAttribute("animalList", animalList);						
			System.out.println(animalList);
			// 占쏙옙占쏙옙占� 占쏙옙占쏙옙트 화占쏙옙占쏙옙占쏙옙 占싱듸옙(forwarding)
			return "/animal/list.jsp"; 
	    }	
		//System.out.println("here!??!?!?!?!?!?");
    	// Controller占쏙옙 Manager占쏙옙 占싻몌옙占쏙옙 占쏙옙占�
		AnimalManager manager = AnimalManager.getInstance();
		List<Animal> animalList = manager.findAnimalList();
		
		// animalList 占쏙옙체占쏙옙  request 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占썰에 占쏙옙占쏙옙
		request.setAttribute("animalList", animalList);						

		// 占쏙옙占쏙옙占� 占쏙옙占쏙옙트 화占쏙옙占쏙옙占쏙옙 占싱듸옙(forwarding)
		return "/animal/list.jsp";        
	}

}
