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

public class UpdateAppController {
    public TextField updateAppTitle;
    public TextField updateAppDesc;
    public TextField updateAppType;
    public TextField updateAppStart;
    public ComboBox updateAppLocation;
    public ComboBox updateAppContact;
    public TextField updateAppID;
    public Button updateAppSaveBtn;
    public Button updateAppCancelBtn;
    public TextField updateAppEnd;
    public TextField updateAppCustName;
    public TextField updateAppCustId;

    public void onUpdateAppSaveClick(ActionEvent actionEvent) {
    }

    public void onUpdateAppCancelBtnClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
