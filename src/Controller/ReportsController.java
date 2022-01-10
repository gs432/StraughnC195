package Controller;

import Model.Contacts;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class controls the Reports view. */
public class ReportsController implements Initializable {

    public ComboBox<String> typeCombo;
    public ComboBox<String> monthCombo;
    public ComboBox<Countries> countryCombo;
    public Button genBtn1;
    public Button genBtn2;
    public Button backButton;
    public Button backButton2;
    public ComboBox<Contacts> contactCombo;

    /** appType.
     Creates type list for combobox population.
     @return typeList */
    public ObservableList<String> appType() {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.addAll("De-briefing", "Planning Session");
        return typeList;
    }

    /** months.
     Creates month list for combobox population.
     @return typeList */
    public ObservableList<String> months() {
        ObservableList<String> monthList = FXCollections.observableArrayList();
        monthList.addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        return monthList;
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
    }
}
