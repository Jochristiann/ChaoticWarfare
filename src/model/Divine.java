package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import feature.BlackMythSkill;

public final class Divine extends Ultra implements BlackMythSkill {
	
	private String blackMythSkill;
	
	public Divine(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower,
			int baseDefend, double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			String ultraSkill, int ultraStats, int energyBoost, String specialSkill, int specialStats,
			String blackMythSkill) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name, ultraSkill, ultraStats, energyBoost, specialSkill, specialStats);
		this.blackMythSkill = blackMythSkill;
		this.addSkillSpin("BS");
	}

	@Override
	public int ultraSkill() {
		int ultraDmg = 0 ;
		ultraDmg = (int) ((this.getEnergyBoost()*this.getBasePower())/5 + this.getBaseAtkPen()*this.getBasePower()/3 + this.getBasePower());
		System.out.printf(" %s divine ultra power appeared! %s deals %d damages\n",this.getName(),this.getUltraSkill(), ultraDmg);
		return ultraDmg;
	}

	@Override
	public int specialSkill(int defEnemy) {
		int specialDmg = 0;
		specialDmg = (int) (defEnemy*this.getBaseAtkSpd()*this.getEnergyBoost()/3 + this.getBasePower()/4);
		System.out.printf(" Special skill is activated! %s is giving %d damage\n", this.getSpecialSkill(),specialDmg);
		return specialDmg;
	}

	@Override
	public int skill1() {
		int skill1Dmg = 0;
		skill1Dmg = this.getBasePower()*this.getBaseAtkPen()+this.getBasePower();
		int rate = rand.nextInt(5);
		if(rate == 1) {
			skill1Dmg *= 12.5/100;
			System.out.println("Critical activated!");
		}
		System.out.printf(" %s hits enemy using %s! Damage deals %d!\n", this.getName(),this.getSkill1Name(), skill1Dmg);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		int skill2Dmg = 0;
		skill2Dmg = (int) ((int) (this.getBaseAtkSpd()*this.getBaseAtkPen()*this.getBasePower()/10 + this.getEnergyBoost()*this.getBasePower()*0.75 +this.getBasePower())/1.5);
		System.out.printf(" %s launchs divine powersource! %s divine power deals %d damage(s)\n", this.getName(), this.getSkill2Name(), skill2Dmg);
		return skill2Dmg;
	}

	@Override
	public void getDamage(int damage) {
		int rands = rand.nextInt(10);
		int realDamage = (int) (damage - this.getEnergyBoost()*0.1 - this.getBaseDefend() - this.getBaseDefend()*this.getBaseDefPen());
		if(rands == 3) {
			damage = 0;
			int heal = (int) (this.getBaseHealth()*0.45);
			this.setBaseHealth(heal);
			System.out.printf(" %s gets heal %d!\n", this.getName(),heal);
		}
		if(realDamage < 0) {
			realDamage = 0;
		}
		System.out.printf(" %s got hit! %d damage taken\n", this.getName(),realDamage);
		this.setHealthAftDmg(realDamage);
	}

	@Override
	public String blackMythName() {
		return this.blackMythSkill;
	}

	@Override
	public int blackMythSkills() {
		int blackMythDmg = (int) (this.getBasePower()*1.5 - this.getBaseHealth()/100);
		int cursedDmg = blackMythDmg/25;
		if(cursedDmg > this.getBaseHealth()*0.15) {
			cursedDmg = (int) (this.getBaseHealth()*0.15);
		}
		this.setHealthAftDmg(cursedDmg);
		System.out.printf(" %s casts the Black Myth Skills! This cursed skill gives %d damage and also hit %s %d damage(s)\n", this.getName(), blackMythDmg, this.getName(), cursedDmg);
		return blackMythDmg;
	}
	
	public void newDivine() {
		String type = "Divine";
		newUltra(type);
		String query = "INSERT INTO blackmyth VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, blackMythSkill);
	        preparedStatement.executeUpdate();
	        System.out.println(" "+this.getName() + " is successfully added as Divine Ultra card.");
	    } catch (SQLException e) {
	    }
	}
	
	public static void deleteDivine(String id) {
		String query = "DELETE FROM blackmyth WHERE CardId = ?";
		try {
			PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteUltra(id);
	}
	
	public void newOffDivine() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s#%s#%d#%d#%s#%d#%s\n", 
						this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Ultra","Divine",this.getUltraSkill(),this.getUltraStats(),this.getEnergyBoost(), this.getSpecialSkill(),this.getSpecialStats(),this.blackMythSkill);
				bw.write(data);
				System.out.println(" "+getName() + " is successfully added to temporary database as Divine Ultra card.");
			}catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println(" Failed to insert data.");
		}
	}
		
}
