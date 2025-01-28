package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bll.RestaurantBLL;
import bo.TableRestaurant;
import exceptions.RestaurantException;

public class TestTables {
	private static Scanner scan;
	private static RestaurantBLL RestaurantBLL = new RestaurantBLL();
	

	public static void main(String[] args) throws Exception {
		
		scan = new Scanner(System.in);
				
		afficherMenuAjout();
		
		
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
			
			System.out.print("nb de places de la table ? :");
			int nbPlaces = scan.nextInt();
			scan.nextLine();
			
			System.out.print("numero de la table ? :");
			int numeroTable = scan.nextInt();
			scan.nextLine();
			
			List<TableRestaurant> tables = new ArrayList<>();
			TableRestaurant table = new TableRestaurant(nbPlaces, numeroTable);
			tables.add(table);
			
			try {
				RestaurantBLL.insert(nom, adresse, url_image, tables);
				insertionFailed = false;
			} catch (RestaurantException e) {
				insertionFailed = true;
				System.err.println("Echec de la création du resto :");
				System.err.println(e.getMessage());
			}
		} while (insertionFailed);
	}
	
	
	
	
	
	

}
