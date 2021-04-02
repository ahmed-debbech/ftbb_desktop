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
public class AddPhaseController implements Initializable {

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
    }    

    

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, Exception {
          if ( event.getSource()== btValider ){
            AddPhase();
            btValider.getScene().getWindow().hide();
          
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
        
        
    }}
   @FXML
    private void AddPhase() throws Exception {
        
        ServicePhase sp = new ServicePhase ();
        Phase c = new Phase ();
        c.setName(ftNamePhase.getText());
        sp.AddPhase(c);
        
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/compit/src/view/competition.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Competition");
            stage.setScene(new Scene(root1));  
            stage.show();
        
         
    }  
}
