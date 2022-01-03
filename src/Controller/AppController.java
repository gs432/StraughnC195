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
import java.util.Objects;
import java.util.ResourceBundle;

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

    public void onNewAppClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/CreateApp.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onUpdateAppClick(ActionEvent actionEvent) throws IOException {
        if (appointmentTable.getSelectionModel().getSelectedItem() != null) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/UpdateApp.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("You must select an appointment to update.");
            alert.showAndWait();
        }
    }

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

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Menu.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onMonthAppClick(ActionEvent actionEvent) {
    }

    public void onWeekAppClick(ActionEvent actionEvent) {
    }

    public void onAllAppClick(ActionEvent actionEvent) {
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
