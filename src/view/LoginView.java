package view;

import controller.AppController;

public class LoginView extends View{
	
	public LoginView(AppController apps) {
		super(apps);
		// TODO Auto-generated constructor stub
	}

	public void loginForm() {
		String username;
		String password;
		do {
			separator();
			System.out.println(" Login to Chaotic Warfare");
			System.out.print(" Enter Username [0 to exit]: ");
			username = sc.nextLine();
			if(username.equals("0")) {
				apps.showMenuView();
			}
			System.out.print(" Enter Password            : ");
	        password = sc.nextLine();
			if(uc.login(username, password)) {
				sc.nextLine();
				break;
			}
			enter();
		} while(true);
		apps.showLobbyView();
	}
	
	public void loginFormInternal() {
		String username;
		String password;
		do {
			separator();
			System.out.println(" Login to Chaotic Warfare Company");
			System.out.print(" Enter Username [0 to exit]: ");
			username = sc.nextLine();
			if(username.equals("0")) {
				apps.showMenuView();
			}
			System.out.print(" Enter Password            : ");
	        password = sc.nextLine();
			if(uc.login(username, password)) {
				sc.nextLine();
				break;
			}
			enter();
		} while(true);
		apps.showStaffRegistrationView();
	}
	
}
