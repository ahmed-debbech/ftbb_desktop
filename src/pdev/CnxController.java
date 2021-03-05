/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdev;

import Entities.Admin;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Yassine
 */
public class CnxController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpwd;
    @FXML
    private Button Blogin;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void Login(ActionEvent event) throws IOException {
        
        Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
