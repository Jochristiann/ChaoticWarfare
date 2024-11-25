package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;
import feature.EarthSkill;

public final class Guardian extends Silver implements EarthSkill{

	private String earthSkills;

	public Guardian(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower,
			int baseDefend, double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			int healEffect, String earthSkills) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name, healEffect);
		this.earthSkills = earthSkills;
		this.addSkillSpin("E");
	}

	@Override
	public void healingEffect() {
		int heal = (int) (150 + 0.2*this.getBaseHealth());
		System.out.printf("%s is regenerating and get %d hp.\n", this.getName(),heal);
		this.setBaseHealth(heal);
	}

	@Override
	public int comboSkill() {
		int comboDmg = (int) (this.getBasePower()*this.getBaseAtkSpd()*2.5 + this.getBaseDefPen()*this.getBaseHealth());
		System.out.printf("Combo skill activated! %s is giving %d damage\n", this.getName(),comboDmg);
		return comboDmg;
	}

	@Override
	public int skill1() {
		int skill1Dmg = this.getBasePower()*this.getBaseAtkPen() + this.getBasePower();
		System.out.printf("%s casts %d damage\n", this.getName(),skill1Dmg);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		int skill2Dmg = this.getBasePower()*this.getBaseAtkPen() + this.getBasePower();
		this.healingEffect();
		System.out.printf("%s casts skill %s! The skills is giving %d damage!\n", this.getName(),this.getSkill2Name(),skill2Dmg);
		return skill2Dmg;
	}

	@Override
	public void getDamage(int damage) {
		int realDmg = (int) (damage - this.getBaseDefend()*1.2 - this.getBaseDefend()*this.getBaseDefPen()*0.1);
		if(realDmg < 0) {
			realDmg = 0;
		}
		System.out.printf("%s hitted! Damage taken %d\n", realDmg);
		int healEff = (int) (realDmg *0.01);
		this.setBaseHealth(healEff);
		System.out.printf("Wow! %s gets lifesteal %d\n", healEff);
		this.setHealthAftDmg(realDmg);
	}

	@Override
	public String earthName() {
		return earthSkills;
	}

	@Override
	public int earthSkills() {
		int earthDmg = (int) (this.getBaseDefend()*this.getBaseHealth()/1.75 + 1000);
		System.out.printf("Earth skill casted! Gives %d damage(s)\n", earthDmg);
		return earthDmg;
	}

	public void newGuardian() {
		String type = "Guardian";
		newSilver(type);
		String query = "INSERT INTO earth VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, earthSkills);
	        preparedStatement.executeUpdate();
	        System.out.println(this.getName() + " is successfully added as Guardian Silver card.");
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void deleteGuardian(String id) {
		String query = "DELETE FROM earth WHERE CardId = ?";
		try {
			PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteSilver(id);
	}
	
	public void newOffGuardian() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s#%d#%s\n", this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Ultra","Celestial",this.getHealEffect(),this.earthName());
				bw.write(data);
				System.out.println(getName() + " is successfully added to temporary database as Guardian Silver card.");
			}
		} catch (IOException e) {
			System.out.println("Failed to insert data.");
		}
	}
	
	public static ResultSet getGuardianbyCardId(String id) {
		String query = "SELECT EarthSkill FROM earth WHERE CardId = ?";
		try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, id);
	        return preparedStatement.executeQuery();
	    } catch (SQLException e) {
	        return null;
	    }
	}
	
}
