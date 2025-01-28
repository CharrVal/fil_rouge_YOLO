package bll;

import java.util.List;

import bo.TableRestaurant;
import dal.TableRestaurantDAO;
import exceptions.TableRestaurantException;

public class TableRestaurantBLL {
	public List<TableRestaurant> select() {
		TableRestaurantDAO dao = new TableRestaurantDAO();
		return dao.select();
	}

	public TableRestaurant insert(int nbPlaces, int numeroTable) throws Exception {
		TableRestaurant tableRestaurant = new TableRestaurant(nbPlaces, numeroTable);
		checkTableRestaurant(tableRestaurant);
		
		TableRestaurantDAO dao = new TableRestaurantDAO();
		dao.insert(tableRestaurant);
		
		return tableRestaurant;
	}
	
	private void checkTableRestaurant(TableRestaurant tableRestaurant) throws TableRestaurantException {
			// a déterminer !
		}
	
	public void update(TableRestaurant tableRestaurant) throws TableRestaurantException {
		checkTableRestaurant(tableRestaurant);
		
		TableRestaurantDAO dao = new TableRestaurantDAO();
		dao.update(tableRestaurant);
	}
	
	public void delete (int id) {
		TableRestaurantDAO dao = new TableRestaurantDAO();
		dao.delete(id);
	}
	
}
