package bll;

import java.util.List;

import bo.Cartes;
import dal.CartesDAO;
import exceptions.CartesException;

public class CartesBLL {
	public List<Cartes> select() {
		CartesDAO dao = new CartesDAO();
		return dao.select();
	}

	public Cartes insert(String nom, String description) throws Exception {
		Cartes restaurant = new Cartes(nom, description);
		checkCartes(restaurant);
		
		CartesDAO dao = new CartesDAO();
		dao.insert(restaurant);
		
		return restaurant;
	}
	
	private void checkCartes(Cartes carte) throws CartesException {
		// if (cartes.getNom() == null || cartes.getNom().length() < 2 || cartes.getNom().length() > 20) {
			// throw new CartesException("Le nom doit faire entre 2 et 20 caractères.");
		// les if sont à déterminer !
		}
	
	public void update(Cartes carte) throws CartesException {
		checkCartes(carte);
		
		CartesDAO dao = new CartesDAO();
		dao.update(carte);
	}
	
	public void delete (int id) {
		CartesDAO dao = new CartesDAO();
		dao.delete(id);
	}
}