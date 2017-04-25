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
		
		int order_id = request.getAttribute("id") != null ? (int)request.getAttribute("id") : Integer.parseInt(request.getParameter("id"));
		
		OrderBean order = db.getOrderInfo(order_id);
		httpSes.setAttribute("order", order);  
		 
		LinkedList<OrderLineBean> orderLines = db.getOrderLineInfo(order_id);
		httpSes.setAttribute("orderlines", orderLines);
		
		response.sendRedirect("view_order.jsp"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
