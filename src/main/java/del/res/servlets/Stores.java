package del.res.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.bo.StoresBO;
import del.res.models.Store;

/**
 * Servlet implementation class Stores
 */
@WebServlet("/Stores")
public class Stores extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context;
	
    public Stores() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	StoresBO storesBO = new StoresBO();
    	context = request.getServletContext();
    	if (context.getAttribute("stores") == null) {
    		ArrayList<Store> stores = storesBO.getAllStores();
    		context.setAttribute("stores", stores);
    		
      	}
    	response.sendRedirect(request.getContextPath() + "/Stores.jsp");
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("bt") != null) {
			String id = request.getParameter("bt");
			session.setAttribute("store_id", id);
			response.sendRedirect(request.getContextPath() + "/ReviewOrder");
		}
	}

}
