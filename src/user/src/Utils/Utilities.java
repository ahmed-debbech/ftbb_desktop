/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.Utils;

import user.src.Utils.SqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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
    
    public static boolean validationEmail (String email){
      boolean a=false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        a=pat.matcher(email).matches();
          
        
        return a;
        
    }
}
