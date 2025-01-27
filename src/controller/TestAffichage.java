package controller;

import bll.RestaurantBLL;

public class TestAffichage {
	public static void main(String[] args) {
		
		RestaurantBLL bll = new RestaurantBLL();
		
		System.out.println(bll.select());
		
	}
}
