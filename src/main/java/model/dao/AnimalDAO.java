package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Adopter;
import model.Animal;
import model.service.AnimalManager;

/**
 * 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙 占쌜억옙占쏙옙 占쏙옙占쏙옙占싹댐옙 DAO 클占쏙옙占쏙옙
 * Community 占쏙옙占싱븝옙占쏙옙 커占승댐옙티 占쏙옙占쏙옙占쏙옙 占쌩곤옙, 占쏙옙占쏙옙, 占쏙옙占쏙옙, 占싯삼옙 占쏙옙占쏙옙 
 */
public class AnimalDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	}
		
	/**
	 * 커占승댐옙티 占쏙옙占싱븝옙 占쏙옙占싸울옙 占쏙옙 占쏙옙占쏙옙 (PK 占쏙옙占쏙옙 Sequence占쏙옙 占싱울옙占싹울옙 占쌘듸옙 占쏙옙占쏙옙)
	 */
	public int create(Animal animal) throws SQLException {
		String sql = "INSERT INTO Animal VALUES (apply_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {
				
				animal.getCategory_id(),
				animal.getAge(),
				animal.getLocation(),
				animal.getImage(),
				animal.getGender(),
				animal.getWeight(),
				animal.getEtc(),
				animal.getAnimal_matched()
				};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쏙옙 insert占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
						
		String key[] = {"animal_id"};	// PK 占시뤄옙占쏙옙 占싱몌옙     
		int generatedKey = 0;
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert 占쏙옙 占쏙옙占쏙옙
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		generatedKey = rs.getInt(1);   // ������ PK ��
		   		animal.setAnimal_id(generatedKey); 	// id�ʵ忡 ����  
		   	}
		   	return generatedKey;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;			
	}

	/**
	 * 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	 */
	public int update(Animal animal) throws SQLException {
		String sql = "UPDATE Animal "
					+ "SET age=?, location=?, image=?, gender=?, weight=?, etc=?, animal_matched=?  "
					+ "WHERE animal_id=?";
		Object[] param = new Object[] {animal.getAge(), animal.getLocation(),
				animal.getImage(), animal.getGender(), animal.getWeight(),
				animal.getEtc(), animal.getAnimal_matched()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쏙옙 update占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
	}

	
	/**
	 * 占쌍억옙占쏙옙 ID占쏙옙 占쌔댐옙占싹댐옙 커占승댐옙티 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙.
	 */
	public int remove(int animal_id) throws SQLException {
		String sql = "DELETE FROM Animal WHERE animal_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil占쏙옙 delete占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 占쏙옙환
		}		
		return 0;
	}

	//animal detail
	public Animal findAnimal(int animal_id) throws SQLException {
        String sql = "SELECT a.animal_id, a.category_id, a.age, a.location, a.animal_matched, a.image,a.gender,a.weight,a.etc, c.species, c.animal_type "
     		   + " FROM Animal a JOIN Category c ON a.category_id = c.category_id " 
     		  + "WHERE animal_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쏙옙占쏙옙
			if (rs.next()) {						// 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
				Animal animal = new Animal(		// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占싻삼옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
					rs.getInt("animal_id"),
					rs.getInt("category_id"),
					rs.getInt("age"),
					rs.getString("location"),
					rs.getInt("animal_matched"),
					rs.getString("image"),
					rs.getString("gender"),
					rs.getString("weight"),
					rs.getString("etc"),
					rs.getString("species"),
					rs.getString("animal_type"));
				return animal;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	/**
	 * 占쏙옙체 커占승댐옙티 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환
	 */
	public List<Animal> findAnimalList() throws SQLException {
        String sql = "SELECT a.animal_id, c.category_id,c.species, a.age, a.location, a.image "
        		   + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
        		   + "ORDER BY animal_id";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쏙옙占쏙옙			
			List<Animal> animalList = new ArrayList<Animal>();	// Community占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getString("image"));

				animalList.add(animal);				// List占쏙옙 Community 占쏙옙체 占쏙옙占쏙옙
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	
	public List<Animal> searchAnimalList(String animal_type,int category_id, int matched) throws SQLException {
		String sql = null;
		Object[] param;
		System.out.println("animal_type"+animal_type);
		System.out.println("category_id"+category_id);
		System.out.println("matched"+matched);
		if (animal_type.equals("none") && matched == -1){
			sql = "SELECT a.animal_id, c.category_id,c.species, a.age, a.location, a.image "
	        		   + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
	        		   + "ORDER BY animal_id";   
			 param = null;
			 System.out.println("1ddddd");
		}
		else if (animal_type.equals("none")) {
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched "
		     		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
		     		   + "WHERE a.animal_matched=? "
		     		   + "ORDER BY a.animal_id"; 
			 param = new Object[] { matched};
			 System.out.println("2ddddd");
		}else if (matched == -1){
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched "
	        		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
	        		   + "WHERE a.category_id=?  "
	        		   + "ORDER BY a.animal_id";    
			 param = new Object[] { category_id};
			 System.out.println("3ddddd");
		}
		else {
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched "
	        		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
	        		   + "WHERE a.category_id=? and a.animal_matched=? "
	        		   + "ORDER BY a.animal_id";    
			 param = new Object[] { category_id,matched};
			 System.out.println("4ddddd");
		}
        jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쏙옙 insert占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙
        
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쏙옙占쏙옙			
			List<Animal> animalList = new ArrayList<Animal>();	// Community占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getString("image"));

				animalList.add(animal);				// List占쏙옙 Community 占쏙옙체 占쏙옙占쏙옙
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 占쏙옙환
		}
		return null;
	}
	

}
