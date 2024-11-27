package database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import controller.CardController;
import model.AirKing;
import model.Beast;
import model.Celestial;
import model.Divine;
import model.Guardian;
import model.Mammals;
import model.Noble;
import model.SeaMonster;

public class FileDatabase {
	
	
	private static FileDatabase fileDB;
	private final static String filePath = "card.txt";
	private CardController cc;
	
	public FileDatabase() {
		cc = new CardController();
	}
	
	public static FileDatabase instance() {
		if(fileDB == null) {
			fileDB = new FileDatabase();
		}
		return fileDB;
	}

	public void migrateToDB() {
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String line;
			System.out.println(" Migrate Log:");
			try {
				while((line = br.readLine())!=null) {
					String[] parts = line.split("#");
					String id = parts[0];
					String name = parts[1];
					if(cc.isCardExist(name)!= null) {
						System.out.println(" "+name + " is not migrated because the name is already exist.");
						continue;
					}
					
					int gold = Integer.parseInt(parts[2]);
					int diamond = Integer.parseInt(parts[3]);
					int baseHealth = Integer.parseInt(parts[4]);
					int basePower = Integer.parseInt(parts[5]);
					int baseDefend = Integer.parseInt(parts[6]);
					double baseAtkSpd = Double.parseDouble(parts[7]);
					int baseAtkPen = Integer.parseInt(parts[8]);
					int baseDefPen = Integer.parseInt(parts[9]);
					String skill1Name = parts[10];
					String skill2Name = parts[11];
					String grade = parts[12];
					String type = parts[13];
					
					if(grade.equals("Ultra")) {
						String ultraSkill = parts[14];
						int ultraStats = Integer.parseInt(parts[15]);
						int energyBoost =  Integer.parseInt(parts[16]);
						String specialSkill = parts[17];
						int specialStats = Integer.parseInt(parts[18]);
						String feature = parts[19];
						if(type.equals("Celestial")) {
							Celestial c = new Celestial(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, feature);
							c.newCelestial();
						}else {
							Divine d = new Divine(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, feature);
							d.newDivine();
						}
					}
					else if(grade.equals("Gold")) {
						String awakeningSkill = parts[14];
						String feature = parts[15];
						if(grade.equals("Noble")) {
							Noble n = new Noble(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, awakeningSkill, feature);
							n.newNoble();
						}else {
							SeaMonster sm = new SeaMonster(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, awakeningSkill, feature);
							sm.newSeaMonster();
						}
					}
					else if(grade.equals("Silver")) {
						int healeffect = Integer.parseInt(parts[14]);
						if(type.equals("Guardian")) {
							String earthSkill = parts[15];
							Guardian g = new Guardian(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, healeffect, earthSkill);
							g.newGuardian();
						}else {
							AirKing ak = new AirKing(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, healeffect);
							ak.newAirKing();
						}
					}else {
						String feature = parts[14];
						if(type.equals("Beast")) {
							Beast b = new Beast(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, feature);
							b.newBeast();	
						}else {
							int heal = Integer.parseInt(feature);
							Mammals m = new Mammals(id, name, gold, diamond, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, heal);
							m.newMammals();
						}
					}
				}
				try (FileWriter writer = new FileWriter(filePath, false)) {
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			} catch (IOException e) {
				
			}
		} catch (FileNotFoundException e) {
			System.out.println(" File not found.");
		}
	}
	
	public void deleteDB() {
		try (FileWriter writer = new FileWriter(filePath, false)) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
