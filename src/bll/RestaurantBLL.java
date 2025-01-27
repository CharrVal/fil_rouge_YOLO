package bll;

import java.util.List;

import bo.Restaurant;
import dal.RestaurantDAO;
import exceptions.RestaurantException;

public class RestaurantBLL {
	
	

	public List<Restaurant> select() {
		RestaurantDAO dao = new RestaurantDAO();
		return dao.select();
	}
	
	/*
	 * Pour avoir le droit de réaliser l'insertion, un restaurant doit respecter les contraintes suivantes :
	 * nom compris entre 2 et 20 caractères,
	 * url_image fait plus de 10 caractères,
	 * id_cartes supérieur ou égale à 1 inférieur ou égal à 3.
	 */
	public Restaurant insert(String nom, String url_image) throws Exception {
		Restaurant restaurant = new Restaurant(nom, url_image);
		checkRestaurants(restaurant);
		
		RestaurantDAO dao = new RestaurantDAO();
		dao.insert(restaurant);
		
		return restaurant;
	}
	
	private void checkRestaurants(Restaurant restaurant) throws RestaurantException {
		if (restaurant.getNom() == null || restaurant.getNom().length() < 2 || restaurant.getNom().length() > 20) {
			throw new RestaurantException("Le nom doit faire entre 2 et 20 caractères.");
		}
		if (restaurant.getUrl_image() == null || restaurant.getUrl_image().length() < 10) {
			throw new RestaurantException("L'url de l'image doit faire au moins 10 caractères.");
		}
		if (restaurant.getId_cartes() == 0 || restaurant.getId_cartes() < 1 || restaurant.getId_cartes() > 3) {
			throw new RestaurantException("la carte associée au restaurant n'existe pas.");
		}
	}
	
	public void update(Restaurant restaurant) throws RestaurantException {
		checkRestaurants(restaurant);
		
		RestaurantDAO dao = new RestaurantDAO();
		dao.update(restaurant);
	}
	
	public void delete (int id) {
		RestaurantDAO dao = new RestaurantDAO();
		dao.delete(id);
	}
	
}