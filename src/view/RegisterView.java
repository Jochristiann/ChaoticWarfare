package view;

import controller.AppController;

public class RegisterView extends View {

	public RegisterView(AppController apps) {
		super(apps);
		// TODO Auto-generated constructor stub
	}

	public void registerForm() {
		separator();
		System.out.println("Create a new account in Chaotic Warfare");
		String username, password;
		do {
			
			System.out.print("Enter username [0 to exit]: ");
			username = sc.nextLine();
			if (username.equals("0")) {
				return;
			}
			if (uc.validateUsername(username)) {
				break;
			}
			enter();
		} while (true);
		
		do {
			System.out.print("Enter password            : ");
			password = sc.nextLine();
			if(uc.validateNewPassword(password)) {
				break;
			}
			enter();
		} while (true);
		uc.register(username, password);
	}
	
}
