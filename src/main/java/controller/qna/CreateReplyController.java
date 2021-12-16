package controller.qna;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.Qna;
import model.Qna_Comment;
import model.service.ExistingUserException;
import model.service.QnaManager;

public class CreateReplyController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateReplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QnaManager manager = QnaManager.getInstance();

		if (request.getMethod().equals("GET")) {
			// GET request: 리뷰 등록 form 요청
			log.debug("CreateForm Request");

			return "/qna/registerForm.jsp";
		}
		System.out.println("USERID넘어와짐"+request.getParameter("comment_writer"));
		System.out.println("comment_content " +request.getParameter("comment_content"));
		Qna_Comment comment = new Qna_Comment(
				request.getParameter("qna_id"),
				request.getParameter("comment_writer"),
				request.getParameter("comment_content")
				);
//		try {
			System.out.println(comment.toString());
			manager.insertComment(comment);

			log.debug("Create Review : {}", comment);
			return "redirect:/qna/view";

//		} 
	}
}