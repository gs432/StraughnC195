package Controller;

import DataBase.DbAppointments;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class UpdateAppController implements Initializable {
    public TextField updateAppTitle;
    public TextField updateAppDesc;
    public TextField updateAppLocation;
    public ComboBox<Contacts> updateAppContact;
    public TextField updateAppType;
    public DatePicker updateAppDay;
    public ComboBox<LocalTime> updateAppStart;
    public ComboBox<LocalTime> updateAppEnd;
    public TextField updateAppId;
    public ComboBox<Customers> updateAppCustId;
    public ComboBox<Users> updateAppUserId;
    public Button updateAppSaveBtn;
    public Button updateAppCancelBtn;

    public void loadAppointment(Appointments appointment) {
        //updateAppId.setText(Integer.toString(appointment.getAppointmentId()));
        updateAppTitle.setText(appointment.getTitle());
        updateAppDesc.setText(appointment.getDescription());
        updateAppLocation.setText(appointment.getLocation());
        int storedContactId = appointment.getContactId();
        for (Contacts contact : updateAppContact.getItems()) {
            if (storedContactId == contact.getContactId()){
                updateAppContact.getSelectionModel().select(contact);
            }
        }
        updateAppType.setText(appointment.getType());
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

    public void onUpdateAppSaveClick(ActionEvent actionEvent) throws IOException {
        if (updateAppTitle.getText().isBlank() || updateAppDesc.getText().isBlank() || updateAppLocation.getText().isBlank() || updateAppContact.getSelectionModel().isEmpty() || updateAppType.getText().isBlank() || updateAppCustId.getSelectionModel().isEmpty() || updateAppUserId.getSelectionModel().isEmpty()) {
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
            String type = updateAppType.getText();
            int contactId = updateAppContact.getValue().getContactId();
            LocalDate date = updateAppDay.getValue();
            LocalTime start = updateAppStart.getValue();
            LocalTime end = updateAppEnd.getValue();
            LocalDateTime appStart = LocalDateTime.of(date, start);
            LocalDateTime appEnd = LocalDateTime.of(date, end);
            int customerId = updateAppCustId.getValue().getCustomerId();
            int userId = updateAppUserId.getValue().getUserId();
            Appointments selectedApp = new Appointments(appointmentId, title, description, location, type, appStart, appEnd, customerId, userId, contactId);
            DbAppointments.updateAppointment(selectedApp);
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onUpdateAppCancelClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
