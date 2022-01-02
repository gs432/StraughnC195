package Controller;

import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class UpdateAppController {
    public TextField updateAppTitle;
    public TextField updateAppDesc;
    public TextField updateAppLocation;
    public ComboBox<Contacts> updateAppContact;
    public TextField updateAppType;
    public DatePicker updateAppDay;
    public ComboBox<LocalDateTime> updateAppStart;
    public ComboBox<LocalDateTime> updateAppEnd;
    public TextField updateAppID;
    public ComboBox<Customers> updateAppCustId;
    public ComboBox<Users> updateAppUserId;
    public Button updateAppSaveBtn;
    public Button updateAppCancelBtn;

    public void onUpdateAppSaveClick(ActionEvent actionEvent) {

    }

    public void onUpdateAppCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
