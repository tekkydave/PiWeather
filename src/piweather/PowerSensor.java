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
public class PowerSensor extends Sensor{
    private FileConnection fileConnectionVAD;
    private FileConnection fileConnectionVDD;
    private ReadingDouble reading;
    
    public PowerSensor(String name, String path, String interfaceCode) throws IOException {
        this.name = name;
        this.interfaceCode = interfaceCode;
        this.fileConnectionVAD = (FileConnection)Connector.open(path + "/VAD");
        this.fileConnectionVDD = (FileConnection)Connector.open(path + "/VDD");
        this.reading = new ReadingDouble();
    }
    
    public void read() {
        String readStringVAD = readFileConnectionString(fileConnectionVAD).trim();
        String readStringVDD = readFileConnectionString(fileConnectionVDD).trim();
        
        double power = 0.0D;
        double vad = Double.parseDouble(readStringVAD);
        double vdd = Double.parseDouble(readStringVDD);
        
    	if (vad >= 0.2 && vad <= 3.78) {
    		power = (((vad - 0.2D) * 5.3073D) + 1.0D ) * Constants.MAINS_VOLTAGE;
    	}
        this.reading.setValueDouble(power);
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
