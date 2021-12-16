package controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Qna;
import model.service.QnaManager;

public class ListQnaController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		QnaManager manager = QnaManager.getInstance();

		List<Qna> QnaList = manager.selectAllQnaList();
		
		if(request.getParameter("user_id") != null) { 
			QnaList = manager.selectMyQnaList(UserSessionUtils.getLoginUserId(request.getSession()));
		}
		else { 
			QnaList = manager.selectAllQnaList();
		}		


		for(int i = 0; i < QnaList.size(); i++) {
//			System.out.println("Title : " + QnaList.get(i).getContent());
		}
		
		request.setAttribute("QnaList", QnaList);
		return "/qna/list.jsp";
	}

}
