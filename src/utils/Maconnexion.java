/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Lenovo
 */
public class Maconnexion {
  final static String URL = "jdbc:mysql://127.0.0.1:3306/ftbb";
  final static String LOGIN ="root";
  final static String PWD = "";
  static Maconnexion instance=null; 
  private Connection cnx;
  
  public Maconnexion (){
      
      try {
          cnx = DriverManager.getConnection(URL,LOGIN,PWD);
          System.out.println("connexion etablie");
      } catch (SQLException ex) {
          System.out.println("pas de connexion");
      }
  }
  public static Maconnexion getInstance() {
      if (instance ==null){
          instance = new Maconnexion ();
      }
      
      return instance;
  }
  
  public Connection getConnection (){
 
      return cnx;
   }
       
}
