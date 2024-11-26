package datasource;

import java.util.ArrayList;

import controller.CardController;
import controller.UserController;
import model.Card;
import model.User;

public class Data {

	private ArrayList<User> leaderboardUser;
	private ArrayList<User> allStaff;
	private ArrayList<Card> gameCard;
	private static Data data;
	private CardController cc;
	private UserController uc;
	private ArrayList<Card> basicCards = new ArrayList<Card>();
	private ArrayList<Card> silverCards = new ArrayList<Card>();
	private ArrayList<Card> goldCards = new ArrayList<Card>();
	private ArrayList<Card> ultraCards = new ArrayList<Card>();
	
	
	private Data() {
		cc = new CardController();
		gameCard = new ArrayList<Card>();
		gameCard = cc.getAllCards();
		basicCards = new ArrayList<Card>();
	    silverCards = new ArrayList<Card>();
	    goldCards = new ArrayList<Card>();
	    ultraCards = new ArrayList<Card>();
	    allStaff = new ArrayList<User>();
	    uc = new UserController();
	    allStaff = uc.getAllStaff();
	    gameCard = cc.getAllCards();
	    basicCards = cc.getAllCardsByGrade("Basic");
        silverCards = cc.getAllCardsByGrade("Silver");
        goldCards = cc.getAllCardsByGrade("Gold");
        ultraCards = cc.getAllCardsByGrade("Ultra");
	}
	
	
	
	public ArrayList<User> getAllStaff() {
		return allStaff;
	}


	public ArrayList<Card> getBasicCards() {
		return basicCards;
	}


	public ArrayList<Card> getSilverCards() {
		return silverCards;
	}


	public ArrayList<Card> getGoldCards() {
		return goldCards;
	}


	public ArrayList<Card> getUltraCards() {
		return ultraCards;
	}
	public static void migrateFreshData() {
		data = null;
		data = new Data();
	}
	
	public static Data getInstance() {
		return data;
	}

	public ArrayList<User> getLeaderboardUser() {
		return leaderboardUser;
	}


	public ArrayList<Card> getGameCard() {
		return gameCard;
	}

	
	
	
}
