package controller.adopt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.AdoptApply;
import model.service.AdoptApplyManager;

//네브바에 보여질 승인결과 페이지
public class ViewApprovalListController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdoptApplyManager manager = AdoptApplyManager.getInstance();
		
		List<AdoptApply> list = manager.findAdoptApplyResultList();
		
		request.setAttribute("AdoptApplyList", list);	
		return "/adopt/applyResult.jsp";				

	}
}
