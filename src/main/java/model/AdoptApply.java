package model;

import java.time.LocalDate;
import java.util.Date;

public class AdoptApply {

   private int apply_id; //sequence 뜝 룞 삕  뜝 룞 삕 뜝 룞 삕 뜝 뙓 슱 삕 parameter 뜝 떕 꽔 뼱 룄?
   private String user_id;
   private int animal_id;
   private String content;
   private String living_environment;
   private String have_pets;
   private int apply_matched;
   private String apply_date;
   private String approval_date;
   private String image; 
   private String user_name;
   private String animal_type;
   private String species;
   
   
   public AdoptApply() { }
    
   

   public AdoptApply(String user_id, int animal_id,
         String image, String user_name, String animal_type, String species) {
      super();
      this.user_id = user_id;
      this.animal_id = animal_id;
      this.image = image;
      this.user_name = user_name;
      this.animal_type = animal_type;
      this.species = species;
   }

   public AdoptApply(int apply_id, String user_id, int animal_id, String content, String living_environment,
         String have_pets, int apply_matched, String apply_date, String image, String user_name, String animal_type,
         String species) {
      super();
      this.apply_id = apply_id;
      this.user_id = user_id;
      this.animal_id = animal_id;
      this.content = content;
      this.living_environment = living_environment;
      this.have_pets = have_pets;
      this.apply_matched = apply_matched;
      this.apply_date = apply_date;
      this.image = image;
      this.user_name = user_name;
      this.animal_type = animal_type;
      this.species = species;
   }

   public AdoptApply(int apply_id, String user_id, int animal_id, String content, String living_environment,
         String have_pets, int apply_matched, String apply_date, String approval_date, String image,
         String user_name, String animal_type, String species) {
      super();
      this.apply_id = apply_id;
      this.user_id = user_id;
      this.animal_id = animal_id;
      this.content = content;
      this.living_environment = living_environment;
      this.have_pets = have_pets;
      this.apply_matched = apply_matched;
      this.apply_date = apply_date;
      this.approval_date = approval_date;
      this.image = image;
      this.user_name = user_name;
      this.animal_type = animal_type;
      this.species = species;
   }
   public AdoptApply(int apply_id, String user_id, int animal_id, int apply_matched, String apply_date, 
         String user_name) {
      super();
      this.apply_id = apply_id;
      this.user_id = user_id;
      this.animal_id = animal_id;
      this.apply_matched = apply_matched;
      this.apply_date = apply_date;
      this.user_name = user_name;
   }
   public AdoptApply(String user_id, int animal_id, String content, String living_environment, String have_pets,
         String image, String user_name, String animal_type, String species) {
      super();
      this.user_id = user_id;
      this.animal_id = animal_id;
      this.content = content;
      this.living_environment = living_environment;
      this.have_pets = have_pets;
      this.image = image;
      this.user_name = user_name;
      this.animal_type = animal_type;
      this.species = species;
   }
   
   public int getApply_id() {
      return apply_id;
   }

   public void setApply_id(int apply_id) {
      this.apply_id = apply_id;
   }

   public String getUser_id() {
      return user_id;
   }

   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }

   public int getAnimal_id() {
      return animal_id;
   }

   public void setAnimal_id(int animal_id) {
      this.animal_id = animal_id;
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

   public int getApply_matched() {
      return apply_matched;
   }

   public void setApply_matched(int apply_matched) {
      this.apply_matched = apply_matched;
   }

   public String getApply_date() {
      return apply_date;
   }

   public void setApply_date(String apply_date) {
      this.apply_date = apply_date;
   }

   public String getApproval_date() {
      return approval_date;
   }

   public void setApproval_date(String approval_date) {
      this.approval_date = approval_date;
   }
   
   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getUser_name() {
      return user_name;
   }

   public void setUser_name(String user_name) {
      this.user_name = user_name;
   }

   public String getAnimal_type() {
      return animal_type;
   }

   public void setAnimal_type(String animal_type) {
      this.animal_type = animal_type;
   }

   public String getSpecies() {
      return species;
   }

   public void setSpecies(String species) {
      this.species = species;
   }
   
   

}