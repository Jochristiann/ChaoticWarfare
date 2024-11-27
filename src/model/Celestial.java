package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import feature.SkydanceSkill;

public final class Celestial extends Ultra implements SkydanceSkill{

	private String skydanceName;

	public Celestial(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower,
			int baseDefend, double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			String ultraSkill, int ultraStats, int energyBoost, String specialSkill, int specialStats,
			String skydanceName) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats);
		this.skydanceName = skydanceName;
		this.addSkillSpin("S");
	}

	@Override
	public int ultraSkill() {
		int ultraDmg = 0;
		ultraDmg = (int) (25*this.getBaseAtkPen() + this.getBasePower()*this.getBaseAtkSpd()*this.getEnergyBoost()/2);
		System.out.printf(" %s is performing ultra skill! %s is giving %d\n", this.getName(),this.getUltraSkill(), ultraDmg);
		int rate = rand.nextInt(5);
		if(rate >= 4) {
			skydanceHeal();
		}
		return ultraDmg;
	}

	@Override
	public int specialSkill(int defEnemy) {
		int specialDmg = 0;
		specialDmg = 3*this.getEnergyBoost() + this.getBasePower()*this.getBaseAtkPen()/10 + this.getBasePower()*defEnemy;
		System.out.printf(" %s launchs special skills! %s gives damage %d\n", this.getName(),this.getSpecialSkill(), specialDmg);
		return specialDmg;
	}

	@Override
	public int skill1() {
		System.out.printf(" %s gains power! %s activated\n", this.getName(),this.getSkill1Name());
		int skill1Dmg = 0;
		skill1Dmg = (int) (this.getBaseAtkPen()*this.getBasePower()/2 + this.getEnergyBoost()*this.getBaseAtkSpd()*this.getBasePower()/25)+this.getBasePower();
		int lifesteal = (int) (skill1Dmg*0.1 + 0.001*this.getBaseHealth());
		System.out.printf(" %s gains lifesteal by %d!\n", this.getName(), lifesteal);
		this.setBaseHealth(lifesteal);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		System.out.printf(" %s revenges! %s is appeared\n", this.getName(),this.getSkill2Name());
		int skill2Dmg = 0;
		skill2Dmg = (int) (this.getBaseAtkPen()*this.getBasePower()/2 + (this.getBaseHealth()*0.05)*this.getBaseAtkSpd()*this.getBasePower())/35+this.getBasePower()/4;
		return skill2Dmg;
	} 

	@Override
	public void getDamage(int damage) {
		int realDamage = damage - this.getBaseDefend() - damage*this.getBaseDefPen()/100;
		if(realDamage < 0) {
			realDamage = 0;
		}
		System.out.printf(" %s got hit! Damage taken %d\n", this.getName(), realDamage);
		this.setHealthAftDmg(realDamage);
	}
	
	@Override
	public String skydanceName() {
		return this.skydanceName;
	}
	
	@Override
	public int skydanceSkills() {
		int skydance = (int) (this.getBaseAtkPen() * this.getBasePower()*0.5 + this.getEnergyBoost()*this.getBaseAtkPen()*this.getBaseDefend()/5);
		System.out.printf(" %s uses hidden noble power! %s is activated with power %d\n",this.getName(),this.skydanceName, skydance);
		return skydance;
	}

	@Override
	public void skydanceHeal() {
		int skydanceEffect = (int) (this.getBaseDefend()*this.getEnergyBoost()*1.5);
		this.setBaseHealth(skydanceEffect);

		System.out.printf(" %s gets heal chance! %d extra hp\n",this.getName(),skydanceEffect);
	}
	
	public void newCelestial() {
		String type = "Celestial";
		newUltra(type);
		String query = "INSERT INTO skydance VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, skydanceName);
	        preparedStatement.executeUpdate();
	        System.out.println(this.getName() + " is successfully added as Celestial Ultra card.");
	    } catch (SQLException e) {
	    }
	}
	
	public void newOffCelestial() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s#%s#%d#%d#%s#%d#%s\n", this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Ultra","Celestial",this.getUltraSkill(),this.getUltraStats(),this.getEnergyBoost(),this.getSpecialSkill(),this.getSpecialStats(),this.skydanceName);
				bw.write(data);
				System.out.println(getName() + " is successfully added to temporary database as Celestial Ultra card.");
			}
		} catch (IOException e) {
			System.out.println(" Failed to insert data.");
		}
	}
	
	public static void deleteCelestial(String id) {
		String query = "DELETE FROM skydance WHERE CardId = ?";
		try {
			PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteUltra(id);
	}
	
}
