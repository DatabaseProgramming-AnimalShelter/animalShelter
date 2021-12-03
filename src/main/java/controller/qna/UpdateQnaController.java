package controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.service.QnaManager;
import model.Qna;

public class UpdateQnaController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UpdateQnaController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

    	String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		QnaManager manager = QnaManager.getInstance();
		
		if (request.getMethod().equals("GET")) {	

			System.out.println("qna_id" + qna_id );
			Qna qna = manager.findQna(qna_id);
			request.setAttribute("qna", qna);			
				
			return "/qna/updateForm.jsp";    
	    }	
		int qna_category_id = manager.findQnaCategoryId(request.getParameter("qna_type"));
		Qna qna = new Qna(
				request.getParameter("title"), 
				user_id, qna_category_id, 
				request.getParameter("content")
			);
		
    	log.debug("Update qna : {}", qna);
		manager.update(qna);		
		
        return "redirect:/qna/list";
    }
}
