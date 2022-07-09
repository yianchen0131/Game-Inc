package gameCrossing.servlet;

import gameCrossing.dal.*;
import gameCrossing.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewdelete")
public class ReviewDelete extends HttpServlet {
	protected ReviewDao reviewDao;

	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();

	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Review");        
        req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate reviewId
        String id = req.getParameter("reviewid");
        if (id == null || id.trim().isEmpty()) {
            messages.put("title", "Invalid Review ID");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	Integer reviewid = Integer.parseInt(req.getParameter("reviewid"));
	        try {
	        	Review review = reviewDao.getReviewByReviewId(reviewid);
	        	review = reviewDao.delete(review);
	        	// Update the message.
		        if (review== null) {
		            messages.put("title", "Successfully deleted review " + reviewid);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete review " + reviewid);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewDelete.jsp").forward(req, resp);
    }
	
}
