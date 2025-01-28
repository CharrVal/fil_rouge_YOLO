package controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import bll.CarteBLL;
import bll.RestaurantBLL;
import bo.Carte;
import bo.Restaurant;
import exceptions.CarteException;
import exceptions.RestaurantException;

public class TestAffichage {
	private static Scanner scan;
	private static RestaurantBLL RestaurantBLL = new RestaurantBLL();
	private static CarteBLL CarteBLL = new CarteBLL();
	
	public static void main(String[] args) throws Exception {
		scan = new Scanner(System.in);
		System.out.println("Bienvenue sur l'application de gestion de vos restaurants !");
		int choix;
		do {
			choix = afficherMenu();
			
			switch(choix) {
			case 1: afficherRestaurant(RestaurantBLL.select()); break;
			case 2: ajouterRestaurant(); break;
			case 3: modifierRestaurant(); break;
			case 4: supprimerRestaurant(); break;
			case 5: afficherCarte(CarteBLL.select()); break;
			case 6: ajouterCarte(); break;
			case 7: modifierCarte(); break;
			case 8: supprimerCarte(); break;
			}
		} while (choix != 9);
		System.out.println("Bonne journée !");
		scan.close();
	}
	
	private static void afficherRestaurant(List<Restaurant> Restaurants) {
		int totalLength = 4 + 30 + 30 + 50 + 50 + 20 + 50 + 7;
	    System.out.println("+" + "-".repeat(totalLength) + "+");
	    System.out.format("| %-4s | %-30s | %-30s | %-30s | %-50s | %-20s | %-50s |\n",
	                      "id", "nom", "adresse", "carte", "horaires", "tables", "url_image");
	    System.out.println("+" + "-".repeat(totalLength) + "+");
		for (Restaurant current : Restaurants) {
			//System.out.print(current);
			String carte = (current.getCarte() != null) ? current.getCarte().getNom() : "N/A";
	        String horaires = "N/A"; // Assuming you have a method to get horaires as a string
	        String tables = "N/A"; // Assuming you have a method to get tables as a string
	        System.out.format("| %-4d | %-30s | %-30s | %-30s | %-50s | %-20s | %-50s |\n",
	                          current.getId(),
	                          current.getNom(),
	                          current.getAdresse(),
	                          carte,
	                          horaires,
	                          tables,
	                          current.getUrl_image());
		}
		 System.out.println("+" + "-".repeat(totalLength) + "+");
	}
	
	private static void ajouterRestaurant() throws Exception {
		boolean insertionFailed;
		do {
			System.out.print("Veuillez saisir le nom du restaurant : ");
			String nom = scan.nextLine();
			
			System.out.print("Veuillez saisir l'adresse du restaurant : ");
			String adresse = scan.nextLine();
			
			System.out.print("Veuillez saisir l'url de l'image du restaurant : ");
			String url_image = scan.nextLine();
			
			try {
				RestaurantBLL.insert(nom, adresse, url_image, null);
				insertionFailed = false;
			} catch (RestaurantException e) {
				insertionFailed = true;
				System.err.println("Echec de la création du restaurant :");
				System.err.println(e.getMessage());
			}
		} while (insertionFailed);
	}

