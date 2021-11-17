package controller.adopt;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.AdoptApplyManager;
import model.AdoptApply;
import model.service.AnimalManager;

public class ListResultApplyController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
      
       AdoptApplyManager manager = AdoptApplyManager.getInstance();
      List<AdoptApply> adoptApplyList = manager.findAdoptApplyList();

      request.setAttribute("adoptApplyList", adoptApplyList);

      return "/adopt/beforeApplyList.jsp";     
    }
}