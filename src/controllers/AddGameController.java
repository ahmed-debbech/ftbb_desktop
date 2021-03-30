/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entites.Competition;
import Entites.Team;
import Service.ServiceTeam;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AddGameController implements Initializable {

    @FXML
    private ChoiceBox<Team> ListTeamHome = new ChoiceBox<Team>();
    @FXML
    private ChoiceBox<Team> ListTeamAway = new ChoiceBox<Team>();
    
    private  ServiceTeam serviceTeam = new ServiceTeam();
  ArrayList<Team> teams = new ArrayList<Team>(); // Create an ArrayList object

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ListTeamHome.setOnAction(this::selectedTeam);
        ListTeamAway.setOnAction(this::selectedTeam);
     
        
        try {
            teams = serviceTeam.AfficherTeam();
            
                for (Team c : teams) {
           
            ListTeamHome.getItems().add(c);
            ListTeamHome.getValue();
            ListTeamAway.getItems().add(c);
            ListTeamAway.getValue();
        }
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void selectedTeam(ActionEvent event) {
        System.out.println("tttt");
           Team c = new Team();
           Team d = new Team();
        ServiceTeam sc = new ServiceTeam();
        c= ListTeamHome.getValue();
        d= ListTeamHome.getValue();
        System.out.println(c.getName());
        System.out.println(d.getName());
        ServiceTeam.setCom(c);
        ServiceTeam.setCom(d);
        System.out.println(c.getName());
    }
}
