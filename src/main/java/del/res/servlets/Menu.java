package del.res.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.bo.ItemsBO;
import del.res.models.Item;



/**
 * Servlet implementation class Menu
 */
@WebServlet("/Menu")
public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Menu() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ItemsBO itemsBO = new ItemsBO();
    	String path = request.getContextPath();
    	ServletContext context = request.getServletContext();
    	if (context.getAttribute("menu") == null) {
    		ArrayList<Item> menu = itemsBO.getAllActiveItems();
    		context.setAttribute("menu", menu);
      	}
    	System.out.println("Hi");
    	response.sendRedirect(path + "/Menu.jsp");
    	
    	
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String message = null;
		
		if(request.getParameter("cart") != null){
			int itemID = Integer.parseInt(request.getParameter("cart"));
			//Check if cart_ids already exists in the session
			if(session.getAttribute("cart_ids") != null) {
				@SuppressWarnings("unchecked")
				HashSet<Integer> cart_ids = (HashSet<Integer>) session.getAttribute("cart_ids");
				//If so, add
				cart_ids.add(itemID);
				session.setAttribute("cart_ids", cart_ids);
			}
			else {
				HashSet<Integer> cart_ids = new HashSet<Integer>();
				cart_ids.add(itemID);
				session.setAttribute("cart_ids", cart_ids);
			}
			
			message="Item added to cart!";
			
		}
		
		if(request.getParameter("reviews") != null){
			//TODO
			response.sendRedirect(request.getContextPath() + "/SingleItem?item_id="+request.getParameter("reviews"));
		}
		
		if(message != null) {
			response.sendRedirect(request.getContextPath() + "/Menu.jsp?message=" + message );
		}
		
		if(request.getParameter("process") != null){
			response.sendRedirect(request.getContextPath() + "/Stores.jsp");
		}
		
	}

}
