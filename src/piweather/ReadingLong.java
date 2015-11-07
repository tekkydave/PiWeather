/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piweather;

/**
 *
 * @author Dave
 */
public class ReadingLong extends Reading{
    private long value;

    public long getValueLong() {
        return value;
    }

    public void setValueLong(long valueLong) {
        this.value = valueLong;
    }

    public String getAsString() {
        return String.valueOf(value);
    }    

    public void print(String name) {
        System.out.println(Util.getDateTimeFormatted(this.getDate())
                           + " " + Util.getSecondsSinceEpoch(this.getDate())
                           + " : "
                           + name + " = " + this.getAsString()
                           + " (" + this.getValid() + ")");
    }

    public void save(FileManager fileManager, String name) {
        String data =                              Util.getDateTimeFormatted(this.getDate())
                      + Constants.DATA_SEPERATOR + Util.getSecondsSinceEpoch(this.getDate())
                      + Constants.DATA_SEPERATOR + name 
                      + Constants.DATA_SEPERATOR + this.getAsString()
                      + Constants.DATA_SEPERATOR + this.getValid()
                      ;
        
        fileManager.saveReading(data);
    }

}
