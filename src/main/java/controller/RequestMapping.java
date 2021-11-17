package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.animal.RegisterAnimalController;
import controller.adopt.CreateAdoptApplyController;
import controller.adopt.ListResultApplyController;
import controller.adopt.ViewApplyController;
import controller.adopt.ViewApprovalController;
import controller.animal.ListAnimalController;
import controller.adopt.ListResultApplyController;
import controller.animal.ViewAnimalController;
import controller.user.*;
public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
   
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/home", new MainController());
        mappings.put("/register",new ForwardController("/user/registerForm.jsp"));
        mappings.put("/user/register", new RegisterUserController());
        //mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/mypage", new ViewUserController());

        mappings.put("/user/register", new RegisterUserController());
       
//        mappings.put("/animal/search", new SearchAnimalController());
        mappings.put("/animal/view", new ViewAnimalController());
        mappings.put("/animal/list", new ListAnimalController());
        mappings.put("/animal/register", new RegisterAnimalController());
        
        //�엯�뼇�떊泥� �룞臾쇱삁�빟
        mappings.put("/adopt/register", new CreateAdoptApplyController());

        mappings.put("/adopt/view", new ViewApplyController());

        mappings.put("/adopt/accept", new RegisterAnimalController());
        mappings.put("/adopt/list", new ListResultApplyController());

        mappings.put("/adopt/approved_list", new ViewApprovalController());

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
        return mappings.get(uri);
    }
}
