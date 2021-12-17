package model.dao.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Animal;
import model.dao.mybatis.mapper.AnimalMapper;


public class AnimalDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public AnimalDAO() {
		String resource = "mybatis-config.xml";
		
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	

	public Animal create(Animal animal) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(AnimalMapper.class).insertAnimal(animal);
			if (result > 0) {
				sqlSession.commit();
			} 
			return animal;
		} finally {
			sqlSession.close();
		}
	}

	public int update(Animal comm) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(AnimalMapper.class).updateAnimal(comm);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}



	
	public int remove(int commId) {		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(AnimalMapper.class).deleteAnimal(commId);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;	
		} finally {
			sqlSession.close();
		}
	}

	public Animal findAnimal(int animal_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(AnimalMapper.class).findAnimal(animal_id);			
		} finally {
			sqlSession.close();
		}
	}


		public List<Animal> findAnimalList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(AnimalMapper.class).findAnimalList();			
		} finally {
			sqlSession.close();
		}
	} 

		public List<Animal> searchAnimalList(Animal animal){
			Map<String,String> paramString=new HashMap<String,String>(2);
			paramString.put("animal_type",animal.getAnimal_type());
			paramString.put("location",animal.getLocation());
			Map<String,Integer> paramInt=new HashMap<String,Integer>(2);
			paramInt.put("category_id",animal.getCategory_id());
			paramInt.put("matched",animal.getMatched());
			
			SqlSession sqlSession = sqlSessionFactory.openSession();
			try {
				return sqlSession.getMapper(AnimalMapper.class).searchAnimalList(paramString,paramInt);			
			} finally {
				sqlSession.close();
			}
		} 
	
}
