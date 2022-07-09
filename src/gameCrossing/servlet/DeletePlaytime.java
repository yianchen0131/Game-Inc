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

@WebServlet("/playtimedelete")
public class DeletePlaytime extends HttpServlet {
	
	protected PlaytimeDao playtimeDao;
	
	@Override
	public void init() throws ServletException {
		playtimeDao = PlaytimeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
	  	req.setAttribute("messages", messages);
		//simple because do not have to read any objects from param
		messages.put("title", "Delete Playtime Record");
		req.getRequestDispatcher("/PlayTimeDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		//validate ID
        String playtimeID = req.getParameter("playtimeID");
        if (playtimeID == null || playtimeID.trim().isEmpty()) {
        	//if invalid, hide the form
        	  messages.put("title", "Invalid Playtime ID");
              messages.put("disableSubmit", "true");
        } else {
        	Playtime playtime = new Playtime(Integer.parseInt(playtimeID));
        	try {
        		playtime = playtimeDao.delete(playtime);
        		//display message if deleted
        		if (playtime == null) {
        			messages.put("title", "Successfully deleted Playtime Record");
		            messages.put("disableSubmit", "true");
        		} else {
        			messages.put("title", "Failed to delete Playtime record " + playtimeID);
		        	messages.put("disableSubmit", "false");
        		}
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/PlayTimeDelete.jsp").forward(req, resp);
		
	}
	
//end servlet	
}
