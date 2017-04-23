package crdelf01.finalproject;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSes = request.getSession();
		DatabaseAccess db = (DatabaseAccess) httpSes.getAttribute("dbInstance");
		
		
		//first create the order
		OrderBean order = null;
		if(httpSes.getAttribute("grandTotal") != null){
			order = db.createOrder(db.getUserInfo().getUserid(), (double)httpSes.getAttribute("grandTotal"));
		}
		
		System.out.println(order);
		
		/*HashMap<Integer,ShoppingCartItemBean> shoppingCart = (HashMap<Integer,ShoppingCartItemBean>) httpSes.getAttribute("shoppingCart");
		if(shoppingCart == null){
			//System.out.println("shopping cart is null so create one, will display blank on order page.");
			shoppingCart = new HashMap<Integer,ShoppingCartItemBean>();
		}*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
