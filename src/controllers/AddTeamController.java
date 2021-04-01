/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entites.Competition;
import Entites.Team;
import Service.ServiceCompetition;
import Service.ServiceTeam;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AddTeamController implements Initializable {
@FXML
    private ChoiceBox<Competition> ListCompetition = new ChoiceBox<Competition>();
    private  ServiceCompetition serviceCompetition = new ServiceCompetition();
  ArrayList<Competition> Coms = new ArrayList<Competition>();
    @FXML
    private Button btValider;
    @FXML
    private Button btAnnuler;
    @FXML
    private TextField ftName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListCompetition.setOnAction(this::selectedCompetition);
        
        try {
            Coms = serviceCompetition.AfficherCompetition();
            
                for (Competition c : Coms) {
           
            ListCompetition.getItems().add(c);
            ListCompetition.getValue();
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML    
    private void handleButtonAction (ActionEvent event) throws SQLException, Exception{
        if ( event.getSource()==btValider ){
            AddTeam();
            btValider.getScene().getWindow().hide();
          
        }
        
        else if (event.getSource()== btAnnuler){
        
         FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/team.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("team");
            stage.setScene(new Scene(root1));  
            stage.show();
            btValider.getScene().getWindow().hide();
        
        }
    
    }
    
    @FXML
    private void AddTeam() throws Exception {
        
        ServiceTeam st = new ServiceTeam();
        Team t = new Team ();
        t.setName(ftName.getText());
        t.setId_competition(ListCompetition.getValue().getId());
        st.AddTeam(t);
        btValider.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/team.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("team");
            stage.setScene(new Scene(root1));  
            stage.show();
            
        
         
    }
    
    private void selectedCompetition(ActionEvent event) {
        System.out.println("tttt");
           Competition c = new Competition();
           
        ServiceCompetition sc = new ServiceCompetition();
        c= ListCompetition.getValue();
        
               
        serviceCompetition.setCom(c);
        
        
        
    
}
    
}
