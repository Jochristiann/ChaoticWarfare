package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;

public abstract class Basic extends Card{
	
	

	public Basic(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend,
			double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name);
	}

	@Override
	public void getDamage(int damage) {
		int realDmg = damage - this.getBaseDefend() - this.getBaseDefPen()*damage;
		if(realDmg < 0) {
			realDmg = 0;
		}
		this.setHealthAftDmg(realDmg);
		System.out.printf("%s gets damage from enemy!\n", realDmg);
	}
	
	public ResultSet getBasicCardbyCardId(String cardId) {
		String query = String.format("SELECT * ", cardId);
		ResultSet result = ct.executeQuery(query);
		return result;
	}
	
	public void newBasic(String type) {
		newCard("Basic");
		String query = "INSERT INTO basic VALUES (?,?)";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, type);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ResultSet getBasicTypebyCardId(String id) {
		String query = "SELECT BasicType FROM basic WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
	public static void deleteBasic(String id) {
		String query = "DELETE FROM basic WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		deleteCard(id);
	}
	
	
	public static ResultSet getBasicbyCardId(String id) {
		String query = "SELECT BasicType FROM basic WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}

}
