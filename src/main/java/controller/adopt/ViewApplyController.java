package controller.adopt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.AdoptApplyManager;
import model.AdoptApply;


// 신청한 항목 다 보이게
public class ViewApplyController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	AdoptApply apply = null;
    	AdoptApplyManager manager = AdoptApplyManager.getInstance();
    	int apply_id = Integer.parseInt(request.getParameter("apply_id"));
		
		try {
			apply = manager.findAdoptApply(apply_id);
			manager.create(apply);
			
			log.debug("View Appply Adopt : {}", apply);
	        return "redirect:/";	
	        
		} catch (Exception e) {		
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("apply", apply);
			return "/adopt/viewApplyForm.jsp";	
		}
  }
}

