package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;

public abstract class User {
	private String userid;
	private String username;
	private String password;

	public User(String userid, String username, String password) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static ResultSet getUser(String username) {
	    String query = "SELECT UserId,UserName,UserPassword, UserRole FROM user WHERE UserName = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, username);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}

	public static void addUser(String id, String username, String pass) {
	    
	    String query = "INSERT INTO user (UserId, UserName, UserPassword) VALUES (?, ?, ?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.setString(2, username);
	        preparedStatement.setString(3, pass);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void deleteUser() {
		String query = "DELETE FROM user WHERE UserId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.userid);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	public void updateUser(String name) {
		String query = "UPDATE user SET UserName = ? WHERE UserId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, name);
	        preparedStatement.setString(2, this.getUserid());
	        preparedStatement.executeUpdate();
	        this.setUsername(name);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
