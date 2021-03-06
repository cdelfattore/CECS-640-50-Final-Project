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
 * Servlet implementation class ItemServlet
 */
@WebServlet(description = "Class to allow retrieving information about items", urlPatterns = { "/ItemServlet" })
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSes = request.getSession();
		
		DatabaseAccess db = (DatabaseAccess) httpSes.getAttribute("dbInstance");

		//if the db instance is null have the user re log in
		if(db == null){
			response.sendRedirect("login.jsp");
		}
		else {
			//retrieve a list of the items
			String searchTerm = request.getParameter("searchTerm");
			
			LinkedList<ItemBean> itemList = db.searchForItems(searchTerm);
			
			request.setAttribute("itemList", itemList);
			
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
