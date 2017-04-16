package crdelf01.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {

	public Connection dbConnection = null;
	public UserBean user = null; //instance for logged on user.
	
	public void connectToDatabase(){
		try {
			//The parameter is the name of the driver
			
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			dbConnection = DriverManager.getConnection("jdbc:db2://216.249.138.85:50000/CRDELF01", "crdelf01", "Spring2017");
            System.out.println("Connected to database.");
		}
		catch (ClassNotFoundException cle){
            System.out.println("Class not found");
            cle.printStackTrace();
        }
        catch (SQLException sqle) {
            System.out.println("SQL Exception");
            sqle.printStackTrace();
        }
        catch (Exception ne)
        {
            System.out.println("General Exception");
            ne.printStackTrace();
        }
	}
	
	public void disconnectFromDB(){
		try {
			dbConnection.close();
		} catch (SQLException e) {
			// Unable to disconnect from the database
			e.printStackTrace();
		}
	}
	
	public boolean verifyUser(String userName, String password) throws SQLException{
		Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery("select * from user where name = '" + userName + "' and password = '" + password + "' fetch first 1 rows only");
		boolean userInDb = rs.isBeforeFirst();
		
		System.out.println(userInDb);
		/*If the user is in the dabase make an instance of the user object*/
		if(userInDb){
			user = new UserBean();
			System.out.println("user in verifyUser method: " + user);
			
			while(rs.next()){
				user.setUsername((rs.getString("username")));
				user.setFirst((rs.getString("first")));
				user.setLast((rs.getString("last")));
			}			
		}
		
		System.out.println(user.toString());
		
        return userInDb;
	}
	
	public UserBean getUserInfo(){
		System.out.println("getUserInfo method");
		UserBean user = new UserBean();
		
		return user;
	}
}
