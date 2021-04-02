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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pidev.AdminhomeController;
import utils.Passable;

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
           Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/compit/src/view/competition.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        else if (event.getSource()== btAnnuler){
        
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/compit/src/view/competition.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
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
