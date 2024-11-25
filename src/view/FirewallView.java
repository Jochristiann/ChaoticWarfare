package view;

import controller.AppController;
import database.Connect;
import datasource.Company;

public class FirewallView extends View{

	public FirewallView(AppController apps) {
		super(apps);
	}

	public void firewallCompany() {
		do {
			separator();
			System.out.print("Enter company's secret code [0 to back]: ");
			String name = sc.nextLine();
			if(name.equals("0")) {
				break;
			}
			String result = Company.getInstance().isTryValid(name);
			if(result.equals("Success")) {
				firewallMenu();
				break;
			}else {
				System.out.println(result);
				enter();
			}
		} while (true);
	}
	
	private void firewallMenu() {
		int input = 0;
		do {
			separator();
			try {
				System.out.println("Chaotic Warfare Internal Menu");
				System.out.println("1. Card Management");
				System.out.println("2. Internal Management");
				System.out.println("0. Exit");
				System.out.print(">> ");
				input = sc.nextInt();sc.nextLine();
				if(input == 1) {
					apps.showOfflineView();
				}else if(input == 2) {
					if(Connect.getInstance() != null) {
						apps.showLoginView(true);
					}else {
						System.out.println("Currently closed. Please contact the database staff to follow up this issue.");
						enter();
					}
				}else if(input == 0){
					apps.showMenuView();
					break;
				}else {
					System.out.println("Invalid input. Please try again.");
					enter();
				}
			} catch (Exception e) {
				System.out.println("Input must be in number. Please try again.");
				enter();
			}
		} while (true);
	}
}
