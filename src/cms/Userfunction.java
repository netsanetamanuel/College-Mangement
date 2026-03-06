package cms;

import java.sql.*;

import java.util.*;

public class Userfunction {
	
	private String  userName;
	private String userEmail;
	private String userSection;
	private String Usertype;
	
	private String[] userCourses = new String[6];
	private String[] userSections = new String[6];
	
	
	public Userfunction(String userName,String userEmail,String Usertype) {
		this.userName=userName;
		this.userEmail=userEmail;
		this.Usertype=Usertype;
		
	}
	
	public Userfunction(String userName,String userEmail,String Usertype,String[] userCourses) {
		this.userName=userName;
		this.userEmail=userEmail;
		this.Usertype=Usertype;
		this.userCourses=userCourses;
	}
	public Userfunction(String userName,String userEmail,String Usertype,String[] userCourses,String userSection) {
		this.userName=userName;
		this.userEmail=userEmail;
		this.Usertype=Usertype;
		this.userCourses=userCourses;
		this.userSection=userSection;
	}
	
	// constructor for staff
	public Userfunction(String userName,String userEmail,String Usertype,String[] userCourses,String[] userSections) {
		this.userName=userName;
		this.userEmail=userEmail;
		this.Usertype=Usertype;
		this.userCourses=userCourses;
		this.userSections=userSections;
	}
	
	
	public void setusertype(String Usertype) {
		this.Usertype=Usertype;
	}
	
	public String getUserType(){
		return Usertype;
	}
	
	
	
	
	

	
	  Scanner scanner = new Scanner(System.in);
	
		// create database connection 
		private Connection connection;

	    public Userfunction(Connection connection) {
	        this.connection = connection;
	    }
	    
	   
		
	  //  public Userfunction newuser = new Userfunction( userName, userEmail,Usertype,userCourses, userSections);
	    
	    public void addUser(Userfunction newuser) {
	    	
	    	// excute query to insert data
	    System.out.println(Usertype +"Name: ");
	     newuser.userName = scanner.nextLine();
	    
	    System.out.println(Usertype+"Email: ");
	     newuser.userEmail = scanner.nextLine();
	    
	    System.out.println(Usertype+"Section: ");
	    if(Usertype.equals("student")) {
	    	 newuser.userSection = scanner.nextLine();
	    }else if (Usertype.equals("staff")) {
	    	for(int i=0;i<=6;i++) {
	    		newuser.userSections[i]=scanner.nextLine();
	    	}
	    }
	    System.out.println(Usertype+"Courses");
	    for(int i=0;i<=6;i++) {
	    	newuser.userCourses[i]=scanner.nextLine();	  
	    }
	    
;	    
	    	
	    	var sql = "INSERT INTO "+Usertype+" (userName,userEmail,userCourses,userSection) values(?,?,?,?)";
	    	
	    	try {
	    		
	    		PreparedStatement statmnt = connection.prepareStatement(sql);
	    		statmnt.setString(1, newuser.userName);
	    		statmnt.setString(2, newuser.userEmail);
	    		//set array method for string 
	    		//statmnt.setArray(3, userCourses);
	    		if(newuser.getUserType().equals("staff")) {
	    		Array courseArray = connection.createArrayOf("staffcourses", newuser.userCourses);
	    		Array sectionArray = connection.createArrayOf("staffsecton", newuser.userSections);
	    		statmnt.setArray(3, courseArray);
	    		statmnt.setArray(4, sectionArray);
	    		
	    		} else if(newuser.getUserType().equals("student")) {
	    			Array studCourse = connection.createArrayOf("studentcourse", newuser.userCourses);
	    			statmnt.setArray(3, studCourse);
	    			statmnt.setString(4, newuser.userSection);
	    		}
	    	
	    		statmnt.executeUpdate();

	    		
	    		
	    		
	    	}catch (SQLException e){
	    		throw new RuntimeException(e);
	    	}
	    	
	    }
	
		
		
	
	
}
