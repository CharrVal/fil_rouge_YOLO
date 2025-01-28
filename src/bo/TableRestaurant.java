package bo;

public class TableRestaurant {
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbTables() {
		return nbTables;
	}

	public void setNbTables(int nbTables) {
		this.nbTables = nbTables;
	}

	public int getNumeroTable() {
		return numeroTable;
	}

	public void setNumeroTable(int numeroTable) {
		this.numeroTable = numeroTable;
	}

	@Override
	public String toString() {
		return "TableRestaurant id : " + id + ", nbTables : " + nbTables + ", numeroTable : " + numeroTable;
	}
	
	
	

}
