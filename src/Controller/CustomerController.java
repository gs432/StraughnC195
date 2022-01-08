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

/** This class controls the Customer table view. */
public class CustomerController implements Initializable {
    public TableView<Customers> customerTable;
    public TableColumn<Customers, Integer> custIdCol;
    public TableColumn<Customers, String> custNameCol;
    public TableColumn<Customers, String> custAddressCol;
    public TableColumn<Customers, String> custPostalCol;
    public TableColumn<Customers, String> custPhoneCol;
    public TableColumn<Customers, String> custDivisionCol;
    public Button newCustomerBtn;
    public Button updateCustomerBtn;
    public Button deleteCustomerBtn;
    public Button backBtn;
    public static Customers selectedCustomer;
    Stage stage;
    Parent scene;

    /** This is the onNewCustomerClick method.
     It is used to load the Add Customer view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onNewCustomerClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/CreateCust.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the onUpdateCustomerClick method.
     It is used to load the Edit Customer view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onUpdateCustomerClick(ActionEvent actionEvent) throws IOException {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/UpdateCust.fxml"));
            loader.load();
            UpdateCustController controller = loader.getController();
            controller.loadCustomer(selectedCustomer);
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("You must select a customer to update.");
            alert.showAndWait();
        }
    }

    /** This is the onDeleteCustomerClick method.
     It is used to delete the selected customer from the database.
     @param actionEvent upon button click
     @throws SQLException throws SQLException */
    public void onDeleteCustomerClick(ActionEvent actionEvent) throws SQLException {
        selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attention!");
            alert.setContentText("Deletion will also remove this customer's appointments.  Would you like to proceed?");
            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK) {
                DbCustomers.deleteCustomer(selectedCustomer.getCustomerId());
                customerTable.setItems(DbCustomers.getAllCustomers());
                Alert removed = new Alert(Alert.AlertType.INFORMATION);
                removed.setTitle("Deleted Successfully");
                removed.setContentText("You have successfully deleted Customer " + selectedCustomer.getCustomerId() + ".");
                removed.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention");
            alert.setContentText("You must select a customer to delete.");
            alert.showAndWait();
        }
    }

    /** This is the onBackClick method.
     It is used to go back to the Main Menu view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onBackClick(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Menu.fxml")));
        stage.setScene(new Scene(scene));
        stage.show();

        /*
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Menu.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

         */
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
