package gameCrossing.dal;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.Statement;
import gameCrossing.model.*;

public class PlaytimeDao {
	protected ConnectionManager connectionManager;
	
	private static PlaytimeDao instance = null;
	protected PlaytimeDao() {
		connectionManager = new ConnectionManager();
	}
	public static PlaytimeDao getInstance() {
		if(instance == null) {
			instance = new PlaytimeDao();
		}
		return instance;
	}
	
	
	public Playtime create(Playtime playTime) throws SQLException {
		String insertPlaytimeStaging = 
				"INSERT INTO Playtime_staging(GameID, AveragePlaytime, MedianPLaytime) " +
				"VALUES(?,?,?);";
		String insertPlaytime = 
				"INSERT INTO Playtime(playtimeID, GameID, AveragePlaytime, MedianPLaytime) " +
				"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPlaytimeStaging,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt1 = connection.prepareStatement(insertPlaytime);
			
			//nullable fields
			if (playTime.getGame() == null || playTime.getGame().getGameId() == null) {
				insertStmt.setNull(1, Types.INTEGER);
				insertStmt1.setNull(2, Types.INTEGER);
			} else {
				insertStmt.setInt(1, playTime.getGame().getGameId());
				insertStmt1.setInt(2, playTime.getGame().getGameId());
			}
			if (playTime.getAveragePlaytime() == null) {
				insertStmt.setNull(2, Types.INTEGER);
				insertStmt1.setNull(3, Types.INTEGER);
			} else {
				insertStmt.setInt(2, playTime.getAveragePlaytime());
				insertStmt1.setInt(3, playTime.getAveragePlaytime());
			}
			if (playTime.getMedianPlaytime() == null) {
				insertStmt.setNull(3, Types.INTEGER);
				insertStmt1.setNull(4, Types.INTEGER);
			} else {
				insertStmt.setInt(3, playTime.getMedianPlaytime());
				insertStmt1.setInt(4, playTime.getMedianPlaytime());
			}
			
			insertStmt.executeUpdate();
			
			//retrieve auto generated key
			resultKey = insertStmt.getGeneratedKeys();
			int playTimeId = -1;
			if(resultKey.next()) {
				playTimeId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			playTime.setPlaytimeId(playTimeId);
			insertStmt1.setInt(1,playTimeId);
			insertStmt1.executeUpdate();
			return playTime;
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
	
	
	public Playtime getPlaytimeByGameId(int gameId) throws SQLException {
		String selectPlaytime = 
				"SELECT GameID, playTimeID, AveragePlaytime, MedianPlaytime " +
				"FROM Playtime " +
				"WHERE GameID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlaytime);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			if (results.next()) {
				int resultPlaytimeId = results.getInt("playTimeID");
				int gameID = results.getInt("GameID");
				int averagePlaytime = results.getInt("AveragePlaytime");
				int medianPlaytime = results.getInt("MedianPlaytime");
				
				Game game = gameDao.getGameByGameId(gameID);
				Playtime playTime = new Playtime(resultPlaytimeId, averagePlaytime, medianPlaytime, game);
				
				return playTime;
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
	
	public Playtime getPlaytimeByPlaytimeId(int playtimeId) throws SQLException {
		String selectPlaytime = 
				"SELECT GameID, playtimeID, AveragePlaytime, MedianPlaytime " +
				"FROM Playtime " +
				"WHERE playtimeID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPlaytime);
			selectStmt.setInt(1, playtimeId);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			if (results.next()) {
				int resultPlaytimeId = results.getInt("playtimeID");
				int gameID = results.getInt("GameID");
				int averagePlaytime = results.getInt("AveragePlaytime");
				int medianPlaytime = results.getInt("MedianPlaytime");
				
				Game game = gameDao.getGameByGameId(gameID);
				Playtime playTime = new Playtime(resultPlaytimeId, averagePlaytime, medianPlaytime, game);
				
				return playTime;
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
	
	
	
	public Playtime updateAveragePlaytime(Playtime playTime, int newAveragePlaytime) throws SQLException {
		String updateAvgPlaytime = "UPDATE Playtime SET AveragePlaytime=? WHERE playtimeID=?;";
		String updateAvgStaging = "UPDATE Playtime_staging SET AveragePlaytime=? WHERE playtimeID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAvgPlaytime);
			updateStmt1 = connection.prepareStatement(updateAvgStaging);
			updateStmt.setInt(1, newAveragePlaytime);
			updateStmt1.setInt(1, newAveragePlaytime);
			updateStmt.setInt(2, playTime.getPlaytimeId());
			updateStmt1.setInt(2, playTime.getPlaytimeId());
			
			updateStmt.executeUpdate();
			updateStmt1.executeUpdate();
			
			//update playtime param before returning to caller
			playTime.setAveragePlaytime(newAveragePlaytime);
			return playTime;
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
	//end update
	}
	
	public Playtime updateMedianPlaytime(Playtime playTime, int newMedianPlaytime) throws SQLException {
		String updateMedPlaytime = "UPDATE Playtime SET MedianPlaytime=? WHERE playtimeID=?;";
		String updateMedStaging = "UPDATE Playtime_staging SET MedianPlaytime=? WHERE playtimeID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMedPlaytime);
			updateStmt1 = connection.prepareStatement(updateMedStaging);
			updateStmt.setInt(1, newMedianPlaytime);
			updateStmt1.setInt(1, newMedianPlaytime);
			updateStmt.setInt(2, playTime.getPlaytimeId());
			updateStmt1.setInt(2, playTime.getPlaytimeId());
			
			updateStmt.executeUpdate();
			updateStmt1.executeUpdate();
			
			//update playtime param before returning to caller
			playTime.setMedianPlaytime(newMedianPlaytime);
			return playTime;
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
	//end update
	}
	
	public Playtime delete(Playtime playTime) throws SQLException {
		String deletePlaytime = "DELETE FROM Playtime WHERE playTimeID=?;";
		String deleteStaging = "DELETE FROM Playtime_staging WHERE playTimeID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		PreparedStatement deleteStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePlaytime);
			deleteStmt1 = connection.prepareStatement(deleteStaging);
			deleteStmt.setInt(1, playTime.getPlaytimeId());
			deleteStmt1.setInt(1, playTime.getPlaytimeId());
			deleteStmt.executeUpdate();
			deleteStmt1.executeUpdate();
			
			//return null so the caller can no longer operate on the Playtime instance
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
