package model.service;

import java.sql.SQLException;
import java.util.List;

//import model.AdoptApply;
import model.Animal;
import model.dao.AnimalDAO;

public class AnimalManager {
	private static AnimalManager animalMan = new AnimalManager();
//	private AdopterDAO adopterDAO;
	private AnimalDAO animalDAO;

	public AnimalDAO getAnimalDAO() {
		return this.animalDAO;
	}

	private AnimalManager() {
		try {
			animalDAO = new AnimalDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static AnimalManager getInstance() {
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

	/*
	 * public Adopter findUser(String user_id) throws SQLException,
	 * UserNotFoundException { Adopter user = adopterDAO.findUser(user_id);
	 * 
	 * if (user == null) { throw new UserNotFoundException(user_id +
	 * "占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占십댐옙 占쏙옙占싱듸옙占쌉니댐옙."); } return user; }
	 */

	public List<Animal> findAnimalList() throws SQLException {
		return animalDAO.findAnimalList();
	}
	public List<Animal> findAnimalList_heart(String user_id) throws SQLException {
		return animalDAO.findAnimalList_heart(user_id);
	}
	public List<Animal> searchAnimalList( String animal_type, int category_id, int matched,String location) throws SQLException {
		return animalDAO.searchAnimalList(animal_type, category_id, matched,location);
	}
	
	

	public Animal findAnimal(int animal_id) throws SQLException, AnimalNotFoundException {
		// TODO Auto-generated method stub
		Animal animal = animalDAO.findAnimal(animal_id);

		if (animal == null) {
			throw new AnimalNotFoundException(animal_id + "媛� 議댁옱�븯吏��븡�뒿�땲�떎.");
		}

		return animal;
	}

	

}
