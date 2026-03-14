package cms;


import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Auth extends Userfunction{
	
	// create connection object 

	private Connection connection;
	


    public Auth(Connection connection) {
    	super(connection);
    	
        this.connection=connection;
    }
    
    // methods for login 
    
    
    
    
	public boolean adminLogin(String username,String password,String user_type) { 
		
		// prepare statement to store username and password  from db 
		try {
			
			// return falsh it don't exist
			if(username.isEmpty()||password.isEmpty()) {
				System.out.println("username or password don't exist!");
				return false;
			}
			
			
			// prepare a sql statment 
			PreparedStatement statement = connection.prepareStatement("SELECT password FROM "+user_type+" WHERE username = ?");
			 
			statement.setString(1,username);
			
			// store sql result to resultset
			ResultSet resultset = statement.executeQuery();
			
			
			// is there no next row 
			if(!resultset.next()) {
				// return false if there is no next row 
				return false;
			}
			
			String hashpassword = resultset.getString("password");
			// store the password in hashpwd
			hashpassword = BCrypt.hashpw(password, BCrypt.gensalt());
			
			return BCrypt.checkpw(password, hashpassword);
			
		
			
			
		} catch(SQLException e) {

			throw new RuntimeException(e);
			
		}
		
		
	}

	
	// new login function for all 
	// pass object 
    
  
    //String className = getClass().getSimpleName();
 
    /*
	public boolean login(String Username,String Password) {
	
		super.setusertype(className);
		String user_type = getUserType();
	    
		
		if(Username.isEmpty()||Password.isEmpty()) {
			System.out.println("username or password don't exist");
			return false;
		}
	
		
		try {
			// create sql statment 
			var sql ="SELECT "+user_type+" password FROM "+user_type+" WHERE "+user_type+" username = ?";
			
			PreparedStatement stament = connection.prepareStatement(sql);
			
			stament.setString(1, Username);
			ResultSet result = stament.executeQuery();
			
			
			// check for next row 
			
			if(!result.next()) {
				return false;
			}
			
			String hashedpw = result.getString("staff_password");
			
			 hashedpw = BCrypt.hashpw(hashedpw, BCrypt.gensalt());
			
			return BCrypt.checkpw(Password, hashedpw);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}

		
		
	}*/
		
		
	}
	

