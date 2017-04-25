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
	
	//get information about the user
	public UserBean getUserInfo(){
		return user;
	}
	
	//get information about a specific order
	public OrderBean getOrderInfo(int order_id) {
		OrderBean order = new OrderBean();
		try {
			Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from order where order_id = '" + order_id  + "'");
			
			while(rs.next()){
				order.setOrder_id(order_id);
				order.setTotal(rs.getDouble("total"));
				order.setUserid(rs.getInt("order_id"));
			}
		}
		catch (SQLException e){
			
		}
		
		return order;
	}
	
	public LinkedList<OrderLineBean> getOrderLineInfo(int order_id){
		LinkedList<OrderLineBean> orderLines = new LinkedList<OrderLineBean>();
		try {
			Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select i.name, i.price, ol.order_line_id, ol.quantity, ol.total, ol.order_id from order_line as ol inner join item as i on ol.item_id = i.item_id where order_id = '" + order_id  + "'");
			
			while(rs.next()){
				OrderLineBean ol = new OrderLineBean();
				ol.setItemName(rs.getString("name"));
				ol.setOrder_line_id(rs.getInt("order_line_id"));
				ol.setItemPrice(rs.getDouble("price"));
				ol.setQuantity(rs.getShort("quantity"));
				ol.setTotal(rs.getDouble("total"));
				ol.setOrder_id(rs.getInt("order_id"));
				
				orderLines.add(ol);
			}
		}
		catch (SQLException e){
			
		}
		
		return orderLines; 
	}
	
	public LinkedList<OrderBean> getOrders(int userid){
		LinkedList<OrderBean> orderList = new LinkedList<OrderBean>();
		try {
			Statement st = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from order where userid = '" + userid + "'");
			
			while(rs.next()){
				OrderBean order = new OrderBean();
				order.setOrder_id(rs.getInt("order_id"));
				order.setTotal(rs.getDouble("total"));
				orderList.add(order);
			}
		}
		catch (SQLException e){
			
		}
		
		return orderList;
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
				ps.setDouble(2, item.getPrice() * item.getQuantity()); 
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
	
	public void updateOrderLines(LinkedList<OrderLineBean> orderLines) {
		try {
			PreparedStatement psOrderLine = dbConnection.prepareStatement("update order_line set quantity = ?, total = ? where order_line_id = ?");
			
			double orderTotal = 0.0;
			for(OrderLineBean ol : orderLines){
				//update the individual order lines
				psOrderLine.setInt(1, ol.getQuantity());
				psOrderLine.setDouble(2, ol.getTotal());
				psOrderLine.setInt(3, ol.getOrder_line_id());
				psOrderLine.addBatch();
				
				orderTotal = orderTotal + ol.getTotal();
			}
			
			psOrderLine.executeBatch();
			
			System.out.println("orderTotal: " + orderTotal);
			
			PreparedStatement psOrder = dbConnection.prepareStatement("update order set total = ? where order_id = ?");
			
			psOrder.setDouble(1, orderTotal);
			
			System.out.println(orderLines.get(0).getOrder_id());
			psOrder.setInt(2, orderLines.get(0).getOrder_id());
						
			psOrder.execute();
		}
		catch (SQLException e){
			System.out.println(e); 
		}		
	}
	
	/*public void updateOrder(LinkedList<OrderLineBean> orderLines){
		try {
			System.out.println(orderLines);
			PreparedStatement psOrder = dbConnection.prepareStatement("update order set total = ? where order_id = ?");
			
			double total = 0.0;
			for(OrderLineBean ol : orderLines){
				total += ol.getQuantity() * ol.getItemPrice();
			}
			System.out.println(total);
			psOrder.setDouble(1, total);
			psOrder.setInt(2, orderLines.get(0).getOrder_id());
			psOrder.addBatch();
			
			psOrder.executeBatch();
			
		}
		catch (SQLException e){
			System.out.println(e); 
		}
	}*/
	
	public void deleteOrder(int order_id){
		try {
			PreparedStatement ps = dbConnection.prepareStatement("delete from order where order_id = " + order_id);
			ps.execute();
		}
		
		catch (SQLException e){
			System.out.println(e);
		}
		
	}
}
