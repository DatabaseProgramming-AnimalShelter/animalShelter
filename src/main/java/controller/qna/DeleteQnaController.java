package controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Qna;
import model.service.QnaManager;

public class DeleteQnaController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteQnaController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		int qna_id = Integer.parseInt(request.getParameter("qna_id"));

		QnaManager manager = QnaManager.getInstance();
	
		manager.remove(qna_id);
		
		return "redirect:/qna/list";	
		
	}
}
