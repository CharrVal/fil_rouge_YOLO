package bo;

public class Restaurants {
	private int id;
	private String nom;
	private String url_image;
	private int id_cartes;
	
	public Restaurants(int id, String nom, String url_image, int id_cartes) {
		this.id = id;
		this.nom = nom;
		this.url_image = url_image;
		this.id_cartes = id_cartes;
	}
	
	public Restaurants(String nom, String url_image, int id_cartes) {
		this.nom = nom;
		this.url_image = url_image;
		this.id_cartes = id_cartes;
	}
	
	public Restaurants() {}

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

	public int getId_cartes() {
		return id_cartes;
	}

	public void setId_cartes(int id_cartes) {
		this.id_cartes = id_cartes;
	}

	@Override
	public String toString() {
		return String.format("%-2d | %-15s| %-15s| %-2d|\n", id, nom, url_image, id_cartes);
	}	
}
