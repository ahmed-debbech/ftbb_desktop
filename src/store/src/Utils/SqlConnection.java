/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.src.Utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class SqlConnection {
    final static String URL ="jdbc:mysql://127.0.0.1:3306/ftbb-integ";
    final static String LOGIN ="root";
    final static String PWD ="";
    static SqlConnection instance =null;


    private Connection cnx;
    
    private SqlConnection(){
        try {
            cnx=(Connection) DriverManager.getConnection(URL, LOGIN, PWD);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println("pas de connexion");
        }
    }
    public static SqlConnection getInstance(){
        if(instance==null)
        {
            instance=new SqlConnection();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
}
