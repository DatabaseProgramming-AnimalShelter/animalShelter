package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Review_Comment;

public class ReviewCommentDAO {
   private JDBCUtil jdbcUtil = null;
   
   public ReviewCommentDAO() {         
      jdbcUtil = new JDBCUtil();   
   }
      
   //create review
   public int create(Review_Comment review_comment) throws SQLException {
      String sql = "INSERT INTO Review_Comment "
            + "VALUES (comment_id_seq.nextval, ?, ?, SYSDATE, ?, ?)";      
      Object[] param = new Object[] {
            review_comment.getPost_id(),
            review_comment.getUser_id(),
            review_comment.getParent(),
            review_comment.getContent()
            };            
      jdbcUtil.setSqlAndParameters(sql, param);   
                  
      String key[] = {"comment_id"};   
      try {    
         int result = jdbcUtil.executeUpdate(key);  
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if(rs.next()) {
               int generatedKey = rs.getInt(1);  
               review_comment.setPost_id(generatedKey);    
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
   
   //delete review comment
   public int remove(int comment_id) throws SQLException {
      String sql = "DELETE FROM Review_Comment WHERE comment_id=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {comment_id});   

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
   
   public List<Review_Comment> findReviewCommentList(int post_id) throws SQLException {
        String sql = "SELECT comment_id, post_id, user_id, creationDate, parent, content "
                 + "FROM Review_Comment " 
                   + "WHERE post_id=?"
                 + "ORDER BY comment_id ";        

      jdbcUtil.setSqlAndParameters(sql, new Object[] {post_id});   
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();            
         List<Review_Comment> reviewCommentList = new ArrayList<Review_Comment>();   
         while (rs.next()) {
            String user_id = rs.getString("user_id");
              if(user_id == null) { 
                 user_id = "(Unknown)";
              }
            Review_Comment review_comment = new Review_Comment(
                  rs.getInt("comment_id"), 
                  rs.getInt("post_id"),
                  user_id,
                  rs.getDate("creationDate"),
                  rs.getInt("parent"),
                  rs.getString("content")
                  );
            reviewCommentList.add(review_comment);   
         }      
         return reviewCommentList;               
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      
      }
      return null;
   }
   


   

}