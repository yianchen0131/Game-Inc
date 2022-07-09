package gameCrossing.dal;

import gameCrossing.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


public class TagDao {
	protected ConnectionManager connectionManager;
	
	private static TagDao instance = null;
	protected TagDao() {
		connectionManager = new ConnectionManager();
	}
	public static TagDao getInstance() {
		if(instance == null) {
			instance = new TagDao();
		}
		return instance;
	}
	
	
	// create a tag
	public Tag create(Tag tag) throws SQLException {

		String insertTag = "INSERT INTO Tag(`GameID`,`1980s`,`1990s`,`2.5d`,`2d`,`3d`) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultId = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTag, Statement.RETURN_GENERATED_KEYS);
			
			
			insertStmt.setInt(1, tag.getGame().getGameId());
			
			if (tag.get_1980s() != null) {
				insertStmt.setInt(2, tag.get_1980s());
			} else {
				insertStmt.setNull(2, Types.INTEGER);
			}
			
			if (tag.get_1990s() != null) {
				insertStmt.setInt(3, tag.get_1990s());
			} else {
				insertStmt.setNull(3, Types.INTEGER);
			}
			
			if (tag.get_2_5d() != null) {
				insertStmt.setInt(4, tag.get_2_5d());
			} else {
				insertStmt.setNull(4, Types.INTEGER);
			}
			
			if (tag.get_2d() != null) {
				insertStmt.setInt(5, tag.get_2d());
			} else {
				insertStmt.setNull(5, Types.INTEGER);
			}
			
			if (tag.get_3d() != null) {
				insertStmt.setInt(6, tag.get_3d());
			} else {
				insertStmt.setNull(6, Types.INTEGER);
			}
			
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultId = insertStmt.getGeneratedKeys();
			int tagId = -1;
			if(resultId.next()) {
				tagId = resultId.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			tag.setTagId(tagId);
			return tag;
			
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
	
	
	// get a tag by tagID
	public Tag getTagbyTagId(int tagid) throws SQLException {

		String selectTag = "SELECT `tagID`, `GameID`, `1980s`, `1990s`, `2.5d`, `2d`, `3d` FROM Tag WHERE tagID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTag);
			selectStmt.setInt(1, tagid);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			
			if (results.next()) {
				Integer resultTagid = results.getInt("tagID");
				Integer GameID = results.getInt("GameID");
				Integer tag1980 = results.getInt("1980s");
				Integer tag1990 = results.getInt("1990s");
				Integer tag25d = results.getInt("2.5d");
				Integer tag2d= results.getInt("2d");
				Integer tag3d = results.getInt("3d");

				Game game = gameDao.getGameByGameId(GameID);
				Tag tag = new Tag(resultTagid,tag1980, tag1990, tag25d, tag2d, tag3d, game);
				return tag;
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
	
	
	
	// get a tag by a game name
	public Tag getTagbyGameName(String gamename) throws SQLException {
		String selectTag = "SELECT `tagID`, Tag.GameID, Game.GameName,`1980s`, `1990s`, `2.5d`, `2d`, `3d` FROM Tag "+
						   "INNER JOIN Game ON Tag.GameID = Game.GameID WHERE GameName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTag);
			selectStmt.setString(1, gamename);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			
			if (results.next()) {
				Integer Tagid = results.getInt("tagID");
				Integer GameID = results.getInt("GameID");
				Integer tag1980 = results.getInt("1980s");
				Integer tag1990 = results.getInt("1990s");
				Integer tag25d = results.getInt("2.5d");
				Integer tag2d= results.getInt("2d");
				Integer tag3d = results.getInt("3d");

				Game game = gameDao.getGameByGameId(GameID);
				Tag tag = new Tag(Tagid,tag1980, tag1990, tag25d, tag2d, tag3d, game);
				return tag;
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
	
	
	
	// delete a tag
	public Tag delete(Tag tag) throws SQLException{
		String deletTag = "DELETE FROM Tag WHERE tagID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletTag);
			deleteStmt.setInt(1, tag.getTagId());
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
