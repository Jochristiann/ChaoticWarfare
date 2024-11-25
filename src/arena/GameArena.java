package arena;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import controller.AppController;
import controller.CardController;
import model.Beast;
import model.Card;
import model.Celestial;
import model.CurrentUser;
import model.Divine;
import model.Gold;
import model.Guardian;
import model.Mammals;
import model.Noble;
import model.Player;
import model.SeaMonster;
import model.Silver;
import model.Ultra;

public abstract class GameArena {
	public static Random rand = new Random();
	private CardController cc = new CardController();
	private Player user;
	private Player opponent;
	private Card userCard;
	private volatile Card opponentCard;
	public String difficulty;
	private volatile ArrayList<String> userGame;
	private volatile ArrayList<String> opponentGame;
	public AppController ap;
	private volatile boolean userStopSpin = false;
	private volatile boolean opponentStopSpin = false;
	private volatile boolean gameRunning = true;
	private volatile boolean isUserWin = false;
	private volatile boolean isOpponentWin = false;
	private volatile int userTap = 0;
	private volatile int opTap = 0;
	private static Scanner sc = new Scanner(System.in);
	
	public GameArena(String difficulty, AppController ap) {
		Player players = (Player) CurrentUser.getInstance().getCurrentUser();
		
		this.setUser(players);
		Player newO = new Player(difficulty, "Opponent", "", "", 0, 0, 0, null); 
		this.setOpponent(newO);
		userGame = new ArrayList<String>();
		opponentGame = new ArrayList<String>();
		userGame.add("?");
		userGame.add("?");
		opponentGame.add("?");
		opponentGame.add("?");
		this.difficulty = difficulty;
		this.ap = ap;
	}
	
	public boolean isGameRunning() {
		return gameRunning;
	}
	
	public boolean isUserWin() {
		return isUserWin;
	}

	public void setUserWin(boolean isUserWin) {
		this.isUserWin = isUserWin;
	}

	public boolean isOpponentWin() {
		return isOpponentWin;
	}

