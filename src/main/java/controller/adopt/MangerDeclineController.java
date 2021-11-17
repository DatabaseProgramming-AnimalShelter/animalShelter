package controller.adopt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.AdoptApply;
import model.service.AdoptApplyManager;

public class MangerDeclineController {
	
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	AdoptApply apply = null;
    	AdoptApplyManager manager = AdoptApplyManager.getInstance();
    	int apply_id = Integer.parseInt(request.getParameter("apply_id"));
		
		try {
			apply = manager.findAdoptApply(apply_id);
			request.setAttribute("apply", apply);
			
			log.debug("View Appply Adopt apply: {}", apply);
	        return "/adopt/reviewApplyForm.jsp";	
	        
		} catch (Exception e) {	
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			return "/adopt/list";	
		}
    }

}
