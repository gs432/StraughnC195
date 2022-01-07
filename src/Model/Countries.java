package Model;

/** This class contains the model for the Countries object. */
public class Countries {
    private int countryId;
    private String country;

    /** Countries. This is the Countries object constructor
     @param countryId int
     @param country String */
    public Countries (int countryId, String country){
        this.countryId = countryId;
        this.country = country;
    }

    /** countryId getter
     @return countryId */
    public int getCountryId() {
        return countryId;
    }

    /** country getter
     @return country */
    public String getCountry() {
        return country;
    }

    /** toString method.
     Displays countries by name in combobox
     @return country */
    @Override
    public String toString() {
        return country;
    }
}
