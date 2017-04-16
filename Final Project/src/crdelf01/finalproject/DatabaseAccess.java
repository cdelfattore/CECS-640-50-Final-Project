package crdelf01.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {

	public Connection dbConnection = null;
	private UserBean user = new UserBean(); //instance for logged on user.
	
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
		ResultSet rs = st.executeQuery("select * from user where username = '" + userName + "' and password = '" + password + "' fetch first 1 rows only");
		boolean userInDb = rs.isBeforeFirst();
		
		/*If the user is in the dabase make an instance of the user object*/
		if(userInDb){
			while(rs.next()){
				user.setUsername((rs.getString("username")));
				user.setFirst((rs.getString("first")));
				user.setLast((rs.getString("last")));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setStreet(rs.getString("street"));
				user.setCity(rs.getString("city"));
				user.setState(rs.getString("state"));
				user.setZip(rs.getString("zip"));
				user.setUserid(rs.getInt("userid"));
			}
		}
		
		System.out.println(user.toString());
		
        return userInDb;
	}
	
	public UserBean getUserInfo(){
		return user;
	}
}
