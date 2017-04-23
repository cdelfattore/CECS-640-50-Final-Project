package crdelf01.finalproject;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewOrder
 */
@WebServlet("/ViewOrder")
public class ViewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSes = request.getSession();
		DatabaseAccess db = (DatabaseAccess) httpSes.getAttribute("dbInstance");
		
		int order_id = (int)request.getAttribute("id");
		
		OrderBean order = db.getOrderInfo(order_id);
		request.setAttribute("order", order);  
		
		LinkedList<OrderLineBean> orderLines = db.getOrderLineInfo(order_id);
		request.setAttribute("orderlines", orderLines);
		
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/view_order.jsp");
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
