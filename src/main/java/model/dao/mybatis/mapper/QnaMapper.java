package model.dao.mybatis.mapper;

import java.util.List;
import model.Qna;

public interface QnaMapper {

	public int create(Qna qna);
	
	public int update(Qna qna);
	
	public int remove(int qnaId);
	
	public int findQnaCategoryId(String qnaType);
	
	public Qna findQnaByPrimaryKey(int qnaId);
	
	public List<Qna> selectAllQnaList();
	
	public List<Qna> selectMyQnaList(String user_id);
	
	public String checkQnaPwd(int qna_id);
}
