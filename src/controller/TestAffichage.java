package controller;

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
	
	public static void main(String[] args) throws Exception {
		scan = new Scanner(System.in);
		System.out.println("Bienvenue sur l'application de gestion de vos restaurants !");
		int choix;
		do {
			choix = afficherMenu();
			
			switch(choix) {
			case 1: afficherMenuAjout(); break;
			case 2: afficherRestaurant(RestaurantBLL.select()); break;
			case 3: afficherMenuModification(); break;
			case 4: afficherMenuSuppression(); break;
			}
		} while (choix != 5);
		System.out.println("Bonne journée !");
		scan.close();
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
			
			try {
				RestaurantBLL.insert(nom, adresse, url_image);
				insertionFailed = false;
			} catch (RestaurantException e) {
				insertionFailed = true;
				System.err.println("Echec de la création du resto :");
				System.err.println(e.getMessage());
			}
		} while (insertionFailed);
	}
	
	private static void afficherMenuModification() throws Exception {
		System.out.println("Saisissez l'id du resto à modifier, ou 0 pour retourner au menu principal.");
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
					scan.nextLine();
					break;
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
		
		boolean updateFailed;
		do {
			System.out.print("Veuillez saisir le nouveau nom du resto : ");
			String nom = scan.nextLine();
			
			System.out.print("Veuillez saisir la nouvelle adresse du resto : ");
			String adresse = scan.nextLine();
			
			System.out.print("Veuillez saisir la nouvelle url de l'image du resto : ");
			String url_image = scan.nextLine();
			
			Restaurant restoTemp = new Restaurant(choix, nom, adresse, url_image);
			
			try {
				RestaurantBLL.update(restoTemp);
				updateFailed = false;
			} catch (RestaurantException e) {
				updateFailed = true;
				System.err.println("Echec de la modification du resto :");
				System.err.println(e.getMessage());
			}
		} while (updateFailed);
	}
		
	private static void afficherMenuSuppression() {
		System.out.println("Saisissez l'id du resto à supprimer, ou 0 pour retourner au menu principal.");
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
		System.out.println("+" + "-".repeat(200) + "+");
		System.out.format("%-4s %-30s %-30s %-20s %-50s %-20s %-50s", "id", "nom", "adresse", "cartes", "horaires", "tables", "url_image\n");
		for (Restaurant current : Restaurants) {
			System.out.print(current);
		}
		System.out.println();
		System.out.println("+" + "-".repeat(200) + "+");
		System.out.println();
	}
	
	

	private static int afficherMenu() {
		int choix;
		do {
			System.out.println();
			System.out.println("Quelle action souhaitez-vous réaliser ?");
			System.out.println("\t1. Enregistrer un nouveau resto.");
			System.out.println("\t2. Consulter le resto existant.");
			System.out.println("\t3. Modifier un resto.");
			System.out.println("\t4. Supprimer un resto.");
			System.out.println("\t5. Quitter l'application");
			
			try {
				choix = scan.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Choix invalide.");
				choix = -1;
			} finally {
				scan.nextLine();
			}
		} while (choix < 1 || choix > 5);
		return choix;
	}
}