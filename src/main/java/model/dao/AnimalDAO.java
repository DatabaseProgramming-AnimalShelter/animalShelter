package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Adopter;
import model.Animal;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * Community 테이블에서 커뮤니티 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class AnimalDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 */
	public int create(Animal animal) throws SQLException {
		String sql = "INSERT INTO Animal VALUES (? , ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {animal.getAnimal_id(), 
				animal.getCategory_id(),
				animal.getAge(),
				animal.getLocation(),
				animal.getImage(),
				animal.getGender(),
				animal.getWeight(),
				animal.getEtc(),
				animal.getAnimal_matched()
				};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = {"animal_id"};	// PK 컬럼의 이름     
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		animal.setAnimal_id(generatedKey); 	// id필드에 저장  
		   	}
		   	return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}

	/**
	 * 기존의 동물 정보를 수정
	 */
	public int update(Animal animal) throws SQLException {
		String sql = "UPDATE Animal "
					+ "SET age=?, location=?, image=?, gender=?, weight=?, etc=?, animal_matched=?  "
					+ "WHERE animal_id=?";
		Object[] param = new Object[] {animal.getAge(), animal.getLocation(),
				animal.getImage(), animal.getGender(), animal.getWeight(),
				animal.getEtc(), animal.getAnimal_matched()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	
	/**
	 * 주어진 ID에 해당하는 커뮤니티 정보를 삭제.
	 */
	public int remove(int animal_id) throws SQLException {
		String sql = "DELETE FROM Animal WHERE animal_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}

	//animal detail
	public Animal findAnimal(int animal_id) throws SQLException {
        String sql = "SELECT animal_id, category_id, age, location, matched, image,gender,weight,etc, species, animal_type"
     		   + "FROM Animal a JOIN Category c ON a.animal_id = c.animal_id" 
     		  + "WHERE animal_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				Animal animal = new Animal(		// User 객체를 생성하여 학생 정보를 저장
					rs.getInt("animal_id"),
					rs.getInt("category_id"),
					rs.getInt("age"),
					rs.getString("location"),
					rs.getInt("matched"),
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	/**
	 * 전체 커뮤니티 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Animal> findAnimalList() throws SQLException {
        String sql = "SELECT animal_id, category_id, age, location, image, animal_type, species"
        		   + "FROM Animal a JOIN Category c ON a.animal_id = c.animal_id" 
        		   + "ORDER BY a.animal_id";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Animal> animalList = new ArrayList<Animal>();	// Community들의 리스트 생성
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getInt("matched"),
						rs.getString("image"));

				animalList.add(animal);				// List에 Community 객체 저장
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	public List<Animal> searchAnimalList(int category_id, String animal_type, int matched) throws SQLException {
		String sql = null;
		// list.jsp에서 species가 0이면  type을 전체로 선택한것이니 category_id=?를 주면 안됨
		if (category_id == 0) {
			sql = "SELECT animal_id, category_id, age, location, image, animal_type, species, matched"
		     		   + "FROM Animal a JOIN Category c ON a.animal_id = c.animal_id"
		     		   + "WHERE animal_type=? or matched=?"
		     		   + "ORDER BY a.animal_id"; 
		}else {
			sql = "SELECT animal_id, category_id, age, location, image, animal_type, species, matched"
	        		   + "FROM Animal a JOIN Category c ON a.animal_id = c.animal_id"
	        		   + "WHERE category_id=? or animal_type=? or matched=?"
	        		   + "ORDER BY a.animal_id";    
		}
		
        Object[] param = new Object[] { category_id, animal_type, matched};	// JDBCUtil에 update문과 매개 변수 설정
        jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
        
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Animal> animalList = new ArrayList<Animal>();	// Community들의 리스트 생성
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getInt("matched"),
						rs.getString("image"),
						rs.getString("species"),
						rs.getString("animal_type"));

				animalList.add(animal);				// List에 Community 객체 저장
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	

}
