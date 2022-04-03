package co.ke.smartcare.dataclasses;

public class Enquiry {

    public Enquiry(){}

    private String enquiry, enquiryDate, enquiryTitle;
    private int user, enquiryID;

    public String getEnquiry() {
        return enquiry;
    }

    public void setUser( int user ) {
        this.user = user;
    }

    public int getUser() {
        return user;
    }

    public String getEnquiryDate() {
        return enquiryDate;
    }

    public String getEnquiryTitle() {
        return enquiryTitle;
    }

    public void setEnquiry( String enquiry ) {
        this.enquiry = enquiry;
    }

    public void setEnquiryDate( String enquiryDate ) {
        this.enquiryDate = enquiryDate;
    }

    public void setEnquiryTitle( String enquiryTitle ) {
        this.enquiryTitle = enquiryTitle;
    }

    public int getEnquiryID() {
        return enquiryID;
    }

    public void setEnquiryID( int enquiryID ) {
        this.enquiryID = enquiryID;
    }
}
