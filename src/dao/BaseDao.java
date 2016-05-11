package dao;

import driver.ConfigProperties;
import org.apache.log4j.*;
import java.sql.*;
import java.util.Set;


public abstract class BaseDao implements ICRUDable
{
    private ConfigProperties configProperties;

    private final String DB_DRIVER_KEY = "DB_DRIVER";
    private final String DB_CONNECTION_KEY = "DB_CONNECTION";
    private final String DB_USER_KEY = "DB_USER";
    private final String DB_PASSWORD_KEY = "DB_PASSWORD";

    private String DB_DRIVER;
    private String DB_CONNECTION;
    private String DB_USER;
    private String DB_PASSWORD;

    protected Connection conn;
    protected Statement stmt;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public BaseDao ()
    {
        Logger.getLogger(this.getClass()).debug("Inside BaseDao constructor");

        try
        {
            configProperties = new ConfigProperties();
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
        }

        Set<String> propertyKeys = configProperties.getKeys();

        DB_DRIVER = configProperties.getPropertyKeys(DB_DRIVER_KEY);
        DB_CONNECTION = configProperties.getPropertyKeys(DB_CONNECTION_KEY);
        DB_USER = configProperties.getPropertyKeys(DB_USER_KEY);
        DB_PASSWORD = configProperties.getPropertyKeys(DB_PASSWORD_KEY);

        Logger.getLogger(this.getClass()).debug(String.format("KEY: %s PROPERTY: %s", DB_DRIVER_KEY, DB_DRIVER));
        Logger.getLogger(this.getClass()).debug(String.format("KEY: %s PROPERTY: %s", DB_CONNECTION_KEY, DB_CONNECTION));
        Logger.getLogger(this.getClass()).debug(String.format("KEY: %s PROPERTY: %s", DB_USER_KEY, DB_USER));
        Logger.getLogger(this.getClass()).debug(String.format("KEY: %s PROPERTY: %s", DB_PASSWORD_KEY, DB_PASSWORD));

        createDBConnection();
    }

    private void createDBConnection()
    {
        Logger.getLogger(this.getClass()).debug("Inside DB Connection");

        conn = null;

        try
        {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            Logger.getLogger(this.getClass()).debug("DB connection Successful");
        }
        catch (Exception ex)
        {
            Logger.getLogger(this.getClass()).error(ex.toString());
        }
    }
}