	public void setOpponentWin(boolean isOpponentWin) {
		this.isOpponentWin = isOpponentWin;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public Player getUser() {
		return user;
	}

	public void setUser(Player user) {
		this.user = user;
	}

	public Card getUserCard() {
		return userCard;
	}

	public void setUserCard(String id) {
		Card userCard = cc.getCardsId(id);
		this.userCard = userCard;
	}

	public Card getOpponentCard() {
		return opponentCard;
	}

	public void setOpponentCard(Card opponentCard) {
		this.opponentCard = opponentCard;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public abstract void opponentCard();
	
	public boolean deadValidation(Card curr) {
		if(curr.getBaseHealth() <= 0) {
			return true;
		}
		return false;
	}
	
	public void setValueResult(String value) {
		this.userGame.set(1, value);
	}
	
	public void setSkillResult(String value) {
		this.userGame.set(0, value);
	}
	
	public void setOpValueResult(String value) {
		this.opponentGame.set(1, value);
	}
	
	public void setOpSkillResult(String value) {
		this.opponentGame.set(0, value);
	}
	
	public boolean isUserStopSpin() {
		return userStopSpin;
	}
	
	public void setUserStopSpin(boolean userStopSpin) {
		this.userStopSpin = userStopSpin;
	}
	public ArrayList<String> getUserGame() {
		return userGame;
	}

	public void setUserGame(ArrayList<String> userGame) {
		this.userGame = userGame;
	}
	public boolean isOpponentStopSpin() {
		return opponentStopSpin;
	}
	
	public void setOpponentStopSpin(boolean opponentStopSpin) {
		this.opponentStopSpin = opponentStopSpin;
	}

	public ArrayList<String> getOpponentGame() {
		return opponentGame;
	}

	public void setOpponentGame(ArrayList<String> opponentGame) {
		this.opponentGame = opponentGame;
	}
	
	public abstract void startGame();
	
	public synchronized void compare() {
		
		boolean isOpDoubling = false;
		boolean isUserDoubling = false;
		if(this.getUserGame().get(0).equals(this.getUserGame().get(1))) {
			System.out.println("User got double!");
			isUserDoubling =  true;
		}
		if(this.getOpponentGame().get(0).equals(this.getOpponentGame().get(1))) {
			System.out.println("Opponent got double!");
			isOpDoubling =  true;
		}
		
		if(isOpDoubling && isUserDoubling) {
			System.out.println("Draw!");
		}else if(!isOpDoubling && isUserDoubling){
			setUserWin(true);
		}else if(isOpDoubling && !isUserDoubling){
			setOpponentWin(true);
		}else {
			int valueOpp = Integer.parseInt(this.getOpponentGame().get(1));
			int valueUs = Integer.parseInt(this.getUserGame().get(1));
			
			if(valueOpp > valueUs) {
				System.out.println("Opponent wins the spin!");
				setOpponentWin(true);
			}else if(valueOpp < valueUs) {
				System.out.println("You wins the spin!");
				setUserWin(true);
			}else {
				System.out.println("Draw!");
				setUserWin(false);
				setOpponentWin(false);
			}
		}
	}
	
	private void addUserValueList(String input) {
		this.userCard.setValueSpin(input);
	}
	
	private void addOpValueList(String input) {
		this.opponentCard.setValueSpin(input);
	}
	
	public void getValueOpponent() {
		int val = rand.nextInt(getOpponentCard().getValueSpin().size());
		setOpValueResult(getOpponentCard().getValueSpin().get(val));
	}
	
	public void getSkillOpponent() {
		int val = rand.nextInt(getOpponentCard().getSkillSpin().size());
		setOpSkillResult(getOpponentCard().getSkillSpin().get(val));
	}
	
	public void getValueUser() {
		int val = rand.nextInt(getUserCard().getValueSpin().size());
		setValueResult(getUserCard().getValueSpin().get(val));
	}
	
	public void getSkillUser() {
		int val = rand.nextInt(getUserCard().getSkillSpin().size());
		setSkillResult(getUserCard().getSkillSpin().get(val));
	}

	public void initThread() {
		Thread userThread = new Thread(() -> {
			while(isGameRunning()) {
                int cdTime = 10000;
                long startTime = System.currentTimeMillis();
                while (!isUserStopSpin() && isGameRunning()) {
                    if (System.currentTimeMillis() - startTime >= cdTime) {
                    	userTap++;
                    	if(userTap == 1) {
                    		getSkillUser();
                    		addUserValueList(userGame.get(0));
                    		try {
                    			Thread.sleep(2000);
                    		} catch (InterruptedException e) {
                    			Thread.currentThread().interrupt();
                    		}
                    	}
                    	else if(userTap == 2) {
                    		getValueUser();
                    		userTap = 0;
                    		setUserStopSpin(true);
                    		try {
                                Thread.sleep(6000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                    	}
                    }
                }
                if (!isGameRunning()) break;
			}
        });
		
		
		Thread opponentThread = new Thread(() -> {
			while (isGameRunning()) {
                int randomTime = rand.nextInt(3000) + 5000;
                long startTime = System.currentTimeMillis();

                while (!isOpponentStopSpin() && isGameRunning()) {
                    if (System.currentTimeMillis() - startTime >= randomTime) {
                    	opTap++;
                    	if(opTap == 1) {
                    		getSkillOpponent();
                    		addOpValueList(opponentGame.get(0));
                    		try {
                    			Thread.sleep(1500);
                    		} catch (InterruptedException e) {
                    			Thread.currentThread().interrupt();
                    		}
                    	}
                    	else if(opTap == 2) {
                    		getValueOpponent();
                    		opTap = 0;
                    		setOpponentStopSpin(true);
                    		try {
                                Thread.sleep(5500);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                    	}
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (!isGameRunning()) break;
            }
        }); 
		
		Thread inputThread = new Thread(() -> {
            while (isGameRunning()) {
            	if(!isUserStopSpin()&&isGameRunning()) {
            		if (sc.hasNextLine()) {
            			String input = sc.nextLine();
            			if (input.equalsIgnoreCase("P")) {
            				userTap++;
            				if(userTap == 1) {
            					getSkillUser();
            					addUserValueList(userGame.get(0));
            					try {
            						Thread.sleep(2000);
            					} catch (InterruptedException e) {
            						Thread.currentThread().interrupt();
            					}
            				}
            				
            				else if(userTap == 2) {
            					getValueUser();
            					userTap = 0;
            					setUserStopSpin(true);
            					try {
                                    Thread.sleep(6000);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
            				}
            			} else if (input.equalsIgnoreCase("Q")) {
            				setGameRunning(false);
            				setUserStopSpin(true);
            				setOpponentStopSpin(true);
            			}
            		}
            	}
            }
        });
		
		
		userThread.start();
		inputThread.start();
		opponentThread.start();
		
	}
	
	public void resetValue() {
		setValueResult("?");
		setSkillResult("?");
		setOpValueResult("?");
		setOpSkillResult("?");
	}
	
	public void resetStatus() {
		setOpponentWin(false);
		setUserWin(false);
		setOpponentStopSpin(false);
		setUserStopSpin(false);
	}
	
	public synchronized void userAttack() {
		String skill = userGame.get(0);
		if(skill.equals("?")) return;
		if(skill.equals("S1")) {
			opponentCard.getDamage(userCard.skill1());
			return;
		}else if(skill.equals("S2")) {
			opponentCard.getDamage(userCard.skill2());
			return;
		}
		
		int damage = 0;
		if(userCard instanceof Ultra) {
			Ultra u = (Ultra) userCard;
			if(skill.equals("U")) {
				damage = u.ultraSkill();
			}else if(skill.equals("SC")) {
				damage = u.specialSkill(opponentCard.getBaseDefend());
			}
			
			if(userCard instanceof Celestial) {
				Celestial c = (Celestial) userCard;
				damage = c.skydanceSkills();
			}else {
				Divine d = (Divine) userCard;
				damage = d.blackMythSkills();
			}
		}
		
		else if(userCard instanceof Gold) {
			Gold g = (Gold) userCard;
			if(skill.equals("A")) {
				damage = g.awakenSkill();
			}
			
			else if(userCard instanceof Noble) {
				Noble c = (Noble) userCard;
				damage = c.skydanceSkills();
			}else {
				SeaMonster d = (SeaMonster) userCard;
				damage = d.iceSkills();
			}
		}
		
		else if(userCard instanceof Silver) {
			Silver g = (Silver) userCard;
			if(skill.equals("CS")) {
				damage = g.comboSkill();
			}
			
			if(userCard instanceof Guardian) {
				Guardian gd = (Guardian) userCard;
				damage = gd.earthSkills();
			}
		}
		
		else{
			if(userCard instanceof Beast) {
				Beast be = (Beast) userCard;
				damage = be.blackMythSkills();
			}else {
				Mammals ma = (Mammals) userCard;
				ma.healingEffect();
			}
		}
		
		opponentCard.getDamage(damage);
	}
	
	public synchronized void opponentAttack() {
		
		String skill = opponentGame.get(0);
		if(skill.equals("?")) return;
		if(skill.equals("S1")) {
			userCard.getDamage(opponentCard.skill1());
			return;
		}else if(skill.equals("S2")) {
			userCard.getDamage(opponentCard.skill2());
			return;
		}
		
		int damage = 0;
		if(opponentCard instanceof Ultra) {
			Ultra u = (Ultra) opponentCard;
			if(skill.equals("U")) {
				damage = u.ultraSkill();
			}else if(skill.equals("SC")) {
				damage = u.specialSkill(opponentCard.getBaseDefend());
			}
			
			if(opponentCard instanceof Celestial) {
				Celestial c = (Celestial) opponentCard;
				damage = c.skydanceSkills();
			}else {
				Divine d = (Divine) opponentCard;
				damage = d.blackMythSkills();
			}
		}
		
		else if(opponentCard instanceof Gold) {
			Gold g = (Gold) opponentCard;
			if(skill.equals("A")) {
				damage = g.awakenSkill();
			}
			
			else if(opponentCard instanceof Noble) {
				Noble c = (Noble) opponentCard;
				damage = c.skydanceSkills();
			}else {
				SeaMonster d = (SeaMonster) opponentCard;
				damage = d.iceSkills();
			}
		}
		
		else if(opponentCard instanceof Silver) {
			Silver g = (Silver) opponentCard;
			if(skill.equals("CS")) {
				damage = g.comboSkill();
			}
			
			if(opponentCard instanceof Guardian) {
				Guardian gd = (Guardian) opponentCard;
				damage = gd.earthSkills();
			}
		}
		
		else{
			if(opponentCard instanceof Beast) {
				Beast be = (Beast) opponentCard;
				damage = be.blackMythSkills();
			}else {
				Mammals ma = (Mammals) opponentCard;
				ma.healingEffect();
			}
		}
		
		userCard.getDamage(damage);
		
	}
	
	public abstract void userWin();
	public abstract void userLose();
	public abstract int prize();
}
