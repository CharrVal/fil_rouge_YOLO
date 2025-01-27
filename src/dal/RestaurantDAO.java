package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Restaurant;

public class RestaurantDAO {
	
	String url = System.getenv("FIL_ROUGE_URL");
	String username = System.getenv("FIL_ROUGE_USERNAME");
	String password = System.getenv("FIL_ROUGE_PASSWORD");
	
	public List<Restaurant> select() {
		List<Restaurant> restaurant = new ArrayList<>();
		
		try {
			Connection cnx = DriverManager.getConnection("FIL_ROUGE_URL;username=FIL_ROUGE_USERNAME;password=FIL_ROUGE_PASSWORD;trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement("SELECT * FROM restaurants");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					restaurant.add(convertResultSetToRestaurant(rs));
				}
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	public Restaurant insert(Restaurant restaurant) {
		try {
			Connection cnx = DriverManager.getConnection("FIL_ROUGE_URL;username=FIL_ROUGE_USERNAME;password=FIL_ROUGE_PASSWORD;trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement(
						"INSERT INTO restaurants(nom, url_image, id_cartes)"
						+ "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, restaurant.getNom());
				ps.setString(2, restaurant.getUrl_image());
				ps.setInt(3, restaurant.getId_cartes());
				// pour une date ps.setDate(n°?, Date.valueof(bo_restaurants.getDate()));
				
				ps.executeUpdate(); // Methode pour executer un INSERT, un UPDATE ou un DELETE.
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					restaurant.setId(rs.getInt(1));
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
			Connection cnx = DriverManager.getConnection("FIL_ROUGE_URL;username=FIL_ROUGE_USERNAME;password=FIL_ROUGE_PASSWORD;trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement(
						"UPDATE restaurants SET nom = ?, url_image = ?, id_cartes = ? WHERE id = ?");
				ps.setString(1, restaurant.getNom());
				ps.setString(2, restaurant.getUrl_image());
				ps.setInt(3, restaurant.getId_cartes());
				ps.setInt(4, restaurant.getId());
				
				ps.executeUpdate();
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void delete(int id) {
		try {
			Connection cnx = DriverManager.getConnection("FIL_ROUGE_URL;username=FIL_ROUGE_USERNAME;password=FIL_ROUGE_PASSWORD;trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement("DELETE FROM restaurants WHERE id = ?");
				ps.setInt(1, id);
				ps.executeUpdate();
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

 	private Restaurant convertResultSetToRestaurant(ResultSet rs) throws SQLException {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(rs.getInt("id"));
		restaurant.setNom(rs.getString("nom"));
		restaurant.setUrl_image(rs.getString("url_image"));
		restaurant.setId_cartes(rs.getInt("id_cartes"));
		return restaurant;
	}
	
}
