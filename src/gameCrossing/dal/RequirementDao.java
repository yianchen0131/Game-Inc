package gameCrossing.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import gameCrossing.model.*;

public class RequirementDao {
	protected ConnectionManager connectionManager;
	
	private static RequirementDao instance = null;
	protected RequirementDao() {
		connectionManager = new ConnectionManager();
	}
	public static RequirementDao getInstance() {
		if(instance == null) {
			instance = new RequirementDao();
		}
		return instance;
	}
	
	public Requirement create(Requirement requirement) throws SQLException {
		String insertRequirementStaging = 
				"INSERT INTO Requirement_staging(GameID, PCRequire, MacRequire, LinuxRequire, Minimum_requirement, Recommended_requirement) " +
				"VALUES(?,?,?,?,?,?);";
		String insertRequirement = 
				"INSERT INTO Requirement(requirementID, GameID, PCRequire, MacRequire, LinuxRequire, Minimum_requirement, Recommended_requirement) " + 
				"VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		PreparedStatement insertStmt1 = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRequirementStaging, Statement.RETURN_GENERATED_KEYS);
			insertStmt1 = connection.prepareStatement(insertRequirement);
			//nullable fields
			if (requirement.getGame() == null || requirement.getGame().getGameId() == null) {
				insertStmt.setNull(1, Types.INTEGER);
				insertStmt1.setNull(2, Types.INTEGER);
			} else {
				insertStmt.setInt(1,  requirement.getGame().getGameId());
				insertStmt1.setInt(2, requirement.getGame().getGameId());
				
			}
			if (requirement.getPcRequire() == null) {
				insertStmt.setNull(2, Types.LONGVARCHAR);
				insertStmt1.setNull(3, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(2,  requirement.getPcRequire());
				insertStmt1.setString(3, requirement.getPcRequire());
			}
			if (requirement.getMacRequire() == null) {
				insertStmt.setNull(3, Types.LONGVARCHAR);
				insertStmt1.setNull(4, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(3, requirement.getMacRequire());
				insertStmt1.setString(4, requirement.getMacRequire());
			}
			if (requirement.getLinuxRequire() == null) {
				insertStmt.setNull(4, Types.LONGVARCHAR);
				insertStmt1.setNull(5, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(4, requirement.getLinuxRequire());
				insertStmt1.setString(5, requirement.getLinuxRequire());
			}
			if (requirement.getMinimum_requirement() == null) {
				insertStmt.setNull(5, Types.LONGVARCHAR);
				insertStmt1.setNull(6, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(5, requirement.getMinimum_requirement());
				insertStmt1.setString(6, requirement.getMinimum_requirement());
			}
			if (requirement.getRecommended_requirement() == null) {
				insertStmt.setNull(6, Types.LONGVARCHAR);
				insertStmt1.setNull(7, Types.LONGVARCHAR);
			} else {
				insertStmt.setString(6, requirement.getRecommended_requirement());
				insertStmt1.setString(7, requirement.getRecommended_requirement());
			}
			
			insertStmt.executeUpdate();
			
			//retrieve auto generated key
			resultKey = insertStmt.getGeneratedKeys();
			int requirementId = -1;
			if (resultKey.next()) {
				requirementId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			requirement.setRequirementId(requirementId);
			insertStmt1.setInt(1, requirementId);
			insertStmt1.executeUpdate();
			return requirement;
			
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
	
	
	public Requirement getRequirementByGameId(int gameId) throws SQLException {
		String selectRequirement = 
				"SELECT GameID, requirementID, PCRequire, MacRequire, LinuxRequire, Minimum_requirement, Recommended_requirement " +
				"FROM Requirement " +
						"WHERE GameID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRequirement);
			selectStmt.setInt(1, gameId);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			if (results.next()) {
				int resultRequirementId = results.getInt("requirementID");
				int gameID = results.getInt("GameID");
				String pcRequire = results.getString("PCRequire");
				String macRequire = results.getString("MacRequire");
				String linuxRequire = results.getString("LinuxRequire");
				String minimumRequire = results.getString("Minimum_requirement");
				String recommendedRequire = results.getString("Recommended_requirement");
				
				Game game = gameDao.getGameByGameId(gameID);
				Requirement requirement = new Requirement(resultRequirementId, pcRequire, macRequire, linuxRequire, minimumRequire, recommendedRequire, game);
				
				return requirement;
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
	
	
	
	public Requirement getRequirementByRequirementId(int requirementId) throws SQLException {
		String selectRequirement = 
				"SELECT GameID, requirementID, PCRequire, MacRequire, LinuxRequire, Minimum_requirement, Recommended_requirement " +
				"FROM Requirement " +
				"WHERE requirementID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRequirement);
			selectStmt.setInt(1, requirementId);
			results = selectStmt.executeQuery();
			
			GameDao gameDao = GameDao.getInstance();
			if (results.next()) {
				int resultRequirementId = results.getInt("requirementID");
				int gameID = results.getInt("GameID");
				String pcRequire = results.getString("PCRequire");
				String macRequire = results.getString("MacRequire");
				String linuxRequire = results.getString("LinuxRequire");
				String minimumRequire = results.getString("Minimum_requirement");
				String recommendedRequire = results.getString("Recommended_requirement");
				
				Game game = gameDao.getGameByGameId(gameID);
				Requirement requirement = new Requirement(resultRequirementId, pcRequire, macRequire, linuxRequire, minimumRequire, recommendedRequire, game);
				
				return requirement;
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
	
	
	public Requirement updatePCRequirement(Requirement requirement, String newPCrequirement) throws SQLException {
		String updatePCrequirement = "UPDATE Requirement SET PCRequire=? WHERE requirementID=?;";
		String updatePCstaging = "UPDATE Requirement_staging SET PCRequire=? WHERE requirementID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePCrequirement);
			updateStmt1 = connection.prepareStatement(updatePCstaging);
			updateStmt.setString(1, newPCrequirement);
			updateStmt1.setString(1, newPCrequirement);
			updateStmt.setInt(2, requirement.getRequirementId());
			updateStmt1.setInt(2, requirement.getRequirementId());
			
			updateStmt.executeUpdate();
			updateStmt1.executeUpdate();
			
			requirement.setPcRequire(newPCrequirement);
			return requirement;
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
	
	public Requirement updateMACRequirement(Requirement requirement, String newMACrequirement) throws SQLException {
		String updateMACrequirement = "UPDATE Requirement SET MacRequire=? WHERE requirementID=?;";
		String updateMACstaging = "UPDATE Requirement_staging SET MacRequire=? WHERE requirementID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMACrequirement);
			updateStmt1 = connection.prepareStatement(updateMACstaging);
			updateStmt.setString(1, newMACrequirement);
			updateStmt1.setString(1, newMACrequirement);
			updateStmt.setInt(2, requirement.getRequirementId());
			updateStmt1.setInt(2, requirement.getRequirementId());
			
			updateStmt.executeUpdate();
			updateStmt1.executeUpdate();
			
			requirement.setMacRequire(newMACrequirement);
			return requirement;
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
	
	public Requirement updateLNXRequirement(Requirement requirement, String newLNXrequirement) throws SQLException {
		String updateLNXrequirement = "UPDATE Requirement SET LinuxRequire=? WHERE requirementID=?;";
		String updateLNXstaging = "UPDATE Requirement_staging SET LinuxRequire=? WHERE requirementID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		PreparedStatement updateStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateLNXrequirement);
			updateStmt1 = connection.prepareStatement(updateLNXstaging);
			updateStmt.setString(1, newLNXrequirement);
			updateStmt1.setString(1, newLNXrequirement);
			updateStmt.setInt(2, requirement.getRequirementId());
			updateStmt1.setInt(2, requirement.getRequirementId());
			
			updateStmt.executeUpdate();
			updateStmt1.executeUpdate();
			
			requirement.setLinuxRequire(newLNXrequirement);
			return requirement;
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
	
	public Requirement delete(Requirement requirement) throws SQLException {
		String deleteRequirement = "DELETE FROM Requirement WHERE requirementID=?";
		String deleteStaging = "DELETE FROM Requirement_staging WHERE requirementID=?";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		PreparedStatement deleteStmt1 = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRequirement);
			deleteStmt1 = connection.prepareStatement(deleteStaging);
			deleteStmt.setInt(1, requirement.getRequirementId());
			deleteStmt1.setInt(1, requirement.getRequirementId());
			deleteStmt.executeUpdate();
			deleteStmt1.executeUpdate();
			
			return null;
		}  catch (SQLException e) {
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
