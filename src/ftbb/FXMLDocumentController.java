/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Competition;
import Service.ServiceCompetition;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    
    @FXML
    private Button btStatistics;
    @FXML
    private Button btCompetition;
    @FXML
    private Button btTeam;
    @FXML
    private Button btPlayer;
    @FXML
    private AnchorPane mainPane;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

//    private void AddCompetition(ActionEvent event) {
//        
//        ServiceCompetition sp = new ServiceCompetition ();
//        Competition c = new Competition ();
//        c.setName(ftName.getText());
//        
//        sp.AddCompetition(c);
//    }

    @FXML
    private void ShowStatistics(ActionEvent event) {
    }

    @FXML
    private void ManageCompetition (ActionEvent event) throws IOException  {
         
        // URL url1 = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\competition.fxml").toURI().toURL();
       // Parent root1 =  FXMLLoader.load(url1);
       // Stage stage1 = new Stage ();
        //stage1.setScene(new Scene(root1));
        //stage1.setTitle("Competition");
        //stage1.show();
        
        
        btCompetition.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/competition.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Competition");
            stage.setScene(new Scene(root1));  
            stage.show();
        
               
    }

    @FXML
    private void ListTeam(ActionEvent event) throws IOException {
        btTeam.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root2 =  fxmlLoader.load(getClass().getResource("/view/team.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Team");
            stage.setScene(new Scene(root2));  
            stage.show();
    }

    @FXML
    private void ListPlayer(ActionEvent event) {
    }
    
}
