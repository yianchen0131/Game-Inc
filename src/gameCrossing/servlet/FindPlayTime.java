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


/**
 * servlet- Primary entry point into the applicaiton
 * @author weinienchen
 * doGet() handles the HTTP GET request, method is called when you put in the /findPlaytime URL in the brower
 * doPost() handles the HTTP POST request, method is called after you click the submit button
 * 
 * 1. run SQL script to recreate database schema
 * 2. insert test data by running Inserter (run as > Java Application
 * 3. Run the tomcat server at localhost
 * 4. point browser to http://localhost:8080/GameCrossing/findplaytime
 * 
 */
@WebServlet("/findplaytime")
public class FindPlayTime extends HttpServlet {
	
	protected PlaytimeDao playtimeDao;
	
	@Override
	public void init() throws ServletException {
		playtimeDao = PlaytimeDao.getInstance();
	}
	
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//map for storing messages apparently
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
			
		Playtime playtime = null;
		//retrieve playtimeID from URL query and validate
		//findplaytime?playtimeID= **retrieves value here**
		String playtimeID = req.getParameter("playtimeID");
		if (playtimeID == null || playtimeID.trim().isEmpty()) {
			messages.put("success", "Please enter a valid playtime ID");
			//when nothing is entered at the moment, show this success message
		} else {
			//retrieve Playtime and store as a message
			try {
				playtime = playtimeDao.getPlaytimeByPlaytimeId(Integer.parseInt(playtimeID));
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for playtimeID" + playtimeID);
			
			//save search term
			messages.put("previousPlaytimeID", playtimeID);
		}
		req.setAttribute("playtime", playtime);;
//send to JSP to render view
//Forwards a request from a servlet to another resource (servlet, JSP file, or HTML file) on the server. 
//This method allows one servlet to do preliminary processing of a request 
//and another resource to generate the response.
		req.getRequestDispatcher("/FindPlayTime.jsp").forward(req,resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//map for storing messages apparently
				Map<String, String> messages = new HashMap<String, String>();
				req.setAttribute("messages", messages);
		
				Playtime playtime = null;		
				
				//retrieve and validate name
				//postID is retried from the form POST submsision
				//by default will be populted by the URL query string in .jsp
				String playtimeID = req.getParameter("playtimeID");
				if (playtimeID == null || playtimeID.trim().isEmpty()) {
					messages.put("success", "Please enter a valid playtime ID");
					//when nothing is entered at the moment, show this success message
				} else {
					//retrieve the Playtime Object by this ID
					try {
						
						//originally was null because playtime was deleted during inserter!
						playtime = playtimeDao.getPlaytimeByPlaytimeId(Integer.parseInt(playtimeID));
						
					} catch (SQLException e) {
						e.printStackTrace();
						throw new IOException(e);
					}
					messages.put("success", "Displaying results for Playtime id " + playtimeID);
				}
				req.setAttribute("playtime", playtime);;
			
			
				req.getRequestDispatcher("/FindPlayTime.jsp").forward(req,resp);	
	}			
	
	
	
	
	
//ends servlet
}
