package gameCrossing.dal;

import gameCrossing.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


public class ReviewDao {
	protected ConnectionManager connectionManager;
	
	private static ReviewDao instance = null;
	protected ReviewDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDao();
		}
		return instance;
	}
	
	
	// create a review
	public Review create(Review review) throws SQLException {

		String insertReview = "INSERT INTO Review(GameID, UserID, DatePosted, Funny, Helpful, HourPlayed, isEarlyAccessed, Recommendation, WrittenContent) VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultId = null;
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, review.getGame().getGameId());
			insertStmt.setInt(2, review.getUser().getUserId());
			insertStmt.setDate(3, new java.sql.Date(review.getDatePosted().getTime()));

			insertStmt.setLong(4, review.getFunny());

			if(review.getHelpful() != null) {
				insertStmt.setInt(5, review.getHelpful());
			} else {
				insertStmt.setNull(5, Types.INTEGER);
			}
			
			if(review.getHourPlayed() != null) {
				insertStmt.setInt(6, review.getHourPlayed());
			} else {
				insertStmt.setNull(6, Types.INTEGER);
			}
			
			if(review.getIsEarlyAccessed() != null) {
				insertStmt.setBoolean(7, review.getIsEarlyAccessed());
			} else {
				insertStmt.setNull(7, Types.BOOLEAN);
			}
			
			if(review.getRecommendation()!= null) {
//				System.out.println(review.getRecommendation().getValue());
				insertStmt.setString(8, review.getRecommendation().name());
			} else {
				insertStmt.setNull(8, Types.LONGNVARCHAR);
			}
			
			if(review.getWrittenContent() != null) {
				insertStmt.setString(9, review.getWrittenContent());
			} else {
				insertStmt.setNull(9, Types.LONGNVARCHAR);
			}
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultId = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultId.next()) {
				reviewId = resultId.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
//			System.out.println(review.getReviewId());
			return review;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	
	// get a review by reviewid
	public Review getReviewByReviewId(int reviewid) throws SQLException {
		String selectReview = "SELECT * "+
							"FROM Review WHERE ReviewID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewid);
			results = selectStmt.executeQuery();
			GameDao gameDao = GameDao.getInstance();
			UserDao userDao = UserDao.getInstance();
			
			if (results.next()) {
				Integer resultReviewId = results.getInt("ReviewID");
				Integer gameId = results.getInt("GameId");
				Integer userId = results.getInt("UserID");
				Date dateposted = results.getDate("DatePosted");
				Long funny = results.getLong("Funny");
				Integer helpful = results.getInt("Helpful");
				Integer HourPlayed = results.getInt("HourPlayed");
				Boolean isearly = results.getBoolean("isEarlyAccessed");
				String enumValue = results.getString("Recommendation");
//				String enumConstant = "";
				Review.Recommendation recommend = Review.Recommendation.valueOf("RECOMMENDED");
				if (enumValue == null) {
					recommend = null;
				} else if (enumValue.contentEquals("NotRecommended")) {
					recommend = Review.Recommendation.valueOf("NOTRECOMMENDED");
				}
//		        switch (enumValue) {
//		            case "Recommended": enumConstant = "RECOMMENDED";
//		                     break;
//		            case "NotRecommended": enumConstant = "NOTRECOMMENDED";
//		                     break;
//		        }
//				Review.Recommendation recommend = Review.Recommendation.valueOf(enumConstant);
				String content = results.getString("WrittenContent");

				Game game = gameDao.getGameByGameId(gameId);
				User user = userDao.getUserByUserId(userId);
				Review review = new Review(resultReviewId, dateposted, funny, helpful, HourPlayed, isearly, recommend, content, game, user);
				return review;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
		
	}
	
	
	// get reviews for a userid
	public List<Review> getReviewByUserId(int userid) throws SQLException{
		String selectReview = "SELECT * FROM Review WHERE UserID = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Review> reviews = new ArrayList<Review>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			GameDao gameDao = GameDao.getInstance();
			UserDao userDao = UserDao.getInstance();
			
			while (results.next()) {
				Integer resultReviewId = results.getInt("ReviewID");
				Integer gameId = results.getInt("GameId");
				Integer userId = results.getInt("UserID");
				Date dateposted = results.getDate("DatePosted");
				Long funny = results.getLong("Funny");
				Integer helpful = results.getInt("Helpful");
				Integer HourPlayed = results.getInt("HourPlayed");
				Boolean isearly = results.getBoolean("isEarlyAccessed");
				String enumValue = results.getString("Recommendation");
				Review.Recommendation recommend = Review.Recommendation.valueOf("RECOMMENDED");
				if (enumValue == null) {
					recommend = null;
				} else if (enumValue.contentEquals("NotRecommended")) {
					recommend = Review.Recommendation.valueOf("NOTRECOMMENDED");
				}
//				String enumConstant = "";
//		        switch (enumValue) {
//		            case "Recommended": enumConstant = "RECOMMENDED";
//		                     break;
//		            case "NotRecommended": enumConstant = "NOTRECOMMENDED";
//		                     break;
//		        }
//				Review.Recommendation recommend = Review.Recommendation.valueOf(enumConstant);
				String content = results.getString("WrittenContent");

				Game game = gameDao.getGameByGameId(gameId);
				User user = userDao.getUserByUserId(userId);
				Review review = new Review(resultReviewId, dateposted, funny, helpful, HourPlayed, isearly, recommend, content, game, user);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	// get reviews for a gameName
	public List<Review> getReviewByGameName(String gamename) throws SQLException{
		String selectReview = "SELECT * FROM Review WHERE GameID = (SELECT GameID FROM GAME WHERE GameName=?);";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Review> reviews = new ArrayList<Review>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setString(1, gamename);
			results = selectStmt.executeQuery();
			GameDao gameDao = GameDao.getInstance();
			UserDao userDao = UserDao.getInstance();
			
			while (results.next()) {
				Integer resultReviewId = results.getInt("ReviewID");
				Integer gameId = results.getInt("GameId");
				Integer userId = results.getInt("UserID");
				Date dateposted = results.getDate("DatePosted");
				Long funny = results.getLong("Funny");
				Integer helpful = results.getInt("Helpful");
				Integer HourPlayed = results.getInt("HourPlayed");
				Boolean isearly = results.getBoolean("isEarlyAccessed");
				String enumValue = results.getString("Recommendation");
				Review.Recommendation recommend = Review.Recommendation.valueOf("RECOMMENDED");
				if (enumValue == null) {
					recommend = null;
				} else if (enumValue.contentEquals("NotRecommended")) {
					recommend = Review.Recommendation.valueOf("NOTRECOMMENDED");
				}
//				String enumConstant = "";
//		        switch (enumValue) {
//		            case "Recommended": enumConstant = "RECOMMENDED";
//		                     break;
//		            case "NotRecommended": enumConstant = "NOTRECOMMENDED";
//		                     break;
//		        }
//				Review.Recommendation recommend = Review.Recommendation.valueOf(enumConstant);
				String content = results.getString("WrittenContent");

				Game game = gameDao.getGameByGameId(gameId);
				User user = userDao.getUserByUserId(userId);
				Review review = new Review(resultReviewId, dateposted, funny, helpful, HourPlayed, isearly, recommend, content, game, user);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	
	// update a review content
	public Review updateReviewContent(Review review, String content) throws SQLException {
		String updateContent = "UPDATE Review SET WrittenContent=? WHERE ReviewID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateContent);
			updateStmt.setString(1, content);
			updateStmt.setInt(2, review.getReviewId());
			updateStmt.executeUpdate();
			review.setWrittenContent(content);
			
			return review;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	
	// delete review
	public Review delete(Review review) throws SQLException {
		String deleteReview = "DELETE FROM Review WHERE ReviewID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
//			System.out.println(review.getReviewId());
			deleteStmt.setInt(1, review.getReviewId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
