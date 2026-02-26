package cms;


import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class Auth {
	
	private Connection connection;

    public Auth(Connection connection) {
        this.connection = connection;
    }
    
    
	public boolean adminLogin(String username,String password) { 
		
		// prepare statement to store username and password  from db 
		try {
			
			if(username.isEmpty()||password.isEmpty()) {
				System.out.println("username or password don't exist!");
				return false;
			}
			
			// preparedstatement class to sotre username in statment object 
			PreparedStatement statement = connection.prepareStatement("SELECT password FROM admin WHERE username = ?");
			// statement object to store username from database 
			statement.setString(1,username);
			
			//fetch the result statement from after excutign query 
			ResultSet resultset = statement.executeQuery();
			
			if(!resultset.next()) {
				// return false if there is no next row 
				return false;
			}
			
			//String hashpassword = resultset.getString("password");
			String hashpassword = BCrypt.hashpw(password, BCrypt.gensalt());
			
			return BCrypt.checkpw(password, hashpassword);
			
			
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
		

		
	}

}
