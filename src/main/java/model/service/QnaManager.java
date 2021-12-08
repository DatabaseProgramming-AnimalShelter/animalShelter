package model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Adopter;
import model.Qna;
import model.dao.mybatis.QnaDAO;

public class QnaManager {
	private static QnaManager qnaMan = new QnaManager();
	private QnaDAO qnaDAO;

	private QnaManager() {
		try {
			qnaDAO = new QnaDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static QnaManager getInstance() {
		return qnaMan;
	}

	public int create(Qna qna) throws SQLException, ExistingUserException {
		System.out.println("Managercreate�떆�옉");
		return qnaDAO.create(qna);
	}

	public int update(Qna qna) throws SQLException, UserNotFoundException {
		return qnaDAO.update(qna);
	}

	public int remove(int qnaId) throws SQLException, UserNotFoundException {

		return qnaDAO.remove(qnaId);
	}
//
//	public boolean login(String password) throws SQLException, UserNotFoundException, PasswordMismatchException {
//		Adopter user = findUser(user_id);
//
//		if (!user.matchPassword(password)) {
//			throw new PasswordMismatchException("PasswordMismatchException");
//		}
//		return true;
//	}

	public List<Qna> selectAllQnaList() throws SQLException {
		return qnaDAO.selectAllQnaList();
	}

	public Qna findQnaByPrimaryKey(int qnaId) throws SQLException, AnimalNotFoundException {
		// TODO Auto-generated method stub
		Qna review = qnaDAO.findQnaByPrimaryKey(qnaId);

		if (review == null) {
			throw new AnimalNotFoundException(qnaId + "媛� �뾾�뒿�땲�떎.");
		}
		return review;
	}

	public int findQnaCategoryId(String qna_type) { 
		return qnaDAO.findQnaCategoryId(qna_type);
	}

	public List<Qna> findQnaCategoryByQnaType(String qnaType) {
		return qnaDAO.findQnaCategoryByQnaType(qnaType);
	}

	// 마이페이지 문의 리스트
	public List<Qna> findUserQnaList(String user_id) throws SQLException {
		return qnaDAO.findUserQnaList(user_id);
	}

}
