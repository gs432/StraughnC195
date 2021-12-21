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

public class Logger {
    //public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
