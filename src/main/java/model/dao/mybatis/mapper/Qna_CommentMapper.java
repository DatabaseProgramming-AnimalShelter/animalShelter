package model.dao.mybatis.mapper;

import model.Qna_Comment;
public interface Qna_CommentMapper {
	
	Qna_Comment selectComment(int qna_id);
	
	int insertComment(Qna_Comment comment);   
 
	int updateComment(Qna_Comment comment);
	
	int deleteComment(int comment_no);
}
