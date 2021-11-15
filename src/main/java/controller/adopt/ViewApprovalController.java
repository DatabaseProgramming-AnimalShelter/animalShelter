package controller.adopt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.AdoptApply;
import model.service.AdoptApplyManager;
//(������)�Ծ��û����?
public class ViewApprovalController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdoptApply adopt = null;
		AdoptApplyManager manager = AdoptApplyManager.getInstance();
		int adopt_id = Integer.parseInt(request.getParameter("user_id"));
//		comm = manager.findCommunity(commId);		// Ŀ�´�Ƽ ���� �˻�			
		//adopt = manager.findAdoptApply(user_id);
		request.setAttribute("AdoptApply", adopt);	// Ŀ�´�Ƽ ���� ����				
		return "/user/mypage.jsp";				// Ŀ�´�Ƽ ���� ȭ������ �̵�

	}
}
