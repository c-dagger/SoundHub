package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dao.DBConnect;

public class UserModel extends DBConnect {

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

	public boolean addTransactions(String username, String productName, String quantity) {
		String query = "INSERT INTO `cdugg_transactions`(`purchaseId`, `userId`, `PRODUCTNAME`, `uname`, `orderDate`, `quantity`) VALUES"
				+ "(" + "'',''," + productName + "'basic',''," + quantity;
		boolean rs = false;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			rs = stmt.execute();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}// end class