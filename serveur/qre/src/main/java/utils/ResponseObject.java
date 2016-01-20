package utils;

import com.google.gson.Gson;

/**
 * Created by stagiaire on 20/01/2016.
 */
public class ResponseObject {

    private String status;
    private String nextURL;
    private String message;

    public ResponseObject(String status) {
        this.status = status;
        this.nextURL = null;
        this.message = null;
    }

    public ResponseObject(String status, String nextURL) {
        this.status = status;
        this.nextURL = nextURL;
        this.message = null;
    }

    public ResponseObject(String status, String nextURL, String message) {
        this.message = message;
        this.nextURL = nextURL;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNextURL() {
        return nextURL;
    }

    public void setNextURL(String nextURL) {
        this.nextURL = nextURL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJSON(){
        return new Gson().toJson(this);
    }
}
