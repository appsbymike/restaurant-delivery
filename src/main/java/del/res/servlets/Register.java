package del.res.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import del.res.bo.UsersBO;
import del.res.utilities.Validator;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersBO usersBO = new UsersBO();
		
		
		if(request.getParameter("cancel") != null){
			response.sendRedirect(request.getContextPath() + "/Login.jsp");
		}
		
		if(request.getParameter("register") != null){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String gender = request.getParameter("gender");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			//Begin Validation
			Validator v = new Validator();
			if(v.isValidRegistration(username, password, repassword, firstname, lastname, gender, address, phone, email)) {
				if(password.equals(repassword)){
					try {
						if(usersBO.registerQuery(username) == false){
							usersBO.createUser(username,password,firstname,lastname,gender,address,phone,email);
							response.sendRedirect(request.getContextPath() + "/Login.jsp");
						}
						else{
							response.sendRedirect(request.getContextPath() + "/Register.jsp?userexists=true");
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response.sendRedirect(request.getContextPath() + "/Error.jsp");
					}
				}
				else{
					response.sendRedirect(request.getContextPath() + "/Register.jsp?nomatch=true");
				}
			}
			else {
				response.sendRedirect(request.getContextPath() + "/Register.jsp?invalidformat=true");
			}
		}
	}
	
	

}
