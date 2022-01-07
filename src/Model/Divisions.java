package Model;

/** This class contains the model for the Divisions object. */
public class Divisions {
    private int divisionId;
    private String division;
    private int countryId;

    /** Divisions. This is the Divisions object constructor
     @param divisionId int
     @param division String
     @param countryId int */
    public Divisions(int divisionId, String division, int countryId){
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /** divisionId getter
     @return divisionId */
    public int getDivisionId() {
        return divisionId;
    }

    /** division getter
     @return division */
    public String getDivision() {
        return division;
    }

    /** countryId getter
     @return countryId */
    public int getCountryId() {
        return countryId;
    }

    /** toString method.
     Displays divisions by name in combobox
     @return division */
    @Override
    public String toString() {
        return division;
    }
}
