package co.ke.smartcare.dataclasses;

public class UploadInfo {

    private String status, message;

    public UploadInfo(){}

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public void setStatus( String status ) {
        this.status = status;
    }
}
