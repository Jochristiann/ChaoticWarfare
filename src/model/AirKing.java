package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AirKing extends Silver {
	
	public AirKing(String id, String name, int goldPrice, int diamondPrice, int baseHealth, int basePower,
			int baseDefend, double baseAtkSpd, int baseAtkPen, int baseDefPen, String skill1Name, String skill2Name,
			int healEffect) {
		super(id, name, goldPrice, diamondPrice, baseHealth, basePower, baseDefend, baseAtkSpd, baseAtkPen, baseDefPen,
				skill1Name, skill2Name, healEffect);
		
	}

	@Override
	public void healingEffect() {
		int heal = (int) (100 + 0.1*this.getBaseHealth());
		System.out.printf("%s is regenerating and get %d hp.\n", this.getName(),heal);
		this.setBaseHealth(heal);
	}

	@Override
	public int comboSkill() {
		int comboDmg = (int) (this.getBasePower()*this.getBaseAtkSpd()*3.5 + this.getBaseAtkPen()*this.getBasePower()/1.75);
		System.out.printf("Combo skill is activated! %s is giving %d damage\n", this.getName(),comboDmg);
		return comboDmg;
	}

	@Override
	public int skill1() {
		int skill1Dmg = this.getBasePower()*this.getBaseAtkPen() + this.getBasePower();
		int healEff = (int) (skill1Dmg*0.05);
		this.setBaseHealth(healEff);
		System.out.printf("%s casts %d damage and raises their health %d\n", this.getName(),skill1Dmg, healEff);
		return skill1Dmg;
	}

	@Override
	public int skill2() {
		int skill2Dmg = this.getBasePower()*this.getBaseAtkPen() + this.getBasePower();
		System.out.printf("%s casts skill %s and gives %d damage!\n", this.getName(),this.getSkill2Name(),skill2Dmg);
		return skill2Dmg;
	}

	@Override
	public void getDamage(int damage) {
		int realDmg = (int) (damage - this.getBaseDefend() - this.getBaseDefend()*this.getBaseDefPen()*0.2);
		if(realDmg < 0) {
			realDmg = 0;
		}
		System.out.printf("%s is hitted! Damage taken %d\n", this.getName(), realDmg);
		
		int val = rand.nextInt(5);
		if(val > 3) {
			healingEffect();
		}
		this.setHealthAftDmg(realDmg);
	}

	public void newAirKing() {
		String type = "Air King";
		newSilver(type);
		System.out.println(getName() + " is successfully added as Air King Silver card.");
	}
	
	public void newOffAirKing() {
		try {
			FileWriter fw = new FileWriter("card.txt", true);
			try (BufferedWriter bw = new BufferedWriter(fw)) {
				String data = String.format("%s#%s#%d#%d#%d#%d#%d#%.1f#%d#%d#%s#%s#%s#%s\n", this.getId(),this.getName(),this.getGoldPrice(),this.getDiamondPrice(),this.getBaseHealth(),this.getBasePower(),this.getBaseDefend(),this.getBaseAtkSpd(),this.getBaseAtkPen(),this.getBaseDefPen(),this.getSkill1Name(),this.getSkill2Name(),"Silver","Air King");
				bw.write(data);
				System.out.println(getName() + " is successfully added to temporary database as Air King Silver card.");
			}
		} catch (IOException e) {
			System.out.println("Failed to insert data.");
		}
	}
	
	public static void deleteAirKing(String id) {
		deleteSilver(id);
	}
	
}
