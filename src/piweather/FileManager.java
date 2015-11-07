/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piweather;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Enumeration;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;

/**
 *
 * @author Dave
 */
public class FileManager {
    private PrintStream printStream = null;
    private OutputStream outputStream = null;
    private FileConnection fcDataOutput = null;
    private long saveCount = 0L;
    private String fileNameCurrent = null;
    
    public void cleanUp() throws IOException {
        String fileRoot = Constants.fileRoot;
        String oldFilename;
        String newFilename;
        
        FileConnection fc = (FileConnection)Connector.open(fileRoot, Connector.READ);
        Enumeration filelist = fc.list("Data*.current", true);
        while(filelist.hasMoreElements()) {
            oldFilename = (String) filelist.nextElement();
            newFilename = oldFilename.substring(0, oldFilename.indexOf(".current")) + ".archive";
            System.out.println("Renaming " + oldFilename + " to " + newFilename);
            fc = (FileConnection)Connector.open(fileRoot + "/" + oldFilename, Connector.READ_WRITE);
            if (fc.isOpen()) {
                fc.rename(newFilename);
            }
        }   
        fc.close();
    }
    
    
    public void newFile() throws IOException {
        saveCount = 0L;
        String fileRoot = Constants.fileRoot;
        fileNameCurrent = "Data" + Util.getDateTimeFormatted(new Date());
        
        String outputFile = fileRoot + "/" + fileNameCurrent + Constants.DATA_FILE_EXT_CURRENT;
        System.out.println("FileManager.newFile: Writing Data to: " + outputFile);
        
        fcDataOutput = (FileConnection)Connector.open(outputFile, Connector.READ_WRITE );
        if (! fcDataOutput.exists()) {
            fcDataOutput.create();
        }
        outputStream = fcDataOutput.openOutputStream();
        printStream = new PrintStream(outputStream);
    }
    
    public void closeFile() throws IOException {
        String newFilename = fileNameCurrent + Constants.DATA_FILE_EXT_ARCHIVED;
        System.out.println("FileManager.closeFile: Renaming to: " + newFilename);
        outputStream.flush();
        outputStream.close();
        fcDataOutput.rename(newFilename);
        fcDataOutput.close();
    }
 
    public void nextFile() throws IOException {
        closeFile();
        newFile();
    }
    
    
    public void saveReading(String readingData) {
        printStream.println(readingData);
        saveCount++;
    }

    public long getSaveCount() {
        return saveCount;
    }
    

}
