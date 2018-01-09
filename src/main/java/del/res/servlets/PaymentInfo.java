package del.res.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.bo.*;
import del.res.utilities.*;

/**
 * Servlet implementation class PaymentInfo
 */
@WebServlet("/PaymentInfo")
public class PaymentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PaymentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdersBO ordersBO = new OrdersBO();
		HttpSession session = request.getSession();
		if(request.getParameter("process") != null && session.getAttribute("store_id") != null){
			
			//Get payment info------------------------------------------
			String creditCardNumber = request.getParameter("ccnumber");
			String securityCode = request.getParameter("seccode");
			String zipCode = request.getParameter("zipcode");
			//----------------------------------------------------------
			
			//Validate Payment Info
			Validator v = new Validator();
			if(v.isValidPaymentInfo(creditCardNumber, securityCode, zipCode)) {
				
				//Get order info-----------------------------------------
				String userID = (String) session.getAttribute("user_id");
				String storeID = (String) session.getAttribute("store_id");
				
				//Get tax values and convert them to strings for easy querying
				double preTax = ((Double) session.getAttribute("pretax_total"));
				String preTaxTotal = toCurrency.format(preTax);
				
				//Same as above
				double tax = ((Double) session.getAttribute("tax_total"));
				String taxTotal = toCurrency.format(tax);
				//--------------------------------------------------------
				
				try {
				//Create Order & Return OrderID
				
				int orderID = ordersBO.createOrder(userID, storeID, creditCardNumber, securityCode, zipCode, preTaxTotal, taxTotal);
				if(orderID != 0){
					//Create an order_item for each item in the cart
					@SuppressWarnings("unchecked")
					HashSet<Integer> cart_ids = (HashSet<Integer>) session.getAttribute("cart_ids");
					for(Integer itemID : cart_ids) {
						if(ordersBO.addItemToOrder(orderID,itemID) == 0) {
							throw new Exception("Error: Item with ID " + itemID + " NOT ADDED TO ORDER!!!!");							
						}
					}
				}
				else {
					throw new Exception("Error: Order ID not found!");
				}
				
				//Retire session attributes relating to the cart
				Enumeration<String> e = session.getAttributeNames();
				while(e.hasMoreElements()){
					String attribute = (String) e.nextElement();
					session.removeAttribute(attribute);
				}
				
				session.setAttribute("user_id", userID);
				
				//Send user to order receipt page
				response.sendRedirect(request.getContextPath() + "/Receipt?order_id="+orderID);
				
				}
				catch (Exception e){
					e.printStackTrace();;
					response.sendRedirect(request.getContextPath() + "/Error.jsp");
				}
			}
			else {
				response.sendRedirect(request.getContextPath() + "/PaymentInfo.jsp?invalidformat=true");
			}
			
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
	}

}
