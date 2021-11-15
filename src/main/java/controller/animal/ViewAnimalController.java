package controller.animal;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.AnimalManager;
import model.service.AnimalNotFoundException;
import controller.Controller;

import model.Animal;

public class ViewAnimalController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Animal animal = null;
		AnimalManager manager = AnimalManager.getInstance();
		int animal_id = Integer.parseInt(request.getParameter("animal_id"));
		System.out.println("#########################" + animal_id);

		try {
			animal = manager.findAnimal(animal_id); // 유기동물 정보 검색
		} catch (AnimalNotFoundException e) {
			return "redirect:/animal/list";
		}
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("/upload");
		File dir = new File(path);
		request.setAttribute("dir", dir);
		request.setAttribute("animal", animal); // 유기동물 정보 저장
		System.out.println("#########################" + animal.getImage());
		return "/animal/view.jsp"; // 유기동물 상세보기 화면으로 이동*/
	}

}