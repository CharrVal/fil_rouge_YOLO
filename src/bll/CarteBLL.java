package bll;

import java.util.List;

import bo.Carte;
import dal.CarteDAO;
import exceptions.CarteException;

public class CarteBLL {
	
	public List<Carte> select() {
		CarteDAO dao = new CarteDAO();
		return dao.select();
	}

	public Carte insert(String nom, String description) throws CarteException {

		Carte carte = new Carte(nom, description);
		checkCarte(carte);
		
		CarteDAO dao = new CarteDAO();
		dao.insert(carte);
		
		return carte;
	}
	
	private void checkCarte(Carte carte) throws CarteException {
		// if (cartes.getNom() == null || cartes.getNom().length() < 2 || cartes.getNom().length() > 20) {
			// throw new CartesException("Le nom doit faire entre 2 et 20 caractères.");
		// les if sont à déterminer !
		}
	
	public void update(Carte carte) throws CarteException {
		checkCarte(carte);
		
		CarteDAO dao = new CarteDAO();
		dao.update(carte);
	}
	
	public void delete (int id) {
		CarteDAO dao = new CarteDAO();
		dao.delete(id);
	}
}