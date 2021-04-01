/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ahmed
 */
public class SqlConnection{
    final static String URL="jdbc:mysql://127.0.0.1:3306/fttb";
    //mysql://127.0.0.1:3306  url of phpmyadmin
    // name of database
    final static String LOGIN ="root";
    final static String PWD="";
    static SqlConnection instance=null;
    private  Connection cnx;

    private SqlConnection(){
        try{
            cnx = (Connection) DriverManager.getConnection(URL,LOGIN,PWD);
            System.out.println("Connection established");
        }catch(SQLException ex){
            System.out.println("no conncetion");
        }
    }

    public static SqlConnection getInstance(){
        if(instance == null){
              instance = new SqlConnection();
        }
        return instance;
    }
    public Connection getConnection(){
        return cnx;
    }
}