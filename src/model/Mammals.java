package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import feature.HealFactorSkill;

public final class Mammals extends Basic implements HealFactorSkill{
	
	private int healRate;

	public Mammals(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower,
			int baseDefend, double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			int healRate) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name);
		this.healRate = healRate;
		this.addSkillSpin("H");
	}

	public int getHealRate() {
		return healRate;
	}

	public void setHealRate(int healRate) {
		this.healRate = healRate;
	}
	
	@Override
	public int skill1() {
		int skill1Dmg = 0;
		skill1Dmg = (int) (this.getBasePower()*this.getBaseAtkPen()*1.5+this.getBasePower()*1.2);
		healingEffect();
		System.out.printf(" %s hits enemy using %s! Damage deals %d!\n", this.getName(),this.getSkill1Name(), skill1Dmg);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		int skill2Dmg = this.getBaseHealth()*this.getHealRate()*2;
		this.setBaseHealth(skill2Dmg);
		System.out.printf(" %s casts skill %s and recover %d hp!\n", this.getName(),this.getSkill2Name(),skill2Dmg);
		return 0;
	}

	@Override
	public void healingEffect() {
		int heal = (int) (this.getBaseHealth()*0.45 + this.healRate*this.getBaseDefend()/1.75);
		this.setBaseHealth(heal);
		System.out.printf(" %s got heal! %d extra hp\n",this.getName(),heal);
	}
	
	public void newMammals() {
		String type = "Mammals";
		newBasic(type);
		String query = "INSERT INTO healfactor VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setInt(2, healRate); 
	        preparedStatement.executeUpdate();
	        System.out.println(" "+this.getName() + " is successfully added as Mammals Basic card.");
	    } catch (SQLException e) {
	    	
	    }
	}
	
	public void newOffMammals() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s#%d\n", this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Basic","Mammals",this.getHealRate());
				bw.write(data);
				System.out.println(getName() + " is successfully added to temporary database as Mammals Basic card.");
			}
		} catch (IOException e) {
			System.out.println(" Failed to insert data.");
		}
	}
	
	public static void deleteMammals(String id) {
		String query = "DELETE FROM healfactor WHERE CardId = ?";
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
