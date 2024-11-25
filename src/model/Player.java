package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class Player extends User{
	private String membershipGrade;
	private int membershipPoint;
	private int gold;
	private int diamond;
	private ArrayList<Card> ownedCard;

	public Player(String userid, String username, String password, String membershipGrade, int membershipPoint,
			int gold, int diamond, ArrayList<Card> ownedCard) {
		super(userid, username, password);
		this.membershipGrade = membershipGrade;
		this.membershipPoint = membershipPoint;
		this.gold = gold;
		this.diamond = diamond;
		this.ownedCard = ownedCard;
	}

	public String getMembershipGrade() {
		return membershipGrade;
	}

	public void setMembershipGrade(String membershipGrade) {
		this.membershipGrade = membershipGrade;
	}

	public int getMembershipPoint() {
		return membershipPoint;
	}

	public void setMembershipPoint(int membershipPoint) {
		this.membershipPoint = membershipPoint;
	}

	public ArrayList<Card> getOwnedCard() {
		return ownedCard;
	}

	public void setOwnedCard(ArrayList<Card> ownedCard) {
		this.ownedCard = ownedCard;
	}

	public int getDiamond() {
		return diamond;
	}

	public void setDiamond(int diamond) {
		this.diamond = diamond;
		this.updateDiamondCurrency();
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
		this.updateGoldCurrency();
	}
		
	public static ResultSet getPlayer(String userId) {
	    String queryUser = "SELECT UserId,UserName,PlayerGold, PlayerDiamond, MembershipName, MembershipPoint FROM user us "
	            + "JOIN player pl ON pl.PlayerId = us.UserId WHERE us.UserId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(queryUser);
	        preparedStatement.setString(1, userId);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static void addPlayer(String id) {
	    
	    String query = "INSERT INTO player (PlayerId, PlayerGold) VALUES (?, 10000)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.executeUpdate();
	        System.out.println("Successfully registered! Please login to your account.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void deletePlayerCard() {
		String query = "DELETE FROM player_card WHERE PlayerId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getUserid());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void deletePlayer() {
		deletePlayerCard();
		String query = "DELETE FROM player WHERE PlayerId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getUserid());
	        preparedStatement.executeUpdate();
	        deleteUser();
	        System.out.println("Account is successfully deleted.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void buyCard(String id) {
		String query = "INSERT INTO player_card VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getUserid());
	        preparedStatement.setString(2, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ResultSet ownedCard(String id) {
		String queryUser = "SELECT DISTINCT CardId FROM player_card WHERE PlayerId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(queryUser);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	private void updateGoldCurrency() {
		String query = "UPDATE player SET PlayerGold = ? WHERE PlayerId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setInt(1, this.gold);
	        preparedStatement.setString(2, this.getUserid());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void updateDiamondCurrency() {
		String query = "UPDATE player SET PlayerDiamond = ? WHERE PlayerId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setInt(1, this.diamond);
	        preparedStatement.setString(2, this.getUserid());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ResultSet isCardOwned(String cardName) {
	    String queryUser = "SELECT DISTINCT PlayerId,pc.CardId FROM player_card pc JOIN card c ON pc.CardId = c.CardId WHERE PlayerId = ? AND CardName = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(queryUser);
	        preparedStatement.setString(1, this.getUserid());
	        preparedStatement.setString(2, cardName);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
