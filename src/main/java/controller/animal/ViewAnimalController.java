package controller.animal;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.service.AdoptApplyManager;
import model.service.AnimalManager;
import model.service.AnimalNotFoundException;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.AdoptApply;
import model.Animal;

public class ViewAnimalController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
	    String user_id = UserSessionUtils.getLoginUserId(request.getSession());		
		Animal animal = null;
		List<AdoptApply> animalList = new ArrayList<AdoptApply>();
		AnimalManager manager = AnimalManager.getInstance();
		AdoptApplyManager applyManager = AdoptApplyManager.getInstance();
		
		int animal_id = Integer.parseInt(request.getParameter("animal_id"));
		System.out.println("#########################" + animal_id);

		try {
			animal = manager.findAnimal(animal_id); // �쑀湲곕룞臾� �젙蹂� 寃��깋
			AdoptApply apply = new AdoptApply();
			apply = null;
			//animalList = applyManager.findUserAdoptList(user_id); 
			animalList = applyManager.findAdoptApplyList(); 
			Iterator<AdoptApply> iter = animalList.iterator();
			
			boolean applied = false;
			while(iter.hasNext()) { // 한 동물에 대해서 이미 입양 신청 한 기록이 있을 경우
				apply = iter.next();
				if(apply.getAnimal_id() == animal_id) {
					applied = true;
					break;
				}
			}
			
			request.setAttribute("apply", apply);
			request.setAttribute("applied", applied);
		} catch (AnimalNotFoundException e) {
			return "redirect:/animal/list";
		}
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/upload");
		File dir = new File(path);
		request.setAttribute("dir", dir);
		request.setAttribute("animal", animal); // �쑀湲곕룞臾� �젙蹂� ���옣
		System.out.println("#########################" + animal.getImage());
		return "/animal/view.jsp"; // �쑀湲곕룞臾� �긽�꽭蹂닿린 �솕硫댁쑝濡� �씠�룞*/
	}

}