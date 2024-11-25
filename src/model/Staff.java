package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;

public class Staff extends User{
	private String origin;
	private String position;
	private String authorization;
	
	public Staff(String userid, String username, String password, String origin, String position,
			String authorization) {
		super(userid, username, password);
		this.origin = origin;
		this.position = position;
		this.authorization = authorization;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	
	public static void addStaff(String id, String origin, String position, String authorization) {
	    
	    String query = "INSERT INTO staff VALUES (?,?,?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.setString(2, origin);
	        preparedStatement.setString(3, position);
	        preparedStatement.setString(4, authorization);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static ResultSet getStaff(String userId) {
	    String queryStaff = "SELECT StaffOrigin, StaffPosition, StaffAuthorization FROM user us "
	            + "JOIN staff st ON st.StaffId = us.UserId WHERE us.UserId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(queryStaff);
	        preparedStatement.setString(1, userId);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static ResultSet getStaffbyUsername(String userName) {
	    String queryStaff = "SELECT StaffId, StaffOrigin, StaffPosition, StaffAuthorization FROM user us "
	            + "JOIN staff st ON st.StaffId = us.UserId WHERE us.UserName = ? AND StaffPosition NOT IN ('CEO') ";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(queryStaff);
	        preparedStatement.setString(1, userName);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public static void updateStaff(String id, String position) {
	    String query = "UPDATE staff SET StaffPosition = ? WHERE StaffId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, position);
	        preparedStatement.setString(2, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void deleteStaff(String id) {
	    String query = "DELETE FROM staff WHERE StaffId = ?";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    	String querys = "DELETE FROM user WHERE UserId = ?";
    	try {
    		PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(querys);
    		preparedStatement.setString(1, id);
    		preparedStatement.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
	    
	}
}
