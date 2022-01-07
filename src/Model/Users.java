package Model;

/** This class contains the model for the Users object. */
public class Users {
    private int userId;
    private String userName;
    private String password;

    /** Users. This is the Users object constructor
     @param userId int
     @param userName String
     @param password int */
    public Users (int userId, String userName, String password){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /** userId getter
     @return userId */
    public int getUserId() {
        return userId;
    }

    /** userName getter
     @return userName */
    public String getUserName() {
        return userName;
    }

    /** password getter
     @return password */
    public String getPassword() {
        return password;
    }

    /** toString method.
     Displays users by name in combobox
     @return userName */
    @Override
    public String toString() {
        return userName;
    }
}
