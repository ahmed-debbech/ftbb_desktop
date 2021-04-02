/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.controllers;

import compit.src.Entites.Week;
import compit.src.Service.ServiceWeek;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class UpdateWeekController implements Initializable {

    @FXML
    private TextField ftNameWeek;
    @FXML
    private Button btValider;
    @FXML
    private Button btAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ftNameWeek.setText(ServiceWeek.getCom().getName_week());
    }    

    @FXML
    private void handleButtonAction(ActionEvent event)throws SQLException, IOException{
    
             if ( event.getSource()== btValider ){
           UpdateWeek();
           FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/compit/src/view/competition.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Competition");
            stage.setScene(new Scene(root1));  
            stage.show();
            btValider.getScene().getWindow().hide();
          
        }
        else if (event.getSource()== btAnnuler){
        
         FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/compit/src/view/competition.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Competition");
            stage.setScene(new Scene(root1));  
            stage.show();
            btValider.getScene().getWindow().hide();
        
        }
    
    
    }
    private void UpdateWeek() throws SQLException {
        
        ServiceWeek sp = new ServiceWeek ();
        Week c = new Week ();
        c=ServiceWeek.getCom();
        c.setName_week(ftNameWeek.getText());
        sp.UpdateWeek(c);
    }
    
}
