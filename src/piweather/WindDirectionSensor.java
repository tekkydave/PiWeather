/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piweather;

import java.io.IOException;
import java.util.Date;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author Dave
 */
public class WindDirectionSensor extends Sensor{
    private FileConnection fileConnectionVAD;
    private FileConnection fileConnectionVDD;
    private ReadingDouble reading;
    
    public WindDirectionSensor(String name, String path, String interfaceCode) throws IOException {
        this.name = name;
        this.interfaceCode = interfaceCode;
        this.fileConnectionVAD = (FileConnection)Connector.open(path + "/VAD");
        this.fileConnectionVDD = (FileConnection)Connector.open(path + "/VDD");
        this.reading = new ReadingDouble();
    }
    
    public void read(){
        String readStringVAD = readFileConnectionString(fileConnectionVAD).trim();
        String readStringVDD = readFileConnectionString(fileConnectionVDD).trim();
        
        double direction    = 0.0D;
        double NORTH_OFFSET = 0.811359D;
        double GAIN         = 1.0D;

        double vad = Double.parseDouble(readStringVAD);
        double vdd = Double.parseDouble(readStringVDD);

        direction = (400 * (vad - (0.05 * vdd))) / vdd;
        direction = (direction * GAIN) + NORTH_OFFSET;
        
        this.reading.setValueDouble(direction);
        this.reading.setDate(new Date());
        this.reading.setValid(true);
    }
    
    public ReadingDouble getLastReading() {
        return this.reading;
    }
    
    public String getLastReadingValue() {
        return this.reading.getAsString();
    }
}
