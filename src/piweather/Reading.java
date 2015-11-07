package piweather;

import java.util.Date;

public class Reading {
    private Date 		date;
    private boolean		valid;

    public Reading()
    {
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValid() {
        return valid;
    }

    public String getValid() {
        return isValid() ? "Valid" : "Invalid";
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}