package Controller;

import DataBase.DbCustomers;
import DataBase.JDBC;
import Model.Customers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import jdk.jshell.execution.JdiDefaultExecutionControl;

import javax.swing.text.html.Option;
import javax.xml.transform.OutputKeys;
import java.io.IOException;
import java.lang.management.OperatingSystemMXBean;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public TableView customerTable;
    public TableColumn custIdCol;
    public TableColumn custNameCol;
    public TableColumn custAddressCol;
    public TableColumn custPostalCol;
    public TableColumn custPhoneCol;
    public TableColumn custDivisionCol;
    public Button newCustomerBtn;
    public Button updateCustomerBtn;
    public Button deleteCustomerBtn;
    public Button backBtn;
    public static Customers selectedCustomer;

    public void onNewCustomerClick(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/CreateCust.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onUpdateCustomerClick(ActionEvent actionEvent) throws IOException {
        if (customerTable.getSelectionModel().getSelectedItem() != null) {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/UpdateCust.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("You must select a customer to update.");
            alert.showAndWait();
        }
    }

    public void onDeleteCustomerClick(ActionEvent actionEvent) throws SQLException {
        selectedCustomer = (Customers) customerTable.getSelectionModel().getSelectedItem();
        if (customerTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attention!");
            alert.setContentText("Deletion will also remove this customer's appointments.  Would you like to proceed?");
            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK) {
                DbCustomers.deleteCustomer(selectedCustomer.getCustomerId());
                customerTable.setItems(DbCustomers.getAllCustomers());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setContentText("You must select a customer to delete.");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerTable.setItems(DbCustomers.getAllCustomers());
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        
    }
}
