package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

public class TestAffichage {
	String url = System.getenv("FIL_ROUGE_URL");
	String username = System.getenv("FIL_ROUGE_USERNAME");
	String password = System.getenv("FIL_ROUGE_PASSWORD");
	
	public static void main(String[] args) throws NamingException {

		try {
			Connection cnx = DriverManager.getConnection(url + ";username=" + username + ";password=" + password + ";trustservercertificate=true");	
			if(!cnx.isClosed()) {
				System.out.println("Connexion réussie !");
				
				PreparedStatement ps = cnx.prepareStatement("SELECT * FROM categories");
				ResultSet rs = ps.executeQuery(); // Pour exécuter un SELECT
				System.out.format("%-5s | %-20s\n", "id", "libelle");
				System.out.println("---------------------------------------");
				while (rs.next()) {
					System.out.format("%-5s | %-20s | %s",
							rs.getInt("id"),
							rs.getString("libelle"));
					}
			}
			cnx.close();
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}
}
