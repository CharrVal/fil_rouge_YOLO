package controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bll.RestaurantBLL;
import bo.Horaire;
import exceptions.RestaurantException;

public class TestHoraire {
	private static Scanner scan;
	private static RestaurantBLL RestaurantBLL = new RestaurantBLL();
	public static void main(String[] args) throws Exception {
		scan = new Scanner(System.in);
		
		
		//afficherMenuAjout();
		ajouterHoraire();
		
		
	}
	
	
	
	private static void ajouterHoraire() {
		
	}
	
	
	
	private static void afficherMenuAjout() throws Exception {
		boolean insertionFailed;
		do {
			System.out.print("Veuillez saisir le nom du nouveau resto : ");
			String nom = scan.nextLine();
			
			System.out.print("Veuillez saisir la adresse du resto : ");
			String adresse = scan.nextLine();
			
			System.out.print("Veuillez saisir l'url de l'image du resto : ");
			String url_image = scan.nextLine();
			
			System.out.print("jour ? :");
			String jour = scan.nextLine();
			
			System.out.print("ouverture? :");
			String ouverture = scan.nextLine() ;
			
			System.out.print("fermeture? :");
			String fermeture = scan.nextLine();
			
			List<Horaire> horaires = new ArrayList<>();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		    LocalTime ouvert = LocalTime.parse(ouverture, formatter);
		    LocalTime ferme = LocalTime.parse(fermeture, formatter);

			Horaire horaire = new Horaire (jour, ouvert, ferme);
			System.out.println(horaire.toString());
			horaires.add(horaire);
			
			try {
				RestaurantBLL.insert(nom, adresse, url_image, horaires);
				insertionFailed = false;
			} catch (RestaurantException e) {
				insertionFailed = true;
				System.err.println("Echec de la création du resto :");
				System.err.println(e.getMessage());
			}
		} while (insertionFailed);
	}
	
	
	
	
	
	

}
