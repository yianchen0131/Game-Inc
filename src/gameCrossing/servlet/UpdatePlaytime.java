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

@WebServlet("/playtimeupdate")
public class UpdatePlaytime extends HttpServlet {
	
	protected PlaytimeDao playtimeDao;
	
	@Override
	public void init() throws ServletException {
		
		playtimeDao = PlaytimeDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        //retrieves the playtime from the URL request and validate
        String playtimeID = req.getParameter("playtimeID");
        if (playtimeID == null || playtimeID.trim().isEmpty()) {
			messages.put("success", "ID invalid. No Action");
			//when nothing is entered at the moment, show this success message
		} else {
			try {
				Playtime playtime = playtimeDao.getPlaytimeByPlaytimeId(Integer.parseInt(playtimeID));
				if (playtime == null) {
					messages.put("success", "Please enter a valid ID for Playtime.");
				}
				req.setAttribute("playtime", playtime);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
		}
        req.getRequestDispatcher("/PlayTimeUpdate.jsp").forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        //retrieve ID and validate
        String playtimeID = req.getParameter("playtimeID");
        if (playtimeID == null || playtimeID.trim().isEmpty()) {
			messages.put("success", "Please enter a valid playtime ID");
			//when nothing is entered at the moment, show this success message
		} else {
			try {
				Playtime playtime = playtimeDao.getPlaytimeByPlaytimeId(Integer.parseInt(playtimeID));
				if (playtime == null) {
					messages.put("success", "ID invalid. No Action");
				} else {
					String newAvgTime = req.getParameter("averagePlayTime");
					String newMedTime = req.getParameter("medianPlayTime");
					if ( (newAvgTime == null || newAvgTime.trim().isEmpty()) &&
						 newMedTime == null || newMedTime.trim().isEmpty() ) 
					{
						messages.put("success", "Please enter a valid number");
					} 
					else {
						playtime = playtimeDao.updateAveragePlaytime(playtime, Integer.parseInt(newAvgTime));
						playtime = playtimeDao.updateMedianPlaytime(playtime, Integer.parseInt(newMedTime));
						messages.put("success", "Successfully updated Average Time");
					}
				}
				//playtime object inside try block
				req.setAttribute("playtime", playtime);
			}  catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
		}
		req.getRequestDispatcher("/PlayTimeUpdate.jsp").forward(req, resp);
	}
	
//end servlet
}


