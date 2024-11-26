package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import feature.IceSkill;

public final class SeaMonster extends Gold implements IceSkill {

	private String iceName;

	public SeaMonster(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower,
			int baseDefend, double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			String awakeningSkill, String iceName) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name, awakeningSkill);
		this.iceName = iceName;
		this.addSkillSpin("I");
	}

	@Override
	public int awakenSkill() {
		int awakenDmg = (int) (this.getBaseAtkPen()*this.getBasePower() + this.getBaseAtkSpd()*3*(this.getBasePower()/10));
		System.out.printf("%s the Sea Monster is using skill %s and giving %d damage\n", this.getName(), this.getAwakeningSkill(),awakenDmg);
		return awakenDmg;
	}

	@Override
	public int skill1() {
		int skill1Dmg = 0;
		skill1Dmg = (int) (this.getBaseDefend()*this.getBaseAtkPen()*this.getBasePower()/1.3*10);
		System.out.printf("%s is using skill %s and giving %d damage!\n", this.getName(), this.getSkill1Name(), skill1Dmg);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		int skill2Dmg = 0;
		skill2Dmg = (int) (this.getBaseDefend() * this.getBaseAtkSpd() *this.getBaseHealth() / 100);
		System.out.printf("%s is gaining power from their defend rate after using skill %s and giving damage %d!\n", this.getName(),this.getSkill2Name(),skill2Dmg);
		return skill2Dmg;
	}

	@Override
	public void getDamage(int damage) {
		int realDmg = damage - this.getBaseDefend()*this.getBaseHealth()/1000;
		this.setHealthAftDmg(realDmg);
		System.out.printf("%s takes the damage by %d\n", this.getName(),realDmg);
	}

	@Override
	public String iceName() {
		return iceName;
	}

	@Override
	public int iceSkills() {
		int ice = (int) (this.getBaseAtkSpd()*this.getBasePower()*1.35 + this.getBaseDefPen()*this.getBaseDefend()*2);
		System.out.printf("%s uses Sea Monster ice power! %s is activated with power %d\n",this.getName(),this.iceName, ice);
		return ice;
	}

	
	public void newSeaMonster() {
		String type = "Sea Monster";
		newGold(type);
		String query = "INSERT INTO ice VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, iceName);
	        preparedStatement.executeUpdate();
	        System.out.println(this.getName() + " is successfully added as Sea Monster Gold card.");
	    } catch (SQLException e) {
	    }
	}
	
	public void newOffSeaMonster() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s#%s\n", this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Gold","SeaMonster",this.iceName);
				bw.write(data);
				System.out.println(getName() + " is successfully added to temporary database as Sea Monster Gold card.");
			}
		} catch (IOException e) {
			System.out.println("Failed to insert data.");
		}
	}

	
	public static void deleteSeaMonster(String id) {
		String query = "DELETE FROM ice WHERE CardId = ?";
		try {
			PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deleteGold(id);
	}
}
