package view;

import controller.AppController;
import database.FileDatabase;
import datasource.Data;
import model.CurrentUser;
import model.Staff;
import model.User;

public class StaffManagementView extends View{

	
	private Staff currentStaff;
	private FileDatabase fd;
	
	public StaffManagementView(AppController apps) {
		super(apps);
		currentStaff = (Staff) CurrentUser.getInstance().getCurrentUser();
		fd = FileDatabase.instance();
	}

	public void staffRegistrationDisplay() {
		int input = 0;
		int auth = 2;
		do {
			separator();
			try {
				System.out.println("Internal Chaotic Warfare Management");
				System.out.println("1. New Employee");
				System.out.println("2. Remove Employee");
				if(currentStaff.getPosition().equals("CEO")) {
					System.out.println("3. Update Employee Position");
					System.out.println("4. Migrate Temporary Database");
					auth = 4;
				}
				System.out.println("0. Exit");
				System.out.print(">> ");
				input = sc.nextInt();sc.nextLine();
				if(input >= 0 && input <= auth) {
					if(input == 0) {
						break;
					}
					switch (input) {
					case 1:
						staffRegister();
						break;
					case 2:
						staffDelete();
						break;
					case 3:
						staffUpdate();
						break;
					case 4:
						migrateDb();
						break;
					}
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("Invalid input. Please try again.");
				enter();
			}
			
		} while (true);
		
	}
	
	private void tableSeparate() {
		for (int i = 0; i < 74; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	private void showAllStaff() {
		tableSeparate();
		System.out.printf("|%-3s|%-20s|%-15s|%-15s|%-15s|\n"
				,"No.","Staff Name", "Origin", "Position", "Authorization");
		tableSeparate();
		int index = 0;
		for (User us : Data.getInstance().getAllStaff()) {
			Staff st = (Staff)us;
			System.out.printf("|%-3d|%-20s|%-15s|%-15s|%-15s|\n"
					,index+1,st.getUsername(), st.getOrigin(),st.getPosition(), st.getAuthorization());
			index++;
		}
		tableSeparate();
	}
	
	private String employeePosition() {
		String position = "Staff";
		do {
			separator();
			try {
				System.out.println("Employee Position");
				System.out.println("1. Director");
				System.out.println("2. Head Division");
				System.out.println("3. Staff");
				System.out.println("To cancel, please choose Staff and continue to next menu to back lobby.");
				System.out.print(">> ");
				int input = sc.nextInt();sc.nextLine();
				switch (input) {
				case 1:
					position = "Director";
					break;
				case 2:
					position = "Head Division";
					break;
				case 3:
					break;
				}
				
				if(input >= 1 && input <= 3) {
					return position;
				}else {
					System.out.println("Your input must be between 1 until 3");
					enter();
				}
			} catch (Exception e) {
				System.out.println("Invalid input. Input must be in number");
				enter();
			}
		} while (true);
	}
	
	private void staffRegister() {
		separator();
		System.out.println("New Employee Information");
		System.out.print("Name (as username) : ");
		String name = sc.nextLine();
		System.out.print("Origin             : ");
		String origin = sc.nextLine();
		String position =  employeePosition();
		do {
			separator();
			System.out.println("New Employee");
			System.out.println("Name     : "+name);
			System.out.println("Origin   : "+origin);
			System.out.println("Position : "+position);
			System.out.print("Are you sure with this information to be inserted? [y/n] ");
			String input = sc.nextLine();
			if(input.equalsIgnoreCase("y")) {
				uc.registerStaff(name, input, origin, position);
				System.out.println("Successfully inserted.");
				enter();
				break;
			}else if(input.equalsIgnoreCase("n")) {
				System.out.println("Registration cancelled.");
				enter();  
				break;
			}
		} while (true);
	}
	
	private void staffUpdate() {
		
		separator();
		showAllStaff();
		System.out.print("Input staff's username  : ");
		String username = sc.nextLine();
		String position = employeePosition();
		System.out.print("Are you sure want to update the user's position? [y/n] ");
		String input = sc.nextLine();
		if(input.equalsIgnoreCase("y")){
			uc.updatePosition(username, position);
		}else {
			System.out.println("Cancelled to update user's position.");
		}
		enter();
	}
	
	private void staffDelete() {
		separator();
		showAllStaff();
		System.out.print("Input staff's username  : ");
		String username = sc.nextLine();
		System.out.print("Are you sure want to delete the user? [y/n] ");
		String input = sc.nextLine();
		if(input.equalsIgnoreCase("y")){
			uc.deleteStaff(username);
		}else {
			System.out.println("Cancelled to delete user.");
		}
		enter();
	}
	
	private void migrateDb() {
		do {
			separator();
			System.out.println("Are you sure to migrate all temporary card?");
			System.out.println("y [Migrate all data]");
			System.out.println("n [Delete all data]");
			System.out.println("0 [Exit]");
			System.out.print(">> ");
			String input = sc.nextLine();
			if(input.equalsIgnoreCase("y")) {
				separator();
				fd.migrateToDB();
				System.out.println("p");
				enter();
				break;
			}else if(input.equalsIgnoreCase("n")) {
				separator();
				System.out.println("Deleting all data...");
				fd.deleteDB();
				enter();
				break;
			}else if(input.equalsIgnoreCase("0")) {
				break;
			}else {
				System.out.println("Unidentified input. Try again.");
				enter();
			}
		} while (true);
	}

}
