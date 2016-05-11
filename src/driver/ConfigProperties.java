package driver;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

public class ConfigProperties
{
    private Properties properties;

    private Set<String> propertyKeys;
    private Enumeration propertyKeys1;

    private String propertyFileName = "config.properties";

    public ConfigProperties() throws FileNotFoundException
    {
        InputStream inputStream;
        properties = new Properties();
        try
        {
            File configFile;
            configFile = new File(propertyFileName);
            inputStream = new FileInputStream(configFile);
            Logger.getLogger(this.getClass()).debug(String.format("config file path: %s", configFile.getAbsolutePath()));
        }
        catch (FileNotFoundException fnfex)
        {
            displayStackTraceErrors(fnfex);
            Logger.getLogger(this.getClass()).error(fnfex.toString());
            Logger.getLogger(this.getClass()).error("dao.ConfigProperties technique #1 failed");
            inputStream = null;
        }
        catch (Exception ex)
        {
            displayStackTraceErrors(ex);
            Logger.getLogger(this.getClass()).error(ex.toString());
            inputStream = null;
        }

        try
        {
            if (inputStream == null)
            {
                inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
            }
            properties.load(inputStream);
            propertyKeys = properties.stringPropertyNames();
            propertyKeys1 = properties.propertyNames();

            displayProperties();
        }
        catch (NullPointerException npex)
        {
            displayStackTraceErrors(npex);
            Logger.getLogger(this.getClass()).error(npex.toString());
            Logger.getLogger(this.getClass()).error("dao.ConfigProperties technique #2 failed");
            throw new FileNotFoundException("application config file does not exist");
        }
        catch (IOException ioex)
        {
            displayStackTraceErrors(ioex);
            Logger.getLogger(this.getClass()).error(ioex.toString());
            throw new FileNotFoundException("application config file does not exist");
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
            displayStackTraceErrors(ex);
            throw new FileNotFoundException("application config file does not exist");
        }
    }

    public Set<String> getKeys()
    {
        return propertyKeys;
    }

    public String getPropertyKeys(String key)
    {
        return properties.getProperty(key);
    }

    private void displayProperties()
    {
        Logger.getLogger(this.getClass()).debug("PROPERTIES / VALUES:");
        for (String key : propertyKeys)
        {
            Logger.getLogger(this.getClass()).debug(String.format("KEY: %s  :  PROPERTY: %s", key, properties.getProperty(key)));
        }
    }

    private void displayStackTraceErrors(Exception ex)
    {
        StackTraceElement[] arrStack = ex.getStackTrace();
        for (StackTraceElement elm : arrStack)
        {
            Logger.getLogger(this.getClass()).error(String.format(". . . .  %s", elm));
        }
    }
}
