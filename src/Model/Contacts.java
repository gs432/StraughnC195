package Model;

/** This class contains the model for the Contacts object. */
public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    /** Contacts. This is the Contacts object constructor
     @param contactId int
     @param contactName String
     @param email String */
    public Contacts (int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /** contactId getter
     @return contactId */
    public int getContactId() {
        return contactId;
    }

    /** contactName getter
     @return contactName */
    public String getContactName() {
        return contactName;
    }

    /** email getter
     @return email */
    public String getEmail() {
        return email;
    }

    /** toString method.
        Displays contacts by name in combobox
        @return contactName */
    @Override
    public String toString() {
        return contactName;
    }
}
