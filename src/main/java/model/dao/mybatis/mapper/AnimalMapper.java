package model.dao.mybatis.mapper;

import java.util.Map;
import java.util.List;
import model.Animal;

public interface AnimalMapper {

	public int insertAnimal(Animal animal);   
	
	public Animal findAnimal(int animal_id);
 
	public int deleteAnimal(int animal_id);
	
	public List<Animal> findAnimalList();
	
	public List<Animal> searchAnimalList(Map<String, String> paramString,Map<String, Integer> paramInt);
	
	public int updateAnimal(Animal animal);
	
	
/*
	
	public Animal selectAnimalWithMembers(int commId);

	public List<Animal> selectAllCommunities();*/
}
