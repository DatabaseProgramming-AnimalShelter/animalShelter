package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.adopt.*;
import controller.animal.*;
import controller.review.*;
import controller.user.*;
import controller.qna.*;

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
        mappings.put("/animal/delete", new DeleteAnimalController());
        mappings.put("/animal/update", new UpdateAnimalController());
        
        mappings.put("/adopt/register", new CreateAdoptApplyController());
        mappings.put("/adopt/view", new ViewApplyController());
        mappings.put("/adopt/accept", new RegisterAnimalController());
        mappings.put("/adopt/list", new ListResultApplyController());

        mappings.put("/adopt/approved_list", new ViewApprovalListController());
        mappings.put("/adopt/approval", new ManagerAcceptController());
        mappings.put("/adopt/decline", new ManagerDeclineController()); 
        mappings.put("/adopt/result", new ManagerResultController());

        mappings.put("/review/list", new ListReviewController());
        mappings.put("/review/register", new RegisterReviewController());
        mappings.put("/review/view", new ViewReviewController());
        mappings.put("/review/update", new UpdateReviewController());
        mappings.put("/review/delete", new DeleteReviewController());

        mappings.put("/qna/create", new RegisterQnaController());
        mappings.put("/qna/list", new ListQnaController());
        mappings.put("/qna/view", new ViewQnaController());
        mappings.put("/qna/update", new UpdateQnaController());
        mappings.put("/qna/delete", new DeleteReviewController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {   
        return mappings.get(uri);
    }
}