package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import feature.BlackMythSkill;
public final class Beast extends Basic implements BlackMythSkill{

	private String blackMythSkill;

	public Beast(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend,
			double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			String blackMythSkill) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name);
		this.blackMythSkill = blackMythSkill;
		this.addSkillSpin("BS");
	}

	@Override
	public int skill1() {
		int skill1Dmg = 0;
		skill1Dmg = this.getBasePower()*this.getBaseAtkPen()+this.getBasePower();
		int rate = rand.nextInt(10);
		if(rate == 1) {
			skill1Dmg += (this.getBaseAtkPen()*this.getBasePower()/1.75);
			System.out.println("Extra damage activated!");
		}
		System.out.printf("%s hits enemy using %s! Damage deals %d!\n", this.getName(),this.getSkill1Name(), skill1Dmg);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		int skill2Dmg = 0;
		skill2Dmg = (int) (this.getBasePower()*1.5 + this.getBasePower()*this.getBaseAtkPen()/4);
		int rate = rand.nextInt(6);
		if(rate == 1) {
			skill2Dmg += blackMythSkills();
		}
		System.out.printf("%s is furious! %d damage(s) is given\n", this.getName(), skill2Dmg);
		return skill2Dmg;
	}

	@Override
	public String blackMythName() {
		return this.blackMythSkill;
	}

	@Override
	public int blackMythSkills() {
		int newPower = this.getBasePower()/this.getBaseAtkPen() ;
		if(newPower < 50) {
			newPower+=25;
		}
		this.setBasePower(newPower);
		System.out.printf("%s is using black myth skills and increases the base attack power by %d!\n", this.getName(),newPower);
		return newPower/2;
	}

	public void newBeast() {
		String type = "Beast";
		newBasic(type);
		String query = "INSERT INTO blackmyth VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, blackMythSkill);
	        preparedStatement.executeUpdate();
	        System.out.println(this.getName() + " is successfully added as Beast Basic card.");
	    } catch (SQLException e) {
	    }
	}
	
	public void newOffBeast() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s%s\n", this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Basic","Beast",blackMythSkill);
				bw.write(data);
				System.out.println(getName() + " is successfully added to temporary database as Beast Basic card.");
			}
		} catch (IOException e) {
			System.out.println("Failed to insert data.");
		}
	}
	
	public static void deleteBeast(String id) {
		String query = "DELETE FROM blackmyth WHERE CardId = ?";
		try {
			PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteBasic(id);
	}
	
}
