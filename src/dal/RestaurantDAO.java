package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Horaire;
import bo.Restaurant;

public class RestaurantDAO {
	
	String url = System.getenv("FIL_ROUGE_URL");
	String username = System.getenv("FIL_ROUGE_USERNAME");
	String password = System.getenv("FIL_ROUGE_PASSWORD");
	
	public List<Restaurant> select() {
		List<Restaurant> restaurants = new ArrayList<>();
		
		try {
			//Connection cnx = DriverManager.getConnection("jdbc:sqlserver://localhost;databasename=YOLO_DB;username=opsResto-control;password=951753;trustservercertificate=true");
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement psResto = cnx.prepareStatement("SELECT * FROM restaurants");
				ResultSet rs = psResto.executeQuery();
				
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
				PreparedStatement psResto = cnx.prepareStatement(
						"INSERT INTO restaurants(nom, adresse, url_image)"
						+ "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
				psResto.setString(1, restaurant.getNom());
				psResto.setString(2, restaurant.getAdresse());
				psResto.setString(3, restaurant.getUrl_image());
				
				psResto.executeUpdate(); 
				ResultSet rs = psResto.getGeneratedKeys();
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
				PreparedStatement psResto = cnx.prepareStatement(
						"UPDATE restaurants SET nom = ?, adresse = ?, url_image = ? WHERE id = ?");
				psResto.setString(1, restaurant.getNom());
				psResto.setString(2, restaurant.getAdresse());
				psResto.setString(3, restaurant.getUrl_image());
				psResto.setInt(4, restaurant.getId());
				
				psResto.executeUpdate();
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
				PreparedStatement psResto = cnx.prepareStatement("DELETE FROM restaurants WHERE id = ?");
				psResto.setInt(1, id);
				psResto.executeUpdate();
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
