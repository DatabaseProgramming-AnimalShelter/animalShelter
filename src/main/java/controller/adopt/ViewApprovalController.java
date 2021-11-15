package controller.adopt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.AdoptApply;
import model.service.UserManager;
//(개인의)입양신청내역?
public class ViewApprovalController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdoptApply adopt = null;
		UserManager manager = UserManager.getInstance();
		int adopt_id = Integer.parseInt(request.getParameter("user_id"));
//		comm = manager.findCommunity(commId);		// 커뮤니티 정보 검색			
		//adopt = manager.findAdoptApply(user_id);
		request.setAttribute("AdoptApply", adopt);	// 커뮤니티 정보 저장				
		return "/user/mypage.jsp";				// 커뮤니티 보기 화면으로 이동

	}
}
