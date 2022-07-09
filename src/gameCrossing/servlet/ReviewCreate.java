package gameCrossing.servlet;

import gameCrossing.dal.*;
import gameCrossing.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewcreate")
public class ReviewCreate extends HttpServlet {
	protected ReviewDao reviewDao;
	protected GameDao gameDao;
	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewDao.getInstance();
		gameDao = GameDao.getInstance();
		userDao = UserDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String gameName = req.getParameter("gamename");
        String id = req.getParameter("userid");
        if (gameName == null || gameName.trim().isEmpty() || id == null || id.trim().isEmpty()) {
            messages.put("success", "This game is NOT found. Please enter a valid game name and user ID");
        } else {
     
			try {
				Integer userId = Integer.parseInt(id);
				if (gameDao.getGameByGameName(gameName) == null){
					messages.put("success", "This game is NOT found. Please enter a valid game name.");
				} else if (userDao.getUserByUserId(userId) == null) {
					messages.put("success", "This user is NOT found. Please enter a valid user ID.");
				}else {
					// Create the Review.
//					Integer userId = Integer.parseInt(id);
					String hourstr = req.getParameter("hourlyPlayed");
					Integer hourlyplay = null;
					if (hourstr != null && !hourstr.equals("")) {
						hourlyplay = Integer.parseInt(req.getParameter("hourlyPlayed"));
					}
					
					String earlystr = req.getParameter("isearly");
					Boolean isearly = null;
					if (earlystr != null && !earlystr.contentEquals("")) {
						isearly = Boolean.parseBoolean(req.getParameter("isearly"));
					}
					
					
					String enumValue = req.getParameter("recommendation");
					Review.Recommendation recommendation = null;
					if (enumValue == null) {
						recommendation = null;
					} else if (enumValue.contentEquals("Recommended")){
						recommendation = Review.Recommendation.RECOMMENDED;
					}else {
						recommendation = Review.Recommendation.NOTRECOMMENDED;
					}
					/**
					 * //			        switch (enumValue) {
//			            case "Recommended": recommendation = Review.Recommendation.RECOMMENDED;
//			                     break;
//			            case "NotRecommended": recommendation = Review.Recommendation.NOTRECOMMENDED;
//			                     break;
//			        }

					 */

				
					String content = req.getParameter("content");
					Date createdWhen = new Date();
					
				    try {
				    	Game game = gameDao.getGameByGameName(gameName);
				    	User user = userDao.getUserByUserId(userId);
				    	Review review = new Review(createdWhen, (long)0,0,hourlyplay,isearly,recommendation, content, game, user);
				    	review = reviewDao.create(review);
				    	messages.put("success", "Successfully created this review!");
				    } catch (SQLException e) {
						e.printStackTrace();
						throw new IOException(e);
				    }
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
        
        }
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
  }
}
