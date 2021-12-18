package model.service;

import java.sql.SQLException;
import model.Adopter;
import model.dao.AdopterDAO;

public class AdopterManager {
   private static AdopterManager userMan = new AdopterManager();
   private AdopterDAO adopterDAO;

   public AdopterDAO getAdopterDAO() {
      return this.adopterDAO;
   }
   
   private AdopterManager() {
      try {
         adopterDAO = new AdopterDAO();
         //commDAO = new AnimalDAO();
      } catch (Exception e) {
         e.printStackTrace();
      }         
   }
   
   public static AdopterManager getInstance() {
      System.out.println("----------------------userMan:--------------------"+userMan);
      return userMan;
   }
   
   public int create(Adopter user) throws SQLException, ExistingUserException {
      if (adopterDAO.existingUser(user.getUser_id()) == true) {
         throw new ExistingUserException(user.getUser_id() + "is already exist");
      }
      return adopterDAO.create(user);
   }

   public int update(Adopter user) throws SQLException, UserNotFoundException {

      return adopterDAO.update(user);
   }   

   public int remove(String user_id) throws SQLException, UserNotFoundException {

      return adopterDAO.remove(user_id);
   }

   public Adopter findUser(String user_id)
      throws SQLException, UserNotFoundException {
      Adopter user = adopterDAO.findUser(user_id);
      System.out.println(user);
      if (user == null) {
         throw new UserNotFoundException(user_id + " is not exist");
      }      
      return user;
   }

   public boolean login(String user_id, String password)
      throws SQLException, UserNotFoundException, PasswordMismatchException {
      Adopter user = findUser(user_id);

      if (!user.matchPassword(password)) {
         throw new PasswordMismatchException("PasswordMismatchException");
      }
      return true;
   }
   
}