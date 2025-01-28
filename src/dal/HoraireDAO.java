package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Horaire;

public class HoraireDAO {
	String url = System.getenv("FIL_ROUGE_URL");
	String username = System.getenv("FIL_ROUGE_USERNAME");
	String password = System.getenv("FIL_ROUGE_PASSWORD");
	
	public List<Horaire> select() {
		List<Horaire> liste_horaire = new ArrayList<>();

		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password +";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement("SELECT * FROM horaires");
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					liste_horaire.add(convertResultSetToHoraires(rs));
				}
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste_horaire;
	}

	public Horaire insert(Horaire horaire) {	
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password +";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement(
						"INSERT INTO horaires(jour, ouverture, fermeture)"
						+ "VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, horaire.getJour());
				ps.setDate(2, horaire.getOuverture());
				ps.setDate(3, horaire.getFermeture());
			
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					horaire.setId(rs.getInt(1));
				}
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horaire;
	}

	public void update(Horaire horaire) {
		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password +";trustservercertificate=true");
			if(!cnx.isClosed()) {
				PreparedStatement ps = cnx.prepareStatement(
						"UPDATE horaires SET jour = ?, ouverture = ?, fermeture = ? WHERE id = ?");
				ps.setString(1, horaire.getJour());
				ps.setDate(2, horaire.getOuverture());
				ps.setDate(3, (horaire.getFermeture());
				
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
				PreparedStatement ps = cnx.prepareStatement("DELETE FROM horaires WHERE id = ?");
				ps.setInt(1, id);
				ps.executeUpdate();
			}
			cnx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
 	private Horaire convertResultSetToHoraires(ResultSet rs) throws SQLException {
		Horaire horaire = new Horaire();
		horaire.setId_horaire(rs.getInt("id"));
		horaire.setJour(rs.getString("jour"));
		horaire.setOuverture(rs.getDate("ouverture").toLocalDate());
		horaire.setFermeture(rs.getDate("fermeture").toLocalDate());
		return horaire;
	} 	
}
