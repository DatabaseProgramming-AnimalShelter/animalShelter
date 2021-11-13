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
        String sql = "SELECT animal_id, category_id, age, location, matched, image,gender,weight,etc, species, animal_type"
     		   + "FROM Animal a JOIN Category c ON a.animal_id = c.animal_id" 
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
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	/**
	 * ��ü Ŀ�´�Ƽ ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Animal> findAnimalList() throws SQLException {
        String sql = "SELECT animal_id, category_id, age, location, image, animal_type, species"
        		   + "FROM Animal a JOIN Category c ON a.animal_id = c.animal_id" 
        		   + "ORDER BY a.animal_id";        
        			
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
						rs.getInt("matched"),
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
	
	public List<Animal> searchAnimalList(int category_id, String animal_type, int matched) throws SQLException {
		String sql = null;
		// list.jsp���� species�� 0�̸�  type�� ��ü�� �����Ѱ��̴� category_id=?�� �ָ� �ȵ�
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
		
        Object[] param = new Object[] { category_id, animal_type, matched};	// JDBCUtil�� update���� �Ű� ���� ����
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
						rs.getInt("matched"),
						rs.getString("image"),
						rs.getString("species"),
						rs.getString("animal_type"));

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
