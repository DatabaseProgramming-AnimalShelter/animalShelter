package controller.adopt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.AdoptApplyManager;
import model.AdoptApply;
public class ManagerAcceptController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int apply_id = Integer.parseInt(request.getParameter("apply_id"));
		AdoptApplyManager manager = AdoptApplyManager.getInstance();
		AdoptApply adoptApply = manager.findAdoptApply(apply_id);
		manager.approval(adoptApply);

		List<AdoptApply> list = manager.findAdoptApplyResultList();

		request.setAttribute("AdoptApplyList", list);
		return "/adopt/applyResultList.jsp";
	}
}
