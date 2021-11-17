package model.service;

import java.sql.SQLException;
import java.util.List;

//import model.AdoptApply;
import model.Adopter;
import model.dao.AdopterDAO;

/**
 * 占쏙옙占쏙옙占� 占쏙옙占쏙옙 API占쏙옙 占쏙옙占쏙옙求占� 占쏙옙占쏙옙占쌘듸옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싹곤옙 占실댐옙 클占쏙옙占쏙옙.
 * UserDAO占쏙옙 占싱울옙占싹울옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌜억옙占쏙옙 占쏙옙占쏙옙占싹듸옙占쏙옙 占싹몌옙,
 * 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙 占쏙옙占쏙옙占싶듸옙占쏙옙 占싱울옙占싹울옙 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占쏙옙占쏙옙 占싼댐옙.
 * 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙荑∽옙占� 占쏙옙占쏙옙占싹쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹댐옙 클占쏙옙占쏙옙占쏙옙 
 * 占쏙옙占쏙옙占쏙옙 占쏙옙 占쏙옙 占쌍댐옙.
 */
public class AdopterManager {
	private static AdopterManager userMan = new AdopterManager();
	private AdopterDAO adopterDAO;
	//private AnimalDAO commDAO;

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
			throw new ExistingUserException(user.getUser_id() + "占쏙옙 占쏙옙占쏙옙占싹댐옙 占쏙옙占싱듸옙占쌉니댐옙.");
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
		
		if (user == null) {
			throw new UserNotFoundException(user_id );
		}		
		return user;
	}

//	public List<User> findUserList() throws SQLException {
//			return userDAO.findUserList();
//	}
//	
//	public List<User> findUserList(int currentPage, int countPerPage)
//		throws SQLException {
//		return userDAO.findUserList(currentPage, countPerPage);
//	}

	public boolean login(String user_id, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		Adopter user = findUser(user_id);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("PasswordMismatchException");
		}
		return true;
	}

	/*
	 * public List<User> makeFriends(String userId) throws Exception { return
	 * userAanlysis.recommendFriends(userId); }
	 * 
	 * public Community createCommunity(Community comm) throws SQLException { return
	 * commDAO.create(comm); }
	 * 
	 * public int updateCommunity(Community comm) throws SQLException { return
	 * commDAO.update(comm); }
	 * 
	 * public Community findCommunity(int commId) throws SQLException { Community
	 * comm = commDAO.findCommunity(commId);
	 * 
	 * List<User> memberList = userDAO.findUsersInCommunity(commId);
	 * comm.setMemberList(memberList);
	 * 
	 * int numOfMembers = userDAO.getNumberOfUsersInCommunity(commId);
	 * comm.setNumOfMembers(numOfMembers); return comm; }
	 * 
	 * public List<Community> findCommunityList() throws SQLException { return
	 * commDAO.findCommunityList(); }
	 * 
	 * public List<User> findCommunityMembers(int commId) throws SQLException {
	 * return userDAO.findUsersInCommunity(commId); }
	 */

	
}
