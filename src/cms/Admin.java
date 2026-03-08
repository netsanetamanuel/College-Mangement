package cms;

import java.sql.Connection;
import java.util.Scanner;
public class Admin  extends Userfunction{
		//
	Scanner scanner = new Scanner(System.in);
	
	public Admin(String userName,String userEmail,String usertype,String password ) {
	
		super( userName, userEmail,usertype);
		
	}
	
	
	// constructor injection 
	private Connection connection;

    public Admin(Connection connection) {
    	super(connection);
        this.connection = connection;
    }
   
    
    
    // 
	
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
			showMenu();
		}else {
			System.out.println("go");
		}
		
		
	
	}
	
	
	
	
	public  void showMenu() {
		System.out.println("----------Admin Dashboard-------------------"
						 + " 1. Manage Staf \n"
						 + " 2. Manage Student \n"
						 + " 3. Manage Course \n"
						 + " 4  Get Report \n");
		System.out.println("Enter option: ");
		int choice = scanner.nextInt();
		scanner.nextLine();
		switch(choice) {
		case 1:
			manageStaf();
			break;
		case 2:
			System.out.println("uc");
			break;
		}
	}
	
	
	
	staff newstaff = new staff(connection);
	
	public void manageStaf() {
		
			super.setusertype("staff");
	
			System.out.println("1. Add Staff\r\n"
					+ "2. View All Staff\r\n"
					+ "3. Search Staff\r\n"
					+ "4. Update Staff\r\n"
					+ "5. Delete Staff\r\n"
					+ "6. Back ");
			System.out.println("Enter option: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1:
				newstaff.setusertype("staff");
				addUser(newstaff);
				break;
			case 2:
				System.out.println("uc");
				break;
				
			}
			
			
	}
	
}
