package controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.service.QnaManager;
import model.service.ReviewNotFoundException;
import controller.Controller;

import model.Qna;

public class ViewQnaController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		QnaManager manager = QnaManager.getInstance();
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		Qna qna = manager.findQna(qna_id); 
		
    	request.setAttribute("qna", qna);			
    	
		return "/qna/view.jsp";				
	}

}
