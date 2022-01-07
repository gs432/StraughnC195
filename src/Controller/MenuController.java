package Controller;

import DataBase.DbAppointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class controls the Main Menu view. */
public class MenuController implements Initializable {

    public Button mainCustomerBtn;
    public Button mainAppBtn;
    public Button reportBtn;

    /** This is the onMainCustomerClick method.
     It is used to load the Customers view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onMainCustomerClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Customers.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the onMainAppClick method.
     It is used to load the Appointments view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onMainAppClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Appointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the onReportClick method.
     It is used to load the Reports view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onReportClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Reports.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbAppointments.appReminder();
    }
}
