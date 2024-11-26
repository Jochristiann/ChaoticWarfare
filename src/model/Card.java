package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import database.Connect;

public abstract class Card {
	private String id;
	private String name;
	private int goldPrice;
	private int diamondPrice;
	private int baseHealth;
	private int basePower;
	private int baseDefend;
	private double baseAtkSpd;
	private int baseAtkPen;
	private int baseDefPen;
	private String skill1Name;
	private String skill2Name;
	private ArrayList<String> skillSpin;
	private volatile ArrayList<String> valueSpin;
	public static Connect ct = Connect.getInstance();
	public static Random rand = new Random();

	public Card(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend,
			double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name) {
		super();
		this.id = id;
		this.name = name;
		this.goldPrice = goldPrice;
		this.diamondPrice = diamondPrice;
		this.baseHealth = baseHealth;
		this.basePower = basePower;
		this.baseDefend = baseDefend;
		this.baseAtkSpd = baseAtkSpd;
		this.baseAtkPen = baseAtkPen;
		this.baseDefPen = baseDefPen;
		this.skill1Name = skill1Name;
		this.skill2Name = skill2Name;
		
		skillSpin = new ArrayList<String>();
		valueSpin = new ArrayList<String>();
		skillSpin.add("G");
		skillSpin.add("T");
		valueSpin.add("10");
		valueSpin.add("20");
		valueSpin.add("30");
		valueSpin.add("40");
		valueSpin.add("50");
		valueSpin.add("");
	}
	
	public ArrayList<String> getSkillSpin() {
		return skillSpin;
	}

	public void addSkillSpin(String skillSpin) {
		this.skillSpin.add(skillSpin);
	}

	public ArrayList<String> getValueSpin() {
		return valueSpin;
	}

	public void setValueSpin(String valueSpin) {
		this.valueSpin.set(5, valueSpin);
	}

	public int getGoldPrice() {
		return goldPrice;
	}

	public void setGoldPrice(int goldPrice) {
		this.goldPrice = goldPrice;
	}
	
	public int getDiamondPrice() {
		return diamondPrice;
	}

	public void setDiamondPrice(int diamondPrice) {
		this.diamondPrice = diamondPrice;
	}

	public int getBaseHealth() {
		return baseHealth;
	}

	public void setBaseHealth(int baseHealth) {
		this.baseHealth += baseHealth;
	}
	
	public void setHealthAftDmg(int dmg) {
		if(dmg < 0) {
			return;
		}
		if(dmg > this.baseHealth) {
			this.baseHealth = 0;
			return;
		}
		
		this.baseHealth -= dmg;
	}

	public String getName() {
		return name;
	}

	public int getBasePower() {
		return basePower;
	}

	public int getBaseDefend() {
		return baseDefend;
	}

	public double getBaseAtkSpd() {
		return baseAtkSpd;
	}

	public int getBaseAtkPen() {
		return baseAtkPen;
	}
	
	public int getBaseDefPen() {
		return baseDefPen;
	}

	public String getSkill1Name() {
		return skill1Name;
	}

	public void setSkill1Name(String skill1Name) {
		this.skill1Name = skill1Name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasePower(int basePower) {
		this.basePower += basePower;
	}

	public void setBaseDefend(int baseDefend) {
		this.baseDefend = baseDefend;
	}

	public void setBaseAtkPen(int baseAtkPen) {
		this.baseAtkPen = baseAtkPen;
	}

	public void setBaseDefPen(int baseDefPen) {
		this.baseDefPen = baseDefPen;
	}
	
	public String getSkill2Name() {
		return skill2Name;
	}

	public void setSkill2Name(String skill2Name) {
		this.skill2Name = skill2Name;
	}

	public void setBaseAtkSpd(double baseAtkSpd) {
		this.baseAtkSpd = baseAtkSpd;
	}
	public String getId() {
		return id;
	}

	public abstract int skill1();
	public abstract int skill2();
	public abstract void getDamage(int damage);
	
	public String grid() {
		String result = String.format("%-15s|Gold: %-8d|Diamond: %-5d", name, goldPrice, diamondPrice);
        return result;
    }
	
	public static ResultSet getCardbyUserId(String id) {
		String query = "SELECT CardId, CardName, CardPower, CardHealth, CardAtkSpd, CardAtkPen, CardDefend, CardDefPen, CardSkill1,CardSkill2, CardGrade, CardGoldPrice, CardDiamondPrice FROM card c JOIN user_card uc ON uc.CardId = c.CardId JOIN user us ON us.UserId = uc.UserId WHERE us.UserId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getCardbyCardId(String id) {
		String query = "SELECT CardId, CardName, CardPower, CardHealth, CardAtkSpd, CardAtkPen, CardDefend, CardDefPen, CardSkill1,CardSkill2, CardGrade, CardGoldPrice, CardDiamondPrice FROM card WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public void newCard(String grade) {
		String query = "INSERT INTO card VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.setString(2, name);
	        preparedStatement.setInt(3, basePower);
	        preparedStatement.setInt(4, baseHealth);
	        preparedStatement.setDouble(5, baseAtkSpd);
	        preparedStatement.setInt(6, baseAtkPen);
	        preparedStatement.setInt(7, baseDefend);
	        preparedStatement.setInt(8, baseDefPen);
	        preparedStatement.setString(9, skill1Name);
	        preparedStatement.setString(10, skill2Name);
	        preparedStatement.setString(11, grade);
	        preparedStatement.setInt(12, goldPrice);
	        preparedStatement.setInt(13, diamondPrice);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void updateIntCard(String table, String col, String id, int value) {
		String query = String.format("UPDATE %s SET %s = ? WHERE CardId = ?", table, col);
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setInt(1, value);
	        preparedStatement.setString(2, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void updateHealFactor(String id, int value) {
		String query = "UPDATE healfactor SET HealFactor = ? WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setInt(1, value);
	        preparedStatement.setString(2, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void deleteCard(String id) {
		String query = "DELETE FROM player_card WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		String querys = "DELETE FROM card WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(querys);
	        preparedStatement.setString(1, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ResultSet getAllCard() {
		String query = "SELECT CardId, CardName, CardPower, CardHealth, CardAtkSpd, CardAtkPen, CardDefend, CardDefPen, CardSkill1,CardSkill2, CardGrade, CardGoldPrice, CardDiamondPrice FROM card";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getAllCardbyGrade(String grade) {
		String query = "SELECT CardId, CardName, CardPower, CardHealth, CardAtkSpd, CardAtkPen, CardDefend, CardDefPen, CardSkill1,CardSkill2, CardGoldPrice, CardDiamondPrice FROM card WHERE CardGrade = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, grade);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getCardbyName(String name) {
		String query = "SELECT CardId, CardName, CardGoldPrice, CardDiamondPrice, CardGrade FROM card WHERE CardName = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, name);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        return null;
	    }
	} 
	
	public static ResultSet getSkydancebyCardId(String id) {
		String query = "SELECT SkydanceSkill FROM skydance WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getHealFactorbyCardId(String id) {
		String query = "SELECT HealFactor FROM healfactor WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getBlackMythbyCardId(String id) {
		String query = "SELECT BlackMythSkill FROM blackmyth WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getIcebyCardId(String id) {
		String query = "SELECT IceSkill FROM ice WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getFirebyCardId(String id) {
		String query = "SELECT FireSkill FROM fire WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getEarthbyCardId(String id) {
		String query = "SELECT EarthSkill FROM earth WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
}
