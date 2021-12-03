package controller.qna;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Adopter;
import model.Qna;
import model.service.AdopterManager;
import model.service.ExistingUserException;
import model.service.QnaManager;

public class RegisterQnaController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(RegisterQnaController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		QnaManager manager = QnaManager.getInstance();

		if (request.getMethod().equals("GET")) {
			// GET request: 리뷰 등록 form 요청
			log.debug("CreateForm Request");

			request.setAttribute("user_id", user_id);

			return "/qna/registerForm.jsp";
		}

		int qna_category_id = manager.findQnaCategoryId(request.getParameter("qna_type"));
		Qna qna = new Qna(
				request.getParameter("title"), 
				user_id, qna_category_id, 
				request.getParameter("content")
			);

		try {

			manager.create(qna);

			log.debug("Create Review : {}", qna);
			return "redirect:/qna/list";

		} catch (ExistingUserException e) {
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("qna", qna);
			return "/review/registerForm.jsp";
		}
	}
}