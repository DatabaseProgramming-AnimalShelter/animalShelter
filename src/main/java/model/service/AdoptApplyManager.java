package model.service;

import java.sql.SQLException;
import java.util.List;

import model.AdoptApply;
import model.dao.AdoptApplyDAO;

public class AdoptApplyManager {

	private static AdoptApplyManager applyMan = new AdoptApplyManager();
	private AdoptApplyDAO adoptApplyDAO;

	public AdoptApplyDAO getAdopterDAO() {
		return this.adoptApplyDAO;
	}

	private AdoptApplyManager() {
		try {
			adoptApplyDAO = new AdoptApplyDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static AdoptApplyManager getInstance() {
		System.out.println("----------------------applyMan:--------------------" + applyMan);
		return applyMan;
	}

	public int create(AdoptApply adoptApply) throws SQLException {
		System.out.println("----------------------applyManCreate:--------------------" + applyMan);

		return adoptApplyDAO.create(adoptApply);
	}
	
	public int apply_result(AdoptApply adoptApply, int apply_result) throws SQLException {
		return adoptApplyDAO.apply_result(adoptApply, apply_result);
	}

	public AdoptApply findAdoptApply(int apply_id) throws SQLException {
		AdoptApply adoptApply = adoptApplyDAO.findAdoptApply(apply_id);
		return adoptApply;
	}

	public List<AdoptApply> findUserAdoptList(String user_id) throws SQLException {
		return adoptApplyDAO.findUserAdoptList(user_id);
	}
	
	public List<AdoptApply> findAnimalAdoptList(int animal_id) throws SQLException {
		return adoptApplyDAO.findAnimalAdoptList(animal_id);
	}
	
	public List<AdoptApply> findAdoptApplyList() throws SQLException {
		return adoptApplyDAO.findAdoptApplyList();
	}

	public List<AdoptApply> findAdoptApplyResultList() throws SQLException {
		return adoptApplyDAO.findAdoptApplyResultList();
	}

}