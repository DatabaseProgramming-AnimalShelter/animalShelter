package controller.adopt;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.AdoptApplyManager;
import model.service.AnimalManager;
import model.AdoptApply;
import model.Animal;



//  떊泥  븳  빆紐   떎 蹂댁씠寃 

public class ViewApplyController implements Controller {
   private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

    @Override
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
