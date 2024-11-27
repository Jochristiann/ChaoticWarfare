package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import model.Card;
import model.CurrentUser;
import model.Player;
import model.Staff;
import model.User;

public class UserController {
	
	private CardController cc = new CardController();
	
	
	
	public UserController() {
		
	}
	
	public boolean validateUsername(String username) {
		if(username.isEmpty()) {
			System.out.println(" Username must be filled.");
			return false;
		}
		
		if(username.length() < 4) {
			System.out.println(" Username must be at least 4 characters");
			return false;
		}
		
		
		ResultSet resultUser = User.getUser(username);
		try {
			if(resultUser != null && resultUser.next()) {
				System.out.println(" Username is already used. Try again.");
				return false;
			}
		} catch (SQLException e) {
		}
		return true;
	}
	
	public boolean validateNewPassword(String password) {
	    if (password.length() < 6) {
	        System.out.println(" Password must be at least 6 characters.");
	        return false;
	    } 
	    boolean hasUpperCase = false;
	    boolean hasLowerCase = false;
	    boolean hasDigit = false;
	    boolean hasSpecialChar = false;

	    for (char c : password.toCharArray()) {
	        if (Character.isUpperCase(c)) {
	            hasUpperCase = true;
	        } else if (Character.isLowerCase(c)) {
	            hasLowerCase = true;
	        } else if (Character.isDigit(c)) {
	            hasDigit = true;
	        } else if ("@#$%^&+=!_*.-".indexOf(c) >= 0) {
	            hasSpecialChar = true;
	        }
	        if(hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar) {
	        	return true;
	        }
	    }
	    if (!hasUpperCase) {
	        System.out.println(" Password must contain at least one uppercase letter.");
	        return false;
	    }
	    if (!hasLowerCase) {
	        System.out.println(" Password must contain at least one lowercase letter.");
	        return false;
	    }
	    if (!hasDigit) {
	        System.out.println(" Password must contain at least one digit.");
	        return false;
	    }
	    if (!hasSpecialChar) {
	        System.out.println(" Password must contain at least one special character.");
	        return false;
	    }
	    
	    return true;
	}

	
	public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
	
	private static byte[] bytesFromHex(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
	
	private static String hashPassword(String newPass) {
		SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String pass = newPass + "24-1";
        try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] hashedBytes = md.digest(pass.getBytes());
			return bytesToHex(salt) + ":" + bytesToHex(hashedBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	
	public boolean validateLoginPassword(String dbPass, String passwordInput) {
		String[] parts = dbPass.split(":");
        String storedSalt = parts[0];
        String storedHash = parts[1];

        String pass = passwordInput + "24-1";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytesFromHex(storedSalt));
            byte[] hashedBytes = md.digest(pass.getBytes());
            return storedHash.equals(bytesToHex(hashedBytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	private ArrayList<Card> getOwnedCard(String id){
		ArrayList<Card> allCards = new ArrayList<Card>();
        ResultSet cards = Player.ownedCard(id);
        try {
			while(cards.next()) {
				String cardid;
				cardid = cards.getString("CardId");
				allCards.add(cc.getCardsId(cardid));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return allCards;
	}
	
	public boolean login(String username, String password) {
		if(username.isEmpty()) {
			System.out.println(" Username must be filled.");
			return false;
		}
		else if(password.isEmpty()) {
			System.out.println(" Password must be filled.");
			return false;
		}
		else if(password.length() < 5) {
			System.out.println(" Password must be at least 5 characters.");
			return false;
		}
		
		
		ResultSet result = User.getUser(username);
		try {
		    if (!result.next()) {
		        System.out.println(" Username not found. Please try again.");
		        return false;
		    }
		    String id = result.getString("UserId");
		    String pass = result.getString("UserPassword");
		    String role = result.getString("UserRole");
		    if (!validateLoginPassword(pass, password)) {
		        System.out.println(" Incorrect password. Please try again.");
		        return false;
		    }

		    if (role.equals("Player")) {
		        ResultSet players = Player.getPlayer(id);
		        while (players.next()) {
		            int gold = players.getInt("PlayerGold");
		            int diamond = players.getInt("PlayerDiamond");
		            String membership = players.getString("MembershipName");
		            int point = players.getInt("MembershipPoint");
		            ArrayList<Card> allCards = getOwnedCard(id);
		            
		            Player currentUser = new Player(id, username, password, membership, point, gold, diamond, allCards);
		            CurrentUser.setInstance(currentUser);
		        }
		    } else if (role.equals("Staff")) {
		        ResultSet staffs = Staff.getStaff(id);
		        while (staffs.next()) {
		            String origin = staffs.getString("StaffOrigin");
		            String position = staffs.getString("StaffPosition");
		            String authorization = staffs.getString("StaffAuthorization");
		            Staff currentUser = new Staff(id, username, password, origin, position, authorization);
		            CurrentUser.setInstance(currentUser);
		        }
		    }
		    System.out.println(" Successfully logged in.");
		    return true;

		} catch (SQLException e) {
		    e.printStackTrace();
		    return false;
		}
	}
	
	public void refreshOwnedCard() {
		Player pl = (Player) CurrentUser.getInstance().getCurrentUser();
		pl.setOwnedCard(getOwnedCard(pl.getUserid()));
	}
	
	public void logout() {
		CurrentUser.deleteInstance();
	}
	
	public void register(String username, String password) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		User.addUser(ids,username, hashPassword(password));
		Player.addPlayer(ids);
	}
	
	public void registerStaff(String username, String password, String origin, String position) {
		UUID id = UUID.randomUUID();
		String ids = id.toString();
		User.addUser(ids,username, hashPassword(password));
		String authorization = "Low";
		if(position.equals("CEO")||position.equals("Director")) {
			authorization = "High";
		}else if(position.equals("Head Division")) {
			authorization = "Mid";
		}
		Staff.addStaff(ids, origin, position, authorization);
		
	}
	
	public ArrayList<User> getAllStaff(){
		ArrayList<User> allStaff = new ArrayList<User>();
		ResultSet res = Staff.getAllStaff();
		try {
			while(res.next()) {
				String userid = res.getString("StaffId");
				String username = res.getString("UserName");
				String password = null;
				String origin = res.getString("StaffOrigin");
				String position = res.getString("StaffPosition");
				String authorization = res.getString("StaffAuthorization");
				Staff newStaff = new Staff(userid, username, password, origin, position, authorization);
				allStaff.add(newStaff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allStaff;
	}
	
	public boolean staffExist(String username) {
	
		ResultSet res = Staff.getStaffbyUsername(username);
		try {
			if(res.next()) {
				String pos = res.getString("StaffPosition");
				if(pos.equals("CEO")) {
					System.out.println(" You can't access this username.");
					return false;
				}else {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(" User not found.");
		return false;
	}
	
	public void updatePosition(String username, String position) {
		ResultSet res = Staff.getStaffbyUsername(username);
		String id = "";
		try {
			if(res.next()) {
				id = res.getString("StaffId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!(id.isEmpty()||id.isBlank())){
			Staff.updateStaff(id, position);
			System.out.println(" Successfully updated.");
		}else {
			System.out.println(" Failed to update");
		}
	}
	
	public void deleteStaff(String username) {
		ResultSet res = Staff.getStaffbyUsername(username);
		String id = "";
		try {
			if(res.next()) {
				id = res.getString("StaffId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!(id.isEmpty()||id.isBlank())){
			Staff.deleteStaff(id);
			System.out.println(" Successfully deleted.");
		}else {
			System.out.println(" Failed to delete");
		}
	}
}
