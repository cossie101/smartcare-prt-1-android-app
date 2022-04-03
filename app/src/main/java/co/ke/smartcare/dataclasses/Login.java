package co.ke.smartcare.dataclasses;

public class Login {

    private String status, message, name, idno, email, phone, username, subscription, account;
    private int userId;

    public Login(){}

    public String getAccount() {
        return account;
    }

    public void setAccount( String account ) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription( String subscription ) {
        this.subscription = subscription;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone( String phone ) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno( String idno ) {
        this.idno = idno;
    }
}
