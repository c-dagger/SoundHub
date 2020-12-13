package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

import Dao.DBConnect;
import application.DynamicTable;
import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.LoginModel;

public class AdminController {

	@FXML
	private Pane pane1;
	@FXML
	private Pane pane2;
	@FXML
	private Pane pane3;
	@FXML
	private TextField txtName;
	@FXML
	private TextField pointsToAdd;
	@FXML
	private Label lblError;

	private LoginModel model;

	// Declare DB objects
	DBConnect conn = null;
	Statement stmt = null;

	public AdminController() {
		conn = new DBConnect();
		model = new LoginModel();

	}

	public void viewPointBalance() {
		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		d.buildData("Select userId,userName,balance from cdugg_accounts order by userId");
	}

	public void viewTransactionsByUser() {
		DynamicTable d = new DynamicTable();
		// pane3.setVisible(false);
		// pane2.setVisible(false);
		// pane1.setVisible(true);
		d.buildData("Select userId,purchaseId,orderDate,quantity from cdugg_transactions order by userId");
	}

	public void addRewardPoints() throws IOException {
		AnchorPane root;
		root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/UserName.fxml"));
		Main.stage.setTitle("User View");
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
	}

	public void addPointsforValidUser() {
		lblError.setText("");
		String username = this.txtName.getText();
		System.out.println(username);
		String pointsToAdd = this.pointsToAdd.getText();
		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (pointsToAdd == null || pointsToAdd.trim().equals("")) {
			lblError.setText("Points Cannot be empty or spaces");
			return;
		}
		int points = 0;
		// user existence check
		if (checkUser(username) == "False") {
			lblError.setText("User does not exist!");
		} else
			points = Integer.parseInt(pointsToAdd);
		Boolean rs = model.addPoints(username, points);
		System.out.println(rs);
		if (rs.equals(Boolean.TRUE)) {
			lblError.setText("Successfully added Points!!!");
		} else {
			lblError.setText("UnExpected error occurred during point addition");
		}

	}

	public String checkUser(String username) {
		Boolean isValid = model.checkUser(username);
		if (!isValid) {
			lblError.setText("User does not exist!");
			return "False";
		} else
			return "User Present";
	}

	public void submitUpdate() {

	}

	public void submitDelete() {

	}

	public void deleteUser() throws IOException {
		AnchorPane root;
		root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/UserName.fxml"));
		Main.stage.setTitle("User View");
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
	}

	public void logout() {
		// System.exit(0);
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/styles.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.setTitle("Login");
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public void deleteExistingUser() {
		lblError.setText("");
		String username = this.txtName.getText();
		System.out.println(username);
		String pointsToAdd = this.pointsToAdd.getText();
		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (pointsToAdd == null || pointsToAdd.trim().equals("")) {
			lblError.setText("Points Cannot be empty or spaces");
			return;
		}
		// user existence check

		if (checkUser(username) == "False") {
			lblError.setText("User does not exist!");
		} else {
			Boolean rs = model.deleteUser(username);
			System.out.println(rs);

			if (rs.equals(Boolean.TRUE)) {
				lblError.setText("Successfully Deleted User");
			} else {
				lblError.setText("UnExpected error during Deletion");
			}
		}

	}

}
