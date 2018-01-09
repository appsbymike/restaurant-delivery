package del.res.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.dao.OrdersDAO;
import del.res.models.PastOrder;

/**
 * Servlet implementation class PastOrders
 */
@WebServlet("/PastOrders")
public class PastOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PastOrders() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		OrdersDAO ordersDAO = new OrdersDAO();
		if (user_id != null) {
			ArrayList<PastOrder> orders = ordersDAO.getOrdersByUser(Integer.parseInt(user_id));
			request.setAttribute("orders", orders);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/PastOrders.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
		
	}

}
