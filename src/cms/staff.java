package cms;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

//import jdk.internal.classfile.Superclass;

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
		
	
	
		
		
		public staff(String userName,String userEmail,String Usertype,String[] userCourses,String[] userSections,
				String staffName,String staffEmail,String staffUsername,String staffpwd,String[] staffCourses,String[] staffSections) {
			super(userName,userEmail,Usertype,userCourses,userSections);
			
			this.staffName = staffName;
			this.staffEmail=staffEmail;
			this.staffUsername=staffUsername;
			this.staffpwd=staffpwd;
			this.staffCourses=staffCourses;
			this.staffSections=staffSections;
			
			
		}
		
		
		
		
		 public void staff_login() {
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
					login();
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
		 
		 

		 
	
		public void login() {
		  	System.out.println("Enter username: ");
			   staffName= scanner.nextLine();	
				
				System.out.println("Enter password: ");
				staffpwd= scanner.nextLine();
				
			// call setter method of supper class 
			super.setusertype("staff");
			// store user type
			String usertype = super.getUserType();			  	// retrive data from db 
			
			// call the login method and pass the parameters 
			if(login(staffName,staffpwd,usertype)) {
				staff_dash();
			}else {
				System.out.println("go");
			}
			
			
			
			
			
			
			
			
		}
		
		public void staff_dash() {
			System.out.println("--------- Staff Dashboard -------------- \n"
					+ "1. My Profile \n"
					+ "2. My Classes \n"
					+ "3. Students \n"
					+ "4. Attendance \n"
					+ "5. Exams \n"
					+ "6. Grades \n");
			System.out.println("Choose [1-6]: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1:
				 System.out.println("uc");
				 break;
			case 2:
				System.out.println("uc");
				break;
			default:
				System.out.println("invalid ");
				break;
			}
			
			
		}
	
		
		
		// Staff Registration 
		public void register_staff() {
			// take the data from the db validate user and display courses 
			// if registered by admin set custom pass and username 
			
	        System.out.println("Enter your your name: ");
	        this.staffName = scanner.nextLine();
	        System.out.println("Enter your email: ");
	        this.staffEmail = scanner.nextLine();

	    
	        System.out.println("Enter your Courses: ");
	        for(int i=0;i<3;i++) {
	        	
	        	this.staffCourses[i] =scanner.nextLine();
	        }
	        
	        System.out.println("Enter your Sections: ");
	        for(int i=0;i<3;i++) {
	        	this.staffSections[i]=scanner.nextLine();
	        }
	        
	        System.out.println("Enter username: ");
	        this.staffUsername = scanner.nextLine();
	        System.out.println("Enter password: ");
	        this.staffpwd = scanner.nextLine();
	        
	        // register to database 
	        
	        var sql = "INSERT INTO staff (staff_name ,staff_email,staff_courses,staff_sections, staff_username ,staff_password) values(?,?,?,?,?,?)";
	        
	        try {
	        	// convert java array to sql array 
	        	Array arrayCourses = connection.createArrayOf("varchar", staffCourses);
	        	Array arraySections = connection.createArrayOf("varchar", staffSections);
	        	
	        	
	        	// make prepared statement 
	        	PreparedStatement statment = connection.prepareStatement(sql);
	        	
	        	statment.setString(1, this.staffName);
	        	statment.setString(2, this.staffEmail);
	        	statment.setArray(3, arrayCourses);
	        	statment.setArray(4, arraySections);
	        	statment.setString(5, this.staffUsername);
	        	statment.setString(6, this.staffpwd);
	        	
	        	
	            statment.executeUpdate();
	        	
	        	
	   
	        }catch(SQLException e) {
	        	throw new RuntimeException(e);
	        }
	        
	        
	        System.out.println("Registered succesfully");
	        return;
	        
			
		}
		
		
	
	
		
		
	
}
