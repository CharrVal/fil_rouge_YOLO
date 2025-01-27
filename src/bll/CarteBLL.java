package bll;

import java.util.List;

import bo.Cartes;
import dal.CarteDAO;
import exceptions.CarteException;

public class CarteBLL {
	public List<Cartes> select() {
		CarteDAO dao = new CarteDAO();
		return dao.select();
	}

	public Cartes insert(String nom, String description) throws Exception {
		Cartes restaurant = new Cartes(nom, description);
		checkCartes(restaurant);
		
		CarteDAO dao = new CarteDAO();
		dao.insert(restaurant);
		
		return restaurant;
	}
	
	private void checkCartes(Cartes carte) throws CarteException {
		// if (cartes.getNom() == null || cartes.getNom().length() < 2 || cartes.getNom().length() > 20) {
			// throw new CartesException("Le nom doit faire entre 2 et 20 caractères.");
		// les if sont à déterminer !
		}
	
	public void update(Cartes carte) throws CarteException {
		checkCartes(carte);
		
		CarteDAO dao = new CarteDAO();
		dao.update(carte);
	}
	
	public void delete (int id) {
		CarteDAO dao = new CarteDAO();
		dao.delete(id);
	}
}