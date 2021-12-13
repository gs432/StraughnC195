package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAppController {
    public TextField newAppTitle;
    public TextField newAppDesc;
    public TextField newAppType;
    public TextField newAppStart;
    public ComboBox newAppLocation;
    public ComboBox newAppContact;
    public TextField newAppID;
    public Button newAppSaveBtn;
    public Button newAppCancelBtn;
    public TextField newAppEnd;
    public TextField newAppCustName;
    public TextField newAppCustId;

    public void onNewAppSaveClick(ActionEvent actionEvent) {
    }

    public void onNewAppCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
