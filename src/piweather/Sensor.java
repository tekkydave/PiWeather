/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piweather;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author Dave
 */
public class Sensor {
    String name;
    String interfaceCode;
    String readValue = null;

    String readFileConnectionString(FileConnection fileConnection) {
        try {
            InputStream inputStream = null;
            byte inputData[]        = new byte[Constants.READBYTES];
            
            inputStream   = fileConnection.openInputStream();
            int readChars = inputStream.read(inputData, 0, Constants.READBYTES);
            if (readChars > 0) {
                readValue = new String(inputData, 0, readChars);
             }
            inputStream.close();

            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return readValue;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode;
    }

}
