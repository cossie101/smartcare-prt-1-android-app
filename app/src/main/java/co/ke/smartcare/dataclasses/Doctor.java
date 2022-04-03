package co.ke.smartcare.dataclasses;

public class Doctor {

    public  Doctor(){}

    private String name;
    private int id;

    public void setId( int id ) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
