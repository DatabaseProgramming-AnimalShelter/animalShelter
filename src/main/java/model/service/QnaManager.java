package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Qna;
import model.dao.QnaDAO;


public class QnaManager {
	private static QnaManager reviewMan = new QnaManager();
	private QnaDAO qnaDAO;

	public QnaDAO getReviewDAO() {
		return this.qnaDAO;
	}
	
	private QnaManager() {
		try {
			qnaDAO = new QnaDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static QnaManager getInstance() {
		return reviewMan;
	}
	
	public int create(Qna qna) throws SQLException, ExistingUserException {
		return qnaDAO.create(qna);
	}

	public int update(Qna qna) throws SQLException, UserNotFoundException {
		return qnaDAO.update(qna);
	}	

	public int remove(int qna_id) throws SQLException, UserNotFoundException {

		return qnaDAO.remove(qna_id);
	}

	public List<Qna> findQnaList() throws SQLException {
			return qnaDAO.findQnaList();
	}

	public Qna findQna(int qna_id) throws SQLException, AnimalNotFoundException {
		// TODO Auto-generated method stub
		Qna review = qnaDAO.findQna(qna_id);
		
		if(review == null) {
			throw new AnimalNotFoundException(qna_id + "ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ê´ï¿½ ï¿½Ä±ï¿½ï¿½Ô´Ï´ï¿½.");
		}
		
		return review;
	}

	public int findQnaCategoryId(String qna_type) { 
		return qnaDAO.findQnaCategoryId(qna_type);
	}
	
	// ¸¶ÀÌÆäÀÌÁö ¹®ÀÇ ¸®½ºÆ®
	public List<Qna> findUserQnaList(String user_id) throws SQLException {
		return qnaDAO.findUserQnaList(user_id);
	}
}
