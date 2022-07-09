package gameCrossing.dal;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.Statement;
import gameCrossing.model.*;

public class RatingDao {
	
	protected ConnectionManager connectionManager;
	
	private static RatingDao instance = null;
	protected RatingDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingDao getInstance() {
		if (instance == null) {
			instance = new RatingDao();
		}
		return instance;
	}
	
	public Rating create(Rating rating) throws SQLException {
		String insertRatingStaging = 
				"INSERT INTO Rating_staging(GameID, NumberOfPositiveRatings, NumberOfNegativeRatings) " +
				"VALUES(?,?,?);";
		String insertRating =
				"INSERT INTO Rating(ratingID, GameID, NumberOfPositiveRatings, NumberOfNegativeRatings) " +
				"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRatingStaging, Statement.RETURN_GENERATED_KEYS);
			insertStmt1 = connection.prepareStatement(insertRating);
			insertStmt.setInt(1, rating.getGame().getGameId());
			insertStmt1.setInt(2, rating.getGame().getGameId());
			
			//nullable ratings
			if (rating.getNumberOfPositiveRatings() == null) {
				insertStmt.setNull(2, Types.INTEGER);
				insertStmt1.setNull(3, Types.INTEGER);
			} else {
				insertStmt.setInt(2, rating.getNumberOfPositiveRatings());
				insertStmt1.setInt(3, rating.getNumberOfPositiveRatings());
			}
			if (rating.getNumberOfNegativeRatings() == null) {
				insertStmt.setNull(3, Types.INTEGER);
				insertStmt1.setNull(4, Types.INTEGER);
			} else {
				insertStmt.setInt(3, rating.getNumberOfNegativeRatings());
				insertStmt1.setInt(4, rating.getNumberOfNegativeRatings());
			}
			
			insertStmt.executeUpdate();
			//retrieve auto generted key
			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("UNable to retireve auto-generated key.");
			}
			rating.setRatingId(ratingId);
			insertStmt1.setInt(1, ratingId);
			insertStmt1.executeUpdate();
			return rating;
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
			if(insertStmt1 != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	//end create
	}
	
	public Rating getRatingByRatingId(int ratingId) throws SQLException {
		String selectRating = 
				"SELECT GameID, ratingID, NumberOfPositiveRatings, NumberOfNegativeRatings " + 
				"FROM Rating " + 
				"WHERE ratingID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRating);
			selectStmt.setInt(1, ratingId);
			results = selectStmt.executeQuery();
			GameDao gameDao = GameDao.getInstance();
			
			if(results.next()) {
				Integer resultRatingID = results.getInt("ratingID");
				Integer gameID = results.getInt("GameID");
				Integer numberOfPositiveRatings = results.getInt("NumberOfPositiveRatings");
				Integer numberOfNegativeRatings = results.getInt("NumberOfNegativeRatings");
				
				Game game = gameDao.getGameByGameId(gameID);
				Rating rating = new Rating(resultRatingID, numberOfPositiveRatings, numberOfNegativeRatings, game);
				
				return rating;
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
	//end read	
	}
	
	
	
	public Rating getRatingByGameId(int gameId) throws SQLException {
		String selectRating = 
				"SELECT GameID, ratingID, NumberOfPositiveRatings, NumberOfNegativeRatings " + 
				"FROM Rating " + 
				"WHERE GameID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRating);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
			GameDao gameDao = GameDao.getInstance();
			
			if(results.next()) {
				Integer resultRatingID = results.getInt("ratingID");
				Integer gameID = results.getInt("GameID");
				Integer numberOfPositiveRatings = results.getInt("NumberOfPositiveRatings");
				Integer numberOfNegativeRatings = results.getInt("NumberOfNegativeRatings");
				
				Game game = gameDao.getGameByGameId(gameID);
				Rating rating = new Rating(resultRatingID, numberOfPositiveRatings, numberOfNegativeRatings, game);
				
				return rating;
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
	//end read	
	}
	
	
	public Rating updatePositiveRatings(Rating rating, int newPositiveRating) throws SQLException {
			String updatePRating = "UPDATE Rating SET NumberOfPositiveRatings=? WHERE ratingID=?;";
			String updatePRstaging = "UPDATE Rating_staging SET NumberOfPositiveRatings=? WHERE ratingID=?;";
			Connection connection = null;
			PreparedStatement updateStmt = null;
			PreparedStatement updateStmt1 = null;
			try {
				connection = connectionManager.getConnection();
				updateStmt = connection.prepareStatement(updatePRating);
				updateStmt1 = connection.prepareStatement(updatePRstaging);
				updateStmt.setInt(1, newPositiveRating);
				updateStmt1.setInt(1, newPositiveRating);
				updateStmt.setInt(2, rating.getRatingId());
				updateStmt1.setInt(2, rating.getRatingId());
				updateStmt.executeUpdate();
				updateStmt1.executeUpdate();
			
				//update rating # before returning to the caller
				rating.setNumberOfPositiveRatings(newPositiveRating);
				return rating;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(updateStmt != null) {
					updateStmt.close();
				}
				if(updateStmt1 != null) {
					updateStmt.close();
				}
			}
	//end Update	
	}
	
	public Rating updateNegativeRatings(Rating rating, int newNegativeRating) throws SQLException {
		String updateNRating = "UPDATE Rating SET NumberOfNegativeRatings=? WHERE ratingID=?;";
		String updateNRstaging = "UPDATE Rating_staging SET NumberOfNegativeRatings=? WHERE ratingID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateNRating);
			updateStmt1 = connection.prepareStatement(updateNRstaging);
			updateStmt.setInt(1, newNegativeRating);
			updateStmt1.setInt(1, newNegativeRating);
			updateStmt.setInt(2, rating.getRatingId());
			updateStmt1.setInt(2, rating.getRatingId());
			updateStmt.executeUpdate();
			
			//update rating # before returning to the caller
			rating.setNumberOfNegativeRatings(newNegativeRating);
			return rating;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
			if(updateStmt1 != null) {
				updateStmt.close();
			}
		}
	//end Update	
	}
	
	public Rating delete(Rating rating) throws SQLException {
		String deleteRating = "DELETE FROM Rating WHERE ratingID=?;";
		String deleteStaging = "DELETE FROM Rating_staging WHERE ratingID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		PreparedStatement deleteStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRating);
			deleteStmt1 = connection.prepareStatement(deleteStaging);
			deleteStmt.setInt(1, rating.getRatingId());
			deleteStmt1.setInt(1, rating.getRatingId());
			deleteStmt.executeUpdate();
			deleteStmt1.executeUpdate();
			//return null so caller can no longer operate on instance
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
			if(deleteStmt1 != null) {
				deleteStmt.close();
			}
		}
	//end delete
	}

//end DAO
}
