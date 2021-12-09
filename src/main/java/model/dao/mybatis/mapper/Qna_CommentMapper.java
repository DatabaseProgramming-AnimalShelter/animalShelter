package model.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import model.Qna_Comment;
import model.Qna_Reply;

public interface Qna_CommentMapper {
	Qna_Comment selectCommentByPrimaryKey(int comment_no);
	
	List<Qna_Comment> selectCommentByCondition(Map<String, Object> condition);
	
	int insertComment(Qna_Comment comment);   
 
	int updateComment(Qna_Comment comment);
	
	int deleteComment(int comment_no);

	int deleteAllComments();
	
	public Qna_Comment selectCommentByPrimaryKeyAssociation(int comment_no);
	
	public Qna_Comment selectCommentByPrimaryKeyCollection(int comment_no);
	
	public int insertReply(Qna_Reply reply);
	
	public int deleteAllReplies();
}
