/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piweather;

import LCD4ME.LCD;
import LCD4ME.Screen;
import LCD4ME.StringWidget;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Dave
 */
public class LCDManager {
    private LCD lcd;

    // Values set from readings
    public double windSpeed;
    public double windDirection;
    public double outsideTemperature;
    public double outsideHumidity;
    public double outsidePressure;

    // For Wind Screen
    private Screen windScreen;
    private StringWidget windScreenLine1;
    private StringWidget windScreenWindSpeed;
    private StringWidget windScreenWindDirection;
    
    // For Outside Screen
    private Screen outsideScreen;
    private StringWidget outsideScreenLine1;
    private StringWidget outsideScreenTemperature;
    private StringWidget outsideScreenHumidity;
    private StringWidget outsideScreenPressure;
    

    
    public LCDManager() {
        lcd = new LCD();
        if (lcd.open(Constants.LCD_HOSTNAME, Constants.LCD_PORT)) {
            System.out.println("Connected to LCD at " + Constants.LCD_HOSTNAME + ":" + Constants.LCD_PORT);
            
            try {
                // Wind Screen
                windScreen = new Screen(lcd, "WindScreen");       
                windScreen.setHeartbeat(lcd, true);
                windScreen.setDuration(lcd, 64);        // in eighths of a second
                windScreen.setPriority(lcd, "foreground");
                windScreenLine1         = new StringWidget(lcd, windScreen, 1);
                windScreenWindSpeed     = new StringWidget(lcd, windScreen, 2);                 
                windScreenWindDirection = new StringWidget(lcd, windScreen, 3);                 

                // Outside Screen
                outsideScreen = new Screen(lcd, "OutsideScreen");       
                outsideScreen.setHeartbeat(lcd, true);
                outsideScreen.setDuration(lcd, 64);        // in eighths of a second
                outsideScreen.setPriority(lcd, "foreground");
                outsideScreenLine1          = new StringWidget(lcd, outsideScreen, 1);
                outsideScreenTemperature    = new StringWidget(lcd, outsideScreen, 2);                 
                outsideScreenHumidity       = new StringWidget(lcd, outsideScreen, 3);          
                outsideScreenPressure       = new StringWidget(lcd, outsideScreen, 4);          

                refreshWindScreen();
                refreshOutsideScreen();        
                
            } catch (IOException ex) {
                System.out.println("\n>>>>> Problem with LCD Connection <<<<<\n");
            }

        }

    }

    public void refreshWindScreen() {
        try {
            Date d = new Date();
            windScreenLine1.setTextXY(lcd, Util.getTimeHuman(d) + " Wind", 1, 1);
            windScreenWindSpeed.setTextXY(lcd,      "    Speed: " + String.valueOf(Util.roundToDecimals(windSpeed, 1)), 1, 2);
            windScreenWindDirection.setTextXY(lcd,  "Direction: " + String.valueOf(Util.roundToDecimals(windDirection,1)), 1, 3);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void refreshOutsideScreen() {
        try {
            Date d = new Date();
            outsideScreenLine1.setTextXY(lcd, Util.getTimeHuman(d) + " Outside", 1, 1);
            outsideScreenTemperature.setTextXY(lcd, "Temperature: " + String.valueOf(Util.roundToDecimals(outsideTemperature, 1)), 1, 2);
            outsideScreenHumidity.setTextXY(lcd,    "   Humidity: " + String.valueOf(Util.roundToDecimals(outsideHumidity, 1)), 1, 3);
            outsideScreenPressure.setTextXY(lcd,    "   Pressure: " + String.valueOf(Util.roundToDecimals(outsidePressure, 1)), 1, 4);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public void setOutsideTemperature(double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public void setOutsideHumidity(double outsideHumidity) {
        this.outsideHumidity = outsideHumidity;
    }

    public void setOutsidePressure(double outsidePressure) {
        this.outsidePressure = outsidePressure;
    }
    

}
