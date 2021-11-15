package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AdoptApply;
import model.Community;
/**
 * 
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Community 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class AdoptApplyDAO {

	private JDBCUtil jdbcUtil = null;

	public AdoptApplyDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	//먼저 DB설계시 CREATE SEQUENCE apply_id_seq;
	public AdoptApply create(AdoptApply adopt) throws SQLException {
		String sql = "INSERT INTO AdoptApply VALUES (apply_id_seq.nextval, ?, ?, ?, ?, SYSDATE, ?, ?)";		
		Object[] param = new Object[] {adopt.getContent(), 
				adopt.getMatched(), adopt.getUser_id(),adopt.getAnimal_id(), 
//				adopt.getApply_date(), : sysdate??
				adopt.getLiving_environment(),adopt.getHave_pets()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = {"apply_id"};	// PK 컬럼의 이름     
		try {    
			jdbcUtil.executeUpdate(key);  // insert 문 실행 //??
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		adopt.setApply_id(generatedKey); 	// id필드에 저장  
		   	}
		   	return adopt;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return null;			
	}
}
