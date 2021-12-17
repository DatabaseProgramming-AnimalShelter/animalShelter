package model.service;

import java.sql.SQLException;
import java.util.List;


import model.Qna;
import model.Qna_Comment;
import model.dao.mybatis.QnaDAO;
import model.dao.mybatis.Qna_CommentDAO;

public class QnaManager {
	private static QnaManager qnaMan = new QnaManager();
	private QnaDAO qnaDAO;
	private Qna_CommentDAO commentDAO;

	private QnaManager() {
		try {
			qnaDAO = new QnaDAO();
			commentDAO = new Qna_CommentDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static QnaManager getInstance() {
		return qnaMan;
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

	public int findQnaCategoryId(String qna_id) {
		return qnaDAO.findQnaCategoryId(qna_id);
	}

	public List<Qna> selectAllQnaList() throws SQLException {
		return qnaDAO.selectAllQnaList();
	}

	public Qna findQnaByPrimaryKey(int qna_id) throws SQLException, AnimalNotFoundException {
		Qna review = qnaDAO.findQnaByPrimaryKey(qna_id);

		if (review == null) {
			throw new AnimalNotFoundException(qna_id + "가 없습니다.");
		}
		return review;
	}

	public List<Qna> findQnaCategoryByQnaType(String qnaType) {
		return qnaDAO.findQnaCategoryByQnaType(qnaType);
	}

	// 댓글 입력
	public int insertComment(Qna_Comment comment) {
		return commentDAO.insertComment(comment);
	}

	// 댓글 수정
	public int updateComment(Qna_Comment comment) {
		return commentDAO.updateComment(comment);
	}

	// 댓글 삭제
	public int deleteComment(int comment_no) {
		return commentDAO.deleteComment(comment_no);
	}
	//댓글선택
	public Qna_Comment selectComment(int qna_id) {
		return commentDAO.selectComment(qna_id);
	}
	// 마이페이지 문의 리스트
	public List<Qna> selectMyQnaList(String user_id) throws SQLException {
		return qnaDAO.selectMyQnaList(user_id);
	}

	// 비밀번호 체크
	public String checkQnaPwd(int qna_id) throws SQLException {
		return qnaDAO.checkQnaPwd(qna_id);
	}

}
