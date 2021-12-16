package controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.service.QnaManager;
import model.service.ReviewNotFoundException;
import controller.Controller;
import controller.adopt.CreateAdoptApplyController;
import controller.user.UserSessionUtils;
import model.Qna;

public class ViewQnaController implements Controller{
	private static final Logger log = LoggerFactory.getLogger(CreateAdoptApplyController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		QnaManager manager = QnaManager.getInstance();
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		Qna qna = manager.findQnaByPrimaryKey(qna_id);
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		int flg = 0;
		
		request.setAttribute("qna", qna);		
		
		if (request.getMethod().equals("POST")) { // 비밀번호 체크
			String pwd = manager.checkQnaPwd(qna_id);
			String input_pwd = request.getParameter("check_qna_password");
			
			if(pwd.equals(input_pwd)) { // 비밀번호 맞으면
				flg = 1;
			}
		}
		if(user_id != null && user_id.equals("admin")) {
			flg = 1;
		}
		
		if(flg == 1) {
			return "/qna/view.jsp";
		}
		return "/qna/checkPwd.jsp";
	}

}
