package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Qna;
import model.dao.mybatis.mapper.*;

public class QnaDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public QnaDAO() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public int create(Qna qna) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(QnaMapper.class).create(qna);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int update(Qna qna) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(QnaMapper.class).update(qna);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int remove(int qnaId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(QnaMapper.class).remove(qnaId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
	}
	
	public int findQnaCategoryId(String qnaType) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(QnaMapper.class).findQnaCategoryId(qnaType);			
		} finally {
			sqlSession.close();
		}
	}
	
	public Qna findQnaByPrimaryKey (int qnaId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(QnaMapper.class).findQnaByPrimaryKey(qnaId);			
		} finally {
			sqlSession.close();
		}
	}

	public List<Qna> selectAllQnaList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(QnaMapper.class).selectAllQnaList();			
		} finally {
			sqlSession.close();
		}
	}
	
	public List<Qna> selectMyQnaList(String user_id){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(QnaMapper.class).selectMyQnaList(user_id);
		}finally {
			sqlSession.close();
		}
	}
	
	public String checkQnaPwd(int qna_id){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(QnaMapper.class).checkQnaPwd(qna_id);
		}finally {
			sqlSession.close();
		}
	}
	
}