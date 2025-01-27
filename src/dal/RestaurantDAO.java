package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Restaurants;

public class RestaurantDAO {
	
	public List<Restaurants> select() {
		List<Restaurants> restaurants = new ArrayList<>();
		
		try {
			Connection cnx = DriverManager.getConnection("jdbc:sqlserver://Ouessant-10;databasename=DEMO_YOLO;username=Utilisateur1;password=Utilisateur1;trustservercertificate=true");
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

	public Restaurants insert(Restaurants restaurant) {
		try {
			Connection cnx = DriverManager.getConnection("jdbc:sqlserver://Ouessant-10;databasename=DEMO_YOLO;username=Utilisateur1;password=Utilisateur1;trustservercertificate=true");
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
	
	public void update(Restaurants restaurant) {
		try {
			Connection cnx = DriverManager.getConnection("jdbc:sqlserver://Ouessant-10;databasename=DEMO_YOLO;username=Utilisateur1;password=Utilisateur1;trustservercertificate=true");
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
			Connection cnx = DriverManager.getConnection("jdbc:sqlserver://Ouessant-10;databasename=DEMO_YOLO;username=Utilisateur1;password=Utilisateur1;trustservercertificate=true");
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

 	private Restaurants convertResultSetToRestaurant(ResultSet rs) throws SQLException {
		Restaurants restaurant = new Restaurants();
		restaurant.setId(rs.getInt("id"));
		restaurant.setNom(rs.getString("nom"));
		restaurant.setUrl_image(rs.getString("url_image"));
		restaurant.setId_cartes(rs.getInt("id_cartes"));
		return restaurant;
	}
	
}
