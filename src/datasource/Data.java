package datasource;

import java.util.ArrayList;

import controller.CardController;
import model.Card;
import model.User;

public class Data {

	private ArrayList<User> leaderboardUser;
	private ArrayList<Card> gameCard;
	private static Data data;
	private CardController cc;
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
	    
	    basicCards = cc.getAllCardsByGrade("Basic");
        silverCards = cc.getAllCardsByGrade("Silver");
        goldCards = cc.getAllCardsByGrade("Gold");
        ultraCards = cc.getAllCardsByGrade("Ultra");
	}
	
	public ArrayList<Card> getBasicCards() {
		return basicCards;
	}

	public void setBasicCards(ArrayList<Card> basicCards) {
		this.basicCards = basicCards;
	}

	public ArrayList<Card> getSilverCards() {
		return silverCards;
	}

	public void setSilverCards(ArrayList<Card> silverCards) {
		this.silverCards = silverCards;
	}

	public ArrayList<Card> getGoldCards() {
		return goldCards;
	}

	public void setGoldCards(ArrayList<Card> goldCards) {
		this.goldCards = goldCards;
	}

	public ArrayList<Card> getUltraCards() {
		return ultraCards;
	}

	public void setUltraCards(ArrayList<Card> ultraCards) {
		this.ultraCards = ultraCards;
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

	public void setLeaderboardUser(ArrayList<User> leaderboardUser) {
		this.leaderboardUser = leaderboardUser;
	}

	public ArrayList<Card> getGameCard() {
		return gameCard;
	}

	public void setGameCard(ArrayList<Card> gameCard) {
		this.gameCard = gameCard;
	}
	
	
	
	
}
