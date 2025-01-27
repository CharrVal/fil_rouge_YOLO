package bll;

import java.util.List;

import bo.Restaurants;
import dal.RestaurantsDAO;
import exceptions.RestaurantsException;

public class RestaurantsBLL {

	public List<Restaurants> select() {
		RestaurantsDAO dao = new RestaurantsDAO();
		return dao.select();
	}
	
	/*
	 * Pour avoir le droit de réaliser l'insertion, un restaurant doit respecter les contraintes suivantes :
	 * nom compris entre 2 et 20 caractères,
	 * url_image fait plus de 10 caractères,
	 * id_cartes supérieur ou égale à 1 inférieur ou égal à 3.
	 */
	public Restaurants insert(String nom, String url_image, int id_cartes) throws Exception {
		Restaurants restaurant = new Restaurants(nom, url_image, id_cartes);
		checkRestaurants(restaurant);
		
		RestaurantsDAO dao = new RestaurantsDAO();
		dao.insert(restaurant);
		
		return restaurant;
	}
	
	private void checkRestaurants(Restaurants restaurant) throws RestaurantsException {
		if (restaurant.getNom() == null || restaurant.getNom().length() < 2 || restaurant.getNom().length() > 20) {
			throw new RestaurantsException("Le nom doit faire entre 2 et 20 caractères.");
		}
		if (restaurant.getUrl_image() == null || restaurant.getUrl_image().length() < 10) {
			throw new RestaurantsException("L'url de l'image doit faire au moins 10 caractères.");
		}
		if (restaurant.getId_cartes() == 0 || restaurant.getId_cartes() < 1 || restaurant.getId_cartes() > 3) {
			throw new RestaurantsException("la carte associée au restaurant n'existe pas.");
		}
	}
	
	public void update(Restaurants restaurant) throws RestaurantsException {
		checkRestaurants(restaurant);
		
		RestaurantsDAO dao = new RestaurantsDAO();
		dao.update(restaurant);
	}
	
	public void delete (int id) {
		RestaurantsDAO dao = new RestaurantsDAO();
		dao.delete(id);
	}
	
}