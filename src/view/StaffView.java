package view;


import controller.AppController;
import controller.CardController;
import database.Connect;
import datasource.Company;
import datasource.Data;
import model.Card;
import model.CurrentUser;
import model.Staff;
import model.Ultra;

public class StaffView extends View{
	
	private CardController cc;
	private Staff currentStaff;

	public StaffView(AppController apps) {
		super(apps);
		cc = new CardController();
		currentStaff = (Staff) CurrentUser.getInstance().getCurrentUser();
	}

	public void staffDisplay() {
		int input = 0;
		int threshold = 1;
		
		if(currentStaff.getAuthorization().equals("Mid")) {
			threshold = 4;
		}else if(currentStaff.getAuthorization().equals("High")) {
			threshold = 5;
		}
		do {
			try {
				separator();
				System.out.println("Welcome to Staff Page");
				System.out.println("1. Profile account");
				if(currentStaff.getAuthorization().equals("Mid") || currentStaff.getAuthorization().equals("High")) {
					System.out.println("2. Create new card");
					System.out.println("3. Update card");
					System.out.println("4. Delete card");
					if(currentStaff.getAuthorization().equals("High")) {
						System.out.println("5. Reset Blocked Registration");
					}
				}
				System.out.println("0. Logout");
				System.out.print(">> ");
				input = sc.nextInt();sc.nextLine();
				if(input >= 0 && input <= threshold) {
					if(input == 0) {
						logout();
						break;
					}
					switch (input) {
					case 1:
						apps.showProfileView();
						break;
					case 2:
						newCardDisplay();
						break;
					case 3:
						updateCard();
						break;
					case 4:
						deleteCard();
						break;
					case 5:
						Company.getInstance().resetTry();
						System.out.println("Successfully reset.");
						enter();
						break;
					}
					
				}else {
					System.out.println("Input must be between 0 until " + threshold);
				}
			} catch (Exception e) {
				System.out.println("Input must be in number.");
			}
			
		} while (true);
		
	}
	
	private void newCardDisplay() {
		do {
			try {
				separator();
				System.out.println("Choose Card Grade");
				System.out.println("1. Ultra");
				System.out.println("2. Gold");
				System.out.println("3. Silver");
				System.out.println("4. Basic");
				System.out.println("0. Cancel");
				System.out.print(">> ");
				int input = sc.nextInt();sc.nextLine();
				if(input >= 1 && input <= 4) {
					newCard(input);
					break;
				}else if(input == 0) {
					break;
				}else {
					System.out.println("Invalid input. Please try again.");
					enter();
				}
				
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Input must be in number between 1 until 5.");
				enter();
			}
		} while (true);
	}
	
