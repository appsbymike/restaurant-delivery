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

import del.res.dao.ReviewsDAO;
import del.res.models.Item;
import del.res.models.PastReview;
import del.res.utilities.Validator;

/**
 * Servlet implementation class SingleItem
 */
@WebServlet("/SingleItem")
public class SingleItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher;
	String successPage;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingleItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		successPage = "/SingleItem.jsp";
		String itemID = request.getParameter("item_id");
		if(itemID != null) {
			ReviewsDAO reviewsDAO = new ReviewsDAO();
			//Grab the item details and attach to request as an attribute
			Item item = reviewsDAO.getItemInfo(itemID);
			request.setAttribute("item", item);
			
			//Grab the past reviews and attach to request as an attribute
			ArrayList<PastReview> reviews = reviewsDAO.getPastReviews(itemID);
			request.setAttribute("reviews", reviews);
			
	    	//Forward to successPage
	    	dispatcher = request.getRequestDispatcher(successPage);
	    	dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		HttpSession session = request.getSession();
		if(request.getParameter("review") != null) {
			String userID = (String) session.getAttribute("user_id");
			String itemID = request.getParameter("review");
			String reviewText = request.getParameter("reviewText");
			Validator v = new Validator();
			if(v.isValidReview(reviewText)) {
				reviewsDAO.submitReview(userID, itemID, reviewText);
				successPage ="/SingleItem.jsp?submit=true";
			}
			else {
				successPage ="/SingleItem.jsp?invalidformat=true";
			}
			//Grab the item details and attach to request as an attribute
    		Item item = reviewsDAO.getItemInfo(itemID);
    		request.setAttribute("item", item);
    		
    		//Grab the past reviews and attach to request as an attribute
    		ArrayList<PastReview> reviews = reviewsDAO.getPastReviews(itemID);
    		request.setAttribute("reviews", reviews);
    		
        	//Forward to successPage
        	dispatcher = request.getRequestDispatcher(successPage);
        	dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
		
	}

}
