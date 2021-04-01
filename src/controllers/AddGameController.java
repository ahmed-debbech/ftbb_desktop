/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entites.Competition;
import Entites.Game;
import Entites.Team;
import Service.ServiceCompetition;
import Service.ServiceGame;
import Service.ServicePhase;
import Service.ServiceTeam;
import Service.ServiceWeek;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
  List<Team> teams = new ArrayList<Team>(); // Create an ArrayList object
    @FXML
    private DatePicker ftDate;
    @FXML
    private TextField ftHall;
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
        ListTeamHome.setOnAction(this::selectedTeamH);
        ListTeamAway.setOnAction(this::selectedTeamW);
     
        
        try {
            int idc = ServiceCompetition.getCom().getId();
            teams =serviceTeam.AfficherTeam(idc);
            
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
    
    
    
    private void AddGame() throws Exception {
        
        ServiceGame sg = new ServiceGame();
        Game g = new Game ();
        int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        int idw =ServiceWeek.getCom().getId();
        g.setId_competition(idc);
        g.setId_phase(idp);
        g.setId_week(idw);
        g.setId_team_home(ListTeamHome.getValue().getId());
        g.setId_team_away(ListTeamAway.getValue().getId());
        g.setSalle(ftHall.getText());
        //g.setTime((Date) ftDate.getDayCellFactory());
        sg.AddGame(g);
        btValider.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/competition.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Competition");
            stage.setScene(new Scene(root1));  
            stage.show();
        
         
    }
    private void selectedTeamH(ActionEvent event) {
        System.out.println("tttt");
           Team th = new Team();
           
        ServiceTeam st = new ServiceTeam();
        th= ListTeamHome.getValue();
        
        System.out.println(th.getName());
        
        ServiceTeam.setCom(th);
        ListTeamAway.getItems().remove(th);
        System.out.println(th.getName());
        
    
}
    private void selectedTeamW(ActionEvent event) {
        System.out.println("tttt");
           
           Team tw = new Team();
        ServiceTeam st = new ServiceTeam();
        
        tw= ListTeamAway.getValue();
        
        System.out.println(tw.getName());
        
        ServiceTeam.setCom(tw);
        ListTeamHome.getItems().remove(tw);
        System.out.println(tw.getName());
    
}
    @FXML    
    private void handleButtonAction (ActionEvent event) throws SQLException, Exception{
        if ( event.getSource()== btValider ){
            AddGame();
            btValider.getScene().getWindow().hide();
          
        }
        else if (event.getSource()== btAnnuler){
        
         FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/competition.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Competition");
            stage.setScene(new Scene(root1));  
            stage.show();
            btValider.getScene().getWindow().hide();
        
        }
    
    }
    
}
