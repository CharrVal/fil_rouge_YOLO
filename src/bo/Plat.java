package bo;

public class Plat {
<<<<<<< HEAD
=======
	
>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	private int id;
	private String nom;
	private double prix;
	private String description;
	private Categorie categorie;
	
<<<<<<< HEAD
	public Plat(int id, String nom, double prix, String description) {
=======
	
	public Plat(int id, String nom, double prix, String description, Categorie categorie) {
>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
<<<<<<< HEAD
	}

	public Plat(String nom, double prix, String description) {
		this.nom = nom;
		this.prix = prix;
		this.description = description;
	}

	public Plat() {}
=======
		this.categorie = categorie;
	}


	public Plat(String nom, double prix, String description, Categorie categorie) {
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
	}


	public Plat() {
	}

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8

	public int getId() {
		return id;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public void setId(int id) {
		this.id = id;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public String getNom() {
		return nom;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public void setNom(String nom) {
		this.nom = nom;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public double getPrix() {
		return prix;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public void setPrix(double prix) {
		this.prix = prix;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public String getDescription() {
		return description;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public void setDescription(String description) {
		this.description = description;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public Categorie getCategorie() {
		return categorie;
	}

<<<<<<< HEAD
=======

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

<<<<<<< HEAD
	@Override
	public String toString() {
		return String.format("%-4s %-10s %-5s %-20s %-15s\n", id, nom, prix, description, categorie);
	}
}

=======

	@Override
	public String toString() {
		return "Plat id : " + id + ", nom : " + nom + ", prix : " + prix + ", description : " + description
				+ ", categorie : " + categorie;
	}
	
	
	
	
	
	

}
>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
