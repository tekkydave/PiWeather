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
public class WindSpeedSensor extends Sensor{
    private FileConnection fileConnection;
    private ReadingLong currentWindSpeedCount;  // Current Reading
    private ReadingLong previousWindSpeedCount; // Previous Reading for LCD calculations

    
    public WindSpeedSensor(String name, String path, String interfaceCode) throws IOException {
        this.name = name;
        this.interfaceCode = interfaceCode;
        this.fileConnection = (FileConnection)Connector.open(path);
    }
    
    public void read() {
        String readString = readFileConnectionString(fileConnection).trim();
        this.currentWindSpeedCount = new ReadingLong();
        this.currentWindSpeedCount.setValueLong(Long.parseLong(readString));
        this.currentWindSpeedCount.setDate(new Date());
        this.currentWindSpeedCount.setValid(true);
    }
    
    public ReadingLong getLastReading() {
        return this.currentWindSpeedCount;
    }
    
    public String getLastReadingValue() {
        return this.currentWindSpeedCount.getAsString();
    }
    
    public ReadingDouble calculateWindSpeed()
    {
      ReadingDouble r = new ReadingDouble();

      if (currentWindSpeedCount.isValid()) {
          if (previousWindSpeedCount != null)
          {
              if (previousWindSpeedCount.isValid())
              {
                  double currCount = (double)currentWindSpeedCount.getValueLong();
                  double prevCount = (double)previousWindSpeedCount.getValueLong();
                  double currTime  = (double)(currentWindSpeedCount.getDate().getTime() / 1000);
                  double prevTime  = (double)(previousWindSpeedCount.getDate().getTime() / 1000);
                  double speed = (double)(2.5D * ((currCount - prevCount) / (currTime - prevTime)));
                  
                  r.setValueDouble(speed);
                  r.setDate(currentWindSpeedCount.getDate());
                  r.setValid(true);
              }
          }

          previousWindSpeedCount = currentWindSpeedCount;
      }

      return r;
    }

}
