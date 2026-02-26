package cms;

import java.sql.Connection;
import java.util.Scanner;
public class Admin  {
		//
	Scanner scanner = new Scanner(System.in);
	
	
	// constructor injection 
	private Connection connection;

    public Admin(Connection connection) {
        this.connection = connection;
    }
	
	
	
	 void adminLogin() {
		System.out.println("--------------Admin Login--------------\n"
						 + "1. Login \n"
						 + "2. Exist \n"
						 + "----Choose [1-2]: ");
		// create object to store input 
		
		
		// create variable to accept input 
		int choice = scanner.nextInt();	
		scanner.nextLine();		
		
		switch(choice) {
		case 1:
			Login();
			break;
		case 2: 
			System.out.println("Program Closed");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid INput");
		}
		
		
		
	}
	public void Login() {
		
		
		System.out.println("Enter Username: ");
		String username = scanner.nextLine();
		
		System.out.println("Enter password");
		String password = scanner.nextLine();
		
		Auth newAuth = new Auth(connection);
		if(newAuth.adminLogin(username, password)) {
			System.out.println("welcom");
		}else {
			System.out.println("go");
		}
		
		
	
	}
	
	

	public  void showMenu() {
		System.out.println("----------Admin Dashboard-------------------"
						 + " 1. Manage Staf"
						 + " 2. Manage Student"
						 + " 3. Manage Course"
						 + " 4  Get Report");
	}
	
	
	
	
}
