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

/**
 * �궗�슜�옄 愿�由щ�� �쐞�빐 �뜲�씠�꽣踰좎씠�뒪 �옉�뾽�쓣 �쟾�떞�븯�뒗 DAO �겢�옒�뒪
 * Animal �뀒�씠釉붿뿉�꽌 而ㅻ�ㅻ땲�떚 �젙蹂대�� 異붽�, �닔�젙, �궘�젣, 寃��깋 �닔�뻾 
 */
public class AnimalDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public AnimalDAO() {
		String resource = "mybatis-config.xml";
		
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}System.out.println("dlrjsehlsk,,,,,,,,");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		System.out.println("myba@tis,,,,,,,,�깮�꽦,,,,,,ehoTwl,,,");
	}
	
	/**
	 * animal �뀒�씠釉붿뿉 �깉濡쒖슫 �뻾 �깮�꽦 (PK 媛믪� Sequence瑜� �씠�슜�븯�뿬 �옄�룞 �깮�꽦)
	 */
	public Animal create(Animal animal) {
		System.out.println("�뿬湲댁솕�늻11");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		System.out.println("�뿬湲댁솕�늻");
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

	/**
	 * 湲곗〈�쓽 animal �젙蹂대�� �닔�젙
	 */
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



	/**
	 * 二쇱뼱吏� ID�뿉 �빐�떦�븯�뒗 animal媛앹껜瑜� �궘�젣.
	 */
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

	/**
	 * 二쇱뼱吏�  ID�뿉 �빐�떦�븯�뒗 �룞臾� �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 Animal �룄硫붿씤 �겢�옒�뒪�뿉 
	 * ���옣�븯�뿬 諛섑솚.
	 */
	public Animal findAnimal(int animal_id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(AnimalMapper.class).findAnimal(animal_id);			
		} finally {
			sqlSession.close();
		}
	}


	/**
	 * �쟾泥� �룞臾� �젙蹂대�� 寃��깋�븯�뿬 List�뿉 ���옣 諛� 諛섑솚*/
		public List<Animal> findAnimalList() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(AnimalMapper.class).findAnimalList();			
		} finally {
			sqlSession.close();
		}
	} 

		/**
		 * �룞臾� �젙蹂대�� 寃��깋�븯�뿬 List�뿉 ���옣 諛� 諛섑솚*/
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
