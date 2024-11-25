package view;

import controller.AppController;
import model.CurrentUser;
import model.Player;
import model.Staff;

public class ProfileView extends View{

	private CurrentUser currUser;
	private Player player;
	private Staff staff;
	
	public ProfileView(AppController apps) {
		super(apps);
		staff = null;
		player = null;
		currUser = CurrentUser.getInstance();
		if(currUser.getCurrentUser() instanceof Player) {
			this.player = (Player) currUser.getCurrentUser();
		}else {
			this.staff = (Staff) currUser.getCurrentUser();
		}
	}

	public void profileDisplay() {
		do {
			separator();
			System.out.println("Your Profile");
			System.out.printf("Username          : %s\n", currUser.getCurrentUser().getUsername());
			if(player != null) {
				System.out.printf("Current gold      : %d\n", player.getGold());
				System.out.printf("Current diamond   : %d\n", player.getDiamond());
				System.out.printf("Membership Status : %s\n", player.getMembershipGrade());
			}else {
				System.out.printf("Origin            : %s\n", staff.getOrigin());
				System.out.printf("Position          : %s\n", staff.getPosition());
				System.out.printf("Authorization     : %s\n", staff.getAuthorization());
			}
			System.out.println("==========================================");
			System.out.println("Settings");
			System.out.println("1. Update profile");
			System.out.println("2. Delete account");
			System.out.println("0. Back");
			try {
				System.out.print(">> ");
				int input = sc.nextInt();sc.nextLine();
				switch (input) {
				case 1:
					updateAcc();
					break;
				case 2:
					deleteAcc();
					break;
				case 0:
					apps.showLobbyView();
					break;
				default:
					break;
				}
			} catch (Exception e) {
			}
			
		} while (true);
	}
	
	private void updateAcc() {
		do {
			separator();
			System.out.println("Update Username");
			System.out.print("New username: ");
			String newusn = sc.nextLine();
			System.out.printf("Are you sure to update your username into %s? [y/n] ", newusn);
			String input = sc.nextLine();
			if(input.equalsIgnoreCase("y")) {
				if(currUser != null) {
					currUser.getCurrentUser().updateUser(newusn);
					System.out.println("Username changed!");
					enter();
					break;
				}
			}else if(input.equalsIgnoreCase("n")) {
				return;
			}else {
				System.out.println("Input must be between y or n (Case Insensitive)");
				enter();
			}
		} while (true);
	}

	
	private void deleteAcc() {
		do {
			System.out.print("Are you sure to delete your account? [y/n] ");
			String input = sc.nextLine();
			if(input.equalsIgnoreCase("y")) {
				
				if(player != null) {
					player.deletePlayer();
				}
				uc.logout();
				apps.showMenuView();
			}else if(input.equalsIgnoreCase("n")) {
				return;
			}else {
				System.out.println("Input must be between y or n (Case Insensitive)");
				enter();
			}
		} while (true);
	}
}
