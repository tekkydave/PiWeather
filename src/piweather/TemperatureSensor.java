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
public class TemperatureSensor extends Sensor{
    private FileConnection fileConnection;
    private ReadingDouble reading;
    
    public TemperatureSensor(String name, String path, String interfaceCode) throws IOException {
        this.name = name;
        this.interfaceCode = interfaceCode;
        this.fileConnection = (FileConnection)Connector.open(path);
        this.reading = new ReadingDouble();
    }
    
    public void read() {
        String readString = readFileConnectionString(fileConnection).trim();
        this.reading.setValueDouble(Double.parseDouble(readString));
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
