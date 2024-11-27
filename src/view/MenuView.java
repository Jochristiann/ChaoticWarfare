package view;

import controller.AppController;
import database.Connect;

public class MenuView extends View {
	
	
	public MenuView(AppController apps) {
		super(apps);
	}

	public void menuDisplay() {
		while (true) {
			try {
				separator();
				System.out.println(" Welcome to Chaotic Warfare Landing Page!");
				System.out.println(" 1. Login");
				System.out.println(" 2. Register");
				System.out.println(" 3. Internal Company");
				System.out.println(" 0. Exit");
				System.out.print(" >> ");
				int input = sc.nextInt();sc.nextLine();
				switch (input) {
				case 1:
					if(Connect.getInstance() != null) {
						apps.showLoginView(false);
					}else {
						System.out.println(" Menu closed. Try again later.");
					}
					break;
				case 2:
					if(Connect.getInstance() != null) {
						apps.showRegisterView();
					}else {
						System.out.println(" Menu closed. Try again later.");
					}
					break;
				case 3:
					apps.showFirewallView();
					break;
				case 0:
					separator();
					System.out.println(" Thank you for enjoying Chaotic Warfare");
					System.exit(0);
					break;
				}
				enter();
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(" Input must be a number 1 or 2.");
				enter();
			}
		}
	}
	

}
