package Controller;

import DataBase.DbAppointments;
import Model.Appointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class controls the Appointment table view. */
public class AppController implements Initializable {
    public TableView<Appointments> appointmentTable;
    public TableColumn<Appointments, Integer> appIdCol;
    public TableColumn<Appointments, String> appTitleCol;
    public TableColumn<Appointments, String> appDescCol;
    public TableColumn<Appointments, String> appLocationCol;
    public TableColumn<Appointments, String> appTypeCol;
    public TableColumn<Appointments, LocalDateTime> appStartCol;
    public TableColumn<Appointments, LocalDateTime> appEndCol;
    public TableColumn<Appointments, Integer> appCustIdCol;
    public TableColumn<Appointments, Integer> appUserIdCol;
    public TableColumn<Appointments, Integer> appContactIdCol;
    public Button newAppBtn;
    public Button updateAppBtn;
    public Button deleteAppBtn;
    public Button backBtn;
    public RadioButton monthAppRadio;
    public RadioButton weekAppRadio;
    public RadioButton allAppRadio;
    Stage stage;
    Parent scene;
    ZoneId zoneId = ZoneId.systemDefault();

    /** This is the onNewAppClick method.
        It is used to load the Add Appointment view.
        @param actionEvent upon button click
        @throws IOException throws IOException */
    public void onNewAppClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/CreateApp.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the onUpdateAppClick method.
     It is used to load the Edit Appointment view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onUpdateAppClick(ActionEvent actionEvent) throws IOException {
        Appointments selectedApp = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedApp != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/UpdateApp.fxml"));
            loader.load();
            UpdateAppController controller = loader.getController();
            controller.loadAppointment(selectedApp);
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("You must select an appointment to update.");
            alert.showAndWait();
        }
    }

    /** This is the onDeleteAppClick method.
     It is used to delete a selected appointment from the appointment table.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onDeleteAppClick(ActionEvent actionEvent) throws IOException {
        Appointments selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            DbAppointments.deleteAppointment(selectedAppointment.getAppointmentId());
            appointmentTable.setItems(DbAppointments.getAllAppointments());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleted Successfully");
            alert.setContentText("You have successfully deleted appointment " + selectedAppointment.getAppointmentId() + " , of the type:  " + selectedAppointment.getType() + " .");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("You must select an appointment to delete.");
            alert.showAndWait();
        }
    }

    /** This is the onBackClick method.
     It is used to go back to the Main Menu view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Menu.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the onMonthAppClick method.
     It is used to populate the appointment tableview with appointments scheduled for the current month.
     @param actionEvent upon radio click */
    public void onMonthAppClick(ActionEvent actionEvent) {
        appointmentTable.setItems(DbAppointments.getMonthlyApps());
    }

    /** This is the onWeekAppClick method.
     It is used to populate the appointment tableview with appointments scheduled for the current week.
     @param actionEvent upon radio click */
    public void onWeekAppClick(ActionEvent actionEvent) {
        appointmentTable.setItems(DbAppointments.getWeeklyApps());
    }

    /** This is the onAllAppClick method.
     It is used to populate the appointment tableview with all appointments in the database.
     @param actionEvent upon radio click */
    public void onAllAppClick(ActionEvent actionEvent) {
        appointmentTable.setItems(DbAppointments.getAllAppointments());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTable.setItems(DbAppointments.getAllAppointments());
        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCustIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        appContactIdCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
    }
}
