package crdelf01.finalproject;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
		
		//create the order
		int order_id = -1;
		if(httpSes.getAttribute("grandTotal") != null){
			order_id = db.createOrder(db.getUserInfo().getUserid(), (double)httpSes.getAttribute("grandTotal"));
		}
				
		HashMap<Integer,ShoppingCartItemBean> shoppingCart = (HashMap<Integer,ShoppingCartItemBean>) httpSes.getAttribute("shoppingCart");
		if(shoppingCart == null){
			//System.out.println("shopping cart is null so create one");
			shoppingCart = new HashMap<Integer,ShoppingCartItemBean>();
		}
		
		//create the order lines
		if(order_id > -1 && shoppingCart.keySet().size() > 0){
			db.createOrderLines(order_id, shoppingCart);
		}
		
		//create a new shopping cart, the order was already placed.
		shoppingCart = new HashMap<Integer,ShoppingCartItemBean>();
		httpSes.setAttribute("shoppingCart", shoppingCart);
		httpSes.setAttribute("grandTotal", 0.0); 
		
		request.setAttribute("id", order_id);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/ViewOrder");
		dispatcher.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
