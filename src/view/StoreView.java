package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.AppController;
import controller.TransactionController;
import datasource.Data;
import model.Card;

public class StoreView extends View {
	
	private static Data data;
	private TransactionController cc;

	public StoreView(AppController apps) {
		super(apps);
		data = Data.getInstance();
		cc = new TransactionController();
	}
	
	private void showAllCard() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards = data.getGameCard();
		int columns = 3;
        int rows = (int) Math.ceil((double) cards.size() / columns);

        int cardIndex = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (cardIndex < cards.size()) {
                    System.out.printf(" [%-45s] ", cards.get(cardIndex).grid());
                    cardIndex++;
                }
            }
            System.out.println();
        }
    }

	public void storeDisplay() {
		do {
			separator();
			System.out.println(" !! Chaotic Warfare Store !!");
			System.out.println("\n\n");
			System.out.println(" Cards");
			showAllCard();
			System.out.print(" Enter card name to buy [0 to back] [case insensitive]: ");
			String name = sc.nextLine();
			if(name.equals("0")) {
				break;
			}else {
				if(cc.isCardValid(name)) {
					ResultSet res = cc.boughtCard(name);
					try {
						while(res.next()) {
							do {
								try {
									String id = res.getString("CardId");
									String type = res.getString("CardGrade");
									String acname = res.getString("CardName");
									int diamondPrice = res.getInt("CardDiamondPrice");
									int goldPrice = res.getInt("CardGoldPrice");
									
									separator();
									System.out.println(" Card Information");
									System.out.printf(" Type: %s\nName: %s\nGold Price: %d\nDiamond Price: %d\n", type, acname, goldPrice, diamondPrice);
									System.out.println(" Currency to buy");
									System.out.println(" 1. Gold");
									System.out.println(" 2. Diamond");
									System.out.println(" 0. Cancel");
									System.out.print(" >> ");
									int input = sc.nextInt();sc.nextLine();
									if(input == 1) {
										cc.buyCards(id, goldPrice, input);
										break;
									}else if(input == 2){
										cc.buyCards(id, diamondPrice, input);
										break;
									}else if(input == 0){
										System.out.println(" Purchasing card is cancelled :(");
										enter();
										break;
									}
									else {
										System.out.println(" Invalid input. Try again.");
										enter();
									}
								} catch (Exception e) {
									System.out.println(" Input must be a number between 1 or 2");
									enter();
								}
							} while (true);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				System.out.print(" Continue to buy item? [n to exit] ");
				String inputs = sc.nextLine();
				if(inputs.equalsIgnoreCase("n")) {
					break;
				}
			}
		} while (true);
		
	}
	

}
