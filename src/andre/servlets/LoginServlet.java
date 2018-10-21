package andre.servlets;

import listener.MySessionContext;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/*
 * Author: And
 * 
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String userID = "andre";
	private final String pwd = "1234";
	private final String userID2 = "user";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		String user = request.getParameter("userName");
		String password = request.getParameter("password");
		MySessionContext myc= MySessionContext.getInstance();
		HttpSession sess = myc.getSession(request.getRequestedSessionId());

		if (userID.equals(user) && pwd.equals(password)) {
//			Cookie loginCookie = new Cookie("user", user);
//			// setting cookie to expiry in 30 mins
//			loginCookie.setMaxAge(30 * 60);
//			response.addCookie(loginCookie);
			//response.sendRedirect("adminpage.jsp");
			//response.sendRedirect("loginSuccess2.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("loginName", user);
			request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess2.jsp").forward(request, response);
		}
		
		else if (userID2.equals(user) && pwd.equals(password)) {
			Cookie loginCookie = new Cookie("user", user);
			// setting cookie to expiry in 30 mins
			loginCookie.setMaxAge(30 * 60);
			response.addCookie(loginCookie);
			response.sendRedirect("loginSuccess.jsp");
		} 
		else {
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Userul sau parola sunt gresite!.</font>");
			requestDispatcher.include(request, response);
		}

	}

}