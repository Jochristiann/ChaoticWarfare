package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;
import feature.HealFactorSkill;

public abstract class Silver extends Card implements HealFactorSkill{
	private int healEffect;
	public Silver(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower,
			int baseDefend, double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			int healEffect) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name);
		this.healEffect = healEffect;
		this.addSkillSpin("CS");
	}

	public int getHealEffect() {
		return healEffect;
	}

	public void setHealEffect(int healEffect) {
		this.healEffect = healEffect;
	}
	
	public abstract int comboSkill();
	
	public void newSilver(String type) {
		newCard("Silver");
		String query = "INSERT INTO silver VALUES (?,?,?)";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setInt(2, healEffect);
	        preparedStatement.setString(3, type);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void deleteSilver(String id) {
		String query = "DELETE FROM silver WHERE CardId = ?";
		try {
			PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteCard(id);
	}
	
	
	public static ResultSet getSilverbyCardId(String id) {
		String query = "SELECT HealEffect, SilverType FROM silver WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static ResultSet getSilverTypebyCardId(String id) {
		String query = "SELECT SilverType FROM silver WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static void updateHealSilverCard(String id, int value) {
		String query = "UPDATE silver SET HealEffect = ? WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setInt(1, value);
	        preparedStatement.setString(2, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
