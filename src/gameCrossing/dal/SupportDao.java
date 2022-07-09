package gameCrossing.dal;

import gameCrossing.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
//import java.util.ArrayList;
//import java.util.List;

public class SupportDao {
	protected ConnectionManager connectionManager;
	
	private static SupportDao instance = null;
	protected SupportDao() {
		connectionManager = new ConnectionManager();
	}
	public static SupportDao getInstance() {
		if(instance == null) {
			instance = new SupportDao();
		}
		return instance;
	}
	
	
	// create a support
	public Support create(Support support) throws SQLException {

		String insertSupport = "INSERT INTO Support(GameID, Website, SupportUrl, SupportEmail) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultId = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSupport,Statement.RETURN_GENERATED_KEYS);
			

			insertStmt.setInt(1, support.getGame().getGameId());

			if (support.getWebsite() != null) {
				insertStmt.setString(2, support.getWebsite());
			} else {
				insertStmt.setNull(2, Types.LONGNVARCHAR);
			}
			
			if (support.getSupportUrl() != null) {
				insertStmt.setString(3, support.getSupportUrl());
			} else {
				insertStmt.setNull(3, Types.LONGNVARCHAR);
			}
			
			if (support.getSupportEmail() != null) {
				insertStmt.setString(4, support.getSupportEmail());
			} else {
				insertStmt.setNull(4, Types.LONGNVARCHAR);
			}
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultId = insertStmt.getGeneratedKeys();
			int supportId = -1;
			if(resultId.next()) {
				supportId = resultId.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			support.setSupportId(supportId);
			return support;
			
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
	
	
	// get a support by id
	public Support getSupportbySupportId(int supportid) throws SQLException {

		String selectSupport = "SELECT supportID, GameID, Website, SupportUrl, SupportEmail FROM Support WHERE supportID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSupport);
			selectStmt.setInt(1, supportid);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			
			if (results.next()) {
				Integer resultSupportid = results.getInt("supportID");
				Integer GameID = results.getInt("GameID");
				String website = results.getString("Website");
				String url = results.getString("SupportUrl");
				String email = results.getString("SupportEmail");

				Game game = gameDao.getGameByGameId(GameID);
				Support support = new Support(resultSupportid, website, url, email, game);
				return support;
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
	
	// get a support by a game name
	public Support getSupportbyGameName(String gamename) throws SQLException {

		String selectSupport = "SELECT supportID, Support.GameID, Game.GameName AS GameName, Website, SupportUrl, SupportEmail FROM Support "+
								"INNER JOIN Game ON Support.GameID = Game.GameID WHERE GameName =?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSupport);
			selectStmt.setString(1, gamename);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			
			if (results.next()) {
				Integer resultSupportid = results.getInt("supportID");
				Integer GameID = results.getInt("GameID");
				String website = results.getString("Website");
				String url = results.getString("SupportUrl");
				String email = results.getString("SupportEmail");

				Game game = gameDao.getGameByGameId(GameID);
				Support support = new Support(resultSupportid, website, url, email, game);
				return support;
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
	
	
	// delete a support
	public Support delete(Support support) throws SQLException{
		String deletSupport = "DELETE FROM Support WHERE supportID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletSupport);
			deleteStmt.setInt(1, support.getSupportId());
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
