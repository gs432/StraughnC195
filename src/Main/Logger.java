package Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/** This class contains the method for archiving login attempts. */
public class Logger {
    //public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** This is the loginTracker method.
        It uses a lambda expression to record the username, time and outcome of each login attempt to a txt file.
        The method can be inserted where needed, less lines of code
        @param user username
        @param validation boolean for credential match
        @throws IOException IOException */
    public static void loginTracker(String user, boolean validation) throws IOException {
        File file = new File("loginArchive.txt");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter outputFile = new PrintWriter(fw);
        LocalDateTime date = LocalDateTime.now();

        if (validation) {
            LoggerInterface output = (name, time) -> "Login @ " + time + ", by " + name;
            outputFile.println(output.fileText(user, Timestamp.valueOf(date)));
            outputFile.close();
        } else {
            LoggerInterface output = (name, time) -> "Failed login @ " + time + ", by " + name;
            outputFile.println(output.fileText(user, Timestamp.valueOf(date)));
            outputFile.close();
        }

    }
}
