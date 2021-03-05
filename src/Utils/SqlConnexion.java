/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narug
 */
public class SqlConnexion {
    final static String URL="jdbc:mysql://127.0.0.1:3306/ftbb";
    final static String LOGIN="root";
    final static String PWD="";
    static SqlConnexion instance =null;
    private Connection cnx;
    
    private SqlConnexion(){
        try{
            cnx=DriverManager.getConnection(URL,LOGIN,PWD);
            System.out.println("Connected successfuly !");
        }catch(SQLException ex){
            System.out.println("No connexion !");
        }
    }
    
    public static SqlConnexion getInstance(){
        if(instance==null){
        
            instance=new SqlConnexion();
        }
        
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
}
