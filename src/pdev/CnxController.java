/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdev;

import Entities.Admin;
import Entities.Client;
import Service.ServiceAdmin;
import Service.ServiceClient;
import Service.ServiceCnx;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    @FXML
    private Button BInsc;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void Login(ActionEvent event) throws IOException {
       ServiceAdmin sa =new ServiceAdmin();
       ServiceClient scl =new ServiceClient();
        ServiceCnx sc =new ServiceCnx();
       Admin a= new Admin ();
       
       Client c= new Client ();
       a.setEmail(tfemail.getText());
        if (sc.CheckAdminCnx(tfemail.getText(),tfpwd.getText())==true) {
            ServiceAdmin.setA(sa.SelectAdmin(tfemail.getText()));
            
                    Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        }else 
        { if (sc.CheckClientCnx(tfemail.getText(),tfpwd.getText())==true) {{
            c=scl.SelectClient(tfemail.getText());
            ServiceClient.setA(scl.SelectClient(tfemail.getText()));
            if (c.getStatus()==2){
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre compte est banné.");
            Optional<ButtonType> result = alert.showAndWait();
           } else {
            
           
           
               
            Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("ProfileUtil.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);}
        }}
        else {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre email ou votre mot de passe est incorect.");
            Optional<ButtonType> result = alert.showAndWait();}

    }}

    @FXML
     private void Inscription(ActionEvent event) throws IOException {
         Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("InscriptionClient.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
