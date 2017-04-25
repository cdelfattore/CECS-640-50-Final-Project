package crdelf01.finalproject;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSes = request.getSession();
		DatabaseAccess db = (DatabaseAccess) httpSes.getAttribute("dbInstance");
		
		//update the order lines
		LinkedList<OrderLineBean> orderLines = (LinkedList<OrderLineBean>)httpSes.getAttribute("orderlines");
		for(OrderLineBean ol : orderLines){
			String newQty = request.getParameter(String.valueOf(ol.getOrder_line_id()) + "_qty");
			ol.setQuantity(Integer.parseInt(newQty));
			ol.setTotal(Integer.parseInt(newQty) * ol.getItemPrice());
		}
		
		db.updateOrderLines(orderLines);
			
		//next update the orders
		//db.updateOrder(db.getOrderLineInfo(orderLines.get(0).getOrder_id()));
		
		UserBean user = db.getUserInfo();
		LinkedList<OrderBean> orderList = db.getOrders(user.getUserid());
		httpSes.setAttribute("orderlist", orderList);	
		
		response.sendRedirect("orders.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
