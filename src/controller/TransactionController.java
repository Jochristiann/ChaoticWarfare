package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Card;
import model.CurrentUser;
import model.Player;
import model.User;

public class TransactionController {
	
	private UserController uc;

	public TransactionController() {
		uc = new UserController();
	}
	
	private static boolean validationPrice(int price, int saldo) {
		if(price > saldo) {
			return false;
		}
		return true;
	}
	
	private boolean isCardOwned(String id) {
		Player pl = (Player) CurrentUser.getInstance().getCurrentUser();
		ResultSet res = pl.isCardOwned(id);
		try {
			if(res.next()) {
				System.out.println("You already owned the card.");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isCardValid(String name) {
		if(isCardOwned(name)) {
			return false;
		}
		ResultSet res = boughtCard(name);
		try {
			if(!res.next()) {
				System.out.println("Card with name " + name + " doesn't exist. Please try again.");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public ResultSet boughtCard(String name) {
		return Card.getCardbyName(name);
	}
	
	public void buyCards(String id, int price, int type) {
		User us = CurrentUser.getInstance().getCurrentUser();
		Player pl = (Player) us;
		int saldo = 0;
		String notifFailed = "";
		if(type == 1) {
			saldo = pl.getGold();
			notifFailed = "No enough gold.";
		}else {
			saldo = pl.getDiamond();
			notifFailed = "No enough diamond.";
		}
		if(validationPrice(price,saldo)) {
			pl.buyCard(id);
			if(type == 1) {
				pl.setGold(saldo - price);
			}else {
				pl.setDiamond(saldo - price);
			}
			System.out.println("Successfully bought.");
			uc.refreshOwnedCard();
		}else {
			System.out.println(notifFailed);
		}
	}

}
