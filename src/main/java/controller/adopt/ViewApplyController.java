package controller.adopt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.AdoptApply;
import model.Community;

//입양신청상세정보 & 신청
public class ViewApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	AdoptApply apply = null;
		UserManager manager = UserManager.getInstance();
		int adopt_id = Integer.parseInt(request.getParameter("user_id"));
//		comm = manager.findCommunity(commId);		// 커뮤니티 정보 검색			
		//adopt = manager.findAdoptApply(user_id);
		request.setAttribute("apply", apply);	// 커뮤니티 정보 저장				
		return "/adopt/viewApplyForm.jsp";				// 커뮤니티 보기 화면으로 이동
  }
}

