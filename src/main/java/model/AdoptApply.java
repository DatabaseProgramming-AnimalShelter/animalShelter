package model;

import java.time.LocalDate;
/**
 * 입양신청서 관리를 위해 필요한 도메인 클래스
 * AdoptApply 테이블과 대응됨
 */
public class AdoptApply {

	private int apply_id; //sequence는 생성자에 parameter안넣어도?
	private int user_id;
	private int animal_id;
	private LocalDate apply_date;
	private int matched;
	private String content;
	private String living_environment;
	private String have_pets;
	private Animal animal;
	
	public AdoptApply() { }
	
	public AdoptApply(int user_id, int animal_id, LocalDate apply_date, int matched, String content,
			String living_environment, String have_pets) {
		super();
		this.user_id = user_id;
		this.animal_id = animal_id;
		this.apply_date = apply_date;
		this.matched = matched;
		this.content = content;
		this.living_environment = living_environment;
		this.have_pets = have_pets;
	}

	public int getApply_id() {
		return apply_id;
	}

	public void setApply_id(int apply_id) {
		this.apply_id = apply_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(int animal_id) {
		this.animal_id = animal_id;
	}

	public LocalDate getApply_date() {
		return apply_date;
	}

	public void setApply_date(LocalDate apply_date) {
		this.apply_date = apply_date;
	}

	public int getMatched() {
		return matched;
	}

	public void setMatched(int matched) {
		this.matched = matched;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLiving_environment() {
		return living_environment;
	}

	public void setLiving_environment(String living_environment) {
		this.living_environment = living_environment;
	}

	public String getHave_pets() {
		return have_pets;
	}

	public void setHave_pets(String have_pets) {
		this.have_pets = have_pets;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}


	
