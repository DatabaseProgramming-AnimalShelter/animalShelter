package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Adopter;
import model.Animal;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * Community ���̺��� Ŀ�´�Ƽ ������ �߰�, ����, ����, �˻� ���� 
 */
public class AnimalDAO {
	private JDBCUtil jdbcUtil = null;
	
	public AnimalDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * Ŀ�´�Ƽ ���̺� ���ο� �� ���� (PK ���� Sequence�� �̿��Ͽ� �ڵ� ����)
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
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
						
		String key[] = {"animal_id"};	// PK �÷��� �̸�     
		try {    
			int result = jdbcUtil.executeUpdate(key);  // insert �� ����
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // ������ PK ��
		   		animal.setAnimal_id(generatedKey); 	// id�ʵ忡 ����  
		   	}
		   	return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;			
	}

	/**
	 * ������ ���� ������ ����
	 */
	public int update(Animal animal) throws SQLException {
		String sql = "UPDATE Animal "
					+ "SET age=?, location=?, image=?, gender=?, weight=?, etc=?, animal_matched=?  "
					+ "WHERE animal_id=?";
		Object[] param = new Object[] {animal.getAge(), animal.getLocation(),
				animal.getImage(), animal.getGender(), animal.getWeight(),
				animal.getEtc(), animal.getAnimal_matched()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	
	/**
	 * �־��� ID�� �ش��ϴ� Ŀ�´�Ƽ ������ ����.
	 */
	public int remove(int animal_id) throws SQLException {
		String sql = "DELETE FROM Animal WHERE animal_id=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}

	//animal detail
	public Animal findAnimal(int animal_id) throws SQLException {
        String sql = "SELECT a.animal_id, a.category_id, a.age, a.location, a.animal_matched, a.image,a.gender,a.weight,a.etc, c.species, c.animal_type "
     		   + " FROM Animal a JOIN Category c ON a.category_id = c.category_id " 
     		  + "WHERE animal_id=?";  
		jdbcUtil.setSqlAndParameters(sql, new Object[] {animal_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {						// �л� ���� �߰�
				Animal animal = new Animal(		// User ��ü�� �����Ͽ� �л� ������ ����
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	/**
	 * ��ü Ŀ�´�Ƽ ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Animal> findAnimalList() throws SQLException {
        String sql = "SELECT a.animal_id, c.category_id,c.species, a.age, a.location, a.image "
        		   + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
        		   + "ORDER BY animal_id";        
        			
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Animal> animalList = new ArrayList<Animal>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getString("image"));

				animalList.add(animal);				// List�� Community ��ü ����
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	public List<Animal> searchAnimalList(String animal_type,int category_id, int matched) throws SQLException {
		String sql = null;
		Object[] param;
		// list.jsp���� species�� 0�̸�  type�� ��ü�� �����Ѱ��̴� category_id=?�� �ָ� �ȵ�
		if (animal_type == "none" && matched == -1){
			sql = "SELECT a.animal_id, c.category_id,c.species, a.age, a.location, a.image "
	        		   + "FROM animal a JOIN Category c ON a.category_id = c.category_id " 
	        		   + "ORDER BY animal_id";   
			 param = new Object[] { };
		}
		else if (animal_type == "none") {
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched "
		     		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
		     		   + "WHERE a.animal_matched=? "
		     		   + "ORDER BY a.animal_id"; 
			 param = new Object[] { matched};
		}else if (matched == -1){
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched "
	        		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
	        		   + "WHERE a.category_id=?  "
	        		   + "ORDER BY a.animal_id";    
			 param = new Object[] { category_id};
		}
		else {
			sql = "SELECT a.animal_id, a.category_id, a.age, a.location, image, animal_type, species, a.animal_matched "
	        		   + "FROM Animal a JOIN Category c ON a.category_id = c.category_id "
	        		   + "WHERE a.category_id=? and a.animal_matched=? "
	        		   + "ORDER BY a.animal_id";    
			 param = new Object[] { category_id,matched};
			 
		}
        jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �� insert���� �Ű� ���� ����
        
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<Animal> animalList = new ArrayList<Animal>();	// Community���� ����Ʈ ����
			while (rs.next()) {
				Animal animal = new Animal(
						rs.getInt("animal_id"), 
						rs.getInt("category_id"),
						rs.getInt("age"),
						rs.getString("location"),
						rs.getString("image"));

				animalList.add(animal);				// List�� Community ��ü ����
			}		
			return animalList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	

}
