package view;

import controller.AppController;
import controller.GameController;
import model.Basic;
import model.Card;
import model.CurrentUser;
import model.Gold;
import model.Player;
import model.Silver;
import model.Ultra;

public class GameView extends View{

	private Player currUser;
	
	public GameView(AppController apps) {
		super(apps);
		currUser = (Player) CurrentUser.getInstance().getCurrentUser();
	}

	public void startGame() {
		do {
			try {
				separator();
				System.out.println("Choose mode");
				System.out.println("1. One versus One (1v1)");
				System.out.println("2. Bloody war");
				System.out.println("0. Cancel");
				System.out.print(">> ");
				int input = sc.nextInt();sc.nextLine();
				switch (input) {
				case 1:
					mode(1);
					break;
				case 2:
					mode(2);
					break;
				case 0:
					apps.showLobbyView();
					break;
				default:
					System.out.println("Invalid input. Your input must be between 1 or 2.");
					enter();
					break;
				}
				
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Invalid input. Your input must be between 1 or 2.");
				enter();
			}
		} while (true);
	}
	
	private void showOwnedCard() {
		int index = 1;
		System.out.println("Owned Card Name List");
		for (Card c : currUser.getOwnedCard()) {
			if(c instanceof Ultra) {
				System.out.printf("%d. %-20s - Ultra\n",index, c.getName());
			}else if(c instanceof Gold) {
				System.out.printf("%d. %-20s - Gold\n", index, c.getName());
			}else if(c instanceof Silver) {
				System.out.printf("%d. %-20s - Silver\n", index, c.getName());
			}else if(c instanceof Basic) {
				System.out.printf("%d. %-20s - Basic\n", index, c.getName());
			}
			index++;
		}
	}
	
	private void mode(int mode) {
		
		if(currUser.getOwnedCard().isEmpty()) {
			System.out.println("You must have atleast one card. You can buy in the store.");
			enter();
			return;
		}
		Card choosenCard = null;
		do {
			separator();
			showOwnedCard();
			System.out.print("Choose your card by name [Case insensitive]: ");
			String name = sc.nextLine();
			for (Card c : currUser.getOwnedCard()) {
				if(c.getName().equalsIgnoreCase(name)) {
					choosenCard = c;
				}
			}
			if(choosenCard != null) {
				break;
			}else {
				System.out.println("You dont have any card with name "+name);
				enter();
			}
		} while (true);
		
		String modeInput = "";
		do {
			try {
				separator();
				System.out.println("Choose difficulty");
				System.out.println("1. Easy");
				System.out.println("2. Hard");
				System.out.println("3. Extreme");
				System.out.println("0. Exit");
				System.out.print(">> ");
				int modes = sc.nextInt();sc.nextLine();
				switch (modes) {
				case 1:
					modeInput = "Easy";
					break;
				case 2:
					modeInput = "Hard";
					break;
				case 3:
					modeInput = "Extreme";
					break;
				case 0:
					return;
				default:
					System.out.println("You must input value between 1 until 3.");
					break;
				}
				
				if (modeInput.equals("Easy") || modeInput.equals("Hard")||modeInput.equals("Extreme")) {
					break;
				}
				
			} catch (Exception e) {
				System.out.println("Your input must be a number that in range 1 until 3.");
				enter();
			}
		} while (true);
		if(mode == 1) {
			GameController.createInstance(choosenCard, "PvP", modeInput, apps);
		}else {
			GameController.createInstance(choosenCard, "Bloody War", modeInput, apps);
		}
		GameController.deleteInstance();
		enter();
		apps.showLobbyView();
	}
}
