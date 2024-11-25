package controller;

import arena.BloodyWarArena;
import arena.GameArena;
import arena.PvpArena;
import model.Card;

public class GameController {
	public GameArena ga;
	private static GameController gc;
	private boolean isPvP;

	private GameController(Card userCard, String mode, String difficulty, AppController ap) {
		this.isPvP = false;
		if(mode.equals("PvP")) {
			isPvP = true;
			ga = PvpArena.createInstance(difficulty, ap);
		}else {
			ga = BloodyWarArena.createInstance(difficulty, ap);
		}
		ga.setUserCard(userCard.getId());
		ga.opponentCard();
		ga.startGame();
	}
	
	public static void createInstance(Card userCard, String mode, String difficulty, AppController ap) {
		if(gc == null) {
			System.out.println("create");
			gc =  new GameController(userCard, mode, difficulty, ap);
		}
	}
	
	public static void deleteInstance() {
		gc = null;
	}

	public boolean isPvP() {
		return isPvP;
	}

	public void setPvP(boolean isPvP) {
		this.isPvP = isPvP;
	}

}
