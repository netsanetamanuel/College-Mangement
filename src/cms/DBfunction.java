package cms;


import java.sql.Connection;
import java.sql.DriverManager;
// function that returns connection from database 


// java class tunnel b/d java and postgress 
public class DBfunction {
	
	// method that returns db connection recieve parameter 
	public Connection connect_to_db(String dbname,String user,String pass) {
		// create initialized object 
		
		Connection connection = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
			if(connection!=null) {
				return connection;
			}else {
				System.out.println("Connection failed");
			}
			
		}catch(Exception e) {
			
			System.out.println(e);
		}
		
		return connection;
	}


}
