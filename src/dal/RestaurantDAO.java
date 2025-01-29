package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;
import bo.Horaire;
import bo.Restaurant;
import bo.TableRestaurant;

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
				PreparedStatement ps = cnx.prepareStatement("SELECT r.id, r.nom, r.adresse, r.url_image, c.id AS carte_id, c.nom AS carte_nom, c.description AS carte_description " +
						"FROM restaurants r LEFT JOIN cartes c ON r.id_cartes = c.id");
				
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

 	private Restaurant convertResultSetToRestaurant(ResultSet rs) throws SQLException {
		Restaurant restaurant = new Restaurant();
		Carte carte = new Carte(rs.getInt("carte_id"),rs.getString("carte_nom"), rs.getString("carte_description"));
		restaurant.setId(rs.getInt("id"));
		restaurant.setNom(rs.getString("nom"));
		restaurant.setAdresse(rs.getString("adresse"));
		restaurant.setUrl_image(rs.getString("url_image"));
		restaurant.setCarte(carte);
		return restaurant;
	}
 	
 	public void ajoutHorairesDansRestaurant(Restaurant restaurant) {
 		try {
            Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");
            
            if (!cnx.isClosed()) {
                PreparedStatement psHoraires = cnx.prepareStatement(
                    "SELECT h.id, h.jour_semaine, h.heure_ouverture, h.heure_fermeture " +
                    "FROM horaires h WHERE h.restaurant_id = ?"
                );
                psHoraires.setInt(1, restaurant.getId());
                ResultSet rsHoraire = psHoraires.executeQuery();

                while (rsHoraire.next()) {
                	Horaire horaire = new Horaire(
                            rsHoraire.getInt("id"),
                            rsHoraire.getString("jour_semaine"),
                            rsHoraire.getTimestamp("heure_ouverture"),
                            rsHoraire.getTimestamp("heure_fermeture")
                    );
                    restaurant.getHoraires().add(horaire);
                }
                cnx.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 	
    public void ajoutTablesDansRestaurant(Restaurant restaurant) {
        try {
            Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");

            if (!cnx.isClosed()) {
                PreparedStatement psTables = cnx.prepareStatement(
                    "SELECT t.id, t.capacite FROM tables_restaurant t WHERE t.restaurant_id = ?"
                );
                psTables.setInt(1, restaurant.getId());
                ResultSet rs = psTables.executeQuery();

                while (rs.next()) {
                    TableRestaurant table = new TableRestaurant(
                            rs.getInt("id"),
                            rs.getInt("capacite")
                    );
                    restaurant.getTables().add(table);
                }
                cnx.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
