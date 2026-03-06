package cms;

import java.sql.Connection;

public class staff extends Userfunction{
		
		
		public staff(Connection connection) {
			super(connection);
		// TODO Auto-generated constructor stub
	
		}
		
		
		public staff(String userName,String userEmail,String Usertype,String[] userCourses,String[] userSections) {
			super(userName,userEmail,Usertype,userCourses,userSections);
		}
		
		
	
		
		
	
}
