package gameCrossing.servlet;

import gameCrossing.dal.*;
import gameCrossing.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findreviews")
public class FindReviews extends HttpServlet{
	protected ReviewDao reviewDao;
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Map for storing messages
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Review> reviews = new ArrayList<>();
        
        // retrieve and validate game name
        // gamename is retrieved from the URL query string
        String gameName = req.getParameter("gamename");
        if (gameName == null || gameName.trim().isEmpty()) {
        	messages.put("success", "This game is NOT found. Please enter a valid game name.");
        } else {
        	// Retrieve Reviews, and store as a message
        	try {
        		reviews = reviewDao.getReviewByGameName(gameName);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + gameName);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindReviews.jsp.
        	messages.put("previousGameName", gameName);
        }
        // "reviews" is the attribute name in the FindReviews.jsp
    	req.setAttribute("reviews", reviews);
    	req.getRequestDispatcher("/FindReviews.jsp").forward(req,resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Review> reviews = new ArrayList<Review>();
        
        // Retrieve and validate name.
        // gamename is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindReviews.jsp).
        String gameName = req.getParameter("gamename");
        if (gameName == null || gameName.trim().isEmpty()) {
            messages.put("success", "This game is NOT found. Please enter a valid game name.");
        } else {
        	// Retrieve Reviews, and store as a message.
        	try {
            	reviews = reviewDao.getReviewByGameName(gameName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + gameName);
        }
        req.setAttribute("reviews", reviews);
        
        req.getRequestDispatcher("/FindReviews.jsp").forward(req, resp);
    }
	
	
}
