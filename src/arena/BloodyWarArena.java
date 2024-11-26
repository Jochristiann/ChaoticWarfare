package arena;

import java.util.ArrayList;

import controller.AppController;
import controller.CardController;
import model.Card;

public class BloodyWarArena extends GameArena{
	
	private ArrayList<Card> opponentCards;
	private static BloodyWarArena bwc = null;
	private volatile int round = 1;
	
	private BloodyWarArena( String difficulty, AppController ap) {
		super(difficulty,ap);
	}
	
	public static void deleteWar() {
		bwc = null;
	}
	
	public static BloodyWarArena getWar() {
		return bwc;
	}
	
	public static BloodyWarArena createInstance(String difficulty, AppController ap) {
		if(bwc == null) {
			bwc = new BloodyWarArena(difficulty,ap);
		}
		return bwc;
	}

	@Override
	public void opponentCard() {
		opponentCards = CardController.randomizeOpponentCards(difficulty);
		if(getOpponentCard() == null) {
			setOpponentCard(opponentCards.get(0));
		}
	}
	
	public void removeOpponentCard() {
		opponentCards.remove(0);
		setOpponentCard(opponentCards.get(0));
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	@Override
	public void startGame() {
		initThread();
		
		Thread viewThread = new Thread(()-> {
			while(isGameRunning()) {
				if(isGameRunning()) {
					if(!(isOpponentStopSpin() && isUserStopSpin())) {
						ap.showBWView(); 
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
				ap.showBWView();
				System.out.println("\nLog: ");
				compare();
				if(isUserWin()) {
					userAttack();
				}else if(isOpponentWin()) {
					opponentAttack();
				}
				
				if(getUserCard().getBaseHealth() <= 0 && getOpponentCard().getBaseHealth() > 0) {
					userLose();
					setGameRunning(false);
					break;
				}else if(getUserCard().getBaseHealth() > 0 && getOpponentCard().getBaseHealth() <= 0) {
					if(round == 3) {
						userWin();
						setGameRunning(false);
						break;
					}else {
						System.out.println("You win in round " + getRound());
						removeOpponentCard();
						round++;
					}
				}else if(getUserCard().getBaseHealth() <= 0 && getOpponentCard().getBaseHealth() <= 0) {
					System.out.println("Match draw!");
					setGameRunning(false);
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
		
		bwc = null;
		
	}

	@Override
	public void userWin() {
		System.out.println("\n\n");
		System.out.println("You Win!");
		int prize = prize();
		System.out.printf("On this Bloody War mode, you get gold %d!\n\n", prize);
		this.getUser().setGold(this.getUser().getGold() + prize);
	}

	@Override
	public void userLose() {
		System.out.println("\n\n");
		System.out.println("You Lose");
		int prize = prize()/100;
		System.out.printf("You still get gold %d!\n\n", prize);
		this.getUser().setGold(this.getUser().getGold() + prize);
	}

	@Override
	public int prize() {
		int result = 0;
		switch (difficulty.toLowerCase()) {
	        case "easy":
	        	result = rand.nextInt(750) + 1500;
	            break;
	        case "hard":
	        	result = rand.nextInt(1000) + 2500;
	            break;
	        case "extreme":
	        	result = rand.nextInt(2000) + 5000;
	            break;
	    }
		return result;
	}
}
