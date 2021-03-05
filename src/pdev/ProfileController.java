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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ProfileController implements Initializable {

    @FXML
    private ImageView Iprofil;
    @FXML
    private Label lnom;
    @FXML
    private Label lprenom;
    @FXML
    private Label lbirth;
    @FXML
    private Button BAjout;
    @FXML
    private Label lnumber;
    @FXML
    private Label lsex;
    @FXML
    private Label lrole;

   

//    public void setLnom(String lnom) {
//        this.lnom.setText(lnom); 
//    }
//
//    public void setLprenom(String lprenom) {
//        this.lprenom.setText(lprenom);
//    }
//
//    public void setLbirth(Date lbirth) {
//        
//        this.lbirth.setText(lbirth.toString()); 
//    }
//
//    public void setLnumber(int lnumber) {
//        String s=String.valueOf(lnumber);
//        this.lnumber.setText(s);
//    }
//
//    public void setLsex(String lsex) {
//        this.lsex.setText(lsex);
//    }
//
//    public void setLrole(int lrole) {
//        if (lrole==1){
//            this.lrole.setText("Admin");
//        }
//        else {  if (lrole==2){
//            this.lrole.setText("Store Manager");
//        }
//            else {  if (lrole==3){
//            this.lrole.setText("Editeur");
//        }} 
//        }
//    }
    


   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void Updateinfo(ActionEvent event) {
    }

  

    @FXML
    private void BajoutAdmin(ActionEvent event) throws IOException {
         BAjout.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("AjoutAdmin.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    
}
