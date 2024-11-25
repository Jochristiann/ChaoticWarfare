package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;

public abstract class Gold extends Card{
	private String awakeningSkill;

	public Gold(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend,
			double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			String awakeningSkill) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name);
		this.awakeningSkill = awakeningSkill;
		this.addSkillSpin("A");
	}

	public String getAwakeningSkill() {
		return awakeningSkill;
	}

	public void setAwakeningSkill(String awakeningSkill) {
		this.awakeningSkill = awakeningSkill;
	}
	
	public abstract int awakenSkill();
	
	public void newGold(String type) {
		newCard("Gold");
		String query = "INSERT INTO gold VALUES (?,?,?)";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, awakeningSkill);
	        preparedStatement.setString(3, type);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ResultSet getGoldTypebyCardId(String id) {
		String query = "SELECT GoldType FROM gold WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static void deleteGold(String id) {
		String query = "DELETE FROM gold WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		deleteCard(id);
	}
	
	public static ResultSet getGoldbyCardId(String id) {
		String query = "SELECT AwakeningSkill, GoldType FROM gold WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
}
