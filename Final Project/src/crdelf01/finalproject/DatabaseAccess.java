package crdelf01.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;


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
		
		/*If the user is in the database make an instance of the user object*/
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
		
		/*System.out.println(user.toString());*/
		
        return userInDb;
	}
	
	public LinkedList<ItemBean> searchForItems(String searchTerm){
		LinkedList<ItemBean> itemList = new LinkedList<ItemBean>();
		try {
			Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from item where name like '%" + searchTerm + "%' or description like '%" + searchTerm + "%'");
			 
			while(rs.next()){
				ItemBean item = new ItemBean();
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setDescription(rs.getString("description"));
				item.setItem_id(rs.getInt("item_id"));

				itemList.add(item);			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return itemList;
	}
	
	public ItemBean getItemInfo(int item_id){
		ItemBean item = new ItemBean();
		try {
			Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from item where item_id = '" + item_id  + "'");
			
			while(rs.next()){
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setDescription(rs.getString("description"));
				item.setItem_id(rs.getInt("item_id"));			
			}
		}
		catch (SQLException e){
			
		}
		
		return item;
	}
	
	public UserBean getUserInfo(){
		return user;
	}
	
	public int createOrder(int userid, double total){
		//create the order
		int order_id = -1;
		try {
			PreparedStatement insertOrderSt = dbConnection.prepareStatement("insert into order(userid, total) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
			insertOrderSt.setInt(1, userid);
			insertOrderSt.setDouble(2, total);
			
			insertOrderSt.execute(); 
			
			ResultSet genKeys = insertOrderSt.getGeneratedKeys();			
			while(genKeys.next()){
				System.out.println(genKeys.getInt(1));
				order_id = genKeys.getInt(1);
			}
		
		} 
		catch (SQLException e){
			System.out.println(e);
		}
		
		return order_id;
	}

	public void createOrderLines(int order_id, HashMap<Integer,ShoppingCartItemBean> shopCart){
		try {
			PreparedStatement ps = dbConnection.prepareStatement("insert into order_line(quantity, total, item_id, order_id) values (?, ?, ?, ?)");
			
			for(ShoppingCartItemBean item : shopCart.values()){
				ps.setInt(1, item.getQuantity());
				ps.setDouble(2, item.getPrice());
				ps.setInt(3, item.getItem_id());
				ps.setInt(4, order_id);
				ps.addBatch();
			}
			
			ps.executeBatch();
			
		}
		catch (SQLException e){
			System.out.println(e);
		}
	}
}
