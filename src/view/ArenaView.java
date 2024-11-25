package view;

import arena.BloodyWarArena;
import arena.GameArena;
import arena.PvpArena;
import controller.AppController;

public class ArenaView extends View{

	private GameArena ga;
	private boolean isPvP;
	
	public ArenaView(AppController apps, GameArena ga) {
		super(apps);
		this.ga = ga;
		this.isPvP = false;
		if(ga instanceof PvpArena) {
			this.isPvP = true;
		}
	}
	
	public synchronized void gameScene() {
		separator();
		System.out.println("Battle Round\n\n");
		if(!isPvP) {
			BloodyWarArena t = (BloodyWarArena) ga ;
			System.out.println("Round " + t.getRound());
		}
		System.out.printf("%-20s %20s %20s\n", ga.getUser().getUsername(),"",ga.getOpponent().getUsername());
		System.out.printf("\n\nCard\n");
		System.out.printf("%-20s %20s %20s\n", ga.getUserCard().getName(), "", ga.getOpponentCard().getName());
		System.out.printf("%-20d %20s %20d\n", ga.getUserCard().getBaseHealth(), "", ga.getOpponentCard().getBaseHealth());
		System.out.println();
		String table =  "----------------------";
		System.out.printf("%-20s %20s %-20s\n", table,"",table);
		System.out.printf("|%-20s| %20s |%-20s|\n", ga.getUserCard().getSkill1Name(),"",ga.getOpponentCard().getSkill1Name());
		System.out.printf("|%-20s| %20s |%-20s|\n", ga.getUserCard().getSkill2Name(),"",ga.getOpponentCard().getSkill2Name());
		System.out.printf("%-20s %20s %-20s\n", table,"",table);
		System.out.println("\n\n");
		System.out.printf("|%-10s|%-10s|%20s|%-10s|%-10s|\n",ga.getUserGame().get(0),ga.getUserGame().get(1),"",ga.getOpponentGame().get(1),ga.getOpponentGame().get(0));
		if(!(ga.isUserStopSpin()&&ga.isOpponentStopSpin())) {
			System.out.println("Press p to stop spinning");
		}
	}
}
