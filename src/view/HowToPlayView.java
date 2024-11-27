package view;

import controller.AppController;

public class HowToPlayView extends View{

	public HowToPlayView(AppController apps) {
		super(apps);
		// TODO Auto-generated constructor stub
	}

	public void howtoplayView() {
		separator();
		System.out.println(" How to Play Chaotic Warfare?");
		System.out.println(" There are two modes in this game");
		System.out.println(" 1. 1v1 (Person vs Person)");
		System.out.println("    In this mode, you will fight the boss. You must kill only one boss.\n"
				+ "  On this mode, you can get the prize if and only if you win the battle.");
		System.out.println(" 2. Bloody War");
		System.out.println("    In this mode, you will fight the the opponent that have 3 different cards.\n"
				+ "   Each round you will face one character. Your health will not restored so you must being alive until the last round\n"
				+ "   You will win the battle if your card's health is still more than zero in the third round.\n"
				+ "   When you are choosing this mode, you still can get prize if you lose the battle.\n\n");
		System.out.println(" In-Game Mechanism");
		System.out.println(" - There are two spin that the user must get. The first value is spinning the card's skill\n"
				+ "  and the second is spinning the value to compared with the opponent value.");
		System.out.println(" - You should press p (Case Insensitive) while the spinning mode is still on.");
		System.out.println(" - If you didn't press the p for 10 seconds, the game will automatically stop the spinning.");
		System.out.println(" - You have a chance to attack the opponent if you have the higher value when it compared with the opponent value.");
		System.out.println(" - You can get 'Doubling' when the value is same with the skill. For each spinning, there is a value that references the skill.\n"
				+ "  When you get the 'Skill' value in the value, you have the higher chance to attack the opponent.");
		System.out.println("\n");
		System.out.println(" Try the game and explore it. Goodluck!");
		System.out.println("\n");
		System.out.println();
		enter(); 
	}

}
