package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import database.Connect;
import datasource.Data;
import model.*;

public class CardController {
	
	private static Data data;
	
	public CardController() {
		
	}
	
	public void newCelestial(String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, String ultraSkill, int ultraStats, int energyBoost,
			String specialSkill, int specialStats,String skydanceName) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		Celestial newCelestial = new Celestial(ids, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, skydanceName);
		if(Connect.getInstance()!=null) {
			newCelestial.newCelestial();
		}else {
			newCelestial.newOffCelestial();
		}
	}
	
	public void newDivine(String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, String ultraSkill, int ultraStats, int energyBoost,
			String specialSkill, int specialStats, String blackMythSkill) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		Divine newDivine = new Divine(ids, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats, blackMythSkill);
		if(Connect.getInstance()!=null) {
			newDivine.newDivine();
		}else {
			newDivine.newOffDivine();
		}
	}
	
	public void newNoble(String name,int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, String awakeningSkill, String skydanceSkill) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		Noble newNoble = new Noble(ids, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, awakeningSkill, skydanceSkill);
		if(Connect.getInstance()!=null) {
			newNoble.newNoble();
		}else {
			newNoble.newOffNoble();
		}
	}
	
	public void newSeaMonster(String name,int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, String awakeningSkill, String iceName) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		SeaMonster newSeaMonster = new SeaMonster(ids, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, awakeningSkill, iceName);
		if(Connect.getInstance()!=null) {
			newSeaMonster.newSeaMonster();
		}else {
			newSeaMonster.newOffSeaMonster();
		}
	}
	
	public void newGuardian(String name,int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, int healEffect, String earthSkills) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		
		Guardian newGuardian = new Guardian(ids, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, healEffect, earthSkills);
		if(Connect.getInstance()!=null) {
			newGuardian.newGuardian();
		}else {
			newGuardian.newOffGuardian();
		}
	}
	
	public void newAirKing(String name,int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, int healEffect) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		AirKing newAirking = new AirKing(ids, name,goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, healEffect);
		if(Connect.getInstance()!=null) {
			newAirking.newAirKing();
		}else {
			newAirking.newOffAirKing();
		}
	}
	
	public void newBeast(String name,int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, String blackMythSkill) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		Beast newBeast = new Beast(ids, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, blackMythSkill);
		if(Connect.getInstance()!=null) {
			newBeast.newBeast();
		}else {
			newBeast.newOffBeast();
		}
	}
	
	public void newMammals(String name,int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend, double baseAtkSpd, int baseAtkPen,
			int baseDefPen, String skill1Name, String skill2Name, int healRate) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		Mammals newMammals = new Mammals(ids, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen, skill1Name, skill2Name, healRate);
		if(Connect.getInstance()!=null){
			newMammals.newMammals();
		}else {
			newMammals.newOffMammals();
		}
	}
	
	public Card getCardsId(String ids) {
		ResultSet r = Card.getCardbyCardId(ids);
		try {
			while(r.next()) {
				String id = r.getString("CardId");
			    String name = r.getString("CardName");
			    int basePower = r.getInt("CardPower");
			    int health = r.getInt("CardHealth");
			    double atkSpd = r.getDouble("CardAtkSpd");
			    int atkPen = r.getInt("CardAtkPen");
			    int baseDefend = r.getInt("CardDefend");
			    int defPen = r.getInt("CardDefPen");
			    String skill1Name = r.getString("CardSkill1");
			    String skill2Name = r.getString("CardSkill2");
			    String grade = r.getString("CardGrade");
			    int goldPrice = r.getInt("CardGoldPrice");
			    int diamondPrice = r.getInt("CardDiamondPrice");
			    if(grade.equals("Ultra")) {
			    	ResultSet u = Ultra.getUltrabyCardId(id);
			    	while(u.next()) {
			    		String ultraSkill = u.getString("UltraSkill");
					    int ultraStats = u.getInt("UltraStats");
					    int ultraEnergyBoost = u.getInt("UltraEnergyBoost");
					    String utype = u.getString("UltraType");
					    String specialSkill = u.getString("SpecialSkill");
					    int specialStats = u.getInt("SpecialStats");
					    
					    if(utype.equals("Celestial")) {
					    	ResultSet s = Card.getSkydancebyCardId(id);
					    	while(s.next()) {
					    		String skydanceName = s.getString("SkydanceSkill");
					    		Celestial c = new Celestial(id, name, goldPrice, diamondPrice,health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, ultraSkill, ultraStats, ultraEnergyBoost, specialSkill, specialStats, skydanceName);
					    		return c;
					    	}
					    }else {
					    	ResultSet s = Card.getBlackMythbyCardId(id);
					    	while(s.next()) {
					    		String blackMythName = s.getString("BlackMythSkill");
					    		Divine d = new Divine(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, ultraSkill, ultraStats, ultraEnergyBoost, specialSkill, specialStats, blackMythName);
					    		return d;
					    	}
					    }
			    	}
			    }else if(grade.equals("Gold")) {
			    	ResultSet g = Gold.getGoldbyCardId(id);
			    	while(g.next()) {
			    		String awakeningSkill = g.getString("AwakeningSkill");
			    		String gtype = g.getString("GoldType");
			    		if(gtype.equals("Noble")) {
			    			ResultSet s = Card.getSkydancebyCardId(id);
			    			while(s.next()) {
			    				String skydanceName = s.getString("SkydanceSkill");
			    				Noble n = new Noble(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, awakeningSkill, skydanceName);
			    				return n;
			    			}
			    		}else {
			    			ResultSet i = Card.getIcebyCardId(id);
			    			while(i.next()) {
			    				String iceSkill = i.getString("IceSkill");
			    				SeaMonster sm = new SeaMonster(id, name, health, goldPrice, diamondPrice,basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, awakeningSkill, iceSkill);
			    				return sm;	
			    			}
			    		}
			    	}
			    }else if(grade.equals("Silver")) {
			    	ResultSet s = Silver.getSilverbyCardId(id);
			    	while(s.next()) {
			    		int healEffect = s.getInt("HealEffect");
			    		String stype = s.getString("SilverType");
			    		if(stype.equals("Guardian")) {
			    			ResultSet i = Card.getEarthbyCardId(id);
			    			while(i.next()) {
			    				String earthSkill = i.getString("EarthSkill");
			    				Guardian g = new Guardian(id, name,goldPrice, diamondPrice,  health, basePower,baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healEffect, earthSkill);
			    				return g;
			    			}
			    		}else {
			    			AirKing ak = new AirKing(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healEffect);
			    			return ak;
			    		}
			    	}
			    	
			    }else{
			    	ResultSet g = Basic.getBasicbyCardId(id);
			    	while(g.next()) {
			    		String gtype = g.getString("BasicType");
			    		if(gtype.equals("Beast")) {
			    			ResultSet s = Card.getBlackMythbyCardId(id);
					    	while(s.next()) {
					    		String blackMythName = s.getString("BlackMythSkill");
					    		Beast b = new Beast(id, name,goldPrice, diamondPrice,  health,basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, blackMythName);
					    		return b;
					    	}
			    		}else {
			    			ResultSet i = Card.getHealFactorbyCardId(id);
			    			while(i.next()) {
			    				int healRate = i.getInt("HealFactor");
			    				Mammals m = new Mammals(id, name, goldPrice, diamondPrice, health,basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healRate);
			    				return m;	
			    			}
			    		}
			    	}
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Card> getAllCardsByGrade(String grade) {
		ArrayList<Card> allCards = new ArrayList<Card>();
		ResultSet r = Card.getAllCardbyGrade(grade);
		try {
			while(r.next()) {
				String id = r.getString("CardId");
			    String name = r.getString("CardName");
			    int basePower = r.getInt("CardPower");
			    int health = r.getInt("CardHealth");
			    double atkSpd = r.getDouble("CardAtkSpd");
			    int atkPen = r.getInt("CardAtkPen");
			    int baseDefend = r.getInt("CardDefend");
			    int defPen = r.getInt("CardDefPen");
			    String skill1Name = r.getString("CardSkill1");
			    String skill2Name = r.getString("CardSkill2");
			    int goldPrice = r.getInt("CardGoldPrice");
			    int diamondPrice = r.getInt("CardDiamondPrice");
			    if(grade.equals("Ultra")) {
			    	ResultSet u = Ultra.getUltrabyCardId(id);
			    	while(u.next()) {
			    		String ultraSkill = u.getString("UltraSkill");
					    int ultraStats = u.getInt("UltraStats");
					    int ultraEnergyBoost = u.getInt("UltraEnergyBoost");
					    String utype = u.getString("UltraType");
					    String specialSkill = u.getString("SpecialSkill");
					    int specialStats = u.getInt("SpecialStats");
					    if(utype.equals("Celestial")) {
					    	ResultSet s = Card.getSkydancebyCardId(id);
					    	while(s.next()) {
					    		String skydanceName = s.getString("SkydanceSkill");
					    		Celestial c = new Celestial(id, name, goldPrice, diamondPrice,health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, ultraSkill, ultraStats, ultraEnergyBoost, specialSkill, specialStats, skydanceName);
					    		allCards.add(c);
					    	}
					    }else {
					    	ResultSet s = Card.getBlackMythbyCardId(id);
					    	while(s.next()) {
					    		String blackMythName = s.getString("BlackMythSkill");
					    		Divine d = new Divine(id, name,goldPrice, diamondPrice,  health,basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, ultraSkill, ultraStats, ultraEnergyBoost, specialSkill, specialStats, blackMythName);
					    		allCards.add(d);
					    	}
					    }
			    	}
			    }else if(grade.equals("Gold")) {
			    	ResultSet g = Gold.getGoldbyCardId(id);
			    	while(g.next()) {
			    		String awakeningSkill = g.getString("AwakeningSkill");
			    		String gtype = g.getString("GoldType");
			    		if(gtype.equals("Noble")) {
			    			ResultSet s = Card.getSkydancebyCardId(id);
			    			while(s.next()) {
			    				String skydanceName = s.getString("SkydanceSkill");
			    				Noble n = new Noble(id, name, goldPrice, diamondPrice,health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, awakeningSkill, skydanceName);
			    				allCards.add(n);
			    			}
			    		}else {
			    			ResultSet i = Card.getIcebyCardId(id);
			    			while(i.next()) {
			    				String iceSkill = i.getString("IceSkill");
			    				SeaMonster sm = new SeaMonster(id, name, goldPrice, diamondPrice, health,basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, awakeningSkill, iceSkill);
			    				allCards.add(sm);	
			    			}
			    		}
			    	}
			    }else if(grade.equals("Silver")) {
			    	ResultSet s = Silver.getSilverbyCardId(id);
			    	while(s.next()) {
			    		int healEffect = s.getInt("HealEffect");
			    		String stype = s.getString("SilverType");
			    		if(stype.equals("Guardian")) {
			    			ResultSet i = Card.getEarthbyCardId(id);
			    			while(i.next()) {
			    				String earthSkill = i.getString("EarthSkill");
			    				Guardian g = new Guardian(id, name, goldPrice, diamondPrice,health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healEffect, earthSkill);
			    				allCards.add(g);
			    			}
			    		}else {
			    			AirKing ak = new AirKing(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healEffect);
			    			allCards.add(ak);
			    		}
			    	}
			    	
			    }else{
			    	ResultSet g = Basic.getBasicbyCardId(id);
			    	while(g.next()) {
			    		String gtype = g.getString("BasicType");
			    		if(gtype.equals("Beast")) {
			    			ResultSet s = Card.getBlackMythbyCardId(id);
					    	while(s.next()) {
					    		String blackMythName = s.getString("BlackMythSkill");
					    		Beast b = new Beast(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, blackMythName);
					    		allCards.add(b);
					    	}
			    		}else {
			    			ResultSet i = Card.getHealFactorbyCardId(id);
			    			while(i.next()) {
			    				int healRate = i.getInt("HealFactor");
			    				Mammals m = new Mammals(id, name, goldPrice, diamondPrice, health,basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healRate);
			    				allCards.add(m);	
			    			}
			    		}
			    	}
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCards;
	}
	
	
	public ArrayList<Card> getAllCards() {
		ArrayList<Card> allCards = new ArrayList<Card>();
		ResultSet r = Card.getAllCard();
		try {
			while(r.next()) {
				String id = r.getString("CardId");
			    String name = r.getString("CardName");
			    int basePower = r.getInt("CardPower");
			    int health = r.getInt("CardHealth");
			    double atkSpd = r.getDouble("CardAtkSpd");
			    int atkPen = r.getInt("CardAtkPen");
			    int baseDefend = r.getInt("CardDefend");
			    int defPen = r.getInt("CardDefPen");
			    String skill1Name = r.getString("CardSkill1");
			    String skill2Name = r.getString("CardSkill2");
			    String grade = r.getString("CardGrade");
			    int goldPrice = r.getInt("CardGoldPrice");
			    int diamondPrice = r.getInt("CardDiamondPrice");
			    if(grade.equals("Ultra")) {
			    	ResultSet u = Ultra.getUltrabyCardId(id);
			    	while(u.next()) {
			    		String ultraSkill = u.getString("UltraSkill");
					    int ultraStats = u.getInt("UltraStats");
					    int ultraEnergyBoost = u.getInt("UltraEnergyBoost");
					    String utype = u.getString("UltraType");
					    String specialSkill = u.getString("SpecialSkill");
					    int specialStats = u.getInt("SpecialStats");
					    
					    if(utype.equals("Celestial")) {
					    	ResultSet s = Card.getSkydancebyCardId(id);
					    	while(s.next()) {
					    		String skydanceName = s.getString("SkydanceSkill");
					    		Celestial c = new Celestial(id, name, goldPrice, diamondPrice,health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, ultraSkill, ultraStats, ultraEnergyBoost, specialSkill, specialStats, skydanceName);
					    		allCards.add(c);
					    	}
					    }else {
					    	ResultSet s = Card.getBlackMythbyCardId(id);
					    	while(s.next()) {
					    		String blackMythName = s.getString("BlackMythSkill");
					    		Divine d = new Divine(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, ultraSkill, ultraStats, ultraEnergyBoost, specialSkill, specialStats, blackMythName);
					    		allCards.add(d);
					    	}
					    }
			    	}
			    }else if(grade.equals("Gold")) {
			    	ResultSet g = Gold.getGoldbyCardId(id);
			    	while(g.next()) {
			    		String awakeningSkill = g.getString("AwakeningSkill");
			    		String gtype = g.getString("GoldType");
			    		if(gtype.equals("Noble")) {
			    			ResultSet s = Card.getSkydancebyCardId(id);
			    			while(s.next()) {
			    				String skydanceName = s.getString("SkydanceSkill");
			    				Noble n = new Noble(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, awakeningSkill, skydanceName);
			    				allCards.add(n);
			    			}
			    		}else {
			    			ResultSet i = Card.getIcebyCardId(id);
			    			while(i.next()) {
			    				String iceSkill = i.getString("IceSkill");
			    				SeaMonster sm = new SeaMonster(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, awakeningSkill, iceSkill);
			    				allCards.add(sm);	
			    			}
			    		}
			    	}
			    }else if(grade.equals("Silver")) {
			    	ResultSet s = Silver.getSilverbyCardId(id);
			    	while(s.next()) {
			    		int healEffect = s.getInt("HealEffect");
			    		String stype = s.getString("SilverType");
			    		if(stype.equals("Guardian")) {
			    			ResultSet i = Card.getEarthbyCardId(id);
			    			while(i.next()) {
			    				String earthSkill = i.getString("EarthSkill");
			    				Guardian g = new Guardian(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healEffect, earthSkill);
			    				allCards.add(g);
			    			}
			    		}else {
			    			AirKing ak = new AirKing(id, name, goldPrice, diamondPrice, health, basePower,baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healEffect);
			    			allCards.add(ak);
			    		}
			    	}
			    	
			    }else{
			    	ResultSet g = Basic.getBasicbyCardId(id);
			    	while(g.next()) {
			    		String gtype = g.getString("BasicType");
			    		if(gtype.equals("Beast")) {
			    			ResultSet s = Card.getBlackMythbyCardId(id);
					    	while(s.next()) {
					    		String blackMythName = s.getString("BlackMythSkill");
					    		Beast b = new Beast(id, name,goldPrice, diamondPrice, health, basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, blackMythName);
					    		allCards.add(b);
					    	}
			    		}else {
			    			ResultSet i = Card.getHealFactorbyCardId(id);
			    			while(i.next()) {
			    				int healRate = i.getInt("HealFactor");
			    				Mammals m = new Mammals(id, name,  goldPrice, diamondPrice,health,basePower, baseDefend, atkSpd, atkPen, defPen, skill1Name, skill2Name, healRate);
			    				allCards.add(m);	
			    			}
			    		}
			    	}
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allCards;
	}
	
	
	private static Card getRandomCard(ArrayList<Card> cards) {
        Random random = new Random();
        return cards.get(random.nextInt(cards.size()));
    }
	
	public static ArrayList<Card> randomizeOpponentCards(String difficulty) {
		data = Data.getInstance();
		ArrayList<Card> selectedCards = new ArrayList<>();
        Random random = new Random();
        
     
        ArrayList<Card> basicCards = new ArrayList<Card>();
    	ArrayList<Card> silverCards = new ArrayList<Card>();
    	ArrayList<Card> goldCards = new ArrayList<Card>();
    	ArrayList<Card> ultraCards = new ArrayList<Card>();
    	
    	basicCards = data.getBasicCards();
        silverCards = data.getSilverCards();
        goldCards = data.getGoldCards();
        ultraCards = data.getUltraCards();
        
        switch (difficulty.toLowerCase()) {
            case "easy":
                selectedCards.add(getRandomCard(basicCards));
                selectedCards.add(getRandomCard(silverCards));
                selectedCards.add(getRandomCard(random.nextBoolean() ? silverCards : goldCards));
                break;
            case "hard":
                selectedCards.add(getRandomCard(silverCards));
                selectedCards.add(getRandomCard(random.nextBoolean() ? silverCards : goldCards));
                selectedCards.add(getRandomCard(random.nextBoolean() ? goldCards : ultraCards));
                break;
            case "extreme":
                selectedCards.add(getRandomCard(random.nextBoolean() ? silverCards : goldCards));
                selectedCards.add(getRandomCard(goldCards));
                selectedCards.add(getRandomCard(ultraCards));
                break;

            default:
                System.out.println("Invalid difficulty.");
                return selectedCards;
        }

        return selectedCards;
    }
	
	public static Card randomizeOneCards(String grade) {
		data = Data.getInstance();
		Card selectedCards = null;
        
        ArrayList<Card> basicCards = new ArrayList<Card>();
    	ArrayList<Card> silverCards = new ArrayList<Card>();
    	ArrayList<Card> goldCards = new ArrayList<Card>();
    	ArrayList<Card> ultraCards = new ArrayList<Card>();
    	
    	basicCards = data.getBasicCards();
        silverCards = data.getSilverCards();
        goldCards = data.getGoldCards();
        ultraCards = data.getUltraCards();
        
        switch (grade.toLowerCase()) {
            case "basic":
                return getRandomCard(basicCards);
            case "silver":
                return getRandomCard(silverCards);
            case "gold":
                return getRandomCard(goldCards);
            case "ultra":
                return getRandomCard(ultraCards);
            default:
                return selectedCards;
        }
    }
	
	public void deleteCard(String id) {
		String grade = typeCard(id);
		if(grade.equals("Celestial")) {
			Celestial.deleteCelestial(id);
		}else if(grade.equals("Divine")) {
			Divine.deleteDivine(id);
		}else if(grade.equals("Noble")) {
			Noble.deleteNoble(id);
		}else if(grade.equals("Sea Monster")) {
			SeaMonster.deleteSeaMonster(id);
		}else if(grade.equals("Air King")) {
			AirKing.deleteAirKing(id);
		}else if(grade.equals("Guardian")) {
			Guardian.deleteGuardian(id);
		}else if(grade.equals("Beast")) {
			Beast.deleteBeast(id);
		}else if(grade.equals("Mammals")) {
			Mammals.deleteMammals(id);
		}
		Data.migrateFreshData();
	}
	
	public String isCardExist(String name) {
		ResultSet res = Card.getCardbyName(name);
		try {
			if(res.next()) {
				return res.getString("CardId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String typeCard(String id) {
		ResultSet res = Card.getCardbyCardId(id);
		try {
			if(res.next()) {
				String grade = res.getString("CardGrade");
				if(grade.equals("Ultra")) {
					ResultSet res2 = Ultra.getUltraTypebyCardId(id);
					if(res2.next()) {
						return res2.getString("UltraType");
					}
				}else if(grade.equals("Gold")) {
					ResultSet res2 = Gold.getGoldTypebyCardId(id);
					if(res2.next()) {
						return res2.getString("GoldType");
					}
				}else if(grade.equals("Silver")) {
					ResultSet res2 = Silver.getSilverTypebyCardId(id);
					if(res2.next()) {
						return res2.getString("SilverType");
					}
				}else if(grade.equals("Basic")) {
					ResultSet res2 = Basic.getBasicTypebyCardId(id);
					if(res2.next()) {
						return res2.getString("BasicType");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateCard(String table, String col, String id, int value) {
		Card.updateIntCard(table, col, id, value);
	}
	
	
}
