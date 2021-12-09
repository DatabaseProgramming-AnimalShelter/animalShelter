package model.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import model.Qna;

public interface QnaMapper {

	public int create(Qna qna);
	
	public int update(Qna qna);
	
	public int remove(int qnaId);
	
	public int findQnaCategoryId(String qnaType); //create할때
	
	public Qna findQnaByPrimaryKey(int qnaId);
	
	public List<Qna> selectAllQnaList();
	
	public List<Qna> findQnaCategoryByQnaType(String qnaType);
}