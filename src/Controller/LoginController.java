package Controller;

import DataBase.DbUser;
import DataBase.JDBC;
import Main.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.ZoneId;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField usernameBox;
    public TextField passwordBox;
    public Label passwordLabel;
    public Label usernameLabel;
    public Button loginButton;
    public Label timeZoneLabel;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat");

    //private static Connection conn = JDBC.getConnection();

    public void onLoginClick(ActionEvent actionEvent) throws IOException {
        String usernameInput = usernameBox.getText();
        String passwordInput = passwordBox.getText();
        boolean validation = DbUser.verifyLogin(usernameInput,passwordInput);
        if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("ErrorAlert"));
            alert.setContentText(resourceBundle.getString("IncorrectEntry"));
            alert.showAndWait();
            Logger.loginTracker(usernameInput, validation);
        } else if (validation){
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/Menu.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            Logger.loginTracker(usernameInput, true);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(resourceBundle.getString("ErrorAlert"));
            alert.setContentText(resourceBundle.getString("IncorrectEntry"));
            alert.showAndWait();
            Logger.loginTracker(usernameInput, false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        passwordLabel.setText(resourceBundle.getString("Password"));
        usernameLabel.setText(resourceBundle.getString("Username"));
        loginButton.setText(resourceBundle.getString("Login"));
        timeZoneLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }
}
