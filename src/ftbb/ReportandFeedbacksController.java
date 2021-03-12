/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Utils.Passable;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class ReportandFeedbacksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    AnchorPane pane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      pane= (AnchorPane)Passable.getInstance().getAnyData();
      
        
    }    

   

    @FXML
    private void ShowReports(ActionEvent event) throws IOException {
       
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminReports.fxml"));
            Parent root = loader.load();
           
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    @FXML
    private void ShowFeedbacks(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFeedbacks.fxml"));
            Parent root = loader.load();
           
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

    
    
}
