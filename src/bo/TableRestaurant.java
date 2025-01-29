package bo;

public class TableRestaurant {
<<<<<<< HEAD
	private int id;
	private int nbPlaces;
	private int numeroTable;
	
	public TableRestaurant(int id, int nbPlaces, int numeroTable) {
		this.id = id;
		this.nbPlaces = nbPlaces;
		this.numeroTable = numeroTable;
	}
	
	public TableRestaurant(int nbPlaces, int numeroTable) {
		this.nbPlaces = nbPlaces;
		this.numeroTable = numeroTable;
	}
	
	public TableRestaurant() {}
=======
	
	private int id;
	private int nbTables;
	private int numeroTable;
	
	public TableRestaurant(int id, int nbTables, int numeroTable) {
		this.id = id;
		this.nbTables = nbTables;
		this.numeroTable = numeroTable;
	}

	public TableRestaurant(int nbTables, int numeroTable) {
		this.nbTables = nbTables;
		this.numeroTable = numeroTable;
	}

	public TableRestaurant() {
	}
>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

<<<<<<< HEAD
	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
=======
	public int getNbTables() {
		return nbTables;
	}

	public void setNbTables(int nbTables) {
		this.nbTables = nbTables;
>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
	}

	public int getNumeroTable() {
		return numeroTable;
	}

	public void setNumeroTable(int numeroTable) {
		this.numeroTable = numeroTable;
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Table restaurant n°" + id + " :" + "\n\t• nombres de places :" + nbPlaces + "\n\t• numero de table :" + numeroTable;
	}	
=======
		return "TableRestaurant id : " + id + ", nbTables : " + nbTables + ", numeroTable : " + numeroTable;
	}
	
	
	

>>>>>>> 1eed5d0e79a87c0eb898f2b5eed6ef9365be32a8
}
