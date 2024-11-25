package view;

import java.util.Scanner;

import controller.AppController;
import controller.UserController;
import database.Connect;

public abstract class View {
	public static Scanner sc = new Scanner(System.in);
	public static UserController uc = new UserController();
	public static Connect co = Connect.getInstance();
	public AppController apps;
	
	public View(AppController apps) {
		this.apps = apps;
	}
	
	public void enter() {
		System.out.println("Press enter to continue...");
		sc.nextLine();
	}
	
	public void separator() {
		for (int i = 0; i < 50; i++) {
			System.out.println("\n");
		}
	}
	
	public void logout() {
		System.out.println("Successfully logged out.");
		uc.logout();
		enter();
		apps.showMenuView();
	}
}
