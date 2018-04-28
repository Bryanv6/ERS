package util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by bryanvillegas on 4/25/18.
 */
public class ConnectionUtil {
    private static ConnectionUtil cu = null;
    private static Properties prop = new Properties();
    private ConnectionUtil(){
        super();
        InputStream dbProps = ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try{
            prop.load(dbProps);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ConnectionUtil getInstance() {
        if(cu==null)
            cu=new ConnectionUtil();
        return cu;
    }
    public Connection getConnection(){
        Connection conn = null;
        try {
            // We have to register the driver class
            Class.forName(prop.getProperty("driver"));
            conn = DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("username"),
                    prop.getProperty("password"));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return conn;

    }
}
