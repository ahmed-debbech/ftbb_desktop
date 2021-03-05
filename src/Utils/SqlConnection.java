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
 * @author Yassine
 */
public class SqlConnection {

    final static String URL = "jdbc:mysql://127.0.0.1:3306/ftbb";
    final static String login = "root";
    final static String pwd = "";
    static SqlConnection instance = null;
    private Connection cnx;

    private SqlConnection() {
        try {
            cnx = DriverManager.getConnection(URL, login, pwd);
            System.out.println("Connexion etablie ");
        } catch (SQLException ex) {
            System.out.println("Connexion failed  ");
        }

    }

    public static SqlConnection getInstance() {
        if (instance == null) {
            instance = new SqlConnection();
        }
        return instance;
    }
    public Connection getConnection () {
        return cnx;
    }
}
