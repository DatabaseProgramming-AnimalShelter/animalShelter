package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Adopter;
import model.Animal;
import model.service.AnimalManager;

/**
 * �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떢釉앹삕�뜝�떛�룞�삕 �뜝�뙗�뼲�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�뙋�삕 DAO �겢�뜝�룞�삕�뜝�룞�삕
 * Community �뜝�룞�삕�뜝�떛釉앹삕�뜝�룞�삕 而ㅵ뜝�듅�뙋�삕�떚 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�뙥怨ㅼ삕, �뜝�룞�삕�뜝�룞�삕, �뜝�룞�삕�뜝�룞�삕, �뜝�떙�궪�삕 �뜝�룞�삕�뜝�룞�삕 
 */
public class AnimalDAO {
	private static JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
	}

	public int create(Animal animal) throws SQLException {
		String sql = "INSERT INTO Animal VALUES (animal_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";		
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
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뜝�룞�삕 insert�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
						
		String key[] = {"animal_id"};	// PK �뜝�떆琉꾩삕�뜝�룞�삕 �뜝�떛紐뚯삕     
		int generatedKey = 0;
		try {    
			jdbcUtil.executeUpdate(key);  // insert �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		generatedKey = rs.getInt(1);   // 占쏙옙占쏙옙占쏙옙 PK 占쏙옙
		   		animal.setAnimal_id(generatedKey); 	// id占십드에 占쏙옙占쏙옙  
		   	}
		   	return generatedKey;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource �뜝�룞�삕�솚
		}		
		return 0;			
	}

	/**
	 * �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
	 */
	public int update(Animal animal) throws SQLException {
		String sql = "UPDATE Animal "
					+ "SET age=?, location=?, image=?, gender=?, weight=?, etc=?, animal_matched=?  "
					+ "WHERE animal_id=?";
		Object[] param = new Object[] {animal.getAge(), animal.getLocation(),
				animal.getImage(), animal.getGender(), animal.getWeight(),
				animal.getEtc(), animal.getAnimal_matched()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�뜝�룞�삕 update�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource �뜝�룞�삕�솚
		}		
		return 0;
	}

	
	/**
	 * �뜝�뙇�뼲�삕�뜝�룞�삕 ID�뜝�룞�삕 �뜝�뙏�뙋�삕�뜝�떦�뙋�삕 而ㅵ뜝�듅�뙋�삕�떚 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕.
	 */
	public int remove(int animal_id) throws SQLException {
		String sql = "DELETE FROM Animal WHERE animal_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil�뜝�룞�삕 delete�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource �뜝�룞�삕�솚
		}		
		return 0;
	}

	//animal detail
	public static Animal findAnimal(int animal_id) throws SQLException {
        String sql = "SELECT a.animal_id, a.category_id, a.age, a.location, a.animal_matched, a.image,a.gender,a.weight,a.etc, c.species, c.animal_type "
     		   + " FROM Animal a JOIN Category c ON a.category_id = c.category_id " 
     		  + "WHERE animal_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil�뜝�룞�삕 query�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �뜝�룞�삕�뜝�룞�삕
			if (rs.next()) {						// �뜝�떩�궪�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�뙥怨ㅼ삕
				Animal animal = new Animal(		// User �뜝�룞�삕泥닷뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�슱�삕 �뜝�떩�궪�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
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
			jdbcUtil.close();		// resource �뜝�룞�삕�솚
		}
		return null;
	}

	public List<Animal> findAnimalList() throws SQLException {
        String sql = "SELECT a.animal_id, c.category_id,c.species, a.age, a.location, a.image ,a.gender "
        		   + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
        		   + "ORDER BY animal_id desc";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�뜝�룞�삕 query�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �뜝�룞�삕�뜝�룞�삕			
			List<Animal> animalList = new ArrayList<Animal>();	// Community�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getString("image"),
						rs.getString("gender"));
				animalList.add(animal);				// List�뜝�룞�삕 Community �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뜝�룞�삕�솚
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
	        		   + "ORDER BY animal_id desc";   
			 param = null;
			 System.out.println("1ddddd");
		}
		else if (animal_type.equals("none")) {
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched  ,a.gender,a.etc "
		     		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
		     		   + "WHERE a.animal_matched=? "
		     		   + "ORDER BY a.animal_id desc"; 
			 param = new Object[] { matched};
			 System.out.println("2ddddd");
		}else if (matched == -1){
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender ,a.etc "
	        		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
					
	        		   
	        		   + "WHERE a.category_id=?  "
	        		   + "ORDER BY a.animal_id desc";    
			 param = new Object[] { category_id};
			 System.out.println("3ddddd");
		}
		else {
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched ,a.gender,a.etc "
	        		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
	        		   + "WHERE a.category_id=? and a.animal_matched=? "
	        		   + "ORDER BY a.animal_id desc";    
			 param = new Object[] { category_id,matched};
			 System.out.println("4ddddd");
		}
        jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뜝�룞�삕 insert�뜝�룞�삕�뜝�룞�삕 �뜝�떊怨ㅼ삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
        
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �뜝�룞�삕�뜝�룞�삕			
			List<Animal> animalList = new ArrayList<Animal>();	// Community�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�듃 �뜝�룞�삕�뜝�룞�삕
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getString("image"),
						rs.getString("gender"),
						rs.getString("etc"));
				animalList.add(animal);				// List�뜝�룞�삕 Community �뜝�룞�삕泥� �뜝�룞�삕�뜝�룞�삕
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource �뜝�룞�삕�솚
		}
		return null;
	}
	

}
