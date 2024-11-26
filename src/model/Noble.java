package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import feature.SkydanceSkill;

public final class Noble extends Gold implements SkydanceSkill{
	
	private String skydanceSkill;

	public Noble(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower, int baseDefend,
			double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			String awakeningSkill, String skydanceSkill) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name, awakeningSkill);
		this.skydanceSkill = skydanceSkill;
		this.addSkillSpin("S+");
	}

	@Override
	public int skill1() {
		int skill1Dmg = 0;
		skill1Dmg = this.getBaseAtkPen()*this.getBasePower()+this.getBaseDefend()*10;
		System.out.printf("%s activates %s! %d power is setted for attack!\n", this.getName(), this.getSkill1Name(), skill1Dmg);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		int skill2Dmg = 0;
		int heal = (int) (this.getBasePower() * this.getBaseDefend()*0.075 + this.getBaseHealth()*0.001);
		System.out.printf("%s is regenerating! Get %d extra hp\n", this.getName(),heal);
		this.setBaseHealth(heal);
		return skill2Dmg;
	}

	@Override
	public void getDamage(int damage) {
		int realDamage = (int) (damage - 0.01*this.getBaseDefend()*damage - this.getBaseDefPen()*this.getBaseDefend());
		System.out.printf("%s got hit! %d damage taken\n", this.getName(),realDamage);
		this.setHealthAftDmg(realDamage);
	}

	@Override
	public int awakenSkill() {
		int awakenDmg = (int) (this.getBaseAtkSpd()*this.getBasePower()/1.7 + this.getBaseAtkSpd()*4*(this.getBasePower()/7));
		System.out.printf("%s the Noble hero uses skill %s and giving %d damage\n", this.getName(), this.getAwakeningSkill(),awakenDmg);
		int rate = rand.nextInt(5);
		if(rate == 1) {
			skydanceHeal();
			System.out.println("Skydance heal activated!");
		}
		return awakenDmg;
	}

	@Override
	public String skydanceName() {
		return this.skydanceSkill;
	}

	@Override
	public int skydanceSkills() {
		int skydance = (int) (this.getBaseAtkPen() * this.getBasePower()*0.35 + this.getBaseAtkPen()*this.getBaseDefend()*1.2);
		System.out.printf("%s uses hidden noble power! %s is activated with power %d\n",this.getName(),this.skydanceSkill, skydance);
		return skydance;
	}

	@Override
	public void skydanceHeal() {
		int skydanceEffect = (int) (this.getBaseDefend()*0.25);
		this.setBaseHealth(skydanceEffect);
		System.out.printf("%s gets heal chance! %d extra hp\n",this.getName(),skydanceEffect);
	}

	public void newNoble() {
		String type = "Noble";
		newGold(type);
		String query = "INSERT INTO skydance VALUES (?,?)";
	    try {
	        PreparedStatement preparedStatement = Connect.getConnect().prepareStatement(query);
	        preparedStatement.setString(1, this.getId());
	        preparedStatement.setString(2, skydanceSkill);
	        preparedStatement.executeUpdate();
	        System.out.println(this.getName() + " is successfully added as Noble Gold card.");
	    } catch (SQLException e) {
	    }
	}
	
	public void newOffNoble() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s#%s\n", 
						this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Gold","Noble",this.skydanceName());
				bw.write(data);
				System.out.println(getName() + " is successfully added to temporary database as Noble Gold card.");
			}
		} catch (IOException e) {
			System.out.println("Failed to insert data.");
		}
	}

	public static void deleteNoble(String id) {
		String query = "DELETE FROM skydance WHERE CardId = ?";
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
