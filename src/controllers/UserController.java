package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import application.DynamicTable;
import application.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.LoginModel;
import models.UserModel;

public class UserController {
	@FXML
	public TextField txtProductName;

	@FXML
	private TextField quantity;

	@FXML
	private Label lblError;

	LoginController lm = new LoginController();
	private UserModel model;

	public UserController() {
		// um = new UserModel();
	}

	public void checkPoints() {
		DynamicTable d = new DynamicTable();
		// call method from DynamicTable class and pass some arbitrary query string
		String query = "Select userId,userName, balance from cdugg_accounts where userName = 'basic'";
		// + lm.txtUsername";
		d.buildData(query);
	}

	public void viewTransactions() {
		DynamicTable d = new DynamicTable();
		// pane3.setVisible(false);
		// pane2.setVisible(false);
		// pane1.setVisible(true);
		String query = "Select userId,purchaseId,orderDate,quantity from cdugg_transactions where uname = 'basic'";
		d.buildData(query);
	}
	
	@SuppressWarnings("null")
	public void addTransaction() throws IOException {	
		Boolean rs = null;
		AnchorPane root;
		root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/Transaction.fxml"));
		Main.stage.setTitle("Transaction View");
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);	
	
		
	}
	public void addUserTransaction() throws IOException {	
		String productName = this.txtProductName.getText();
			String quantity = this.quantity.getText();
		//	model.addTransactions(lm.txtUsername.toString(), productName, quantity);
			//rs=Boolean.TRUE;
			//if(rs) {
			lblError.setText("Successfully added Transaction!!!");			
			//}
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

	public static void setUserid(int user_id) {
		// TODO Auto-generated method stub

	}

}
