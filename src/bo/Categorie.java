package bo;

public class Categorie {
<<<<<<< HEAD
	private int id;
	private String libelle;
	
=======
	
	private int id;
	private String libelle;
	
	
>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	public Categorie(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}
	
	public Categorie(String libelle) {
		this.libelle = libelle;
	}
	
	public Categorie() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Categorie n°" + id + " :" + "\n nom de la categorie :" + libelle;
=======
		return "Categorie id : " + id + ", libelle : " + libelle;
>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	}
	
	
	
<<<<<<< HEAD
=======
	
	

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
}
