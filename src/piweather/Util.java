package piweather;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {
    
    public static void delayMillis(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String intLFZ(int val, int width) {
        String s = String.valueOf(val);
        
        for (int i = 1; i < width; i++) {
            if (val < (i * 10)) {
                s = "0" + s;
            }
        }
        return s;
    }

    public static double roundToDecimals(double d, int c) {
        // horrible way of truncating d to c decimal places as Java ME does not have the Math.pow method
        double tempD = d;       
        for (int i = 0; i < c; i++) {
            tempD *= 10;
        }
        long tempL = (long)tempD;
        tempD = (double)tempL;
        for (int j = 0; j < c; j++) {
            tempD /= 10;
        }
        return tempD;
    }


    public static String getDateTimeFormatted(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        int year   = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day    = calendar.get(Calendar.DAY_OF_MONTH);
        int hour   = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        
        String dateFormatted = String.valueOf(year) +
                               intLFZ(month + 1,2) +
                               intLFZ(day,2) +
                               intLFZ(hour,2) +
                               intLFZ(minute,2) +
                               intLFZ(second,2);
        return dateFormatted;        
    }

    public static long getSecondsSinceEpoch(Date date) {
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date);
        long millis = calendar.getTime().getTime();     
        return millis / 1000;        
    }

    public static String getDateHuman(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        int year   = calendar.get(Calendar.YEAR);
        int month  = calendar.get(Calendar.MONTH);
        int day    = calendar.get(Calendar.DAY_OF_MONTH);
        
        return(intLFZ(day,2) + "/" + intLFZ(month + 1,2) + "/" + String.valueOf(year));      
    }

    public static String getTimeHuman(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        int hour   = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        
        //return(intLFZ(hour,2) + ":" + intLFZ(minute,2) + ":" + intLFZ(second,2));       
        return(intLFZ(hour,2) + ":" + intLFZ(minute,2));       
    }
    
    
}
