package del.res.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.dao.UsersDAO;
import del.res.models.User;
import del.res.utilities.Validator;

/**
 * Servlet implementation class UpdateAccount
 */
@WebServlet("/UpdateAccount")
public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	if(session.getAttribute("user_id") != null) {
    		String successPage = "/AccountDetails.jsp";
        	UsersDAO usersDAO = new UsersDAO();
    		//Grab userID and make sure it's not null
    		String userID = (String) request.getSession().getAttribute("user_id");
    		//Grab the user details and attach to request as an attribute
    		User user_info = usersDAO.getDetails(userID);
    		if(user_info != null) {
    			request.setAttribute("user_info", user_info);
            	//Forward to successPage
            	dispatcher = request.getRequestDispatcher(successPage);
            	dispatcher.forward(request, response);
    		}
    		else {
    			response.sendRedirect(request.getContextPath() + "/Error.jsp");
    		}
            	
    	}
    	else {
    		response.sendRedirect(request.getContextPath() + "/Error.jsp");
    	}
    	
    	
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("update") != null && session.getAttribute("user_id") != null) {
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String userID = (String) session.getAttribute("user_id");
			Validator v = new Validator();
			String successPage;
			//Validate user info
			if(v.isValidUpdate(password, repassword, firstname, lastname, address, phone, email)) {
				//Validate password is the same as re-entered password
				if(password.equals(repassword)) {
					//Store info in model
					User info = new User(firstname,lastname,password,address,phone,email);
					info.setId(userID);
					UsersDAO UsersDAO = new UsersDAO();
					//Update user with info in model
					UsersDAO.updateUser(info);
					successPage = "/AccountDetails.jsp?success=true";
				}
				else {
					successPage = "/AccountDetails.jsp?nomatch=true";
				}
			}
			else {
				successPage = "/AccountDetails.jsp?invalidformat=true";
			}
			
			UsersDAO usersDAO = new UsersDAO();
	    	//Grab the user details and attach to request as an attribute
	    	User user_info = usersDAO.getDetails(userID);
	    		if(user_info != null) {
	    			request.setAttribute("user_info", user_info);
	            	//Forward to successPage
	            	dispatcher = request.getRequestDispatcher(successPage);
	            	dispatcher.forward(request, response);
	    		}
	    		else {
	    			response.sendRedirect(request.getContextPath() + "/Error.jsp");
	    		}
	        	
	    	}
	    	else {
	    		//Send to error page if userID is null
	    		response.sendRedirect(request.getContextPath() + "/Error.jsp");
	    	}
			
		}
}
