package co.ke.smartcare.dataclasses;

public class Appointment {

    public Appointment(){}

    private String title, appointment, doctor, date;

    public String getAppointment() {
        return appointment;
    }

    public String getDate() {
        return date;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getTitle() {
        return title;
    }

    public void setAppointment( String appointment ) {
        this.appointment = appointment;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public void setDoctor( String doctor ) {
        this.doctor = doctor;
    }

    public void setTitle( String title ) {
        this.title = title;
    }
}
