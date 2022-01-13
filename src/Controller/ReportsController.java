package Controller;

import DataBase.DbAppointments;
import DataBase.DbContacts;
import Main.ScheduleInterface;
import Model.Appointments;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/** This class controls the Reports view. */
public class ReportsController implements Initializable {

    public ComboBox<String> typeCombo;
    public ComboBox<String> monthCombo;
    public TableView<Appointments> appSchedule;
    public ComboBox<Contacts> contactCombo;
    public Button genBtn1;
    public Button genBtn2;
    public Button backBtn1;
    public Button backBtn2;
    public TableColumn<Appointments, Integer> appIdCol;
    public TableColumn<Appointments, String> titleCol;
    public TableColumn<Appointments, String> descriptionCol;
    public TableColumn<Appointments, String> typeCol;
    public TableColumn<Appointments, LocalDateTime> startCol;
    public TableColumn<Appointments, LocalDateTime> endCol;
    public TableColumn<Appointments, Integer> custIdCol;
    public Button showBtn;


    /** months.
     Creates month list for combobox population.
     @return typeList */
    public ObservableList<String> months() {
        ObservableList<String> monthList = FXCollections.observableArrayList();
        monthList.addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        return monthList;
    }

    /** appType.
     Creates type list for combobox population.
     @return typeList */
    public ObservableList<String> appType() {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.addAll("De-briefing", "Planning Session");
        return typeList;
    }

    /** This is the onGenerate1 method.
     It is used to display the total number of appointment of chosen type during the selected month.
     @param actionEvent upon button click */
    public void onGenerate1(ActionEvent actionEvent) {
        String type = typeCombo.getValue();
        String month = monthCombo.getValue();
        if (type == null || month == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Attention!");
            alert.setContentText("Please select both a type and month.");
            alert.showAndWait();
        } else {
            int appTotal = DbAppointments.filteredTotal(type, month);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Report");
            alert.setContentText("There are " + appTotal + " " + typeCombo.getValue() + " appointments scheduled during the month of " + monthCombo.getValue() + ".");
            alert.showAndWait();
        }
    }

    /** This is the onGenerate2 method.
        It is used to display the total number of appointments in the database.
        @param actionEvent upon button click */
    public void onGenerate2(ActionEvent actionEvent) {
        int grandTotal = DbAppointments.grandTotal();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Report");
        alert.setContentText("There is a total of " + grandTotal + " appointments in the database.");
        alert.showAndWait();
    }

    /** schedule Lambda.
        Used to create list of appointments according to contact. */
    ScheduleInterface schedule = (int contact) -> {
        ObservableList<Appointments> a = DbAppointments.getAppsByContact(contact);
        return a;
    };

    /** The is the onShowSchedule method.
        It uses the 'schedule' lambda to populate the schedule table view with appointments.
        Using the lambda here makes the code more concise, and thus saving time.
        @param actionEvent upon button click */
    public void onShowSchedule(ActionEvent actionEvent) {
        int chosenContact = contactCombo.getValue().getContactId();
        schedule.contactSchedule(chosenContact);
        appSchedule.getSortOrder().add(appIdCol);
        appSchedule.setItems(schedule.contactSchedule(chosenContact));
    }

    /** This is the onBack1 method.
     It is used to go back to the Main Menu view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onBack1(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Menu.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /** This is the onBack2 method.
     It is used to go back to the Main Menu view.
     @param actionEvent upon button click
     @throws IOException throws IOException */
    public void onBack2(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Menu.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCombo.setItems(appType());
        monthCombo.setItems(months());
        monthCombo.setVisibleRowCount(6);
        contactCombo.setItems(DbContacts.getAllContacts());

        //appSchedule.setItems(DbAppointments.getAllAppointments());
        appIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));


    }



}
