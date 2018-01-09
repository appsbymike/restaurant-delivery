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

import del.res.dao.ReceiptsDAO;
import del.res.models.Item;
import del.res.models.ReceiptSummary;

/**
 * Servlet implementation class Receipt
 */
@WebServlet("/Receipt")
public class Receipt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Receipt() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ReceiptsDAO receiptsDAO = new ReceiptsDAO();
    	HttpSession session = request.getSession();
		//Check that an order_id was passed
		if(request.getParameter("order_id") !=null && session.getAttribute("user_id") != null){
			String strOrderID = (String) request.getParameter("order_id");
			//Make sure that order_id is in the proper format
			if(strOrderID.matches("[0-9]+")){
				int orderID =  Integer.parseInt(strOrderID);
				int userID = Integer.parseInt((String) session.getAttribute("user_id"));
				//Check that the order was made by this user
				if(receiptsDAO.validateUser(orderID, userID)){
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Receipt.jsp");
					
					//Grab receiptItems and receiptSummary
					ArrayList<Item> receiptItems = receiptsDAO.getReceiptItems(orderID);
					ReceiptSummary receiptSummary = receiptsDAO.getReceiptSummary(orderID);
					
					//Add both along with order_id to request attributes
					request.setAttribute("order_id", orderID);
					request.setAttribute("receipt", receiptItems);
					request.setAttribute("rs", receiptSummary);
					
					//Forward the request
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
		
	}

}
