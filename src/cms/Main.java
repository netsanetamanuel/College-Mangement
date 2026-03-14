package cms;

import java.util.Scanner;
import java.sql.Connection;
public class Main {
	// calling object 

	Scanner scanner = new Scanner(System.in);
	
	private Connection connection;
	public Main(Connection connection) {
		this.connection = connection;
	}
	
	public void mainDash() {
		
		System.out.println("---------- CMS Dashboard -----------\n"
						  +"  1. Admin \n"
				         + "  2. Staff \n"
				         + "  3. Student \n"
				         + "----- 	  Choose (1-3): -------------\n");	
		
		
		int choice = scanner.nextInt(); 
		
		Admin newAdmin = new Admin(connection);
		staff newStaff = new staff(connection);
		
		
		switch(choice) {
		case 1:
			// call login function 
			newAdmin.adminLogin();
			break;
		case 2: 
			newStaff.staff_login();
			break;
		case 3:
			System.out.println("uc");
			break;
		default:
			System.out.println("uc");
		}
		
		scanner.close();
	}
	
	// create main dashboard
	public static void main(String[] args) {
		
	// connect to driver 
	DBfunction dbconn = new DBfunction();
	Connection conn = dbconn.connect_to_db("college","postgres", "dsa321");
	

	
	Main ui = new Main(conn);

	ui.mainDash();
	

		
	}


}
