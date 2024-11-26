package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;

public abstract class Ultra extends Card{
	private String ultraSkill;
	private int ultraStats;
	private int energyBoost;
	private String specialSkill;
	private int specialStats; 

	public Ultra(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend,
			double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name, String ultraSkill,
			int ultraStats, int energyBoost, String specialSkill, int specialStats) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name);
		this.ultraSkill = ultraSkill;
		this.ultraStats = ultraStats;
		this.energyBoost = energyBoost;
		this.specialSkill = specialSkill;
		this.specialStats = specialStats;
		
		this.addSkillSpin("U");
		this.addSkillSpin("SC");
	}


	public int getEnergyBoost() {
		return energyBoost;
	}

	public void setEnergyBoost(int energyBoost) {
		this.energyBoost = energyBoost;
	}


	public String getSpecialSkill() {
		return specialSkill;
	}


	public void setSpecialSkill(String specialSkill) {
		this.specialSkill = specialSkill;
	}

	public int getSpecialStats() {
		return specialStats;
	}


	public void setSpecialStats(int specialStats) {
		this.specialStats = specialStats;
	}

	public String getUltraSkill() {
		return ultraSkill;
	}
	public void setUltraSkill(String ultraSkill) {
		this.ultraSkill = ultraSkill;
	}
	public int getUltraStats() {
		return ultraStats;
	}
	public void setUltraStats(int ultraStats) {
		this.ultraStats = ultraStats;
	}
	
	public abstract int ultraSkill();
	public abstract int specialSkill(int defEnemy);
	
	public void newUltra(String type) {
		newCard("Ultra");
		String query = "INSERT INTO ultra VALUES (?,?,?,?,?,?,?)";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, ultraSkill);
	        preparedStatement.setInt(3, ultraStats);
	        preparedStatement.setInt(4, energyBoost);
	        preparedStatement.setString(5, type);
	        preparedStatement.setString(6, specialSkill);
	        preparedStatement.setInt(7, specialStats);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ResultSet getUltraTypebyCardId(String id) {
		String query = "SELECT UltraType FROM ultra WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static void deleteUltra(String id) {
		String query = "DELETE FROM ultra WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		deleteCard(id);
	}
	
	public static ResultSet getUltrabyCardId(String id) {
		String query = "SELECT UltraSkill, UltraStats, UltraEnergyBoost, UltraType, SpecialSkill, SpecialStats FROM ultra WHERE CardId = ?";
		try { 
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
}
