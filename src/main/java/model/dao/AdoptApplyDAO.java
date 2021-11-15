package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AdoptApply;
import model.Community;
/**
 * 
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * Community ���̺��� Ŀ�´�Ƽ ������ �߰�, ����, ����, �˻� ���� 
 */
public class AdoptApplyDAO {

	private JDBCUtil jdbcUtil = null;

	public AdoptApplyDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	//���� DB����� CREATE SEQUENCE apply_id_seq;
	public AdoptApply create(AdoptApply adopt) throws SQLException {
		String sql = "INSERT INTO AdoptApply VALUES (apply_id_seq.nextval, ?, ?, ?, ?, SYSDATE, ?, ?)";		
		Object[] param = new Object[] {adopt.getContent(), 
				adopt.getMatched(), adopt.getUser_id(),adopt.getAnimal_id(), 
//				adopt.getApply_date(), : sysdate??
				adopt.getLiving_environment(),adopt.getHave_pets()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = {"apply_id"};	// PK �÷��� �̸�     
		try {    
			jdbcUtil.executeUpdate(key);  // insert �� ���� //??
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		adopt.setApply_id(generatedKey); 	// id�ʵ忡 ����  
		   	}
		   	return adopt;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return null;			
	}
}
