package cms;

import java.sql.*;  // java class for sql builtin function

import java.util.*;  // for scanner object 

import org.mindrot.jbcrypt.BCrypt;

public class Userfunction {
	
	
	// user function instance variables 
	private String  userName;
	private String userEmail;
	private String userSection;
	private String Usertype;
	private String defpwd;
	private String defUsername;
	private String[] userCourses = new String[3];
	private String[] userSections = new String[3];
	


	// setter and getter function for usertype ( student or staff_)
	public void setusertype(String Usertype) {
		this.Usertype=Usertype;
	}
	
	public String getUserType(){
		return this.Usertype;
	}
	

	public void setDefpwd(String defpwd) {
		this.defpwd = userName+"4321";
	}
	public String getdefpwd() {
		return defpwd;
	}
	
	
	public void setUsername(String defUsername) {
		 this.defUsername = userName.toLowerCase()+"1234";
	}
	public String getUsername() {
		return defUsername;
	}
	
	
	

	// constructor for user with default username and pwd
	public Userfunction(String userName,String userEmail,String Usertype,String defpwd,String defUsername) {
		this.userName=userName;
		this.userEmail=userEmail;
		this.Usertype=Usertype;
     	this.defpwd=defpwd;
     	this.defUsername=defUsername;
//		
	}
	
	

	// constructor for students instance
	public Userfunction(String userName,String userEmail,String Usertype,String[] userCourses,String userSection) {
		this.userName=userName;
		this.userEmail=userEmail;
		this.Usertype=Usertype;
		this.userCourses=userCourses;
		this.userSection=userSection;
	}
	
	// constructor for staff instance 
	public Userfunction(String userName,String userEmail,String Usertype,String[] userCourses,String[] userSections) {
		this.userName=userName;
		this.userEmail=userEmail;
		this.Usertype=Usertype;
		this.userCourses=userCourses;
		this.userSections=userSections;
	}
	
	

	   Scanner scanner = new Scanner(System.in);
	
		// create database connection 
		private Connection connection;

	    public Userfunction(Connection connection) {
	        this.connection = connection;
	    }
	    
	   
		
	  //  public Userfunction newuser = new Userfunction( userName, userEmail,Usertype,userCourses, userSections);
	    
	    public boolean login(String User_Name,String User_Pass,String Usertype) {
	  
			
	    	try {
	    		
	    		var sql = "SELECT "+User_Pass+" FROM "+Usertype+" WHERE "+User_Name+" =?";
		    	
		    	PreparedStatement statement = connection.prepareStatement(sql);
		    	statement.setString(1,User_Pass);
		    	//ResultSet result = statement.executeQuery();
		    	
		    	// store the result 
		    	
		    	// user Bcrypt for secure password 
		    	String hashed_pw = BCrypt.hashpw(User_Pass, BCrypt.gensalt());
		    	
		    	return BCrypt.checkpw(User_Pass, hashed_pw);
		    	
		    	
	    	}catch(SQLException e) {
	    		throw new RuntimeException(e);
	    	}
	    
	    }
	    
	    // add register user 
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
	    	for(int i=0;i<3;i++) {
	    		
	    		newuser.userSections[i]=scanner.nextLine();
	    	}
	    }
	    
	    System.out.println(Usertype+"Courses");
	    for(int i=0;i<3;i++) {
	    	
	    	newuser.userCourses[i]=scanner.nextLine();	  
	    }
	    
;	    
	    	// create precompiled sql to insert data 
	    	var sql = "INSERT INTO "+Usertype+" (staff_name,staff_email,staff_sections,staff_courses,staff_username,staff_password) values(?,?,?,?,?,?)";
	    
	    	try {
	    		
	    		PreparedStatement statmnt = connection.prepareStatement(sql);
		    	
		    	statmnt.setString(1, newuser.userName);
	    		statmnt.setString(2, newuser.userEmail);
	    	
	    		//set array method for string 
	    		//statmnt.setArray(3, userCourses);
	    	
	    		statmnt.setString(2, newuser.userEmail);
	    		if(newuser.getUserType().equals("staff")) {
	    			
		    		
		    		// create array of courseArray
		    		Array courseArray = connection.createArrayOf("varchar", newuser.userCourses);
		    		Array sectionArray = connection.createArrayOf("varchar", newuser.userSections);
		    		statmnt.setArray(3, courseArray);
		    		statmnt.setArray(4, sectionArray);
		    		
		    		
	    		
	    		} else if(newuser.getUserType().equals("student")) {
	    			statmnt.setString(1, newuser.userName);
		    		statmnt.setString(2, newuser.userEmail);
	    			Array studCourse = connection.createArrayOf("varchar", newuser.userCourses);
	    			statmnt.setArray(3, studCourse);
	    			statmnt.setString(4, newuser.userSection);
	    			
	    			
	    		}
	    		
	    		// set default password and username for new user 
	    		newuser.setDefpwd(userName);
	    		newuser.setUsername(userName);
	    		
	    		statmnt.setString(5, newuser.getUsername());
	    		statmnt.setString(6,newuser.getdefpwd());
	    	
	    		statmnt.executeUpdate();
	    		System.out.println("Registered succesfully");
	    	

	    	}catch (SQLException e){
	    		throw new RuntimeException(e);
	    	}
	    	
	    }
	
		
		
	
}
