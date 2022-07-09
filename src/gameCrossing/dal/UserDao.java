package gameCrossing.dal;

import gameCrossing.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
	protected ConnectionManager connectionManager;
	
	private static UserDao instance = null;
	protected UserDao() {
		connectionManager = new ConnectionManager();
	}
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	
	// create a user
	public User create(User user) throws SQLException {

		String insertUser = "INSERT INTO User(FirstName, LastName) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultId = null;
		try {
			connection = connectionManager.getConnection();
			
			insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, user.getFirstName());
			
			// last name is nullable
			if (user.getLastName() != null) {
				insertStmt.setString(2, user.getLastName());
			} else {
				insertStmt.setNull(2, Types.LONGVARCHAR);
			}
			
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultId = insertStmt.getGeneratedKeys();
			int userId = -1;
			if(resultId.next()) {
				userId = resultId.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserId(userId);
			return user;
			
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
	
	
	//get all users by firstname
	public List<User> getUserByFirstName(String firstName) throws SQLException {

		String selectUser = "SELECT userID, FirstName, LastName FROM User WHERE FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<User> users = new ArrayList<>();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				Integer userId = results.getInt("userID");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");

				User user = new User(userId, resultFirstName, lastName);
				users.add(user);
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
		return users;
	}
	
	
	// get a user by userID
	public User getUserByUserId(int userid) throws SQLException {

		String selectUser = "SELECT userID, FirstName, LastName FROM User WHERE userID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userid);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				Integer resultUserId = results.getInt("userID");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");


				User user = new User(resultUserId, firstName, lastName);
				return user;
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
	
	
	
	// delete a user
	public User delete(User user) throws SQLException {
		String deleteUser = "DELETE FROM User WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserId());
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
