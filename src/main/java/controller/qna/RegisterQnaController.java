package controller.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.Qna;
import model.service.ExistingUserException;
import model.service.QnaManager;

public class RegisterQnaController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterQnaController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		QnaManager manager = QnaManager.getInstance();

		if (request.getMethod().equals("GET")) {
			// GET request: 리뷰 등록 form 요청
			log.debug("CreateForm Request");

			return "/qna/registerForm.jsp";
		}
		int qna_category_id = manager.findQnaCategoryId(request.getParameter("qna_type"));
		Qna qna = new Qna(
				request.getParameter("qna_writer"),
				request.getParameter("qna_title"),
				request.getParameter("qna_content"),
				request.getParameter("qna_password"),
				qna_category_id
				);
		try {
			System.out.println(qna.toString());
			manager.create(qna);

			log.debug("Create Qna : {}", qna);
			return "redirect:/qna/list";

		} catch (ExistingUserException e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("qna", qna);
			return "/qna/registerForm.jsp";
		}
	}
}