package piweather;

public class Constants {
    
    // Output Data File parameters
    public static final String fileRoot = "file:///rootfs/1wire/OWFSDATA";
    
    
    // Sensors
    public static final String testTemperatureSensor               = "file:///OWFS/uncached/28.0E6DB9010000";
    public static final String insideTemperaturePressureSensor     = "file:///OWFS/uncached/12.BA225F000000";
    public static final String outsideTemperatureHumiditySensor    = "file:///OWFS/uncached/26.6DDCCF000000";
    public static final String greenhouseTemperatureHumiditySensor = "file:///OWFS/uncached/26.F48817010000";
    public static final String garagePowerTemperatureSensor        = "file:///OWFS/uncached/26.48B780000000";
    public static final String rainSensor                          = "file:///OWFS/uncached/1D.310A09000000";
    public static final String windSpeedSensor                     = "file:///OWFS/uncached/1D.8CBC0D000000";
    public static final String windDirectionSensor                 = "file:///OWFS/uncached/26.8471E7000000";

    // Paths to values on individual Sensors
    public static final String testTemperaturePath       = testTemperatureSensor + "/temperature";    
    public static final String insideTemperaturePath     = insideTemperaturePressureSensor + "/TAI8570/temperature";
    public static final String insidePressurePath        = insideTemperaturePressureSensor + "/TAI8570/pressure";  
    public static final String outsideTemperaturePath    = outsideTemperatureHumiditySensor + "/temperature";
    public static final String outsideHumidityPath       = outsideTemperatureHumiditySensor + "/humidity";
    public static final String greenhouseTemperaturePath = greenhouseTemperatureHumiditySensor + "/temperature";
    public static final String greenhouseHumidityPath    = greenhouseTemperatureHumiditySensor + "/humidity";
    public static final String garageTemperaturePath     = garagePowerTemperatureSensor + "/temperature";
    public static final String garagePowerPath           = garagePowerTemperatureSensor;
    public static final String rainSensorCountPath       = rainSensor + "/counters.A";
    public static final String windSpeedCountPath        = windSpeedSensor + "/counters.A";
    public static final String windDirectionPath         = windDirectionSensor;

    public static final String testTemperatureCode       = "TEMPERATURE_TEST";
    public static final String insideTemperatureCode     = "TEMPERATURE_INSIDE";
    public static final String insidePressureCode        = "AIR_PRESSURE";
    public static final String outsideTemperatureCode    = "TEMPERATURE_OUTSIDE";
    public static final String outsideHumidityCode       = "HUMIDITY_OUTSIDE";
    public static final String greenhouseTemperatureCode = "TEMPERATURE_GREENHOUSE";
    public static final String greenhouseHumidityCode    = "HUMIDITY_GREENHOUSE";
    public static final String garageTemperatureCode     = "TEMPERATURE_GARAGE";
    public static final String garagePowerCode           = "MAINS_POWER";
    public static final String rainSensorCountCode       = "RAIN_COUNT";
    public static final String windSpeedCountCode        = "WINDSPEED_COUNT";
    public static final String windDirectionCode         = "WIND_DIRECTION";

    public static final int    LOOPDELAYMILLIS             = 1000;
    public static final int    READBYTES                   = 100;
    public static final double MAINS_VOLTAGE               = 230.0D; 
    public static final long   SAVECOUNTLIMIT              = 100L;
    
//    public static final double WIND_SPEED_MPH_FACTOR       = 2.453D; 
//    public static final double WIND_DIR_TOLERANCE          = 0.6D;  
//    public static final double MPH_TO_KPH_FACTOR           = 1.609344D;
//    public static final double WINDCHILL_MIN_WIND_SPEED    = 3.0D;
//    public static final double WINDCHILL_MAX_TEMPERATURE   = 10.0D;

    public static final String DATA_SEPERATOR = "|";
    public static final String DATA_FILE_EXT_CURRENT  = ".current";
    public static final String DATA_FILE_EXT_ARCHIVED = ".archive";
    
    // For LCD
    public static final String  LCD_HOSTNAME    =   "127.0.0.1";
    public static final String  LCD_PORT        =   "13666";
    
}


//drwxrwxrwx 1 root root  8 Aug  4 13:25 12.87185F000000 TAI8570_SN (Writer)
//drwxrwxrwx 1 root root  8 Aug  4 13:25 12.BA225F000000 TAI8570_SN (Reader)
//drwxrwxrwx 1 root root  8 Aug  4 13:25 1D.310A09000000 RAIN_COUNT_SN
//drwxrwxrwx 1 root root  8 Aug  4 13:25 1D.8CBC0D000000 WIND_SPEED_SN
//drwxrwxrwx 1 root root  8 Aug  4 13:25 26.48B780000000 CURRENT_SN
//drwxrwxrwx 1 root root  8 Aug  4 13:25 26.6DDCCF000000 HUMIDITY_SN
//drwxrwxrwx 1 root root  8 Aug  4 13:25 26.8471E7000000 WIND_DIR_SN
//drwxrwxrwx 1 root root  8 Aug  4 13:25 26.F48817010000 GREENHOUSE_SN
//drwxrwxrwx 1 root root  8 Aug  4 13:25 28.0E6DB9010000 TEST_TEMP_SN
// 29.F4360C000000 DS2408