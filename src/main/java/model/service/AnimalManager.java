package model.service;

import java.sql.SQLException;
import java.util.List;

//import model.AdoptApply;
import model.Animal;
import model.dao.mybatis.AnimalDAO;

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

	public Animal create(Animal animal) throws SQLException, ExistingUserException {
		System.out.println("animal,,,도착,,,,핸나요...? "+animal);
		return animalDAO.create(animal);
	}

	public int update(Animal animal) throws SQLException, UserNotFoundException {
		return animalDAO.update(animal);
	}
	public int remove(int animal_id) throws SQLException, UserNotFoundException {
		return animalDAO.remove(animal_id);
	}


	public List<Animal> searchAnimalList( Animal animal) throws SQLException {
		return animalDAO.searchAnimalList(animal);
	}
	
	public Animal findAnimal(int animal_id) throws SQLException, AnimalNotFoundException{
		// TODO Auto-generated method stub
		Animal animal = animalDAO.findAnimal(animal_id);

		if (animal == null) {
			throw new AnimalNotFoundException(animal_id + "  not found Animal");
		}

		return animal;
	}
	
	public List<Animal> findAnimalList() throws SQLException {
		return animalDAO.findAnimalList();
	}



}
