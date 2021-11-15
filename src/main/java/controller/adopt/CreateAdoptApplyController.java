package controller.adopt;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.adopt.CreateAdoptApplyController;
import model.AdoptApply;
import model.service.UserManager;

public class CreateAdoptApplyController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// POST request (form의 입력데이터가 parameter로 전송됨)
		AdoptApply adopt = new AdoptApply(
				Integer.parseInt(request.getParameter("user_id")),
				Integer.parseInt(request.getParameter("animal_id")),
				// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
				LocalDate.now(),
				0,//입양승낙거부 미정-0
				request.getParameter("content"),
				request.getParameter("living_environment"),
				request.getParameter("have_pets")
				);
		try {
			UserManager manager = UserManager.getInstance();
			manager.createAdoptApply(adopt);
			
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
