package model.service;

import java.sql.SQLException;
import java.util.List;

//import model.AdoptApply;
import model.Heart;
import model.dao.HeartDAO;

public class HeartManager {
	private static HeartManager HeartMan = new HeartManager();
//	private AdopterDAO adopterDAO;
	private HeartDAO HeartDAO;

	public HeartDAO getHeartDAO() {
		return this.HeartDAO;
	}

	private HeartManager() {
		try {
			HeartDAO = new HeartDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static HeartManager getInstance() {
		return HeartMan;
	}

	public Heart create(Heart heart) throws SQLException, ExistingUserException {
		return HeartDAO.create_heart(heart);
	}

}
