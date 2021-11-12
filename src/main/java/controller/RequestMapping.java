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
    
    // �� ��û uri�� ���� controller ��ü�� ������ HashMap ����
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// �� uri�� �����Ǵ� controller ��ü�� ���� �� ����
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/home", new ForwardController("/home/main.jsp"));
        mappings.put("/register",new ForwardController("/user/registerForm.jsp"));
        //mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));//header �α��� ��ư���� /form����
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/mypage", new ViewUserController());

        mappings.put("/user/register", new RegisterUserController());
       
        mappings.put("/animal/search", new SearchAnimalController());
        mappings.put("/animal/view", new ViewAnimalController());
        mappings.put("/animal/list", new ListAnimalController());
        mappings.put("/adopt/createForm", new CreateApplyFormController());
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// �־��� uri�� �����Ǵ� controller ��ü�� ã�� ��ȯ
        return mappings.get(uri);
    }
}
