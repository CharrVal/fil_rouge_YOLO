package bo;

import java.util.ArrayList;
import java.util.List;

public class Carte {
	private int id;
	private String nom;
	private String description;
	private List<Plat> plats = new ArrayList<>();
	
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
	

	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}
	

	@Override
	public String toString() {
		return String.format("\n%-4d %-30s %50s %-30s\n", id, nom, description, plats);
	}


	
	
}
