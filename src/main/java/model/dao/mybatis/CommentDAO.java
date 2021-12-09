package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Qna_Comment;
import model.Qna_Reply;
import model.dao.mybatis.mapper.*;

public class CommentDAO {

	private SqlSessionFactory sqlSessionFactory;

	public CommentDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public Qna_Comment selectCommentByPrimaryKey(int comment_no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(Qna_CommentMapper.class).selectCommentByPrimaryKey(comment_no);			
		} finally {
			sqlSession.close();
		}
	}

	public List<Qna_Comment> selectCommentByCondition(Map<String, Object> condition) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(Qna_CommentMapper.class).selectCommentByCondition(condition);			
		} finally {
			sqlSession.close();
		}
	}
	
	public int insertComment(Qna_Comment comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(Qna_CommentMapper.class).insertComment(comment);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int updateComment(Qna_Comment comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(Qna_CommentMapper.class).updateComment(comment);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int deleteComment(int comment_no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(Qna_CommentMapper.class).deleteComment(comment_no);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
	}
	
	public int deleteAllComments() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(Qna_CommentMapper.class).deleteAllComments();
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;		
		} finally {
			sqlSession.close();
		}
	}

	public Qna_Comment selectCommentByPrimaryKeyCollection(int comment_no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(Qna_CommentMapper.class).selectCommentByPrimaryKeyCollection(comment_no);			
		} finally {
			sqlSession.close();
		}
	}

	public int insertReply(Qna_Reply reply) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(Qna_CommentMapper.class).insertReply(reply);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int deleteAllReplies() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(Qna_CommentMapper.class).deleteAllReplies();
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;		
		} finally {
			sqlSession.close();
		}
	}
}