	private void newCard(int cardType) {
		separator();
		int skillTreshold = 15;
		String name = inputNickName("Card name");
		int goldPrice = inputStats("Gold price",2000000);
		int diamondPrice = inputStats("Diamond price",1000);
		int health = inputStats("Base health",90000);
		int power = inputStats("Base power",1000);
		int atkPen = inputStats("Base atk pen",10);
		double atkSpd = inputStatsDouble("Base atk spd",5);
		int baseDef = inputStats("Base defense",900);
		int defPen = inputStats("Base def pen",10);
		String skill1 = inputName("Skill 1",skillTreshold);
		String skill2 = inputName("Skill 2",skillTreshold);
		int input;
		do {
			try {
				separator();
				System.out.println("Choose type");
				if (cardType == 1) {
					System.out.println("1. Celestial");
					System.out.println("2. Divine");
				}else if(cardType == 2) {
					System.out.println("1. Noble");
					System.out.println("2. Sea Monster");
				}else if(cardType == 3) {
					System.out.println("1. Guardian");
					System.out.println("2. Air King");
				}else {
					System.out.println("1. Beast");
					System.out.println("2. Mammals");
				}
				System.out.print(">> ");
				input = sc.nextInt();sc.nextLine();   
				if(input >= 1 && input <= 2) {
					break;
				}else {
					System.out.println("Choose the type by input 1 or 2");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Input must be in number between 1 and 2.");
				enter();
			}
		} while (true);
		
		separator();
		if(cardType == 1) {
			String ultraSkill = inputName("Ultra skill name",skillTreshold);
			int ultraStats = inputStats("Ultra stats",150);
			int energyBoost = inputStats("Energy boost",150);
			String specialSkill = inputName("Special skill name",skillTreshold);
			int specialStats = inputStats("Special stats", 200);
			if(input == 1) {
				String skydance = inputName("Skydance skill name",skillTreshold);
				cc.newCelestial(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, skydance);
			}else {
				String blackMyth = inputName("Black myth skill name",skillTreshold);
				cc.newDivine(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, blackMyth);
			}
		}else if(cardType == 2) {
			String awakeningSkill = inputName("Gold skill name",skillTreshold);
			if(input == 1) {
				String skydance = inputName("Skydance skill name",skillTreshold);
				cc.newNoble(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, awakeningSkill, skydance);
			}else {
				String iceName = inputName("Ice skill name",skillTreshold);
				cc.newSeaMonster(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, awakeningSkill, iceName);
			}
		}else if(cardType == 3) {
			int healEffect = inputStats("Heal effect",150);
			if(input == 1) {
				String earthSkill = inputName("Earth skill name",skillTreshold);
				cc.newGuardian(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, healEffect, earthSkill);
			}else {
				cc.newAirKing(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, healEffect);
			}
		}else if(cardType == 4) {
			if(input == 1) {
				String blackMythSkill = inputName("Black myth skill name",skillTreshold);
				cc.newBeast(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, blackMythSkill);
			}else {
				int healEffect = inputStats("Heal effect", 150);
				cc.newMammals(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, healEffect);
			}
		}
		if(Connect.getInstance() != null) {
			Data.migrateFreshData();
		}
		enter();
	}
	
	private String inputName(String ui, int threshold) {
		String input = "";
		do {
			try {	
				System.out.printf("%-25s : ",ui);
				input = sc.nextLine();
				if (input.length() < 3  || input.isEmpty()) {
					System.out.println("The name length must be more than 3 characters and less than " + threshold + " characters." );
					enter();
				}else if(input.contains("#")){
					System.out.println("Name is containing forbidden character. Try again.");
				}else {
					break;
				}
			} catch (Exception e) {
				
			}
		} while (true);
		return input;
	}
	
	private String inputNickName(String ui) {
		String input = "";
		do {
			try {	
				System.out.printf("%-25s : ",ui);
				input = sc.nextLine();
				if ((input.length() < 3 || input.length() > 15)  || input.isEmpty()) {
					System.out.println("The name length must be more than 3 characters and less than or equals 15 characters.");
				}else if(cc.isCardExist(input)!= null) {
					System.out.println("Name " + input + " is already used. Try another name.");
				}else if(input.contains("#")){
					System.out.println("Name is containing forbidden character \"#\". Try again.");
				}
				else {
					break;
				}
				enter();
			} catch (Exception e) {
				
			}
		} while (true);
		return input;
	}
	
	private int inputStats(String ui, int threshold) {
		int input = 0;
		do {
			try {
				System.out.printf("%-25s [Range 0 - %d] : ",ui, threshold);
				input = sc.nextInt();sc.nextLine();
				if (input <= 0) {
					System.out.println("Statistic must more than 0.");
					enter();
				}
				if (input > threshold) {
					System.out.printf("Statistic must less than or equals %d.", threshold);
					enter();
				}else {
					break;
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Input must be in number and more than 0.");
				enter();
			}
		} while (true);
		return input;
	}
	
	private double inputStatsDouble(String ui, int threshold) {
		double input = 0;
		do {
			try {
				System.out.printf("%-25s [Range 0 - %d]: ",ui, threshold);
				input = sc.nextDouble();sc.nextLine();
				if (input <= 0) {
					System.out.println("Statistic must more than 0.");
					enter();
				}
				if (input > threshold) {
					System.out.printf("Statistic must less than or equals %d.", threshold);
					enter();
				}else {
					break;
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Input must be in number and more than 0.");
				enter();
			}
		} while (true);
		return input;
	}
	
	private void tableSeparate() {
		for (int i = 0; i < 186; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private void showAllCard(){
		tableSeparate();
		System.out.println("Chaotic Warfare's All Staff");
		System.out.printf("|%-3s|%-20s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n"
				,"No.","Card Name", "Gold Price", "Diamond Price","Base Health", "Base Power","Base Defend","Base Atk Pen", "Base Atk Spd","Base Def Pen", "Ultra Stats", " Special Stats");
		tableSeparate();
		int index = 0;
		for (Card card: Data.getInstance().getGameCard()) {
			System.out.printf("|%-3d|%-20s|%-15d|%-15d|%-15d|%-15d|%-15d|%-15d|%-15.1f|%-15d|" ,index+1,card.getName(), card.getGoldPrice(), card.getDiamondPrice(),card.getBaseHealth(), card.getBasePower(),card.getBaseDefend(),card.getBaseAtkPen(), card.getBaseAtkSpd(), card.getBaseDefPen());
			if(card instanceof Ultra) {
				Ultra u = (Ultra)card;
				System.out.printf("%-15d|%-15d|\n", u.getUltraStats(), u.getSpecialStats());
			}else {
				System.out.printf("%-15s|%-15s|\n", "-", "-");
			}
			index++;
		}
		tableSeparate();
	}
	
	private void deleteCard() {
		do {
			separator();
			showAllCard();
			System.out.print("Input the name to delete the card [Case sensitive] [0 to exit]: ");
			String name = sc.nextLine();
			if(name.isBlank()) {
				System.out.println("Name must not blank. Try again.");
			}else if (name.equals("0")){
				break;
			}else {
				String id = cc.isCardExist(name);
				if(cc.isCardExist(name)!= null) {
					System.out.printf("Are you sure to delete card with name %s? [y/n] ", name);
					String input = sc.nextLine();
					if(input.equalsIgnoreCase("y")) {
						cc.deleteCard(id);
						System.out.println("Successfully delete " + name + ".");
					}else {
						System.out.println("Cancel to delete card.");
					}
					enter();
					break;
				}else {
					System.out.printf("Card with name %s is not found.\n", name);
					enter();
				}
			}
		} while (true);
	}
	
	private void updateUltra(String id, String grade) {
		
		do {
			separator();
			String col = "";
			int value = 0;
			try {
				System.out.println("Change ultra stats");
				System.out.println("1. Ultra Stats");
				System.out.println("2. Energy Boost");
				System.out.println("3. Special stats");
				System.out.println("0. Cancel");
				System.out.print(">> ");
				int input = sc.nextInt(); sc.nextLine();
				
				if(input == 0) break;
				
				switch (input) {
				case 1:
					value = inputStats("Ultra stats",150);
					col = "UltraStats";
					break;
				case 2:
					value = inputStats("Energy boost",150);
					col = "UltraEnergyBoost";
					break;
				case 3:
					value = inputStats("Special stats", 200);
					col = "SpecialStats";
					break;
				default:
					break;
				}
				
				if(input >=1 && input <= 3) {
					cc.updateCard("ultra",col, id, value);
					System.out.println("Successfully updated.");
					break;
				}
			} catch (Exception e) {
				System.out.println("Your input must be between 0 until 3.");
				enter();
			}
		} while (true);
		
	}
	
	private void printUpdateMenu() {
		System.out.println("What skill do you want to update?");
		System.out.println("1. Gold Price");
		System.out.println("2. Diamond Price");
		System.out.println("3. Base Health");
		System.out.println("4. Base Attack Power");
		System.out.println("5. Base Defend");
		System.out.println("6. Attack Penetration");
		System.out.println("7. Defend Penetration");
	}
	
	
	private void updateCard() {
		do {
			separator();
			int thresh = 7;
			String table = "card";
			System.out.println("Update Card Statistic\n");
			showAllCard();
			System.out.print("Input card name to update [Case sensitive]: ");
			String name = sc.nextLine();
			String id = cc.isCardExist(name);
			
			if(id != null) {
				String grade = cc.typeCard(id);
				
				printUpdateMenu();
				if(grade.equals("Celestial")|| grade.equals("Divine")) {
					thresh++;
					System.out.println("8. Ultra Stats");
				}
				System.out.println("0. Cancel");
				
				
				int input;
				do {
					try {
						System.out.print(">> ");
						input = sc.nextInt();sc.nextLine();
						if (input >= 0 && input <= thresh ) {
							break;
						} else {
							System.out.println("Your input must be between 0 until " + thresh);
							enter();
						}
					} catch (Exception e) {
						sc.nextLine();
						System.out.println("Invalid input. Please try again.");
						enter();
					}
				} while (true);
				
				if(input == 0) {
					break;
				}
				
				int changed = 0;
				String col = "";
				switch (input) {
				case 1:
					changed = inputStats("Gold price",2000000);
					col = "CardGoldPrice";
					break;
				case 2:
					changed = inputStats("Diamond price",1000);
					col = "CardDiamondPrice";
					break;
				case 3:
					changed = inputStats("Base health",90000);
					col = "CardHealth";
					break;
				case 4:
					changed = inputStats("Base power",200);
					col = "CardPower";
					break;
				case 5:
					changed = inputStats("Base defense",900);
					col = "CardDefend";
					break;
				case 6:
					changed = inputStats("Base atk pen",10);
					col = "CardAtkPen";
					break;
				case 7:
					changed = inputStats("Base def pen",10);
					col = "CardDefPen";
					break;
				case 8:
					updateUltra(id, grade);
					break;
				default:
					System.out.println("Invalid input.");
					break;
				}
				if (input >=1 && input <= 7 ) {
					cc.updateCard(table,col, id, changed);
					System.out.println("Successfully updated.");
				}
				break;
			}else {
				System.out.println("Card with name " + name + " doesn't exist. Try again");
				enter();
			}
		} while (true);
	}
}
