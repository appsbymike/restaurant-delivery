package del.res.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.bo.ItemsBO;
import del.res.models.Item;
import del.res.utilities.toCurrency;

/**
 * Servlet implementation class Review
 */
@WebServlet("/ReviewOrder")
public class ReviewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewOrder() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ItemsBO itemsBO = new ItemsBO();
    	HttpSession session = request.getSession();
    	if(session.getAttribute("cart_ids") != null) {
    		@SuppressWarnings("unchecked")
    		HashSet<Integer> cartIDs = (HashSet<Integer>) session.getAttribute("cart_ids");
        	ArrayList<Item> cart = itemsBO.getCartItems(cartIDs);
        	session.setAttribute("cart", cart);
        	
        	ArrayList<Double> totals = itemsBO.getTotals(cartIDs);
        	session.setAttribute("pretax_total", totals.get(0));
        	session.setAttribute("tax_total", totals.get(1));
        	session.setAttribute("posttax_total", toCurrency.format(totals.get(2)));
        	
        	System.out.println(cart);
        	System.out.println(totals);
        	
        	
        	response.sendRedirect(request.getContextPath() + "/OrderReview.jsp");
    	}
    	else {
    		response.sendRedirect(request.getContextPath() + "/Menu.jsp?isempty=true");
    	}
	}
    
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		if(request.getParameter("remove") != null){
			int id = Integer.parseInt(request.getParameter("remove"));
			HashSet<Integer> cart_ids = (HashSet<Integer>) session.getAttribute("cart_ids");
			cart_ids.remove(id);
			session.setAttribute("cart_ids", cart_ids);
			response.sendRedirect(request.getContextPath() + "/ReviewOrder");
		}
		
		if(request.getParameter("cancel") != null){
			session.removeAttribute("cart_ids");
			response.sendRedirect(request.getContextPath() + "/Menu.jsp");
		}
		
			
		
		if(request.getParameter("process") != null && session.getAttribute("cart") != null){
			ArrayList<Item> test = (ArrayList<Item>) session.getAttribute("cart");
			if(test.isEmpty()) {
				response.sendRedirect(request.getContextPath() + "/OrderReview.jsp?isempty=true");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/PaymentInfo.jsp");
			}
		}
	}

}
