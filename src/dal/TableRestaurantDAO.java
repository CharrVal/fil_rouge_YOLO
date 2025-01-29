package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.TableRestaurant;

public class TableRestaurantDAO {
	String url = System.getenv("FIL_ROUGE_URL");
	String username = System.getenv("FIL_ROUGE_USERNAME");
	String password = System.getenv("FIL_ROUGE_PASSWORD");
	
	public List<TableRestaurant> select() {
		List<TableRestaurant> tableRestaurants = new ArrayList<>();
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password +";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement("SELECT * FROM tables_restaurant");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					tableRestaurants.add(convertResultSetToTableRestaurant(rs));
				}
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableRestaurants;
	}

	public TableRestaurant insert(TableRestaurant tableRestaurant) {
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password +";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement(
						"INSERT INTO tables_restaurant(nb_places, numero_table)"
						+ "VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setInt(1, tableRestaurant.getNbPlaces());
				ps.setInt(2, tableRestaurant.getNumeroTable());

				
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					tableRestaurant.setId(rs.getInt(1));
				}
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableRestaurant;
	}
	
	public void update(TableRestaurant tableRestaurant) {
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password +";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement(
						"UPDATE tables_restaurant SET nb_places = ?, numero_table = ? WHERE id = ?");
				ps.setInt(1, tableRestaurant.getNbPlaces());
				ps.setInt(2, tableRestaurant.getNumeroTable());
				ps.setInt(3, tableRestaurant.getId());
				
				ps.executeUpdate();
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void delete(int id) {
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password +";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement("DELETE FROM tables_restaurant WHERE id = ?");
				ps.setInt(1, id);
				ps.executeUpdate();
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

 	private TableRestaurant convertResultSetToTableRestaurant(ResultSet rs) throws SQLException {
		TableRestaurant restaurant = new TableRestaurant();
		restaurant.setId(rs.getInt("id"));
		restaurant.setNbPlaces(rs.getInt("nb_places"));
		restaurant.setNumeroTable(rs.getInt("numero_table"));
		return restaurant;
	}
}
