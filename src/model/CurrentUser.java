package model;

public class CurrentUser {

	private Card usedCard;
	private static CurrentUser currentInstance;
	private User currentUser;
	
	private CurrentUser(User user) {
		this.currentUser = user;
	}
	
	public static void setInstance(User user) {
		if(currentInstance == null) {
			currentInstance = new CurrentUser(user);
		}
		return;
	}
	
	public static CurrentUser getInstance() {
		return currentInstance;
	}
	
	public static void deleteInstance() {
		currentInstance = null;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public Card getUsedCard() {
		return usedCard;
	}

	public void setUsedCard(Card usedCard) {
		this.usedCard = usedCard;
	}
	
	public void finishCard() {
		this.usedCard = null;
	}
	

}
