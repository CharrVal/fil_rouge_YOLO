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
		List<Restaurant> restaurants = new ArrayList<>();
		
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement("SELECT * FROM restaurants");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					restaurants.add(convertResultSetToRestaurant(rs));
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
				PreparedStatement ps = cnx.prepareStatement(
						"INSERT INTO restaurants(nom, adresse, url_image)"
						+ "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, restaurant.getNom());
				ps.setString(2, restaurant.getAdresse());
				ps.setString(3, restaurant.getUrl_image());
				
				ps.executeUpdate(); 
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
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement(
						"UPDATE restaurants SET nom = ?, adresse = ?, url_image = ? WHERE id = ?");
				ps.setString(1, restaurant.getNom());
				ps.setString(2, restaurant.getAdresse());
				ps.setString(3, restaurant.getUrl_image());
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
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
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
		restaurant.setAdresse(rs.getString("adresse"));
		restaurant.setUrl_image(rs.getString("url_image"));
		return restaurant;
	}
	
}
