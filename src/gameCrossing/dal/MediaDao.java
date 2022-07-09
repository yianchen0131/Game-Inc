package gameCrossing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import gameCrossing.model.*;

public class MediaDao {
	protected ConnectionManager connectionManager;
	
	private static MediaDao instance = null;
	protected MediaDao() {
		connectionManager = new ConnectionManager();
	}
	public static MediaDao getInstance() {
		if(instance == null) {
			instance = new MediaDao();
		}
		return instance;
	}
	
	
	
	public Media create(Media media) throws SQLException {
		String insertMediaStaging = 
				"INSERT INTO Media_staging(GameID, HeaderImage, Screenshot, Background, Movie) " +
				"VALUES(?,?,?,?,?);";
		String insertMedia = 
				"INSERT INTO Media(mediaID, GameID, HeaderImage, Screenshot, Background, Movie) " +
				"VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertMediaStaging,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt1 = connection.prepareStatement(insertMedia);
			//nullable fields
			if (media.getGame() == null || media.getGame().getGameId() == null) {
				insertStmt.setNull(1, Types.INTEGER);
				insertStmt1.setNull(2, Types.INTEGER);
			} else {
				insertStmt.setInt(1, media.getGame().getGameId());
				insertStmt1.setInt(2, media.getGame().getGameId());
			}
			if (media.getHeaderImage() == null) {
				insertStmt.setNull(2, Types.LONGVARCHAR);
				insertStmt1.setNull(3, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(2, media.getHeaderImage());
				insertStmt1.setString(3, media.getHeaderImage());
			}
			if (media.getScreenshot() == null) {
				insertStmt.setNull(3, Types.LONGVARCHAR);
				insertStmt1.setNull(4, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(3, media.getScreenshot());
				insertStmt1.setString(4, media.getScreenshot());
			}
			if (media.getBackground() == null) {
				insertStmt.setNull(4, Types.LONGVARCHAR);
				insertStmt1.setNull(5, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(4, media.getBackground());
				insertStmt1.setString(5, media.getBackground());
			}
			if (media.getMovie() == null) {
				insertStmt.setNull(5, Types.LONGVARCHAR);
				insertStmt1.setNull(6, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(5, media.getMovie());
				insertStmt1.setString(6, media.getMovie());
			}
			
			insertStmt.executeUpdate();
			
			//retrieve auto key
			resultKey = insertStmt.getGeneratedKeys();
			int mediaId = -1;
			if (resultKey.next()) {
				mediaId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			media.setMediaId(mediaId);
			insertStmt1.setInt(1, mediaId);
			insertStmt1.executeLargeUpdate();
			return media;
			
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
	
	public Media getMediaByGameId(int gameId) throws SQLException {
		String selectMedia = 
				"SELECT GameID, mediaID, HeaderImage, Screenshot, Background, Movie " +
				"FROM Media " +
				"WHERE GameID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMedia);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			if (results.next()) {
				int resultMediaId = results.getInt("mediaID");
				int gameID = results.getInt("GameID");
				String headerImage = results.getString("HeaderImage");
				String screenshot = results.getString("Screenshot");
				String background = results.getString("Background");
				String movie = results.getString("Movie");
				
				Game game = gameDao.getGameByGameId(gameID);
				Media media = new Media(resultMediaId, headerImage, screenshot, background, movie, game);
				
				return media;	
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
	
	
	public Media getMediaByMediaId(int mediaId) throws SQLException {
		String selectMedia = 
				"SELECT GameID, mediaID, HeaderImage, Screenshot, Background, Movie " +
				"FROM Media " +
				"WHERE mediaID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMedia);
			selectStmt.setInt(1, mediaId);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			if (results.next()) {
				int resultMediaId = results.getInt("mediaID");
				int gameID = results.getInt("GameID");
				String headerImage = results.getString("HeaderImage");
				String screenshot = results.getString("Screenshot");
				String background = results.getString("Background");
				String movie = results.getString("Movie");
				
				Game game = gameDao.getGameByGameId(gameID);
				Media media = new Media(resultMediaId, headerImage, screenshot, background, movie, game);
				
				return media;	
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
	

	
	public Media updateHeaderImage(Media media, String newHeaderImage) throws SQLException {
		String updateHeaderImage = "UPDATE Media SET HeaderImage=? WHERE mediaID=?;";
		String updateHeaderStaging = "UPDATE Media_staging SET HeaderImage=? WHERE mediaID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHeaderImage);
			updateStmt1 = connection.prepareStatement(updateHeaderStaging);
			updateStmt.setString(1, newHeaderImage);
			updateStmt1.setString(1, newHeaderImage);
			updateStmt.setInt(2, media.getMediaId());
			updateStmt1.setInt(2, media.getMediaId());
			updateStmt.executeUpdate();
			updateStmt1.executeUpdate();
			
			media.setHeaderImage(newHeaderImage);
			return media;
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
	
	public Media delete(Media media) throws SQLException {
		String deleteMedia = "DELETE FROM Media WHERE mediaID=?;";
		String deleteStaging = "DELETE FROM Media_staging WHERE mediaID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		PreparedStatement deleteStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteMedia);
			deleteStmt1 = connection.prepareStatement(deleteStaging);
			deleteStmt.setInt(1, media.getMediaId());
			deleteStmt1.setInt(1, media.getMediaId());
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
