package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import bo.Carte;
import bo.Restaurant;

public class RestaurantDAO {
	
	private String url;
	private String username;
	private String password;
	
	public RestaurantDAO() {
		url = System.getenv("FIL_ROUGE_URL");
		username = System.getenv("FIL_ROUGE_USERNAME");
		password = System.getenv("FIL_ROUGE_PASSWORD");
	}
	
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
	       
     // Fetch carte
        int carteId = rs.getInt("id_cartes");
        if (!rs.wasNull()) {
            Carte carte = fetchCarteById(carteId);
            restaurant.setCarte(carte);	
        }

        return restaurant;
    }
 	
        private Carte fetchCarteById(int carteId) throws SQLException {
            Carte carte = null;
            String query = "SELECT * FROM cartes WHERE id = ?";
            Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");	
            
            try (PreparedStatement ps = cnx.prepareStatement(query)) {
                ps.setInt(1, carteId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    carte = new Carte();
                    carte.setId(rs.getInt("id"));
                    carte.setNom(rs.getString("nom"));
                    carte.setDescription(rs.getString("description"));
                }
            }
            return carte;
        }
}
