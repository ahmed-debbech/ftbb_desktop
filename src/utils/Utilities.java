/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Ahmed
 */
/**
 * a function that generates an ID of 8 numbers based on your table and the id attribute 
 * ver: 0.1
 * @author Ahmed
 */
public class Utilities {
    public static int generatedId(String table_name, String attribute_name){
        Connection cnx = SqlConnection.getInstance().getConnection();
        int generated = 0;
        try {
            ResultSet rst;
            do{
                Statement stm = cnx.createStatement();
                Random rand = new Random();
                generated = rand.nextInt(99999999); 
                //System.out.println("[Utlities.generatedId()] the random id is " + generated);
                String query = "select * from `"+table_name+"` where "+attribute_name+" = " + generated + ";";
                rst = stm.executeQuery(query);
            }while(rst.next());
            
        } catch (SQLException ex) {
            System.out.println("Could not generate an id due to the loss of connection.");
            generated = -1;
        }
        return generated;
    }
    public static void alert(String title, String context){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle(title);
           alert.setHeaderText(null);
           alert.setContentText(context);
           Optional<ButtonType> result = alert.showAndWait();
    }
    public static boolean alertConfirmation(String title, String context){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle(title);
           alert.setHeaderText(null);
           alert.setContentText(context);
           Optional<ButtonType> result = alert.showAndWait();
           if(result.isPresent() && result.get() == ButtonType.OK){
               return true;
           }
           return false;
    }
    public static String pathToUrl(String str){
            Path p = Paths.get(str);
            String file = p.getFileName().toString();
            String s=(new StringBuilder()).append("http://127.0.0.1/uploads/").append(file).toString();  
            return s;
    }
    public static String timestampConverter(Timestamp ts){
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm").format(ts);
        return timeStamp;
    }
}
