package Model;

/** This class contains the model for the Customers object. */
public class Customers {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionId;
    //private String country;

    /** Customers. This is the Customers object constructor
     @param customerId int
     @param customerName String
     @param address String
     @param postalCode String
     @param phone String
     @param divisionId int */
    public Customers (int customerId,String customerName, String address, String postalCode, String phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        //this.country = country;


    }

    /** customerId getter
     @return customerId */
    public int getCustomerId() {
        return customerId;
    }

    /** customerName getter
     @return customerName */
    public String getCustomerName() {
        return customerName;
    }

    /** address getter
     @return address */
    public String getAddress() {
        return address;
    }

    /** postalCode getter
     @return postalCode */
    public String getPostalCode() {
        return postalCode;
    }

    /** phone getter
     @return phone */
    public String getPhone() {
        return phone;
    }

    /** divisionId getter
     @return divisionId */
    public int getDivisionId() {
        return divisionId;
    }

    /** toString method.
     Displays customers by name in combobox
     @return customerName */
    @Override
    public String toString() {
        return customerName;
    }
}
