package controller.adopt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.AdoptApply;
import model.service.AdoptApplyManager;

public class ViewApprovalController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdoptApplyManager manager = AdoptApplyManager.getInstance();
		int adopt_id = Integer.parseInt(request.getParameter("user_id"));
		
		List<AdoptApply> list = manager.approval(null);
		
		request.setAttribute("AdoptApply", adopt);	
		return "/user/mypage.jsp";				

	}
}
