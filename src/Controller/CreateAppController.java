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
    public ComboBox<LocalDateTime> newAppStart;
    public ComboBox<LocalDateTime> newAppEnd;
    public TextField newAppId;
    public ComboBox<Customers> newAppCustId;
    public ComboBox<Users> newAppUserId;
    public Button newAppSaveBtn;
    public Button newAppCancelBtn;


    public void onNewAppSaveClick(ActionEvent actionEvent) throws IOException {
        if (newAppTitle==null || newAppDesc==null || newAppType==null || newAppLocation==null || newAppContact==null || newAppDay==null || newAppStart==null || newAppEnd==null || newAppCustId==null || newAppUserId==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("All fields must contain data.");
            alert.showAndWait();
        } else {
            int appointmentId = Integer.parseInt(newAppId.getText());
            String title = newAppTitle.getText();
            String description = newAppDesc.getText();
            String type = newAppType.getText();
            String location = newAppLocation.getText();
            int contactId = newAppContact.getValue().getContactId();
            LocalDate date = newAppDay.getValue();
            LocalDateTime start = newAppStart.getValue();
            LocalDateTime end = newAppEnd.getValue();
            int customerId = newAppCustId.getValue().getCustomerId();
            int userId = newAppUserId.getValue().getUserId();
            Appointments selectedApp = new Appointments(appointmentId, title, description, type, location, start, end, customerId, userId, contactId);
            DbAppointments.addAppointment(selectedApp);
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Customers.fxml")));
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
        newAppCustId.setItems(DbCustomers.getAllCustomers());
        newAppUserId.setItems(DbUser.getAllUsers());
    }
}
