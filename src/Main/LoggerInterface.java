package Main;

import java.sql.Timestamp;

/** LoggerInterface.
    Interface for lambda used to archive login attempts. */
public interface LoggerInterface {
    String fileText(String name, Timestamp time);
}
