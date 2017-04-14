package crdelf01.finalproject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DatabaseAccess database = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogIn() {
        super();
        
        database = new DatabaseAccess();
        database.connectToDatabase();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(userName == null || password == null){
			//throw some error
		}
		else {
			try {
				boolean userInDB = database.verifyUser(userName, password);
				
				String url = null;
				if(userInDB){
					//credentials are valid
					url = "/home.jsp";
				}
				else {
					//user is not in database, wrong username typed or wrong password typed.
					url = "/login.jsp";
				}
				
				request.setAttribute("userInDB", userInDB);
				ServletContext context = getServletContext();
				RequestDispatcher dispatcher = context.getRequestDispatcher(url);
				//database.disconnectFromDB();
				dispatcher.forward(request, response);
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("did do post");
		doGet(request, response);
	}
	

}

