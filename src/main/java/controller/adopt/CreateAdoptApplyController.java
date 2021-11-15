package controller.adopt;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.adopt.CreateAdoptApplyController;
import controller.user.UserSessionUtils;
import model.AdoptApply;
import model.Adopter;
import model.service.AdoptApplyManager;
import model.service.AdopterManager;
import model.service.ExistingUserException;
import model.service.UserManager;


// view.jsp에서 동물 정보 받아서 createApplyForm으로 전달
public class CreateAdoptApplyController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// POST request (form의 입력데이터가 parameter로 전송됨)
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		
		if (request.getMethod().equals("GET")) {	
    		// GET request: 회원정보 등록 form 요청	
    		log.debug("RegisterForm Request");

			return "/adopt/createApplyForm.jsp";   // 검색한 사용자 정보를 update form으로 전송     	
	    }	
		
		AdoptApply adopt = new AdoptApply(
				request.getParameter("apply_id"),
				user_id,
				request.getParameter("animal_id"),
				request.getParameter("content"),
				request.getParameter("living_environment"),
				request.getParameter("have_pets"),
				request.getParameter("apply_matched"),
				request.getParameter("apply_date"),
				request.getParameter("approval_date"),
				request.getParameter("image"),
				request.getParameter()
				// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
				LocalDate.now(),
				0,//입양승낙거부 미정-0
				);
		try {
			AdoptApplyManager manager = AdoptApplyManager.getInstance();
			manager.create(adopt);
			
	    	log.debug("Create Adopt : {}", adopt);
	        return "redirect:/adopt/applyList";	// 성공 시 adopt form으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("adopt", adopt);
			return "/adopt/createApplyForm.jsp";
		}
	}
}
