package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {
    public TableView appointmentTable;
    public TableColumn appIdCol;
    public TableColumn appTitleCol;
    public TableColumn appDescCol;
    public TableColumn appLocationCol;
    public TableColumn appContactCol;
    public TableColumn appTypeCol;
    public TableColumn appStartCol;
    public TableColumn appEndCol;
    public TableColumn appNameCol;
    public TableColumn appUserIdCol;
    public Button newAppBtn;
    public Button updateAppBtn;
    public Button deleteAppBtn;
    public Button backBtn;
    public RadioButton monthAppRadio;
    public RadioButton weekAppRadio;
    public RadioButton allAppRadio;

    public void onNewAppClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/CreateApp.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onUpdateAppClick(ActionEvent actionEvent) throws IOException {
        if (appointmentTable.getSelectionModel().getSelectedItem() != null) {
            Parent parent = FXMLLoader.load(getClass().getResource("/View/UpdateApp.fxml"));
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
        if (appointmentTable.getSelectionModel().getSelectedItem() != null) {

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("You must select an appointment to delete.");
            alert.showAndWait();
        }
    }

    public void onBackClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
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
}
