package Model;

import java.time.LocalDateTime;

/** This class contains the model for the Appointments object. */
public class Appointments {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;
    private int contactId;

    /** Appointments. This is the Appointments object constructor
        @param appointmentId int
        @param title String
        @param description String
        @param location String
        @param type String
        @param start LocalDateTime
        @param end LocalDateTime
        @param customerId int
        @param userId int
        @param contactId int */
    public Appointments (int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;

    }

    /** Appointments. This is the Appointments object constructor used for the contact schedule report view.
     @param appointmentId int
     @param title String
     @param description String
     @param type String
     @param start LocalDateTime
     @param end LocalDateTime
     @param customerId int */
    public Appointments(int appointmentId, String title, String description, String type, LocalDateTime start, LocalDateTime end, int customerId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
    }

    /** Appointments. This is the Appointments object constructor used for the filterAppsByCustomer method.
     @param appId int
     @param start LocalDateTime
     @param end LocalDateTime
     @param cId int */
    public Appointments(int appId, LocalDateTime start, LocalDateTime end, int cId) {
        this.appointmentId = appId;
        this.start = start;
        this.end = end;
        this.customerId = cId;

    }

    /** appointmentId getter
        @return appointmentId */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** title getter
     @return title */
    public String getTitle() {
        return title;
    }

    /** description getter
     @return description */
    public String getDescription() {
        return description;
    }

    /** location getter
     @return location */
    public String getLocation() {
        return location;
    }

    /** type getter
     @return type */
    public String getType() {
        return type;
    }

    /** start getter
     @return start */
    public LocalDateTime getStart() {
        return start;
    }

    /** end getter
     @return end */
    public LocalDateTime getEnd() {
        return end;
    }

    /** customerId getter
     @return customerId */
    public int getCustomerId() {
        return customerId;
    }

    /** userId getter
     @return userId */
    public int getUserId() {
        return userId;
    }

    /** contactId getter
     @return contactId */
    public int getContactId() {
        return contactId;
    }
}
