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

@WebServlet("/playtimecreate")
public class CreatePlaytime extends HttpServlet {
	
	protected PlaytimeDao playtimeDao;
	protected GameDao gameDao;
	
	@Override
	public void init() throws ServletException {
		playtimeDao = PlaytimeDao.getInstance();
		gameDao = GameDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
		//render view with JSP
        req.getRequestDispatcher("PlayTimeCreate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		 Map<String, String> messages = new HashMap<String, String>();
		 req.setAttribute("messages", messages);
		
		 //Retrieve and validate name
		 String gameID = req.getParameter("gameID");
		 if (gameID == null || gameID.trim().isEmpty()) {
			 messages.put("success", "Invalid Game ID");
		 } else {
			 //create playtime object
			 gameID = req.getParameter("gameID");
			 String avgTime = req.getParameter("averagePlayTime");
			 String medTime = req.getParameter("medianPlayTime");
			 try {
				 Game game = gameDao.getGameByGameId(Integer.parseInt(gameID));
				 Integer a = Integer.parseInt(avgTime);
				 Integer b = Integer.parseInt(medTime);
				 Playtime playtime = new Playtime(a, b, game);
				 playtime = playtimeDao.create(playtime);
				 messages.put("success", "Successfully created new PlayTime for " + game.getGameName());
			 } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
		 }
		req.getRequestDispatcher("/PlayTimeCreate.jsp").forward(req, resp);
	}
//end servlet
}
