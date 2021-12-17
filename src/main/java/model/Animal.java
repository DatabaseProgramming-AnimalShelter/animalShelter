package model;

public class Animal {
	private int animal_id;
	private int category_id;
	private int age;
	private String location;
	private int matched;
	private String image;
	private String gender;
	private String weight;
	private String etc;
	private String species;
	private String animal_type;

	
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getAnimal_type() {
		return animal_type;
	}

	public void setAnimal_type(String animal_type) {
		this.animal_type = animal_type;
	}

	
	public Animal(int animal_id, int category_id, int age, String location, int matched, String image, String gender,
			String weight, String etc, String species, String animal_type) {
		super();
		this.animal_id = animal_id;
		this.category_id = category_id;
		this.age = age;
		this.location = location;
		this.matched = matched;
		this.image = image;
		this.gender = gender;
		this.weight = weight;
		this.etc = etc;
		this.species = species;
		this.animal_type = animal_type;
	}
	public Animal(int animal_id, int category_id, int age, String location, int matched, String image, String gender,
			String weight, String etc) {
		super();
		this.animal_id = animal_id;
		this.category_id = category_id;
		this.age = age;
		this.location = location;
		this.matched = matched;
		this.image = image;
		this.gender = gender;
		this.weight = weight;
		this.etc = etc;
	}
	public Animal(int animal_id) {
		super();
		this.animal_id = animal_id;
	}
	public Animal(int animal_id, int category_id, int age, String location, int matched, String image) {
		super();
		this.animal_id = animal_id;
		this.category_id = category_id;
		this.age = age;
		this.location = location;
		this.matched = matched;
		this.image = image;
	}
	//create animal
	public Animal( int category_id, int age, String location,  String image, String gender,
			String weight, String etc, int matched) {
		super();
		this.category_id = category_id;
		this.age = age;
		this.location = location;
		this.image = image;
		this.gender = gender;
		this.weight = weight;
		this.etc = etc;
		this.matched = matched;
	}
	public Animal(int animal_id, int category_id, int age, String location, int matched, String image, String species,
			String animal_type) {
		super();
		this.animal_id = animal_id;
		this.category_id = category_id;
		this.age = age;
		this.location = location;
		this.matched = matched;
		this.image = image;
		this.species = species;
		this.animal_type = animal_type;
	}
	public Animal(int animal_id, int category_id, int age, String location,  String image, String gender) {
		super();
		this.animal_id = animal_id;
		this.category_id = category_id;
		this.age = age;
		this.location = location;
		this.image = image;
		this.gender = gender;
	}
	public Animal(int animal_id, int category_id, int age, String location,  String image, String gender,String etc) {
		super();
		this.animal_id = animal_id;
		this.category_id = category_id;
		this.age = age;
		this.location = location;
		this.image = image;
		this.gender = gender;
		this.etc = etc;
	}
	public Animal(int animal_id, int category_id, String species, int age, String location, String image, String gender) {
		super();
		this.animal_id = animal_id;
		this.category_id = category_id;
		this.species=species;
		this.age = age;
		this.location = location;
		this.image = image;
		this.gender = gender;
	}
	//search
	public Animal(String animal_type,int category_id, int matched,String location) {
		this.animal_type = animal_type;
		this.category_id = category_id;
		this.matched=matched;
		this.location = location;
	}
	public int getAnimal_id() {
		return animal_id;
	}
	public void setAnimal_id(int animal_id) {
		this.animal_id = animal_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getMatched() {
		return matched;
	}
	public void setMatched(int matched) {
		this.matched = matched;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public int getAnimal_matched() {
		return matched;
	}
	public void setAnimal_matched(int matched) {
		this.matched = matched;
	}
}