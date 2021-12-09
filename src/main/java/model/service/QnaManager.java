package model.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Qna;
import model.Qna_Comment;
import model.Qna_Reply;
import model.dao.mybatis.CommentDAO;
import model.dao.mybatis.QnaDAO;

public class QnaManager {
	private static QnaManager qnaMan = new QnaManager();
	private QnaDAO qnaDAO;
	private CommentDAO commentDAO;

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
		System.out.println("Managercreate시작");
		return qnaDAO.create(qna);
	}

	public int update(Qna qna) throws SQLException, UserNotFoundException {
		return qnaDAO.update(qna);
	}

	public int remove(int qna_id) throws SQLException, UserNotFoundException {

		return qnaDAO.remove(qna_id);
	}

	public int findQnaCategoryId(String qna_type) {
		System.out.println("qnaTYPE임~" + qna_type);
		return qnaDAO.findQnaCategoryId(qna_type);
	}

	public List<Qna> selectAllQnaList() throws SQLException {
		return qnaDAO.selectAllQnaList();
	}

	public Qna findQnaByPrimaryKey(int qna_id) throws SQLException, AnimalNotFoundException {
		// TODO Auto-generated method stub
		Qna review = qnaDAO.findQnaByPrimaryKey(qna_id);

		if (review == null) {
			throw new AnimalNotFoundException(qna_id + "가 없습니다.");
		}
		return review;
	}

	public List<Qna> findQnaCategoryByQnaType(String qnaType) {
		return qnaDAO.findQnaCategoryByQnaType(qnaType);
	}
	//댓글 입력
	public int insertComment(Qna_Comment comment) {
			return commentDAO.insertComment(comment);
	}
	//댓글 삭제
	public int deleteAllComments() {		
		return commentDAO.deleteAllComments();
	}	
	//대댓글입력
	public int insertReply(Qna_Reply reply) {
		//int reply_id, int comment_no, String reply_content, Date reg_Date
		return commentDAO.insertReply(reply);
	}
	//대댓글 전체 삭제
	public int deleteAllReplies() {
		return commentDAO.deleteAllReplies();
	}
	//comment번호로 선택
	public Qna_Comment selectCommentByPrimaryKey(int comment_no) {		
		return commentDAO.selectCommentByPrimaryKey(comment_no);		
	}
	//comment, qna, reply모두 선택
	public Qna_Comment selectCommentByPrimaryKeyCollection(int comment_no) {
		return commentDAO.selectCommentByPrimaryKeyCollection(comment_no);	
	}
	//qna_id로 comment선택하기
	public List<Qna_Comment> selectCommentByCondition(int qna_id) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("qna_id", qna_id);
		return commentDAO.selectCommentByCondition(condition);
	}

}

