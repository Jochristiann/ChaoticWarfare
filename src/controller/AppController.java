package controller;

import java.util.Scanner;

import arena.BloodyWarArena;
import arena.PvpArena;
import model.CurrentUser;
import model.Player;
import view.ArenaView;
import view.FirewallView;
import view.GameView;
import view.HowToPlayView;
import view.LobbyView;
import view.LoginView;
import view.MenuView;
import view.OfflineView;
import view.ProfileView;
import view.RegisterView;
import view.StaffManagementView;
import view.StaffView;
import view.StoreView;

public class AppController {
	
	private Scanner sc;

	public AppController() {
		sc = new Scanner(System.in);
	}
	
	public void start() {
		System.out.println("\r\n"
				+ "\r\n"
				+ "  ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░ ░▒▓██████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓██████▓▒░       ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░░▒▓████████▓▒░▒▓██████▓▒░░▒▓███████▓▒░░▒▓████████▓▒░ \r\n"
				+ " ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \r\n"
				+ " ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \r\n"
				+ " ░▒▓█▓▒░      ░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓███████▓▒░░▒▓██████▓▒░░▒▓████████▓▒░▒▓███████▓▒░░▒▓██████▓▒░   \r\n"
				+ " ░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \r\n"
				+ " ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░        \r\n"
				+ "  ░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░  ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓██████▓▒░        ░▒▓█████████████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░     ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░ \r\n"
				+ "\r\n");
		System.out.println(" Chaotic Warfare is a fast-paced, strategic card-battling game where players summon powerful"
				+ " creatures from the depths of nature and mythology to fight in epic, high-stakes duels.\n"
				+ " Set in a wild, untamed world where mythical beasts, legendary guardians, and primal forces reign supreme, "
				+ " players must harness their creatures’ unique abilities and master the art of\n strategy to dominate their opponents.\n\n");
		System.out.println(" Are you ready for battle?");
		System.out.print(" Press enter to continue...");
		sc.nextLine();
		showMenuView();
	}
	
	public void showOfflineView() {
		OfflineView ov = new OfflineView(this);
		ov.offlineDisplay();
	}
	
	public void showMenuView() {
		MenuView menu = new MenuView(this);
		menu.menuDisplay();
	}
	
	public void showLoginView(boolean isInternal) {
		if(isInternal) {
			LoginView login = new LoginView(this);
			login.loginFormInternal();
		}else {
			LoginView login = new LoginView(this);
			login.loginForm();
		}
	}
	
	public void showRegisterView() {
		RegisterView register = new RegisterView(this);
		register.registerForm();
	}
	
	public void showStoreView() {
		StoreView store = new StoreView(this);
		store.storeDisplay();
	}
	
	public void showLobbyView() {
		CurrentUser curr = CurrentUser.getInstance();
		if(curr.getCurrentUser() instanceof Player) {
			LobbyView lobby = new LobbyView(this);
			lobby.lobbyDisplay();
		}else {
			StaffView staffLobby = new StaffView(this);
			staffLobby.staffDisplay();
		}
	}

	public void showGameView() {
		GameView game = new GameView(this);
		game.startGame();
	}
	
	public void showPvPView() {
		ArenaView arena = new ArenaView(this, PvpArena.getWar());
		arena.gameScene(); 
	}
	
	public void showBWView() {
		ArenaView arena = new ArenaView(this, BloodyWarArena.getWar());
		arena.gameScene();
	}
	
	public void showProfileView() {
		if(CurrentUser.getInstance().getCurrentUser() == null) {
			System.out.println(" User is not logged in.");
			showLoginView(false);
		}
		ProfileView profile = new ProfileView(this);
		profile.profileDisplay();
	}
	
	public void showhowtoplayView() {
		HowToPlayView htp = new HowToPlayView(this);
		htp.howtoplayView();
	}
	
	public void showFirewallView() {
		FirewallView fv = new FirewallView(this);
		fv.firewallCompany();
	}
	
	public void showStaffRegistrationView() {
		StaffManagementView sr = new StaffManagementView(this);
		sr.staffRegistrationDisplay();
	}
}
