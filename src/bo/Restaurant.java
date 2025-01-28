package bo;

// import java.util.ArrayList;
// import java.util.List;

public class Restaurant {
	private int id;
	private String nom;
	private String url_image;
	// private List<Carte> liste_cartes = new ArrayList<>();
	// private List<Horaire> liste_horaires = new ArrayList<>();
	// private List<Table_Restaurant> liste_tables = new ArrayList<>();
	
	public Restaurant(int id, String nom, String url_image) {
		this.id = id;
		this.nom = nom;
		this.url_image = url_image;
	}
	
	public Restaurant(String nom, String url_image) {
		this.nom = nom;
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

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	@Override
	public String toString() {
		return String.format("%-2d | %-15s| %-15s| %-2d|\n", id, nom, url_image);
	}	
}
