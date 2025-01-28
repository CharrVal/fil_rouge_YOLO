package bo;

public class Table_restaurant {
	private int id;
	private int nbPlaces;
	private int numeroTable;
	
	public Table_restaurant(int id, int nbPlaces, int numeroTable) {
		this.id = id;
		this.nbPlaces = nbPlaces;
		this.numeroTable = numeroTable;
	}
	
	public Table_restaurant(int nbPlaces, int numeroTable) {
		this.nbPlaces = nbPlaces;
		this.numeroTable = numeroTable;
	}
	
	public Table_restaurant() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public int getNumeroTable() {
		return numeroTable;
	}

	public void setNumeroTable(int numeroTable) {
		this.numeroTable = numeroTable;
	}

	@Override
	public String toString() {
		return "Table restaurant n°" + id + " :" + "\n\t• nombres de places :" + nbPlaces + "\n\t• numero de table :" + numeroTable;
	}	
}
