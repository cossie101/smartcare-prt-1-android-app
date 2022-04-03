package co.ke.smartcare.dataclasses;

public class  Payment {

    public Payment(){}

    private String reference, amount, created_at;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at( String created_at ) {
        this.created_at = created_at;
    }

    public String getAmount() {
        return amount;
    }

    public String getReference() {
        return reference;
    }

    public void setAmount( String amount ) {
        this.amount = amount;
    }

    public void setReference( String reference ) {
        this.reference = reference;
    }
}
