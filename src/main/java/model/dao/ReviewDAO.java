package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;

public class ReviewDAO {
   private JDBCUtil jdbcUtil = null;
   
   public ReviewDAO() {         
      jdbcUtil = new JDBCUtil();   
   }

   public int create(Review review) throws SQLException {
      String sql = "INSERT INTO Review "
            + "VALUES (post_id_seq.nextval, ?, ?, SYSDATE, ?, ?, ?)";      
      Object[] param = new Object[] {
            review.getTitle(),
            review.getContent(),
            review.getImage(),
            review.getWriter(),
            review.getAnimal_id()
            };            
      jdbcUtil.setSqlAndParameters(sql, param);   
                  
      String key[] = {"post_id"};
      try {    
         int result = jdbcUtil.executeUpdate(key);  
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if(rs.next()) {
               int generatedKey = rs.getInt(1); 
               review.setPost_id(generatedKey);    
            }
            return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   
      }      
      return 0;         
   }
   
   //update review
   public int update(Review review) throws SQLException {
      String sql = "UPDATE Review "
               + "SET  title=?, content=? "
               + "WHERE post_id=?";
      Object[] param = new Object[] {
            review.getTitle(), 
            review.getContent(),
            review.getPost_id()
            };            
      jdbcUtil.setSqlAndParameters(sql, param);   
         
      try {            
         int result = jdbcUtil.executeUpdate();   
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   
      }      
      return 0;
   }

   //delete review
   public int remove(int post_id) throws SQLException {
      String sql = "DELETE FROM Review WHERE post_id=? ";
      jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});   

      try {            
         int result = jdbcUtil.executeUpdate();   
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   
      }      
      return 0;
   }

   //review detail
   public Review findReview(int post_id) throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate, image "
              + "FROM Review " 
             + "WHERE post_id=?";  
      jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});   

      try {
         ResultSet rs = jdbcUtil.executeQuery();      
         if (rs.next()) {                  
            String writer = rs.getString("writer");
              if(writer == null) { 
                 writer = "(Unknown)";
              }
            Review review = new Review(      
               rs.getInt("post_id"),
               rs.getInt("animal_id"),
               writer,
               rs.getString("title"),
               rs.getString("content"),
               rs.getDate("creationDate"),
               rs.getString("image")
               );
            return review;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
   }
   
   
   public List<Review> findReviewList() throws SQLException {
        String sql = "SELECT post_id, animal_id, writer, title, content, creationDate "
                 + "FROM Review " 
                 + "ORDER BY post_id ";  
                 
      jdbcUtil.setSqlAndParameters(sql, null);      
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();                  
         List<Review> animalList = new ArrayList<Review>();   
         while (rs.next()) {
            String writer = rs.getString("writer");
              if(writer == null) { 
                 writer = "(Unknown)";
              }
            Review animal = new Review(
                  rs.getInt("post_id"), 
                  rs.getString("title"),
                  rs.getString("content"), 
                  rs.getDate("creationDate"),
                  writer,
                  rs.getInt("animal_id")
                  );
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
   
   public Review findUserReview(String user_id, int animal_id)throws SQLException {
        String sql = "SELECT post_id, title, content, creationDate, image "
               + "FROM Review " 
              + "WHERE writer=? and animal_id=? ";  
       jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id, animal_id});   

       try {
          ResultSet rs = jdbcUtil.executeQuery();      
          if (rs.next()) {                  
             Review review = new Review(      
                rs.getInt("post_id"),
                animal_id,
                user_id,
                rs.getString("title"),
                rs.getString("content"),
                rs.getDate("creationDate"),
                rs.getString("image")
                );
             return review;
          }
       } catch (Exception ex) {
          ex.printStackTrace();
       } finally {
          jdbcUtil.close();      
       }
       return null;
    }
   
   public List<Review> findUserReviewList(String user_id) throws SQLException {
        String sql = "SELECT post_id, animal_id, title, content, creationDate "
                 + "FROM Review "
                 + "WHERE writer=? "
                 + "ORDER BY post_id ";        
        
      jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});      
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();                  
         List<Review> animalList = new ArrayList<Review>();   
         while (rs.next()) {
            Review animal = new Review(
                  rs.getInt("post_id"), 
                  rs.getString("title"),
                  rs.getString("content"), 
                  rs.getDate("creationDate"),
                  user_id,
                  rs.getInt("animal_id")
            );
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
   
   public List<Review> findReviewCommentList(String user_id) throws SQLException {
        String sql = "SELECT r.post_id, r.title, r.writer, rc.creationDate "
                 + "FROM Review r JOIN Review_Comment rc ON r.post_id = rc.post_id "
                 + "WHERE rc.user_id=? "
                 + "ORDER BY rc.creationDate DESC ";        
      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {user_id});      
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();                  
         List<Review> commentList = new ArrayList<Review>();   
         while (rs.next()) {
            String writer = rs.getString("writer");
              if(writer == null) { 
                 writer = "(Unknown)";
              }
            Review animal = new Review(
                  rs.getInt("post_id"), 
                  writer,
                  rs.getString("title"),
                  rs.getDate("creationDate")
            );
            commentList.add(animal);            
         }      
         return commentList;               
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
   }


}