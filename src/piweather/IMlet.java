package piweather;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.midlet.*;
import piweather.Constants;
import piweather.Reading;
import piweather.Util;

/**
 * @author Dave
 */
public class IMlet extends MIDlet {

    // Sensors
    TemperatureSensor       testTemperature;
    TemperatureSensor       insideTemperature;
    TemperatureSensor       outsideTemperature;
    TemperatureSensor       greenhouseTemperature;
    TemperatureSensor       garageTemperature;
    HumiditySensor          outsideHumidity;
    HumiditySensor          greenhouseHumidity;
    PressureSensor          airPressure;
    PowerSensor             garagePower;
    WindDirectionSensor     windDirection;
    RainSensor              rainCount;
    WindSpeedSensor         windSpeedCount;
    
    // File Manager
    FileManager             fileManager;
    LCDManager              lcdManager;
    
    // For LCD
    ReadingDouble windSpeed;
    
    long event_time;
    long saveCount;

    public IMlet() {
        
        // Create the Sensors
        try {
            this.testTemperature       = new TemperatureSensor("Test Temperature",Constants.testTemperaturePath,Constants.testTemperatureCode);
            this.insideTemperature     = new TemperatureSensor("Inside Temperature",Constants.insideTemperaturePath,Constants.insideTemperatureCode);
            this.airPressure           = new PressureSensor("Air Pressure",Constants.insidePressurePath,Constants.insidePressureCode);
            this.outsideTemperature    = new TemperatureSensor("Outside Temperature",Constants.outsideTemperaturePath,Constants.outsideTemperatureCode);
            this.outsideHumidity       = new HumiditySensor("Outside Humidity",Constants.outsideHumidityPath,Constants.outsideHumidityCode);
            this.greenhouseTemperature = new TemperatureSensor("Greenhouse Temperature",Constants.greenhouseTemperaturePath,Constants.greenhouseTemperatureCode);
            this.greenhouseHumidity    = new HumiditySensor("Greenhouse Humidity",Constants.greenhouseHumidityPath,Constants.greenhouseHumidityCode);
            this.garageTemperature     = new TemperatureSensor("Garage Temperature",Constants.garageTemperaturePath,Constants.garageTemperatureCode);
            this.garagePower           = new PowerSensor("Mains Power",Constants.garagePowerPath,Constants.garagePowerCode);
            this.rainCount             = new RainSensor("Rain Count",Constants.rainSensorCountPath,Constants.rainSensorCountCode);
            this.windSpeedCount        = new WindSpeedSensor("Wind Speed Count",Constants.windSpeedCountPath,Constants.windSpeedCountCode);
            this.windDirection         = new WindDirectionSensor("Wind Direction",Constants.windDirectionPath,Constants.windDirectionCode);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        // Create the File Manager
        fileManager = new FileManager();
        
        // Create the LCD Manager
        lcdManager = new LCDManager();
        
    }
    

    public void startApp() {
        
        try {
            fileManager.cleanUp();      // rename any existing .current data files to .archive
            fileManager.newFile();      // create new .current data file
        } catch (IOException ex) {
            ex.printStackTrace();
        }        
            
        // ============   MAIN LOOP Start ================

        while (true) {
            event_time = System.currentTimeMillis() / 1000;
            System.out.print(event_time % 60 + " ");
            if (event_time % 60 == 0) {						// Every 60s on the dot
                System.out.println("\n--------------- Min + 00s -----------------------------------------------------------------------------------------------");
                    doWindDirection();
                    doWindSpeed();
                    lcdManager.refreshWindScreen();

                    doTestTemperature();
                    doOutsideTemperature();
                    doOutsideHumidity();
                    lcdManager.refreshOutsideScreen();
                
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            } else if ((event_time - 15) % 60 == 0) {				// Every 60s at 15 past
                System.out.println("\n--------------- Min + 15s -----------------------------------------------------------------------------------------------");
                    doWindDirection();
                    doWindSpeed();
                    lcdManager.refreshWindScreen();

                    doGreenhouseTemperature();
                    doGreenhouseHumidity();
                
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            } else if ((event_time - 30) % 60 == 0) {				// Every 60s at 30 past
                System.out.println("\n--------------- Min + 30s -----------------------------------------------------------------------------------------------");
                    doWindDirection();
                    doWindSpeed();
                    lcdManager.refreshWindScreen();

                    doRain();
                    doGarageTemperature();
                    doMainsPower();
                
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            } else if ((event_time - 45) % 60 == 0) {				// Every 60s at 45 past
                System.out.println("\n--------------- Min + 45s -----------------------------------------------------------------------------------------------");
                    doWindDirection();
                    doWindSpeed();
                    lcdManager.refreshWindScreen();

                    doInsideTemperature();
                    doAirPressure();
                
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            }
            
            // If save count exceeded switch files
            saveCount = fileManager.getSaveCount();
            if (  saveCount > Constants.SAVECOUNTLIMIT) {
                try {
                    System.out.println(" >>>>>>> Save Count Limit Exceeded - Switching Data Files");
                    fileManager.nextFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            Util.delayMillis(Constants.LOOPDELAYMILLIS);
        } // while(true)
        // ============   MAIN LOOP Finish ================
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        try {
            fileManager.closeFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void doWindSpeed() {
        windSpeedCount.read();          
        windSpeedCount.getLastReading().print(windSpeedCount.getInterfaceCode());
        windSpeedCount.getLastReading().save(fileManager,windSpeedCount.getInterfaceCode());
        //----- For LCD ------------------------
        windSpeed = windSpeedCount.calculateWindSpeed();
        System.out.println("Wind Speed = " + windSpeed.getAsString());
        lcdManager.setWindSpeed(windSpeed.getValueDouble());
        //--------------------------------------
    }

    private void doWindDirection() {
        windDirection.read();           
        windDirection.getLastReading().print(windDirection.getInterfaceCode());
        windDirection.getLastReading().save(fileManager,windDirection.getInterfaceCode());
        //----- For LCD ------------------------
        lcdManager.setWindDirection(windDirection.getLastReading().getValueDouble());
        //--------------------------------------
    }

    private void doTestTemperature() {
        testTemperature.read();         
        testTemperature.getLastReading().print(testTemperature.getInterfaceCode());
        testTemperature.getLastReading().save(fileManager,testTemperature.getInterfaceCode());
    }

    private void doOutsideTemperature() {
        outsideTemperature.read();      
        outsideTemperature.getLastReading().print(outsideTemperature.getInterfaceCode());
        outsideTemperature.getLastReading().save(fileManager,outsideTemperature.getInterfaceCode());
        //----- For LCD ------------------------
        lcdManager.setOutsideTemperature(outsideTemperature.getLastReading().getValueDouble());
        //--------------------------------------
    }

    private void doOutsideHumidity() {
        outsideHumidity.read();         
        outsideHumidity.getLastReading().print(outsideHumidity.getInterfaceCode());
        outsideHumidity.getLastReading().save(fileManager,outsideHumidity.getInterfaceCode());
        //----- For LCD ------------------------
        lcdManager.setOutsideHumidity(outsideHumidity.getLastReading().getValueDouble());
        //--------------------------------------
    }

    private void doGreenhouseTemperature() {
        greenhouseTemperature.read();   
        greenhouseTemperature.getLastReading().print(greenhouseTemperature.getInterfaceCode());
        greenhouseTemperature.getLastReading().save(fileManager,greenhouseTemperature.getInterfaceCode());
    }

    private void doGreenhouseHumidity() {
        greenhouseHumidity.read();      
        greenhouseHumidity.getLastReading().print(greenhouseHumidity.getInterfaceCode());
        greenhouseHumidity.getLastReading().save(fileManager,greenhouseHumidity.getInterfaceCode());
    }

    private void doRain() {
        rainCount.read();               
        rainCount.getLastReading().print(rainCount.getInterfaceCode());
        rainCount.getLastReading().save(fileManager,rainCount.getInterfaceCode());
    }

    private void doGarageTemperature() {
        garageTemperature.read();       
        garageTemperature.getLastReading().print(garageTemperature.getInterfaceCode());
        garageTemperature.getLastReading().save(fileManager,garageTemperature.getInterfaceCode());
    }

    private void doMainsPower() {
        garagePower.read();             
        garagePower.getLastReading().print(garagePower.getInterfaceCode());
        garagePower.getLastReading().save(fileManager,garagePower.getInterfaceCode());
    }

    private void doInsideTemperature() {
        insideTemperature.read();       
        insideTemperature.getLastReading().print(insideTemperature.getInterfaceCode());
        insideTemperature.getLastReading().save(fileManager,insideTemperature.getInterfaceCode());
    }

    private void doAirPressure() {
        airPressure.read();             
        airPressure.getLastReading().print(airPressure.getInterfaceCode());
        airPressure.getLastReading().save(fileManager,airPressure.getInterfaceCode());
        //----- For LCD ------------------------
        lcdManager.setOutsidePressure(airPressure.getLastReading().getValueDouble());
        //--------------------------------------
    }

}
