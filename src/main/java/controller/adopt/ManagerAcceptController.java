package controller.adopt;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.AdoptApplyManager;
import model.service.AnimalManager;
import model.AdoptApply;
import model.Animal;

public class ManagerAcceptController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	AdoptApply adoptApply =  (AdoptApply) request.getAttribute("adoptApply");
    	AdoptApplyManager manager = AdoptApplyManager.getInstance();
    	
		manager.approval(adoptApply);
		request.setAttribute("adoptApply", adoptApply);	
		return "/adopt/viewApplyForm.jsp";				
  }
}

