 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Cartman
 */
public class DbUtil {

    private Properties properties;
    
    public DbUtil() throws Exception {
        properties=new Properties();
        properties.load(new FileInputStream("db.properties"));
    }
    
    public String getURL(){
        String currentDB=properties.getProperty("current_db");
        return properties.getProperty(currentDB+"_"+"url");
    }
    
    public String getUser(){
        String currentDB=properties.getProperty("current_db");
        return properties.getProperty(currentDB+"_"+"user");
    }
    
    public String getPassword(){
        String currentDB=properties.getProperty("current_db");
        return properties.getProperty(currentDB+"_"+"password");
    }
    
}
