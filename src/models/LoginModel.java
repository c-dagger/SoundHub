package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.DBConnect;

public class LoginModel extends DBConnect {

	private Boolean admin;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getCredentials(String username, String password) {
		String query = "SELECT * FROM cdugg_users WHERE uname = ? and passwd = ?;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				setId(rs.getInt("id"));
				setAdmin(rs.getBoolean("admin"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean checkUser(String username) {
		String query = "SELECT * FROM cdugg_users WHERE uname = ? ;";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				setId(rs.getInt("id"));
				setAdmin(rs.getBoolean("admin"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addPoints(String username, int points) {
		String query = "UPDATE cdugg_accounts SET balance= balance + ? WHERE userName = ? ;";
		boolean rs = false;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, points);
			stmt.setString(2, username);
			rs = stmt.execute();
			rs = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}	
	public boolean deleteUser(String username) {
		String query = "DELETE from cdugg_users WHERE uname = ? ;";
		boolean rs = false;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {			
			stmt.setString(1, username);
			rs = stmt.execute();
			rs = Boolean.TRUE;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;		
	}

	private void setBalance(int int1) {
		// TODO Auto-generated method stub

	}

	private void setName(String string) {
		// TODO Auto-generated method stub

	}
}// end class