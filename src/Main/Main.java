package Main;

import DataBase.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/** This class contains the application startup. */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../View/Login.fxml")));
        primaryStage.setTitle("Database App");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {

         //Locale.setDefault(new Locale("fr"));

        ResourceBundle rb = ResourceBundle.getBundle("Main/Nat", Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr"))

        JDBC.openConnection();
        //DBCountries.checkDateConversion();
        launch(args);
        JDBC.closeConnection();
    }
}
