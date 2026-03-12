package cms;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

public class staff extends Userfunction{
	
	
		Scanner scanner = new Scanner(System.in);
		
		private String staffName;
		private String staffEmail;
		private String staffUsername;
		private String staffpwd;
		private String[] staffCourses = new String[3];
		private String[] staffSections = new String[3];
		
		 
		
		// create field of connection to store methods 
		private Connection connection;
		
		// injecting constructore with all the methods 
		public staff(Connection connection) {
			super(connection);
			this.connection = connection;
	
		}
		
		
		public staff(String userName,String userEmail,String Usertype,String[] userCourses,String[] userSections) {
			super(userName,userEmail,Usertype,userCourses,userSections);
			this.staffName = staffName;
			this.staffEmail=staffEmail;
			this.staffUsername=staffUsername;
			this.staffpwd=staffpwd;
			this.staffCourses=staffCourses;
			this.staffSections=staffSections;
		}
		
		
	
		
		 public void staff_Dash() {
				System.out.println("--------------Staff Login--------------\n"
								 + "1. Login \n"
								 + "2. Register \n"
								 + "2. Exist \n"
								 + "----Choose [1-2]: ");
				// create object to store input 
				
				
				// create variable to accept input 
				int choice = scanner.nextInt();	
				scanner.nextLine();		
				
				switch(choice) {
				case 1:
					//login_Dash();
					break;
				case 2: 
					register_staff();
					System.exit(0);
					break;
				default:
					System.out.println("invalid input");
					break;
				}
				
		// 
		 }
		 
			public void show_Menu() {
				System.out.println("----------Staff Dashboard--------------\n"
						 +"1. Profile \n"
						 +"1. Class \n"
						 +"2. Exams \n"
						 + "3. Grades \n");
				System.out.println("Choose 1-3: ");
				
			}
			
			
//		 public void login_dash() {
//			 System.out.println("Enter username");
//			 String username = scanner.nextLine();
//			 System.out.println("Enter password");
//			 String pwd = scanner.nextLine();
//			 
//			 // get user user credential
//			
//		 }
		
		public void register_staff() {
			// take the data from the db validate user and display courses 
			// if registered by admin set custom pass and username 
			
	        System.out.println("Enter your your name: ");
	        staffName = scanner.nextLine();
	        System.out.println("Enter your email: ");
	        staffEmail = scanner.nextLine();

	    
	        System.out.println("Enter your Courses: ");
	        for(int i=0;i<3;i++) {
	        	
	        	staffCourses[i] =scanner.nextLine();
	        }
	        
	        System.out.println("Enter your Sections: ");
	        for(int i=0;i<3;i++) {
	        	staffSections[i]=scanner.nextLine();
	        }
	        
	        System.out.println("Enter username: ");
	        staffUsername = scanner.nextLine();
	        System.out.println("Enter password: ");
	        staffpwd = scanner.nextLine();
	        
	        // register to database 
	        
	        var sql = "INSERT INTO staff (staffname ,staffemail,staffcourses,staffsection, username ,password) values(?,?,?,?,?,?)";
	        
	        try {
	        	// convert java array to sql array 
	        	Array arrayCourses = connection.createArrayOf("varchar", staffCourses);
	        	Array arraySections = connection.createArrayOf("varchar", staffSections);
	        	
	        	
	        	// make prepared statement 
	        	PreparedStatement statment = connection.prepareStatement(sql);
	        	statment.setString(1, staffName);
	        	statment.setString(2, staffEmail);
	        	statment.setArray(3, arrayCourses);
	        	statment.setArray(4, arraySections);
	        	statment.setString(5, staffUsername);
	        	statment.setString(6, staffpwd);
	        	
	        	
	            statment.executeUpdate();
	        	
	        	
	   
	        }catch(SQLException e) {
	        	throw new RuntimeException(e);
	        }
	        
	        
	        System.out.println("Registered succesfully");
	        return;
	        
			
		}
		
		
	
	
		
		
	
}
