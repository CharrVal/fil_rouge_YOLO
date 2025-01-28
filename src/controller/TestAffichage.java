package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import bll.RestaurantBLL;
import bo.Restaurant;
import exceptions.RestaurantException;

public class TestAffichage {
	private static Scanner scan;
	private static RestaurantBLL RestaurantBLL = new RestaurantBLL();
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		System.out.println("Bienvenue sur l'application de gestion de vos restaurants !");
		int choix;
		do {
			choix = afficherMenu();
			
			switch(choix) {
			case 1: afficherMenuAjout(); break;
			case 2: afficherRestaurant(RestaurantBLL.select()); break;
			case 3: afficherMenuSuppression(); break;
			}
		} while (choix != 4);
		System.out.println("Bonne journée !");
		scan.close();
	}
	
	private static void afficherMenuAjout() {
		boolean insertionFailed;
		do {
			System.out.print("Veuillez saisir le nom du nouveau composant : ");
			String nom = scan.nextLine();
			
			System.out.print("Veuillez saisir la nature du composant : ");
			String nature = scan.nextLine();
			
			System.out.print("Veuillez saisir la date de sortie du composant (jj/mm/aaaa) : ");
			String dateAsString = scan.nextLine();
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			try {
				RestaurantBLL.insert(nom, nature, LocalDate.parse(dateAsString, dtf));
				insertionFailed = false;
			} catch (RestaurantException | DateTimeParseException e) {
				insertionFailed = true;
				System.err.println("Echec de la création du matériel :");
				System.err.println(e.getMessage());
			}
		} while (insertionFailed);
	}

	private static void afficherMenuSuppression() {
		System.out.println("Saisissez l'id du matériel à supprimer, ou 0 pour retourner au menu principal.");
		List<Restaurant> Restaurants = RestaurantBLL.select();
		
		if (Restaurants.isEmpty()) {
			System.err.println("Aucun élément à afficher.");
			return;
		}
		
		afficherRestaurant(Restaurants);
		int choix;
		do {
			try {
				choix = scan.nextInt();
				
				if (Restaurants.stream().map(Restaurant::getId).collect(Collectors.toList()).contains(choix)) {
					RestaurantBLL.delete(choix);
					choix = 0;
				} else {
					if (choix != 0) {
						System.err.println("L'identifiant sélectionné n'existe pas en base.");
						choix = -1;
					}
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Choix invalide");
				choix = -1;
			}
		} while (choix != 0);
	}

	private static void afficherRestaurant(List<Restaurant> Restaurants) {
		System.out.println();
		System.out.println("+" + "-".repeat(59) + "+");
		System.out.format("| %-4s | %-20s | %-10s | %-14s |\n", "id", "nom", "nature", "date sortie");
		System.out.println("+" + "-".repeat(59) + "+");
		for (Restaurant current : Restaurants) {
			System.out.print(current);
		}
		System.out.println("+" + "-".repeat(59) + "+");
		System.out.println();
	}

	private static int afficherMenu() {
		int choix;
		do {
			System.out.println();
			System.out.println("Quelle action souhaitez-vous réaliser ?");
			System.out.println("\t1. Enregistrer un nouveau matériel.");
			System.out.println("\t2. Consulter le matériel existant.");
			System.out.println("\t3. Supprimer un matériel.");
			System.out.println("\t4. Quitter l'application");
			
			try {
				choix = scan.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Choix invalide.");
				choix = -1;
			} finally {
				scan.nextLine();
			}
		} while (choix < 1 || choix > 4);
		return choix;
	}
}