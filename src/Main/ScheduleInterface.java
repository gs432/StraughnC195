package Main;

import Model.Appointments;
import javafx.collections.ObservableList;

/** ScheduleInterface.
    Interface for lambda used to populate contact schedule. */
public interface ScheduleInterface {
    ObservableList<Appointments> contactSchedule(int contactId);
}
