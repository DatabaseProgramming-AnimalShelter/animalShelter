package controller.adopt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.AdoptApply;
import model.service.AdoptApplyManager;

public class ManagerResultController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int apply_id = Integer.parseInt(request.getParameter("apply_id"));
		int apply_result = 1; 	// 입양 승인 = 1
		
		if (request.getMethod().equals("POST")) {
			apply_result = 0; 	// 입양 거부 = 0
		}
		AdoptApplyManager manager = AdoptApplyManager.getInstance();
		AdoptApply adoptApply = manager.findAdoptApply(apply_id);
		manager.apply_result(adoptApply, apply_result);

		List<AdoptApply> list = manager.findAdoptApplyResultList();

		request.setAttribute("AdoptApplyList", list);
		return "/adopt/applyResultList.jsp";
	}
}
