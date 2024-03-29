/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.SqlConnection;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
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
       Stage window = new Stage();
       
       window.initModality(Modality.APPLICATION_MODAL);
       window.setTitle(title);
       window.setMinWidth(260);
       
       Label label = new Label();
       label.setText(context);
       Button closeButton = new Button("OK");
       closeButton.setOnAction(e -> window.close());
       closeButton.setStyle("-fx-background-radius: 5em; -fx-background-color: da4646;");
     
       closeButton.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
       
       
        VBox layout =new VBox(12);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

   }

}