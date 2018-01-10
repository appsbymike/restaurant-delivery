package del.res.servlets;


import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.dao.UsersDAO;
import del.res.utilities.Validator;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersDAO usersDAO = new UsersDAO();
		HttpSession session = request.getSession();
		String context = request.getContextPath();
		String username = null;
		String password = null;
		String id = null;
		if (request.getParameter("login") != null){
			username = request.getParameter("username");
			password = request.getParameter("password");
			Validator v = new Validator();
			if(v.isValidLogin(username, password)) {
				try {
					id = usersDAO.loginQuery(username, password);
					if(id != null) {
						Enumeration<String> e = session.getAttributeNames();
						while(e.hasMoreElements()){
							String attribute = (String) e.nextElement();
							session.removeAttribute(attribute);
						}
						session.setAttribute("user_id", id);
						if(usersDAO.isAdmin(id)) {
							System.out.println("User is an admin");
							session.setAttribute("isAdmin",true);
							response.sendRedirect(context + "/admin/");
						}
						else {
							response.sendRedirect(context + "/Menu");
						}
					}
					else {
						response.sendRedirect(context + "/Login.jsp?invalid=true");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				response.sendRedirect(context + "/Login.jsp?invalid=true");
			}
		}
		
		if (request.getParameter("register") != null){
			response.sendRedirect(context + "/Register.jsp");
		}
	}

}
