package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField usernameBox;
    public TextField passwordBox;
    public Label passwordLabel;
    public Label usernameLabel;
    public Button loginButton;
    public Label timeZoneLabel;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("Main/Nat");

    public void onLoginClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../View/Menu"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        passwordLabel.setText(resourceBundle.getString("Password"));
        usernameLabel.setText(resourceBundle.getString("Username"));
        loginButton.setText(resourceBundle.getString("Login"));
        timeZoneLabel.setText(String.valueOf(ZoneId.systemDefault()));
    }
}
