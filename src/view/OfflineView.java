package view;

import controller.AppController;
import controller.CardController;
import database.Connect;
import datasource.Data;

public class OfflineView extends View {
	
	private CardController cc;

	public OfflineView(AppController apps) {
		super(apps);
		cc = new CardController();
	}

	public void offlineDisplay() {
		do {		
			separator();
			System.out.println(" Welcome to Temporary Database");
			System.out.println(" 1. Create card");
			System.out.println(" 0. Exit");
			System.out.print(" >> ");
			try {
				int input = sc.nextInt();sc.nextLine();
				if(input == 1) {
					newCardDisplay();
				}else if(input == 0) {
					break;
				}else {
					System.out.println(" Invalid input. Please try again.");
					enter();
				}
			} catch (Exception e) {
				System.out.println(" Input must be in number. Please try again");
				enter();
			}
		} while (true);
		
	}
	private void newCardDisplay() {
		do {
			try {
				separator();
				System.out.println(" Choose Card Grade");
				System.out.println(" 1. Ultra");
				System.out.println(" 2. Gold");
				System.out.println(" 3. Silver");
				System.out.println(" 4. Basic");
				System.out.println(" 0. Cancel");
				System.out.print(" >> ");
				int input = sc.nextInt();sc.nextLine();
				if(input >= 1 && input <= 4) {
					newCard(input);
					break;
				}else if(input == 0) {
					break;
				}else {
					System.out.println(" Invalid input. Please try again.");
					enter();
				}
				
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(" Input must be in number between 1 until 5.");
				enter();
			}
		} while (true);
	}
	
	private String inputName(String ui ) {
		String input = "";
		do {
			try {	
				System.out.printf(" %-25s : ",ui);
				input = sc.nextLine();
				if (input.length() < 3 || input.isEmpty()) {
					System.out.println(" The name length must be more than 3 characters.");
					enter();
				}else if(input.contains("#")){
					System.out.println(" Name is containing forbidden character. Try again.");
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
				System.out.printf(" %-25s : ",ui);
				input = sc.nextLine();
				if (input.length() < 3 || input.isEmpty()) {
					System.out.println(" The name length must be more than 3 characters.");
				}else if(input.contains("#")){
					System.out.println(" Name is containing forbidden character. Try again.");
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
				System.out.printf(" %-25s : ",ui);
				input = sc.nextInt();sc.nextLine();
				if (input <= 0) {
					System.out.println(" Statistic must be more than 0.");
					enter();
				}
				if (input > threshold) {
					System.out.printf(" Statistic must less than or equals %d.\n", threshold);
					enter();
				}else {
					break;
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(" Input must be in number and more than 0.");
				enter();
			}
		} while (true);
		return input;
	}
	
	private double inputStatsDouble(String ui, int threshold) {
		double input = 0;
		do { 
			try {
				System.out.printf(" %-25s : ",ui);
				input = sc.nextDouble();sc.nextLine();
				if (input <= 0) {
					System.out.println(" Statistic must be more than 0.");
					enter();
				}
				if (input > threshold) {
					System.out.printf(" Statistic must less than or equals %d.\n", threshold);
					enter();
				}else {
					break;
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(" Input must be in number and more than 0.");
				enter();
			}
		} while (true);
		return input;
	}
	
	private void newCard(int cardType) {
		separator();
		String name = inputNickName("Card name");
		int goldPrice = inputStats("Gold price",2000000);
		int diamondPrice = inputStats("Diamond price",1000);
		int health = inputStats("Base health",90000);
		int power = inputStats("Base power",1000);
		int atkPen = inputStats("Base atk pen",10);
		double atkSpd = inputStatsDouble("Base atk spd",5);
		int baseDef = inputStats("Base defense",900);
		int defPen = inputStats("Base def pen",10);
		String skill1 = inputName("Skill 1");
		String skill2 = inputName("Skill 2");
		int input;
		do {
			try {
				separator();
				System.out.println(" Choose type");
				if (cardType == 1) {
					System.out.println(" 1. Celestial");
					System.out.println(" 2. Divine");
				}else if(cardType == 2) {
					System.out.println(" 1. Noble");
					System.out.println(" 2. Sea Monster");
				}else if(cardType == 3) {
					System.out.println(" 1. Guardian");
					System.out.println(" 2. Air King");
				}else {
					System.out.println(" 1. Beast");
					System.out.println(" 2. Mammals");
				}
				System.out.print(" >> ");
				input = sc.nextInt();sc.nextLine();   
				if(input >= 1 && input <= 2) {
					break;
				}else {
					System.out.println(" Choose the type by input 1 or 2");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(" Input must be in number between 1 and 2.");
				enter();
			}
		} while (true);
		
		separator();
		if(cardType == 1) {
			String ultraSkill = inputName("Ultra skill name");
			int ultraStats = inputStats("Ultra stats",150);
			int energyBoost = inputStats("Energy boost",150);
			String specialSkill = inputName("Special skill name");
			int specialStats = inputStats("Special stats", 200);
			if(input == 1) {
				String skydance = inputName("Skydance skill name");
				cc.newCelestial(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, skydance);
			}else {
				String blackMyth = inputName("Black myth skill name");
				cc.newDivine(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, blackMyth);
			}
		}else if(cardType == 2) {
			String awakeningSkill = inputName("Gold skill name");
			if(input == 1) {
				String skydance = inputName("Skydance skill name");
				cc.newNoble(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, awakeningSkill, skydance);
			}else {
				String iceName = inputName("Ice skill name");
				cc.newSeaMonster(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, awakeningSkill, iceName);
			}
		}else if(cardType == 3) {
			int healEffect = inputStats("Heal effect",150);
			if(input == 1) {
				String earthSkill = inputName("Earth skill name");
				cc.newGuardian(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, healEffect, earthSkill);
			}else {
				cc.newAirKing(name, goldPrice, diamondPrice, health, power, baseDef, atkSpd, atkPen, defPen, skill1, skill2, healEffect);
			}
		}else if(cardType == 4) {
			if(input == 1) {
				String blackMythSkill = inputName("Black myth skill name");
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
}
