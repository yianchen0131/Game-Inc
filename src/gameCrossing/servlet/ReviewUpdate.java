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


@WebServlet("/reviewupdate")
public class ReviewUpdate extends HttpServlet {
	
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

        // Retrieve userid
        String id = req.getParameter("reviewid");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid review ID.");
        } else {
        	try {
        		Integer reviewid = Integer.parseInt(id);
        		Review review = reviewDao.getReviewByReviewId(reviewid);
        		if(review == null) {
        			messages.put("success", "This review does not exist!");
        		}
        		req.setAttribute("review", review);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve review and validate.
        String id = req.getParameter("reviewid");
        if (id == null || id.trim().isEmpty()) {
            messages.put("success", "Please enter a valid review ID.");
        } else {
        	try {
        		Integer reviewid = Integer.parseInt(id);
        		Review review = reviewDao.getReviewByReviewId(reviewid);
        		if(review == null) {
        			messages.put("success", "Review ID does not exist. No update to perform.");
        		} else {
        			String newcontent = req.getParameter("newcontent");
        			if (newcontent== null || newcontent.trim().isEmpty()) {
        	            messages.put("success", "Please do not leave the content empty");
        	        } else {
        	        	review = reviewDao.updateReviewContent(review, newcontent);
        	        	messages.put("success", "Successfully updated Review" + reviewid);
        	        }
        		}
        		req.setAttribute("review", review);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReviewUpdate.jsp").forward(req, resp);
    }
}
