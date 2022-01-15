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
import java.lang.reflect.Type;
import java.net.URL;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class controls the Edit Appointment view. */
public class UpdateAppController implements Initializable {
    public TextField updateAppTitle;
    public TextField updateAppDesc;
    public TextField updateAppLocation;
    public ComboBox<Contacts> updateAppContact;
    public ComboBox<String> updateAppType;
    public DatePicker updateAppDay;
    public ComboBox<LocalTime> updateAppStart;
    public ComboBox<LocalTime> updateAppEnd;
    public TextField updateAppId;
    public ComboBox<Customers> updateAppCustId;
    public ComboBox<Users> updateAppUserId;
    public Button updateAppSaveBtn;
    public Button updateAppCancelBtn;
    ZoneId zoneId = ZoneId.systemDefault();

    /** This is the loadAppointment method.
     It is used to populate the text fields with the selected appointment's data.
     @param appointment Appointments */
    public void loadAppointment(Appointments appointment) {
        updateAppId.setText(Integer.toString(appointment.getAppointmentId()));
        updateAppTitle.setText(appointment.getTitle());
        updateAppDesc.setText(appointment.getDescription());
        updateAppLocation.setText(appointment.getLocation());
        int storedContactId = appointment.getContactId();
        for (Contacts contact : updateAppContact.getItems()) {
            if (storedContactId == contact.getContactId()){
                updateAppContact.getSelectionModel().select(contact);
            }
        }
        updateAppType.setValue(appointment.getType());
        updateAppDay.setValue(appointment.getStart().toLocalDate());
        updateAppStart.setValue(appointment.getStart().toLocalTime());
        updateAppEnd.setValue(appointment.getEnd().toLocalTime());
        int storedCustomerId = appointment.getCustomerId();
        for (Customers customer : updateAppCustId.getItems()) {
            if (storedCustomerId == customer.getCustomerId()) {
                updateAppCustId.getSelectionModel().select(customer);
            }
        }
        int storedUserId = appointment.getUserId();
        for (Users user : updateAppUserId.getItems()) {
            if (storedUserId == user.getUserId()) {
                updateAppUserId.getSelectionModel().select(user);
            }
        }
    }

    /** This is the onUpdateAppSaveClick method.
     It is used to overwrite the selected appointment with the data entered.
     @param actionEvent upon button click
     @throws IOException IOException */
    public void onUpdateAppSaveClick(ActionEvent actionEvent) throws IOException {
        if (updateAppTitle.getText().isBlank() || updateAppDesc.getText().isBlank() || updateAppLocation.getText().isBlank() || updateAppContact.getSelectionModel().isEmpty() || updateAppType.getSelectionModel().isEmpty() || updateAppCustId.getSelectionModel().isEmpty() || updateAppUserId.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("All fields must contain data.");
            alert.showAndWait();
        } else if (updateAppStart.getValue().isAfter(updateAppEnd.getValue()) || updateAppStart.getValue().equals(updateAppEnd.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("Appointments cannot be less than 15 minutes in length.");
            alert.showAndWait();
        } else {
            int appointmentId = Integer.parseInt(updateAppId.getText());
            String title = updateAppTitle.getText();
            String description = updateAppDesc.getText();
            String location = updateAppLocation.getText();
            String type = updateAppType.getValue();
            int contactId = updateAppContact.getValue().getContactId();
            LocalDate date = updateAppDay.getValue();
            LocalTime start = updateAppStart.getValue();
            LocalTime end = updateAppEnd.getValue();
            LocalDateTime appStart = LocalDateTime.of(date, start);
            LocalDateTime appEnd = LocalDateTime.of(date, end);
            int customerId = updateAppCustId.getValue().getCustomerId();
            int userId = updateAppUserId.getValue().getUserId();
            Appointments conflict = DbAppointments.detectConflict(appStart, appEnd, customerId);
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
                Appointments selectedApp = new Appointments(appointmentId, title, description, location, type, appStart, appEnd, customerId, userId, contactId);
                DbAppointments.updateAppointment(selectedApp);
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }

        }
    }

    /** This is the onUpdateAppCancelClick method.
     It is used to go back to the Appointments view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onUpdateAppCancelClick(ActionEvent actionEvent) throws IOException {
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
        updateAppContact.setItems(DbContacts.getAllContacts());
        updateAppContact.setVisibleRowCount(5);
        updateAppType.setItems(appType());
        LocalTime startChoices = LocalTime.of(8,0);
        LocalTime endChoices = LocalTime.of(22, 0);
        while(startChoices.isBefore(endChoices.plusSeconds(1))){
            updateAppStart.getItems().add(startChoices);
            updateAppEnd.getItems().add(startChoices);
            startChoices = startChoices.plusMinutes(15);
        }
        updateAppStart.setVisibleRowCount(8);
        updateAppEnd.setVisibleRowCount(8);
        updateAppCustId.setItems(DbCustomers.getAllCustomers());
        updateAppCustId.setVisibleRowCount(5);
        updateAppUserId.setItems(DbUser.getAllUsers());
        updateAppUserId.setVisibleRowCount(5);
    }
}
