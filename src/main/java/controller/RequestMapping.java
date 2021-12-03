package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.animal.RegisterAnimalController;
import controller.adopt.CreateAdoptApplyController;
import controller.adopt.ListResultApplyController;
import controller.adopt.ManagerAcceptController;
import controller.adopt.ManagerDeclineController;
import controller.adopt.ManagerResultController;
import controller.adopt.ViewApplyController;
import controller.adopt.ViewApprovalListController;
import controller.animal.ListAnimalController;
import controller.animal.ViewAnimalController;
import controller.adopt.UserAdoptListController;
import controller.review.DeleteReviewController;
import controller.review.ListReviewController;
import controller.review.RegisterReviewController;
import controller.review.UpdateReviewController;
import controller.review.ViewReviewController;

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
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/delete", new DeleteUserController()); 
        mappings.put("/user/register", new RegisterUserController()); 
        mappings.put("/adopt/userAdoptList", new UserAdoptListController()); 

//        mappings.put("/animal/search", new SearchAnimalController());
        mappings.put("/animal/view", new ViewAnimalController());
        mappings.put("/animal/list", new ListAnimalController());
        mappings.put("/animal/register", new RegisterAnimalController());
        
        // 占쎈엷 �젆占� 占쎈뼁癲딉옙   �뙴�쉮�닱占쎈닔占쎄탽 �뜮占�
        mappings.put("/adopt/register", new CreateAdoptApplyController());
        mappings.put("/adopt/view", new ViewApplyController());
        mappings.put("/adopt/accept", new RegisterAnimalController());
        mappings.put("/adopt/list", new ListResultApplyController());

        mappings.put("/adopt/approved_list", new ViewApprovalListController());
        mappings.put("/adopt/approval", new ManagerAcceptController());
        mappings.put("/adopt/decline", new ManagerDeclineController()); 
        mappings.put("/adopt/result", new ManagerResultController());
        mappings.put("/adopt/userAdoptList", new UserAdoptListController()); 

        mappings.put("/review/list", new ListReviewController());
        mappings.put("/review/register", new RegisterReviewController());
        mappings.put("/review/view", new ViewReviewController());
        mappings.put("/review/update", new UpdateReviewController());
        mappings.put("/review/delete", new DeleteReviewController());


        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {   
        return mappings.get(uri);
    }
}