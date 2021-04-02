/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entites.Classement;
import Entites.Game;
import Entites.Team;
import Service.ServiceClassement;
import Service.ServiceCompetition;
import Service.ServiceGame;
import Service.ServicePhase;
import Service.ServiceTeam;
import Service.ServiceWeek;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
     Team vv = new Team();
     vv.setName("Select --");
        ListTeamHome.getItems().add(vv);
        Team v2v = new Team();
     v2v.setName("Select --");
        ListTeamAway.getItems().add(v2v);
        ListTeamHome.getSelectionModel().selectFirst();
        ListTeamAway.getSelectionModel().selectFirst();
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
        LocalDate d = ftDate.getValue();
        System.out.println(d);
       g.setTime(Date.valueOf(d));
        //g.setTime((Date) ftDate.getDayCellFactory());
        sg.AddGame(g);
        System.out.println(g.getId_team_home());
        System.out.println(g.getId_team_away());
        
        
         ServiceClassement serviceClassement = new ServiceClassement();
          List<Classement> classementstest = new ArrayList<Classement>(); // Create an ArrayList object
        classementstest =serviceClassement.AfficherClassement(idc, idp);
        
        for (Classement c: classementstest){
            if (c.getId_team()==g.getId_team_home()){
                System.out.println("usm local");
            }
            else if (c.getId_team()==g.getId_team_away()){
            System.out.println("usm etranger");}
            else System.out.println("usm not here");
            }
//        for (Classement c : classements) {
//           if ( c.getId_team()!= g.getId_team_home()){
//           Classement cn = new Classement();
//           cn.setId_competition(idc);
//           cn.setId_phase(idp);
//           cn.setId_team(g.getId_team_home());
//           serviceClassement.AddClassement(cn);
//           
//                  }
//           
//            if (c.getId_team()!= g.getId_team_away()){
//           Classement cn1 = new Classement();
//           cn1.setId_competition(idc);
//           cn1.setId_phase(idp);
//           cn1.setId_team(g.getId_team_away());
//           serviceClassement.AddClassement(cn1);
//           
//           
//           }
//           else System.out.println("trouve");
//            
//        }
//        
        
        
        
        
        
        
        btValider.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/competition.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Competition");
            stage.setScene(new Scene(root1));  
            stage.show();
        
         
    }
    private void selectedTeamH(ActionEvent event) {
        
           Team th = new Team();
           
        ServiceTeam st = new ServiceTeam();
        th= ListTeamHome.getValue();
        
        
        
        ServiceTeam.setCom(th);
        ListTeamAway.getItems().remove(th);
        
        
    
}
    private void selectedTeamW(ActionEvent event) {
        
           
           Team tw = new Team();
        ServiceTeam st = new ServiceTeam();
        
        tw= ListTeamAway.getValue();
        
       
        
        ServiceTeam.setCom(tw);
        ListTeamHome.getItems().remove(tw);
        
    
}
    @FXML    
    private void handleButtonAction (ActionEvent event) throws SQLException, Exception{
        if ( event.getSource()== btValider ){
            if((ListTeamHome.getSelectionModel().getSelectedIndex() != 0) 
                    && (ListTeamAway.getSelectionModel().getSelectedIndex() != 0)
                    && (!ftHall.getText().equals(""))){
                AddGame();
                            btValider.getScene().getWindow().hide();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select !.");
            Optional<ButtonType> result = alert.showAndWait();
            }
          
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
