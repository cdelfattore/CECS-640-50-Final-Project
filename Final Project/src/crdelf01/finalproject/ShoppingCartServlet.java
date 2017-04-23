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
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer qty = Integer.parseInt(request.getParameter("qty"));
		Integer item_id = Integer.parseInt(request.getParameter("item_id"));
		String name = request.getParameter("name");
		Double price = Double.parseDouble(request.getParameter("price"));
						
		HttpSession httpSes = request.getSession();
		//A shopping cart will be represented using the ShoppingCartBean
		//first check to see if a shopping has been created
		HashMap<Integer,ShoppingCartItemBean> shoppingCart = (HashMap<Integer,ShoppingCartItemBean>) httpSes.getAttribute("shoppingCart");
		if(shoppingCart == null){
			//System.out.println("shopping cart is null so create one");
			shoppingCart = new HashMap<Integer,ShoppingCartItemBean>();
		}
		/*else {
			System.out.println("shopping cart is not null");
		}*/

		//create a new ShoppingCartItemBean that represents a new item being added to the cart
		if(shoppingCart.get(item_id) == null){
			shoppingCart.put(item_id, new ShoppingCartItemBean(item_id, qty, name, price));
		}
		//update an existing item in the cart with the new quantity
		else {
			shoppingCart.get(item_id).setQuantity(qty);
			shoppingCart.get(item_id).setName(name);
			shoppingCart.get(item_id).setPrice(price);
		}

		//add the shopping cart to the session object
		httpSes.setAttribute("shoppingCart", shoppingCart); 
		
		//set the grand total of the shopping cart
		Double grandTotal = 0.0; 
		for(Integer i : shoppingCart.keySet()){
			grandTotal += shoppingCart.get(i).getPrice() * shoppingCart.get(i).getQuantity();
		}
		httpSes.setAttribute("grandTotal", grandTotal); 
				
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/cart.jsp");
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
