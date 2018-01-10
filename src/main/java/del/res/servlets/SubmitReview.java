package del.res.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import del.res.dao.ReviewsDAO;
import del.res.utilities.Validator;

/**
 * Servlet implementation class SubmitReview
 */
@WebServlet("/SubmitReview")
public class SubmitReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitReview() {
        super();
        // TODO Auto-generated constructor stub
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
				response.sendRedirect(request.getContextPath() + "/SingleItem.jsp?item_id=" + itemID + "&submit=true");
			}
			else {
				response.sendRedirect(request.getContextPath() + "/SingleItem.jsp?item_id=" + itemID + "&invalidformat=true");
			}
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Error.jsp");
		}
		
		
	}

}
