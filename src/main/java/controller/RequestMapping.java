package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.adopt.CreateApplyFormController;
import controller.animal.ListAnimalController;
import controller.animal.SearchAnimalController;
import controller.animal.ViewAnimalController;
import controller.user.*;
public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 媛� �슂泥� uri�뿉 ���븳 controller 媛앹껜瑜� ���옣�븷 HashMap �깮�꽦
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 媛� uri�뿉 ���쓳�릺�뒗 controller 媛앹껜瑜� �깮�꽦 諛� ���옣
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/home", new ForwardController("/home/main.jsp"));
        mappings.put("/register",new ForwardController("/user/registerForm.jsp"));
        //mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));//header 濡쒓렇�씤 踰꾪듉�뿉�꽌 /form議곗젅
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/mypage", new ViewUserController());

        mappings.put("/user/register", new RegisterUserController());
       
//        mappings.put("/animal/search", new SearchAnimalController());
//        mappings.put("/animal/view", new ViewAnimalController());
//      //  mappings.put("/animal/list", new ListAnimalController());
//        mappings.put("/adopt/createForm", new CreateApplyFormController());
        
        //adopt form
        mappings.put("/user/register", new RegisterUserController());
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 二쇱뼱吏� uri�뿉 ���쓳�릺�뒗 controller 媛앹껜瑜� 李얠븘 諛섑솚
        return mappings.get(uri);
    }
}
