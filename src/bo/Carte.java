package bo;

public class Carte {
	private int id;
	private String nom;
	private String description;
	//private List<Plats> liste_plats = new ArrayList<>();
	
	public Carte(int id, String nom, String description) {
		this.id = id;
		this.nom = nom;
		this.description = description;
	}
	
	public Carte(String nom, String description) {
		this.nom = nom;
		this.description = description;
	}
	
	public Carte() {}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Cartes d'id n°" + id + " de nom : " + nom + " et de description : " + description;
	}
}
