/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatnt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class DBHelpers implements Serializable{
    public static Connection makeConnection() 
            throws NamingException, SQLException{
//        //1. Load Drivers
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connecttion String
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1601";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url,"sa","12345");
//        return con;
        //1. get current system file
            Context context = new InitialContext();
        //2. get container context
            Context tomcatContext = (Context) context.lookup("java:comp/env");
        //3. get Datasource from container
            DataSource ds = (DataSource) tomcatContext.lookup("DungHocGioi");
        //4. get connection
            Connection con = ds.getConnection();
            
            return con;
    }
    
    public static Properties getSiteMaps(String siteMapFile, ServletContext context)
            throws IOException {
        if(siteMapFile == null){
            return null;
        }
        if(siteMapFile.trim().isEmpty()){
            return null;
        }
        Properties result = new Properties();
        InputStream is = null;
        try{
            is = context.getResourceAsStream(siteMapFile);
            result.load(is);
            
            return result;
        }finally{
           if(is != null){
               is.close();
           }
        }
    }
}
