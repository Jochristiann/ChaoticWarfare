package view;

import controller.AppController;

public class LobbyView extends View{

	public LobbyView(AppController apps) {
		super(apps);
		// TODO Auto-generated constructor stub
	}

	public void lobbyDisplay() {
		do {
			try {
				separator();
				System.out.println("Game Lobby");
				System.out.println("1. Play game");
				System.out.println("2. Store");
				System.out.println("3. How to Play");
				System.out.println("4. Profile");
				System.out.println("0. Log out");
				System.out.print(">> ");
				int input = sc.nextInt();sc.nextLine();
				switch (input) {
				case 1:
					apps.showGameView();
					break;
				case 2:
					apps.showStoreView();
					break;
				case 3:
					apps.showhowtoplayView();
					break;
				case 4:
					apps.showProfileView();
					break;
				case 0:
					logout();
					break;
				default:
					System.out.println("Invalid input. Please try again.");
					break;
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Input must be in number between 0 until 4.");
				enter();
			}
		} while (true);
	}
	
	
}
