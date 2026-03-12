package cms;


import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Auth {
	
	// create connection object 
	
	private Connection connection;

    public Auth(Connection connection) {
        this.connection = connection;
    }
    
    // methods for login 
	public boolean adminLogin(String username,String password) { 
		
		// prepare statement to store username and password  from db 
		try {
			
			// return falsh it don't exist
			if(username.isEmpty()||password.isEmpty()) {
				System.out.println("username or password don't exist!");
				return false;
			}
			
	
			// prepare a sql statment 
			PreparedStatement statement = connection.prepareStatement("SELECT password FROM admin WHERE username = ?");
			 
			statement.setString(1,username);
			
			// store sql result to resultset
			ResultSet resultset = statement.executeQuery();
			
			
			// is there no next row 
			if(!resultset.next()) {
				// return false if there is no next row 
				return false;
			}
			
			//String hashpassword = resultset.getString("password");
			// store the password in hashpwd
			String hashpassword = BCrypt.hashpw(password, BCrypt.gensalt());
			
			return BCrypt.checkpw(password, hashpassword);
			
		
			
			
		} catch(SQLException e) {

			throw new RuntimeException(e);
			
		}
		
		
	}

	
	public boolean userLogin(String username, String password) {
		
		if(username.isEmpty()||password.isEmpty()) {
			System.out.println("username or password don't exist");
			return false;
		}
		
		try {
			
			
			
			// prepared statment pre compiled sql statment 
			PreparedStatement statmnt = connection.prepareStatement("SELECT password FROM staff WHERE username = ?");
			ResultSet result = statmnt.executeQuery();
			
			// create hasspwd using bcrypt 
			String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
			
			return BCrypt.checkpw(password, hashpw);
			
			
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
