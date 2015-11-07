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
public class RainSensor extends Sensor{
    private FileConnection fileConnection;  // Current Reading
    private ReadingLong currentRainCount;
    
    public RainSensor(String name, String path, String interfaceCode) throws IOException {
        this.name = name;
        this.interfaceCode = interfaceCode;
        this.fileConnection = (FileConnection)Connector.open(path);
    }
    
    public void read() {
        String readString = readFileConnectionString(fileConnection).trim();
        this.currentRainCount = new ReadingLong();
        this.currentRainCount.setValueLong(Long.parseLong(readString));
        this.currentRainCount.setDate(new Date());
        this.currentRainCount.setValid(true);
    }
    
    public ReadingLong getLastReading() {
        return this.currentRainCount;
    }
    
    public String getLastReadingValue() {
        return this.currentRainCount.getAsString();
    }
}
