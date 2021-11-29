
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Adopter;

import model.Animal;
import model.service.AnimalManager;


public class AnimalDAO {
   private static JDBCUtil jdbcUtil = null;
   
   public AnimalDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil  뜝 룞 삕泥   뜝 룞 삕 뜝 룞 삕
   }

   public int create(Animal animal) throws SQLException {
      String sql = "INSERT INTO Animal VALUES (animal_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?) " ;      

      Object[] param = new Object[] {
            
            animal.getCategory_id(),
            animal.getAge(),
            animal.getLocation(),
            animal.getImage(),
            animal.getGender(),
            animal.getWeight(),
            animal.getEtc(),
            animal.getAnimal_matched()
            };            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil  뜝 룞 삕 insert 뜝 룞 삕 뜝 룞 삕  뜝 떊怨ㅼ삕  뜝 룞 삕 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕
                  
      String key[] = {"animal_id"};   // PK  뜝 떆琉꾩삕 뜝 룞 삕  뜝 떛紐뚯삕     
      int generatedKey = 0;
      try {    
         jdbcUtil.executeUpdate(key);  // insert  뜝 룞 삕  뜝 룞 삕 뜝 룞 삕
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if(rs.next()) {//함수를 새로 쓰는게 낫나?
               generatedKey = rs.getInt(1);   // 占쏙옙占쏙옙占쏙옙 PK 占쏙옙
               animal.setAnimal_id(generatedKey);    // id占십드에 占쏙옙占쏙옙  
            }
            return generatedKey;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   // resource  뜝 룞 삕 솚
      }      
      return 0;         
   }
 
   public int update(Animal animal) throws SQLException {
      String sql = "UPDATE Animal "
               + "SET age=?, location=?, image=?, gender=?, weight=?, etc=?, animal_matched=?  "
               + "WHERE animal_id=?";
      Object[] param = new Object[] {animal.getAge(), animal.getLocation(),
            animal.getImage(), animal.getGender(), animal.getWeight(),
            animal.getEtc(), animal.getAnimal_matched()};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 뜝 룞 삕 update 뜝 룞 삕 뜝 룞 삕  뜝 떊怨ㅼ삕  뜝 룞 삕 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕
         
      try {            
         int result = jdbcUtil.executeUpdate();   // update  뜝 룞 삕  뜝 룞 삕 뜝 룞 삕
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource  뜝 룞 삕 솚
      }      
      return 0;
   }


   public int remove(int animal_id) throws SQLException {
      String sql = "DELETE FROM Animal WHERE animal_id=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});   // JDBCUtil 뜝 룞 삕 delete 뜝 룞 삕 뜝 룞 삕  뜝 떊怨ㅼ삕  뜝 룞 삕 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕

      try {            
         int result = jdbcUtil.executeUpdate();   // delete  뜝 룞 삕  뜝 룞 삕 뜝 룞 삕
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource  뜝 룞 삕 솚
      }      
      return 0;
   }

   //animal detail
   public static Animal findAnimal(int animal_id) throws SQLException {
        String sql = "SELECT a.animal_id, a.category_id, a.age, a.location, a.animal_matched, a.image,a.gender,a.weight,a.etc, c.species, c.animal_type "
              + " FROM Animal a JOIN Category c ON a.category_id = c.category_id " 
             + "WHERE animal_id=?";  
      jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});   // JDBCUtil 뜝 룞 삕 query 뜝 룞 삕 뜝 룞 삕  뜝 떊怨ㅼ삕  뜝 룞 삕 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕

      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query  뜝 룞 삕 뜝 룞 삕
         if (rs.next()) {                  //  뜝 떩 궪 삕  뜝 룞 삕 뜝 룞 삕  뜝 뙥怨ㅼ삕
            Animal animal = new Animal(      // User  뜝 룞 삕泥닷뜝 룞 삕  뜝 룞 삕 뜝 룞 삕 뜝 떦 슱 삕  뜝 떩 궪 삕  뜝 룞 삕 뜝 룞 삕 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕
               rs.getInt("animal_id"),
               rs.getInt("category_id"),
               rs.getInt("age"),
               rs.getString("location"),
               rs.getInt("animal_matched"),
               rs.getString("image"),
               rs.getString("gender"),
               rs.getString("weight"),
               rs.getString("etc"),
               rs.getString("species"),
               rs.getString("animal_type"));
            return animal;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource  뜝 룞 삕 솚
      }
      return null;
   }

   public List<Animal> findAnimalList() throws SQLException {
	   
        String sql = "SELECT a.animal_id, c.category_id,c.species, a.age, a.location, a.image ,a.gender "
                 + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
                 + "ORDER BY animal_id desc";        
                 
      jdbcUtil.setSqlAndParameters(sql,null);      
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();         
         List<Animal> animalList = new ArrayList<Animal>();   
         while (rs.next()) {
            Animal animal = new Animal(
                  rs.getInt("animal_id"), 
                  rs.getInt("category_id"),
                  rs.getInt("age"),
                  rs.getString("location"),
                  rs.getString("image"),
                  rs.getString("gender"));
            animalList.add(animal);            
         }      
         return animalList;               
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
   }
   public List<Animal> findAnimalList_heart(String user_id) throws SQLException {
       String sql = "SELECT a.animal_id, c.category_id,c.species, a.age, a.location, a.image ,a.gender,a_heart.a_heart_id  "
                + "FROM animal a JOIN Category c ON a.category_id = c.category_id ,a_heart  " 
    		    + "WHERE a_heart.animal_id = a.animal_id and a_heart.user_id='?' "
                + "ORDER BY animal_id desc";       
     System.out.println("############"+user_id+"#################");
     jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});      
              
     try {
        ResultSet rs = jdbcUtil.executeQuery();         
        List<Animal> animalList = new ArrayList<Animal>();   
        while (rs.next()) {
           Animal animal = new Animal(
                 rs.getInt("animal_id"), 
                 rs.getInt("category_id"),
                 rs.getInt("age"),
                 rs.getString("location"),
                 rs.getString("image"),
                 rs.getString("gender"),
                 rs.getString("a_heart_id"));
           animalList.add(animal);            
        }      
        return animalList;               
        
     } catch (Exception ex) {
        ex.printStackTrace();
     } finally {
        jdbcUtil.close();      
     }
     return null;
  }
   public List<Animal> searchAnimalList(String animal_type,int category_id, int matched,String location) throws SQLException {
      String sql = null;
      Object[] param;
      System.out.println("animal_type"+animal_type);
      System.out.println("category_id"+category_id);
      System.out.println("matched"+matched);
      System.out.println("location"+location);
      
      if (animal_type.equals("none") && matched == -1){
           if (location.equals("all")){
           sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched  ,a.gender,a.etc "
                      + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
                      + "ORDER BY animal_id desc";   
             param = null;
            System.out.println("싹다전체1ddddd");
         }
           else{
           sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched  ,a.gender,a.etc "
                      + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
                      + "WHERE a.location=? "
                      + "ORDER BY animal_id desc";   
            param = new Object[] { location};
            // param = null;
            System.out.println("다른건 전체 지역만 검색");
         }
         }
         else if (animal_type.equals("none")) {
           if (location.equals("all")){
           sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched  ,a.gender,a.etc "
                     + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                     + "WHERE a.animal_matched=? "
                     + "ORDER BY a.animal_id desc"; 
            param = new Object[] { matched};
            System.out.println("입양여부만 검색");}
           else{
           sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched  ,a.gender,a.etc "
                     + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                     + "WHERE a.animal_matched=? and a.location=? "
                     + "ORDER BY a.animal_id desc"; 
            param = new Object[] { matched,location};
            System.out.println("입양여부랑 지역검색");}
         }
         else {
           if (category_id == 100){
              if (matched == -1){
                 if (location.equals("all")){
                 sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                            + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                            + "WHERE a.category_id in (101,102,103,104,105,106,107,108,109,110,111,112,113) "
                            + "ORDER BY a.animal_id desc";    
                  param = null;
                  System.out.println("강아지에서 전체 검색");
                  
               }
                  else{
                 sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                            + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                            + "WHERE a.category_id in (101,102,103,104,105,106,107,108,109,110,111,112,113) and a.location=? "
                            + "ORDER BY a.animal_id desc";    
                  param = new Object[] { location};
                  System.out.println("강아지전체에서 지역검색");
                  
               }
                  
               }
              else{      if (location.equals("all")){
                 sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                            + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                            + "WHERE a.category_id in (101,102,103,104,105,106,107,108,109,110,111,112,113) and a.animal_matched=? "
                            + "ORDER BY a.animal_id desc";    
                  param = new Object[] { matched};
                  System.out.println("강아지전체에서 입양여부검색");}
                 else {
                 sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                            + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                            + "WHERE a.category_id in (101,102,103,104,105,106,107,108,109,110,111,112,113) and a.location=? and a.animal_matched=? "
                            + "ORDER BY a.animal_id desc";    
                  param = new Object[] { location, matched};
                  System.out.println("강아지 전체에서 지역이랑 입양여부검색");}
                  
               }
              
           }
           else if (category_id == 200 ){
             
              if (matched == -1){
             if (location.equals("all")){
                sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                          + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                          + "WHERE a.category_id in (201,202,203,204,205,206,207) "
                          + "ORDER BY a.animal_id desc";    
                param = null;
                System.out.println("고양이전체에서 지역검색");}
               else{
                sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                          + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                          + "WHERE a.category_id in (201,202,203,204,205,206,207) and a.location=?"
                          + "ORDER BY a.animal_id desc";    
                param = new Object[] { location};
                System.out.println("c고양이전체에서 지역검색");}

               }
              else{
               if (location.equals("all")){
                sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                          + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                          + "WHERE a.category_id in (201,202,203,204,205,206,207)  and a.animal_matched=? "
                          + "ORDER BY a.animal_id desc";    
                param = new Object[] {  matched};
                System.out.println("c고양이전체에서 입양여부검색");}
                else{
                 sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                           + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                           + "WHERE a.category_id in (201,202,203,204,205,206,207) and a.location=? and a.animal_matched=? "
                           + "ORDER BY a.animal_id desc";    
                 param = new Object[] { location, matched};
                 System.out.println("ca고양이전체에서 지역이랑 입양여부검색");}
               }
           }
           else if (matched == -1){
             if (location.equals("all")){
               sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                         + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                         + "WHERE a.category_id=? "
                         + "ORDER BY a.animal_id desc";    
               param = new Object[] { category_id};
               System.out.println("입양안된애들중에서 카테고리검색");}
             else{
               sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
                         + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                         + "WHERE a.category_id=? and a.location=? "
                         + "ORDER BY a.animal_id desc";    
               param = new Object[] { category_id,location};
               System.out.println("입양안된애들중에서 카테고리랑 지역검색");}
           }
           else {
             sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender,a.etc "
                        + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
                        + "WHERE a.category_id=? and a.animal_matched=? and a.location=? "
                        + "ORDER BY a.animal_id desc";    
              param = new Object[] { category_id,matched,location};
              System.out.println("모두 검색필드 입력됨");
           }
         }
      
        jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil  뜝 룞 삕 insert 뜝 룞 삕 뜝 룞 삕  뜝 떊怨ㅼ삕  뜝 룞 삕 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕
        
      try {
         ResultSet rs = jdbcUtil.executeQuery();         // query  뜝 룞 삕 뜝 룞 삕         
         List<Animal> animalList = new ArrayList<Animal>();   // Community 뜝 룞 삕 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕 듃  뜝 룞 삕 뜝 룞 삕
         while (rs.next()) {
            Animal animal = new Animal(
                  rs.getInt("animal_id"), 
                  rs.getInt("category_id"),
                  rs.getInt("age"),
                  rs.getString("location"),
                  rs.getString("image"),
                  rs.getString("gender"),
                  rs.getString("etc"));
            animalList.add(animal);            // List 뜝 룞 삕 Community  뜝 룞 삕泥   뜝 룞 삕 뜝 룞 삕
         }      
         return animalList;               
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource  뜝 룞 삕 솚
      }
      return null;
   }
   

   
   
   
   
   
   
}