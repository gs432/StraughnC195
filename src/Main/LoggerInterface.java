package Main;

import java.sql.Timestamp;

public interface LoggerInterface {
    String fileText(String name, Timestamp time);
}
