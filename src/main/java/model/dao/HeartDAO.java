package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Heart;
import model.service.AnimalManager;
public class HeartDAO {
	  private static JDBCUtil jdbcUtil = null;
	   
	   public HeartDAO() {         
	      jdbcUtil = new JDBCUtil();   // JDBCUtil  뜝 룞 삕泥   뜝 룞 삕 뜝 룞 삕
	   }
	   public Heart create_heart(Heart heart) throws SQLException {
		      String sql = "INSERT INTO A_Heart VALUES (a_heart_id_seq.nextval, ?, ?) " ;      
		      Object[] param = new Object[] {
		    		  heart.getAnimal_id(),
		    		  heart.getUser_id(),
		            };      
		      System.out.println("----여기까진왔어??"+ heart.getAnimal_id());
		      jdbcUtil.setSqlAndParameters(sql, param); 
		                  
		      String key[] = {"a_heart_id"};  
		      int generatedKey = 0;
		      try {    
		         jdbcUtil.executeUpdate(key);  
		            ResultSet rs = jdbcUtil.getGeneratedKeys();
		            if(rs.next()) {//함수를 새로 쓰는게 낫나?
		               generatedKey = rs.getInt(1);   
		               heart.setA_heart_id(generatedKey);   
		            }
		            return heart;
		      } catch (Exception ex) {
		         jdbcUtil.rollback();
		         ex.printStackTrace();
		      } finally {      
		         jdbcUtil.commit();
		         jdbcUtil.close(); 
		      }      
		      return null;         
		   }

}
