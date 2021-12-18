package controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.QnaManager;

public class DeleteQnaController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

		int qna_id = Integer.parseInt(request.getParameter("qna_id"));

		QnaManager manager = QnaManager.getInstance();
	
		manager.remove(qna_id);
		
		return "redirect:/qna/list";	
		
	}
}
