package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Restaurant;
import bo.TableRestaurant;

public class RestaurantDAO {
	
	String url = System.getenv("FIL_ROUGE_URL");
	String username = System.getenv("FIL_ROUGE_USERNAME");
	String password = System.getenv("FIL_ROUGE_PASSWORD");
	
	public List<Restaurant> select() {
		List<Restaurant> restaurants = new ArrayList<>();
		
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement psRestos = cnx.prepareStatement("SELECT * FROM restaurants");
				ResultSet rsRestos = psRestos.executeQuery();
				
				PreparedStatement psTables = cnx.prepareStatement("SELECT * FROM tables_restaurant");
				ResultSet rsTables = psTables.executeQuery();
				
				while (rsRestos.next()) {
					restaurants.add(convertResultSetToRestaurant(rsRestos, rsTables));
				}
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurants;
	}

	public Restaurant insert(Restaurant restaurant) {
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement psRestos = cnx.prepareStatement(
						"INSERT INTO restaurants(nom, adresse, url_image)"
						+ "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
				psRestos.setString(1, restaurant.getNom());
				psRestos.setString(2, restaurant.getAdresse());
				psRestos.setString(3, restaurant.getUrl_image());
				
				psRestos.executeUpdate(); 
				ResultSet rsRestos = psRestos.getGeneratedKeys();
				if (rsRestos.next()) {
					restaurant.setId(rsRestos.getInt(1));
				}
				
				for(TableRestaurant tableCurrent : restaurant.getTablesRestaurants()){
					PreparedStatement psTables = cnx.prepareStatement(
							"INSERT INTO tables_restaurant(nb_places, numero_table, id_restaurants)"
							+ "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
					psTables.setInt(1, tableCurrent.getNbPlaces());
					psTables.setInt(2, tableCurrent.getNumeroTable());
					psTables.setInt(3, restaurant.getId());
					
					psTables.executeUpdate(); 
				}
				
				
				
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}
	
	public void update(Restaurant restaurant) {
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement psRestos = cnx.prepareStatement(
						"UPDATE restaurants SET nom = ?, adresse = ?, url_image = ? WHERE id = ?");
				psRestos.setString(1, restaurant.getNom());
				psRestos.setString(2, restaurant.getAdresse());
				psRestos.setString(3, restaurant.getUrl_image());
				psRestos.setInt(4, restaurant.getId());
				
				psRestos.executeUpdate();
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void delete(int id) {
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement psRestos = cnx.prepareStatement("DELETE FROM restaurants WHERE id = ?");
				psRestos.setInt(1, id);
				psRestos.executeUpdate();
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

 	private Restaurant convertResultSetToRestaurant(ResultSet rsRestos, ResultSet rsTables) throws SQLException {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(rsRestos.getInt("id"));
		restaurant.setNom(rsRestos.getString("nom"));
		restaurant.setAdresse(rsRestos.getString("adresse"));
		restaurant.setUrl_image(rsRestos.getString("url_image"));
		// attribuer la bonne table au bon resto
		return restaurant;
	}
	
}
