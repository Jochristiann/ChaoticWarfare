package arena;

import controller.AppController;
import controller.CardController;

public class PvpArena extends GameArena{
	
	private static PvpArena pvc;
	
	private PvpArena(String difficulty, AppController ap) {
		super(difficulty, ap);
	}

	public static void deleteWar() {
		pvc = null;
	}
	
	public static PvpArena getWar() {
		return pvc;
	}
	
	public static PvpArena createInstance(String difficulty, AppController ap) {
		if(pvc == null) {
			pvc = new PvpArena(difficulty,ap);
		}
		return pvc;
	}
	
	@Override
	public void opponentCard() {
		String grade = "";
		double chance = rand.nextDouble() * 100;
		switch (difficulty.toLowerCase()) {
	        case "easy":
	        	if (chance < 20) {
	                grade = "Silver";
	            } else {
	                grade = "Gold";
	            }
	            break;
	        case "hard":
	        	if (chance < 5) {
	                grade = "Silver";
	            } else if (chance < 45) {
	                grade = "Gold";
	            } else {
	                grade = "Ultra";
	            }
	            break;
	        case "extreme":
	        	if (chance < 2.5) {
	                grade = "Silver";
	            } else if (chance < 35) {
	                grade = "Gold";
	            } else {
	                grade = "Ultra";
	            }
	            break;
	        default:
	        	grade = "Ultra";
	        	break;
	    }
		setOpponentCard(CardController.randomizeOneCards(grade));
	}

	@Override
	public void startGame() {
		initThread();
		
		Thread viewThread = new Thread(()-> {
			while(isGameRunning()) {
				if(isGameRunning()) {
					if(!(isOpponentStopSpin() && isUserStopSpin())) {
						ap.showPvPView();
					}
					
					if(isOpponentStopSpin() && isUserStopSpin()) {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}else if(isUserStopSpin()) {
						try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
					}
				}
				try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
			}
		});
		viewThread.start();
		while(true) {
			if(isOpponentStopSpin() && isUserStopSpin()) {
				ap.showPvPView();
				System.out.println("\nLog: ");
				compare();
				if(isUserWin()) {
					userAttack();
				}else if(isOpponentWin()) {
					opponentAttack();
				}
				if(getUserCard().getBaseHealth() <= 0 && getOpponentCard().getBaseHealth() > 0) {
					setGameRunning(false);
					userLose();
					break;
				}else if(getUserCard().getBaseHealth() > 0 && getOpponentCard().getBaseHealth() <= 0) {
					setGameRunning(false);
					userWin();
					break;
				}else if(getUserCard().getBaseHealth() <= 0 && getOpponentCard().getBaseHealth() <= 0) {
					setGameRunning(false);
					System.out.println("Match draw!");
					break;
				}
				try {
	                Thread.sleep(6000);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
				resetValue();
				resetStatus();
			}
		}
		pvc = null;
		
	}

	@Override
	public synchronized void userWin() {
		System.out.println("\n\n");
		System.out.println("You Win!");
		int prize = prize();
		System.out.printf("On this 1v1 mode, you get gold %d!\n\n", prize);
		this.getUser().setGold(this.getUser().getGold() + prize);
	}

	@Override
	public synchronized void userLose() {
		System.out.println("\n\n");
		System.out.println("You Lose");
		System.out.println("You don't get any gift.\nComeback stronger!");
	}

	@Override
	public int prize() {
		int result = 0;
		switch (difficulty.toLowerCase()) {
	        case "easy":
	        	result = rand.nextInt(500) + 1000;
	            break;
	        case "hard":
	        	result = rand.nextInt(750) + 1500;
	            break;
	        case "extreme":
	        	result = rand.nextInt(2000) + 7500;
	            break;
	    }
		return result;
	}
}
