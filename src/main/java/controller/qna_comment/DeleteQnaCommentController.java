package controller.qna_comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.QnaManager;

public class DeleteQnaCommentController implements Controller {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		int comment_no = Integer.parseInt(request.getParameter("comment_no"));

		QnaManager manager = QnaManager.getInstance();
		System.out.println();

		int qna_id = Integer.parseInt(request.getParameter("qna_id"));

		System.out.println("qna_id" + qna_id);
		manager.deleteComment(comment_no);
		    
		return "redirect:/qna/view?qna_id=" + qna_id;		
	}
}