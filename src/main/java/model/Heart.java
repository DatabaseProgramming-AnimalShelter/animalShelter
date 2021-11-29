package model;

public class Heart {
	private int a_heart_id;
	private int  animal_id ;
	private String user_id;
	//create heart
	public Heart(int animal_id, String user_id) {
		super();
		this.animal_id = animal_id;
		this.user_id = user_id;
	}
	public int getA_heart_id() {
		return a_heart_id;
	}
	public void setA_heart_id(int a_heart_id) {
		this.a_heart_id = a_heart_id;
	}
	public int getAnimal_id() {
		return animal_id;
	}
	public void setAnimal_id(int animal_id) {
		this.animal_id = animal_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id( String user_id) {
		this.user_id = user_id;
	}
	
	
}
