package controller.adopt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.AdoptApply;
import model.service.AdoptApplyManager;

public class UserAdoptListController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)   throws Exception {
      String user_id = UserSessionUtils.getLoginUserId(request.getSession());		

      AdoptApplyManager manager = AdoptApplyManager.getInstance();
      List<AdoptApply> userAdoptList = manager.findUserAdoptList(user_id);
      
      request.setAttribute("userAdoptList", userAdoptList);
      
      return "/user/adoptList.jsp";     
    }
}