	private static void modifierRestaurant() throws Exception {
		
		System.out.println("Saisissez l'id du restaurant à modifier, ou 0 pour retourner au menu principal.");
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
						System.err.println("L'identifiant sélectionné n'existe.");
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
			System.out.print("Veuillez saisir le nouveau nom du restaurant : ");
			String nom = scan.nextLine();
			
			System.out.print("Veuillez saisir la nouvelle adresse du restaurant : ");
			String adresse = scan.nextLine();
			
			System.out.print("Veuillez saisir la nouvelle url de l'image du restaurant : ");
			String url_image = scan.nextLine();
			
			Restaurant restoTemp = new Restaurant(choix, nom, adresse, url_image, null);
			
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
	
	private static void supprimerRestaurant() {
		System.out.println("Saisissez l'id du restaurant à supprimer, ou 0 pour retourner au menu principal.");
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
						System.err.println("L'identifiant sélectionné n'existe pas.");
						choix = -1;
					}
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Choix invalide");
				choix = -1;
			}
		} while (choix != 0);
	}

	//***********************************************************************************
	private static void afficherCarte(List<Carte> Cartes) {
		int totalLength = 4 + 30 + 50 + 30;
	    System.out.println("+" + "-".repeat(totalLength) + "+");
	    System.out.format("%-4s %-30s %-50s %-30s\n",
	                      "id", "nom", "description", "plats");
	    System.out.println("+" + "-".repeat(totalLength) + "+");
		for (Carte current : Cartes) {
			System.out.print(current);
		}
		 System.out.println("+" + "-".repeat(totalLength) + "+");
	}
	
	private static void ajouterCarte() throws Exception {
		boolean insertionFailed;
		do {
			System.out.print("Veuillez saisir le nom de la carte : ");
			String nom = scan.nextLine();
			
			System.out.print("Veuillez saisir la description de la carte : ");
			String description = scan.nextLine();
			
			try {
				CarteBLL.insert(nom, description);
				insertionFailed = false;
			} catch (CarteException e) {
				insertionFailed = true;
				System.err.println("Echec de la création de la carte :");
				System.err.println(e.getMessage());
			}
		} while (insertionFailed);
	}

	private static void modifierCarte() throws Exception {
		
		System.out.println("Saisissez l'id de la carte à modifier, ou 0 pour retourner au menu principal.");
		List<Carte> Cartes = CarteBLL.select();
		
		if (Cartes.isEmpty()) {
			System.err.println("Aucun élément à afficher.");
			return;
		}
		
		afficherCarte(Cartes);
		int choixCarte;
		do {
			try {
				choixCarte = scan.nextInt();
				
				if (Cartes.stream().map(Carte::getId).collect(Collectors.toList()).contains(choixCarte)) {
					scan.nextLine();
					break;
				} else {
					if (choixCarte != 0) {
						System.err.println("L'identifiant sélectionné n'existe pas.");
						choixCarte = -1;
					}
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Choix invalide");
				choixCarte = -1;
			}
			
		
		} while (choixCarte != 0);
		
		boolean updateFailed;
		do {
			System.out.print("Veuillez saisir le nouveau nom de la carte : ");
			String nom = scan.nextLine();
			
			System.out.print("Veuillez saisir la nouvelle description de la carte : ");
			String description = scan.nextLine();
			
			Carte carteTemp = new Carte(choixCarte, nom, description);
			
			try {
				CarteBLL.update(carteTemp);
				updateFailed = false;
			} catch (CarteException e) {
				updateFailed = true;
				System.err.println("Echec de la modification de la carte : ");
				System.err.println(e.getMessage());
			}
		} while (updateFailed);
	}
	
	private static void supprimerCarte() {
		System.out.println("Saisissez l'id de la carte à modifier, ou 0 pour retourner au menu principal.");
		List<Carte> Cartes = CarteBLL.select();
		
		if (Cartes.isEmpty()) {
			System.err.println("Aucun élément à afficher.");
			return;
		}
		
		afficherCarte(Cartes);
		int choixCarte;
		do {
			try {
				choixCarte = scan.nextInt();
				
				if (Cartes.stream().map(Carte::getId).collect(Collectors.toList()).contains(choixCarte)) {
					CarteBLL.delete(choixCarte);
					choixCarte = 0;
				} else {
					if (choixCarte != 0) {
						System.err.println("L'identifiant sélectionné n'existe pas.");
						choixCarte = -1;
					}
				}
				
			} catch (InputMismatchException e) {
				System.err.println("Choix invalide");
				choixCarte = -1;
			}
		} while (choixCarte != 0);
	}
	
	private static int afficherMenu() {
		int choix;
		do {
			System.out.println();
			System.out.println("Quelle action souhaitez-vous réaliser ?");
			System.out.println("\t1. Consulter les restaurants existants.");
			System.out.println("\t2. Creer un nouveau restaurant.");
			System.out.println("\t3. Modifier un restaurant.");
			System.out.println("\t4. Supprimer un restaurant.");
			System.out.println("\t5. Consulter les cartes existantes.");
			System.out.println("\t6. Creer une nouvelle carte.");
			System.out.println("\t7. Modifier une carte.");
			System.out.println("\t8. Supprimer une carte.");
			System.out.println("\t9. Quitter l'application");
			
			try {
				choix = scan.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Choix invalide.");
				choix = -1;
			} finally {
				scan.nextLine();
			}
		} while (choix < 1 || choix > 10);
		return choix;
	}
}