package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import model.AdoptApply;
import model.Adopter;
import model.Animal;

public class AdoptApplyDAO {

   private JDBCUtil jdbcUtil = null;
   AdoptApply adoptApply = new AdoptApply();
   
   public AdoptApplyDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 揶쏆빘猿  占쎄문占쎄쉐
   }
   // 솒 눘占  DB占쎄퐬 ④쑴 뻻 CREATE SEQUENCE apply_id_seq;
   /*public AdoptApply create(AdoptApply adopt) throws SQLException {
      String sql = "INSERT INTO AdoptApply VALUES (apply_id_seq.nextval, ?, ?, ?, ?, SYSDATE, ?, ?)";      
      Object[] param = new Object[] {adopt.getContent(), 
            adopt.getMatched(), adopt.getUser_id(),adopt.getAnimal_id(), 
//            adopt.getApply_date(), : sysdate??
            adopt.getLiving_environment(),adopt.getHave_pets()};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 占쎈퓠 insert 눧硫몃궢 筌띲끆而  癰귨옙占쎈땾 占쎄퐬占쎌젟
                  
      String key[] = {"apply_id"};   // PK  뚎됱쓥占쎌벥 占쎌뵠 뵳占      
      try {    
         jdbcUtil.executeUpdate(key);  // insert  눧占  占쎈뼄占쎈뻬 //??
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if(rs.next()) {
               int generatedKey = rs.getInt(1);   // 占쎄문占쎄쉐占쎈쭆 PK 揶쏉옙
               adopt.setApply_id(generatedKey);    // id占쎈툡占쎈굡占쎈퓠 占쏙옙占쎌삢  
            }
            return adopt;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 獄쏆꼹 넎
      }      
      return null;         
   }*/
   
   public int create(AdoptApply adoptApply) throws SQLException {
      /*
       * System.out.println("create0.%%%%%%%%%%%%%%%%%%%%"+adoptApply);
       * 
       * LocalDate now = LocalDate.now();
       * 
       * System.out.println("LocalDate.%%%%%%%%%%%%%%%%%%%%"+adoptApply);
       * 
       * 
       * SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
       * 
       * System.out.println("SimpleDate.%%%%%%%%%%%%%%%%%%%%"+adoptApply);
       * 
       * 
       * String formatedNow = fm.format(now);
       * 
       * System.out.println("formateN.%%%%%%%%%%%%%%%%%%%%"+adoptApply);
       * 
       * 
       * Date date = new Date(formatedNow);
       * 
       * System.out.println("create1.%%%%%%%%%%%%%%%%%%%%"+now +"::"+ date);
       */
      
      /*String sql = "INSERT INTO AdoptApply VALUES (apply_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?";            
      Object[] param = new Object[] {adoptApply.getUser_id(), 
            adoptApply.getAnimal_id(), adoptApply.getContent(), adoptApply.getLiving_environment(),
            adoptApply.getHave_pets(), 0, date, 0};*/ 
      
      String sql = "INSERT INTO AdoptApply VALUES (apply_id_seq.nextval, ?, ?, ?, ?, ?, ?, SYSDATE, null)"; 
      Object[] param = new Object[] { adoptApply.getUser_id(), adoptApply.getAnimal_id(), adoptApply.getContent(),
               adoptApply.getLiving_environment(), adoptApply.getHave_pets(),
               0};
      jdbcUtil.setSqlAndParameters(sql, param);
   
      String key[] = {"apply_id"};   
      int generatedKey = 0;
      try {    
         jdbcUtil.executeUpdate(key);  
            ResultSet rs = jdbcUtil.getGeneratedKeys();
            if(rs.next()) {
               generatedKey = rs.getInt(1);   
               adoptApply.setAnimal_id(generatedKey);    
            }
            return generatedKey;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   
      }      
      return 0;         
   }


   //  엯 뼇 떊泥   듅 씤 떆 matched = 1, approval_date 媛   꽔 뼱二쇨린
   public int approval(AdoptApply adoptApply) throws SQLException {
      /*
       * LocalDate now = LocalDate.now(); SimpleDateFormat fm = new
       * SimpleDateFormat("yyyy/MM/dd"); String formatedNow = fm.format(now); Date
       * date = new Date(formatedNow);
       */
      
      String sql = "UPDATE AdoptApply "
               + "SET  matched=?, approval_date=SYSDATE "
               + "WHERE apply_id=?";
      Object[] param = new Object[] {1, adoptApply.getApply_id()};
      
      //DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      try {      
         //Date date = new Date(df.parse(adoptApply.getApproval_date()).getTime());         
         jdbcUtil.setSqlAndParameters(sql, param);
         int result = jdbcUtil.executeUpdate();   // update  눧占  占쎈뼄占쎈뻬
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      }
      finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 獄쏆꼹 넎
      }      
      return 0;
   }
   
   //  엯 뼇 떊泥  嫄곗젅 떆 matched = -1, approval_date 媛   꽔 뼱二쇨린
   public int decline(AdoptApply adoptApply) throws SQLException {
      /*
       * LocalDate now = LocalDate.now(); SimpleDateFormat fm = new
       * SimpleDateFormat("yyyy/MM/dd"); String formatedNow = fm.format(now); Date
       * date = new Date(formatedNow);
       */
         
      String sql = "UPDATE AdoptApply "
                  + "SET  matched=?, approval_date=SYSDATE "
                  + "WHERE apply_id=?";
      Object[] param = new Object[] {-1, adoptApply.getApply_id()};
         
      //DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
         
      try {      
         //Date date = new Date(df.parse(adoptApply.getApproval_date()).getTime());         
         jdbcUtil.setSqlAndParameters(sql, param);
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

   // view 
   public AdoptApply findAdoptApply(int apply_id) throws SQLException {
        String sql = "SELECT adp.apply_id, adp.user_id, adp.animal_id, adp.content, adp.living_environment, have_pets, adp.apply_matched, adp.apply_date, a.image, u.user_name, c.animal_type, c.species  "
                 + "FROM AdoptApply adp JOIN User u ON adp.user_id = u.user_id and Animal a JOIN adp ON a.animal_id = adp.animal_id and a JOIN Category c ON a.category_id = c.category_id"
                 + "WHERE apply_matched=? ";              
      jdbcUtil.setSqlAndParameters(sql, new Object[] {0});   
      
      DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
      
      try {
         ResultSet rs = jdbcUtil.executeQuery();      
         if (rs.next()) {
            Date apply_date = new Date(rs.getDate("apply_date").getTime());
            String apply_dateString = df.format(apply_date);
            
            adoptApply = new AdoptApply(      
               rs.getInt("apply_id"),
               rs.getString("user_id"),
               rs.getInt("animal_id"),
               rs.getString("content"),
               rs.getString("living_environment"),
               rs.getString("have_pets"),
               rs.getInt("apply_matched"),
               apply_dateString,
               rs.getString("image"),
               rs.getString("user_name"),
               rs.getString("animal_type"),
               rs.getString("species")
            );
            return adoptApply;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource 獄쏆꼹 넎
      }
      return null;
   }
   
   //  떊泥   듅 씤/嫄곗젅  씠 썑 뿉  떊泥  寃곌낵 由ъ뒪 듃 李얠쓣  븣  궗 슜
      public AdoptApply findAdoptApplyResult(String apply_id) throws SQLException {
           String sql = "SELECT apply_id, user_id, animal_id, content, living_environment, have_pets, apply_matched, apply_date, approval_date, image, user_name, animal_type, species "
                    + "FROM AdoptApply a"
                    + "WHERE apply_matched IN (?,?) ";              
         jdbcUtil.setSqlAndParameters(sql, new Object[] {-1, 1});   // JDBCUtil占쎈퓠 query 눧硫몃궢 筌띲끆而  癰귨옙占쎈땾 占쎄퐬占쎌젟
         
         DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
         
         
         try {
            ResultSet rs = jdbcUtil.executeQuery();      // query 占쎈뼄占쎈뻬
            if (rs.next()) {
               Date apply_date = new Date(rs.getDate("apply_date").getTime());
               String apply_dateString = df.format(apply_date);
               
               Date approval_date = new Date(rs.getDate("approval_date").getTime());
               String approval_dateString = df.format(approval_date);
               
               adoptApply = new AdoptApply(      // User 揶쏆빘猿쒐몴占  占쎄문占쎄쉐占쎈릭占쎈연 占쎈린占쎄문 占쎌젟癰귣 占쏙옙 占쏙옙占쎌삢
                  rs.getInt("apply_id"),
                  rs.getString("user_id"),
                  rs.getInt("animal_id"),
                  rs.getString("content"),
                  rs.getString("living_environment"),
                  rs.getString("have_pets"),
                  rs.getInt("apply_matched"),
                  apply_dateString,
                  approval_dateString,
                  rs.getString("image"),
                  rs.getString("user_name"),
                  rs.getString("animal_type"),
                  rs.getString("species")   
                     );
               return adoptApply;
            }
         } catch (Exception ex) {
            ex.printStackTrace();
         } finally {
            jdbcUtil.close();      // resource 獄쏆꼹 넎
         }
         return null;
      }

   public List<AdoptApply> findAdoptApplyList() throws SQLException {
       String sql = "SELECT adp.apply_id, adp.user_id, adp.animal_id,adp.apply_matched, adp.apply_date "
              + "FROM AdoptApply adp "
                 + "ORDER BY apply_id";
       DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
         
               
      try {
         ResultSet rs = jdbcUtil.executeQuery();         // query 占쎈뼄占쎈뻬         
         List<AdoptApply> adoptApplyList = new ArrayList<AdoptApply>();   
         while (rs.next()) {
            Date apply_date = new Date(rs.getDate("apply_date").getTime());
            String apply_dateString = df.format(apply_date);
            AdoptApply adoptApply = new AdoptApply(         
               rs.getString("apply_id"),
               rs.getString("user_id"),
               rs.getString("animal_id"),
               rs.getInt("apply_matched"),
               apply_dateString;
            adoptApplyList.add(adoptApply);            
         }      
         return adoptApplyList;               
         
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();      // resource 獄쏆꼹 넎
      }
      return null;
   }

   

}

