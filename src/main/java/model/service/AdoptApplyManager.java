package model.service;

import java.sql.SQLException;
import java.util.List;

//import model.AdoptApply;
import model.Animal;
import model.dao.AdoptApplyDAO;

/**
 * ����� ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * UserDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
 */
public class AdoptApplyManager {
	private static AdoptApplyManager animalMan = new AdoptApplyManager();
//	private AdopterDAO adopterDAO;
	private AdoptApplyDAO ;

	public AnimalDAO getAnimalDAO() {
		return this.animalDAO;
	}
	
	private AdoptApplyManager() {
		try {
			animalDAO = new AnimalDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static AdoptApplyManager getInstance() {
		return animalMan;
	}
	
	public int create(Animal animal) throws SQLException, ExistingUserException {
		return animalDAO.create(animal);
	}

	public int update(Animal animal) throws SQLException, UserNotFoundException {
		return animalDAO.update(animal);
	}	

	public int remove(int animal_id) throws SQLException, UserNotFoundException {

		return animalDAO.remove(animal_id);
	}

	/*public Adopter findUser(String user_id)
		throws SQLException, UserNotFoundException {
		Adopter user = adopterDAO.findUser(user_id);
		
		if (user == null) {
			throw new UserNotFoundException(user_id + "�� �������� �ʴ� ���̵��Դϴ�.");
		}		
		return user;
	}*/

	public List<Animal> findAnimalList() throws SQLException {
			return animalDAO.findAnimalList();
	}
	
	public List<Animal> searchAnimalList(int category_id, String animal_type, int matched)
		throws SQLException {
		return animalDAO.searchAnimalList(category_id, animal_type, matched);
	}

	public Animal findAnimal(int animal_id) throws SQLException, AnimalNotFoundException {
		// TODO Auto-generated method stub
		Animal animal = animalDAO.findAnimal(animal_id);
		
		if(animal == null) {
			throw new AnimalNotFoundException(animal_id + "�� �������� �ʴ� �����Դϴ�.");
		}
		
		return animal;
	}
	
	// currentPage ??
//	public List<User> findUserList(int currentPage, int countPerPage)
//	throws SQLException {
//	return userDAO.findUserList(currentPage, countPerPage);
//}

	/*public boolean login(String user_id, String password)
		throws SQLException, UserNotFoundException, PasswordMismatchException {
		Adopter user = findUser(user_id);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}*/

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
