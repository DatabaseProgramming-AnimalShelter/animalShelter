package controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Qna;
import model.Review;
import model.service.QnaManager;
import model.service.ReviewManager;

public class ListQnaController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		QnaManager manager = QnaManager.getInstance();

		List<Qna> QnaList = null;
		
		if(request.getParameter("user_id") != null) { // 마이페이지에서 사용자가 작성한 문의 리스트 볼 때	
			QnaList = manager.findUserQnaList(UserSessionUtils.getLoginUserId(request.getSession()));
		}
		else { // 모든 사람이 작성한 문의 리스트 볼 때
			QnaList = manager.selectAllQnaList();
		}		

	//	List<Qna> QnaList = manager.selectAllQnaList();


		for(int i = 0; i < QnaList.size(); i++) {
//			System.out.println("Title : " + QnaList.get(i).getContent());
		}
		
		request.setAttribute("QnaList", QnaList);
		return "/qna/list.jsp";
	}

}
