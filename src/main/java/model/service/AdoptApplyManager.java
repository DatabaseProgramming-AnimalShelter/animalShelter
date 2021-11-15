package model.service;

import java.sql.SQLException;
import java.util.List;

//import model.AdoptApply;
import model.Animal;
import model.dao.AdoptApplyDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
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
			throw new UserNotFoundException(user_id + "는 존재하지 않는 아이디입니다.");
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
			throw new AnimalNotFoundException(animal_id + "는 존재하지 않는 동물입니다.");
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
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
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
