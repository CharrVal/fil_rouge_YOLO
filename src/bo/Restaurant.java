package bo;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	private int id;
	private String nom;
	private String adresse;
	private String url_image;
	private Carte carte;
	private List<Horaire> horaires = new ArrayList<>();
	private List<TableRestaurant> tablesRestaurant = new ArrayList<>();
	
	public Restaurant(int id, String nom, String adresse, String url_image, Carte carte, List<TableRestaurant> tablesRestaurant) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.url_image = url_image;
		this.carte = carte;
		this.tablesRestaurant = tablesRestaurant;
	}

	public Restaurant(int id, String nom, String adresse, String url_image, Carte carte) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.url_image = url_image;
		this.carte = carte;
	}

	public Restaurant(String nom, String adresse, String url_image, Carte carte) {
		this.nom = nom;
		this.adresse = adresse;
		this.url_image = url_image;
		this.carte = carte;
	}

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
	
	public Restaurant(String nom, String adresse, String url_image, List<TableRestaurant> tablesRestaurant) {
		this.nom = nom;
		this.adresse = adresse;
		this.url_image = url_image;
		this.tablesRestaurant = tablesRestaurant;
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

	public String getUrl_image() {
		return url_image;
	}


	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public List<Horaire> getHoraires() {
		return horaires;
	}

	public void setHoraires(List<Horaire> horaires) {
		this.horaires = horaires;
	}

	public List<TableRestaurant> getTables() {
		return tablesRestaurant;
	}

	public void setTables(List<TableRestaurant> tables) {
		this.tablesRestaurant = tables;
	}

	@Override
	public String toString() {
		return String.format("%-4d %-30s %-30s %-20s %-50s %-20s %-50s\n", id, nom, adresse, carte, horaires, tablesRestaurant, url_image);
	}

}
