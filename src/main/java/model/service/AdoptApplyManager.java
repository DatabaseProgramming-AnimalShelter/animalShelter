package model.service;

import java.sql.SQLException;
import java.util.List;

import model.AdoptApply;
import model.Adopter;
import model.Animal;
import model.dao.AdoptApplyDAO;
import model.dao.AdopterDAO;

public class AdoptApplyManager {
   private static AdoptApplyManager applyMan = new AdoptApplyManager();
   private AdoptApplyDAO adoptApplyDAO;
   //private AnimalDAO commDAO;

   public AdoptApplyDAO getAdopterDAO() {
      return this.adoptApplyDAO;
   }
   
   private AdoptApplyManager() {
      try {
         adoptApplyDAO = new AdoptApplyDAO();
         //commDAO = new AnimalDAO();
      } catch (Exception e) {
         e.printStackTrace();
      }         
   }
   
   public static AdoptApplyManager getInstance() {
      System.out.println("----------------------applyMan:--------------------"+applyMan);
      return applyMan;
   }
   
   public int create(AdoptApply adoptApply) throws SQLException {
      System.out.println("----------------------applyManCreate:--------------------"+applyMan);

      return adoptApplyDAO.create(adoptApply);
   }

   public int approval(AdoptApply adoptApply) throws SQLException {

      return adoptApplyDAO.approval(adoptApply);
   }
   
   public int decline(AdoptApply adoptApply) throws SQLException {
      return adoptApplyDAO.decline(adoptApply);
   }

   public AdoptApply findAdoptApply(int apply_id)
      throws SQLException {
      AdoptApply adoptApply = adoptApplyDAO.findAdoptApply(apply_id);
      
      return adoptApply;
   }
   
   public AdoptApply findAdoptApplyResult(String apply_id)
         throws SQLException {
         AdoptApply adoptApply = adoptApplyDAO.findAdoptApplyResult(apply_id);
         
         return adoptApply;
      }

   public List<AdoptApply> findAdoptApplyList() throws SQLException {
      return adoptApplyDAO.findAdoptApplyList();
   }

}