package controller.adopt;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.AdoptApply;
import model.service.AdoptApplyManager;

public class ManagerResultController implements Controller {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int apply_id = Integer.parseInt(request.getParameter("apply_id"));
		int apply_result = 0; // 거부 = 0

		if (request.getMethod().equals("POST")) {
			apply_result = 1; // 승인 = 1
		}
		
		// 거부 or 승인 처리
		AdoptApplyManager manager = AdoptApplyManager.getInstance();
		AdoptApply adoptApply = manager.findAdoptApply(apply_id);
		manager.apply_result(adoptApply, apply_result);

		// 한 동물에 대해 다른 입양 신청이 존재 할 경우 다른 신청들 모두 거절 처리
		if(apply_result == 1) {
			List<AdoptApply> adoptApplyList = manager.findAnimalAdoptList(adoptApply.getAnimal_id()); // 이 동물에 대한 모든 입양 신청 리스트 반환
			Iterator<AdoptApply> iter = adoptApplyList.iterator();
			AdoptApply apply = null;
			apply_result = 0;
			
			while (iter.hasNext()) {
				apply = iter.next();
				manager.apply_result(apply, apply_result);
			}
		}
		
		List<AdoptApply> list = manager.findAdoptApplyResultList();

		request.setAttribute("AdoptApplyList", list);
		return "/adopt/applyResultList.jsp";
	}
}
