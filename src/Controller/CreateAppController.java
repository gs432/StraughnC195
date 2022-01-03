package Controller;

import DataBase.DbAppointments;
import DataBase.DbContacts;
import DataBase.DbCustomers;
import DataBase.DbUser;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateAppController implements Initializable {
    public TextField newAppTitle;
    public TextField newAppDesc;
    public TextField newAppType;
    public TextField newAppLocation;
    public ComboBox<Contacts> newAppContact;
    public DatePicker newAppDay;
    public ComboBox<LocalTime> newAppStart;
    public ComboBox<LocalTime> newAppEnd;
    public TextField newAppId;
    public ComboBox<Customers> newAppCustId;
    public ComboBox<Users> newAppUserId;
    public Button newAppSaveBtn;
    public Button newAppCancelBtn;


    public void onNewAppSaveClick(ActionEvent actionEvent) throws IOException {
        if (newAppTitle.getText().isBlank() || newAppDesc.getText().isBlank() || newAppType.getText().isBlank() || newAppLocation.getText().isBlank() || newAppContact.getSelectionModel().isEmpty() || newAppCustId.getSelectionModel().isEmpty() || newAppUserId.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("All fields must contain data.");
            alert.showAndWait();
        } else if (newAppStart.getValue().isAfter(newAppEnd.getValue()) || newAppStart.getValue().equals(newAppEnd.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("Appointments cannot be less than 15 minutes in length.");
            alert.showAndWait();
        } else {
            String title = newAppTitle.getText();
            String description = newAppDesc.getText();
            String location = newAppLocation.getText();
            String type = newAppType.getText();
            int contactId = newAppContact.getValue().getContactId();
            LocalDate date = newAppDay.getValue();
            LocalTime start = newAppStart.getValue();
            LocalTime end = newAppEnd.getValue();
            LocalDateTime appStart = LocalDateTime.of(date, start);
            LocalDateTime appEnd = LocalDateTime.of(date, end);
            int customerId = newAppCustId.getValue().getCustomerId();
            int userId = newAppUserId.getValue().getUserId();
            DbAppointments.addAppointment(title, description, location, type, appStart, appEnd, customerId, userId, contactId);
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onNewAppCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newAppContact.setItems(DbContacts.getAllContacts());
        newAppContact.setVisibleRowCount(5);
        newAppDay.setValue(LocalDate.now());
        LocalTime start = LocalTime.of(8,0);
        LocalTime end = LocalTime.of(22, 0);
        while(start.isBefore(end.plusSeconds(1))){
            newAppStart.getItems().add(start);
            newAppEnd.getItems().add(start);
            start = start.plusMinutes(15);
        }
        newAppStart.getSelectionModel().select(LocalTime.of(8, 0));
        newAppStart.setVisibleRowCount(8);
        newAppEnd.getSelectionModel().select(LocalTime.of(8, 15));
        newAppEnd.setVisibleRowCount(8);
        newAppCustId.setItems(DbCustomers.getAllCustomers());
        newAppCustId.setVisibleRowCount(5);
        newAppUserId.setItems(DbUser.getAllUsers());
        newAppUserId.setVisibleRowCount(5);
    }
}
