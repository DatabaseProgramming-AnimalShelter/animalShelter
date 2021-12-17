package controller.qna_comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.Qna_Comment;
import model.service.QnaManager;

public class CreateQnaCommentController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateQnaCommentController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QnaManager manager = QnaManager.getInstance();

		int qna_id = Integer.parseInt(request.getParameter("qna_id"));

		Qna_Comment comment = new Qna_Comment(qna_id, request.getParameter("comment_content"));
		
		manager.insertComment(comment);

		log.debug("Create Comment : {}", comment);
		return "redirect:/qna/view?qna_id=" + qna_id;

	}
}