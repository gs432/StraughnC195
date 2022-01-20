package Controller;

import DataBase.DbAppointments;
import DataBase.DbContacts;
import DataBase.DbCustomers;
import DataBase.DbUser;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.*;
import java.time.zone.ZoneRulesProvider;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.TimeZone;

/** This class controls the Add Appointment view. */
public class CreateAppController implements Initializable {
    public TextField newAppTitle;
    public TextField newAppDesc;
    public ComboBox<String> newAppType;
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

    /** This is the onNewAppSaveClick method.
     It is used to save the entered data as a new appointment in the database.
     @param actionEvent upon button click */
    public void onNewAppSaveClick(ActionEvent actionEvent) throws IOException {
        if (newAppTitle.getText().isBlank() || newAppDesc.getText().isBlank() || newAppType.getSelectionModel().isEmpty() || newAppLocation.getText().isBlank() || newAppContact.getSelectionModel().isEmpty() || newAppCustId.getSelectionModel().isEmpty() || newAppUserId.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("All fields must contain data.");
            alert.showAndWait();
        } else if (newAppStart.getValue().isAfter(newAppEnd.getValue()) || newAppStart.getValue().equals(newAppEnd.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("Appointments cannot be less than 15 minutes in length.");
            alert.showAndWait();
        } else if (newAppDay.getValue().isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("Appointment date cannot be set in the past.");
            alert.showAndWait();
        } else {
            String title = newAppTitle.getText();
            String description = newAppDesc.getText();
            String location = newAppLocation.getText();
            String type = newAppType.getValue();
            int contactId = newAppContact.getValue().getContactId();
            LocalDate date = newAppDay.getValue();
            LocalTime start = newAppStart.getValue();
            LocalTime end = newAppEnd.getValue();
            LocalDateTime appStart = LocalDateTime.of(date, start);
            LocalDateTime appEnd = LocalDateTime.of(date, end);
            int customerId = newAppCustId.getValue().getCustomerId();
            int userId = newAppUserId.getValue().getUserId();
            Appointments conflict = DbAppointments.detectAddConflict(appStart, appEnd, customerId);
            if (conflict != null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Scheduling Conflict!");
                alert.setContentText("The start/end time of this appointment conflicts with another appointment in the database.  There can be no appointment overlap. ");
                alert.showAndWait();
            } else {
            /*
                ZonedDateTime utcStartTime = appStart.atZone(zoneId).withZoneSameInstant(ZoneId.of("UTC"));
                ZonedDateTime utcEndTime = appEnd.atZone(zoneId).withZoneSameInstant(ZoneId.of("UTC"));
                LocalDateTime newStartLDT = utcStartTime.toLocalDateTime();
                LocalDateTime newEndLDT = utcEndTime.toLocalDateTime();
            */
                DbAppointments.addAppointment(title, description, location, type, appStart, appEnd, customerId, userId, contactId);
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            }

        }
    }

    /** This is the onNewAppCancelClick method.
     It is used to go back to the Appointments view.
     @param actionEvent upon radio click
     @throws IOException IOException */
    public void onNewAppCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** appType.
        Creates type list for combobox population.
        @return typeList */
    public ObservableList<String> appType() {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.addAll("De-briefing", "Planning Session");
        return typeList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newAppContact.setItems(DbContacts.getAllContacts());
        newAppContact.setVisibleRowCount(5);
        newAppType.setItems(appType());
        newAppDay.setValue(LocalDate.now());
        LocalTime startChoices = LocalTime.of(8,0);
        LocalTime endChoices = LocalTime.of(22, 0);
        ZoneId est = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();
        ZonedDateTime estOpen = ZonedDateTime.of(LocalDate.now(), startChoices, est);
        ZonedDateTime estClose = ZonedDateTime.of(LocalDate.now(), endChoices, est);
        ZonedDateTime zonedOpen = estOpen.withZoneSameInstant(localZone);
        ZonedDateTime zonedClose = estClose.withZoneSameInstant(localZone);
        LocalTime open = zonedOpen.toLocalTime();
        LocalTime close = zonedClose.toLocalTime();
        while(open.isBefore(close.plusSeconds(1))){
            newAppStart.getItems().add(open);
            newAppEnd.getItems().add(open);
            open = open.plusMinutes(15);
        }
        newAppStart.getSelectionModel().select(LocalTime.of(8, 0));
        newAppStart.setVisibleRowCount(6);
        newAppEnd.getSelectionModel().select(LocalTime.of(8, 15));
        newAppEnd.setVisibleRowCount(6);
        newAppCustId.setItems(DbCustomers.getAllCustomers());
        newAppCustId.setVisibleRowCount(5);
        newAppUserId.setItems(DbUser.getAllUsers());
        newAppUserId.setVisibleRowCount(5);
    }
}
