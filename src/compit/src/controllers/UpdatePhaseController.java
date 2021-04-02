/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.controllers;


import compit.src.Entites.Phase;
import compit.src.Service.ServicePhase;
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
public class UpdatePhaseController implements Initializable {

    @FXML
    private TextField ftNamePhase;
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
        ftNamePhase.setText(ServicePhase.getCom().getName());
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException{
    
             if ( event.getSource()== btValider ){
           UpdatePhase();
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
    
    private void UpdatePhase() throws SQLException {
        
        ServicePhase sp = new ServicePhase ();
        Phase c = new Phase ();
        c=ServicePhase.getCom();
        c.setName(ftNamePhase.getText());
        sp.UpdatePhase(c);
    }
}
