package bo;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private int id;
	private String nom;
	private String adresse;
	private String url_image;
	private List<Carte> cartes = new ArrayList<>();
	private List<Horaire> horaires = new ArrayList<>();
	private List<TableRestaurant> tablesRestaurants = new ArrayList<>();
	
			
	public Restaurant(int id, String nom, String adresse, String url_image) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.url_image = url_image;

	}
	
	
	public Restaurant(String nom, String adresse, String url_image) {
		this.nom = nom;
		this.adresse = adresse;
		this.url_image = url_image;
	}

	public Restaurant() {}
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public List<Carte> getCartes() {
		return cartes;
	}


	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}


	public List<Horaire> getHoraires() {
		return horaires;
	}


	public void setHoraires(List<Horaire> horaires) {
		this.horaires = horaires;
	}


	public List<TableRestaurant> getTablesRestaurants() {
		return tablesRestaurants;
	}


	public void setTablesRestaurants(List<TableRestaurant> tablesRestaurants) {
		this.tablesRestaurants = tablesRestaurants;
	}


	public String getUrl_image() {
		return url_image;
	}


	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}


	
	@Override
	public String toString() {
		
		return String.format("   \n%-4d %-30s %-30s %-20s %-50s %-20s %-30s\n", id, nom, adresse, cartes, horaires, tablesRestaurants, url_image);
		
	}
	
	
	
	
	
	

}
