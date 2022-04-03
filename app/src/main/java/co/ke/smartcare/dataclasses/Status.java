package co.ke.smartcare.dataclasses;

public class Status {


    public Status(){}

    private String date, description, author, id;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public void setDate( String date ) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }
}
