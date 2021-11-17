package controller.adopt;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.AdoptApply;
import model.service.UserManager;

public class ListResultApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	UserManager manager = UserManager.getInstance();
		List<AdoptApply> adopterList = manager.getAdoptResultList();
		
		
		request.setAttribute("adopterList", adopterList);				
		return "/adopt/applyResult.jsp";        
    }
}