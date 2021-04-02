/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entites.Classement;
import Entites.Game;
import Entites.Gamef;
import Service.ServiceClassement;
import Service.ServiceCompetition;
import Service.ServiceGame;
import Service.ServicePhase;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EditScoreController implements Initializable {

    @FXML
    private Label ftNameHome;
    @FXML
    private Label ftNameAway;
    @FXML
    private TextField ftScoreHome;
    @FXML
    private TextField ftScoreAway;
    @FXML
    private Button btValider;
    @FXML
    private Button btAnnuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ftNameHome.setText(ServiceGame.getCom().getTeam_home());
    ftNameAway.setText(ServiceGame.getCom().getTeam_away());
    ftScoreHome.setText(String.valueOf(ServiceGame.getCom().getScore_home()));
    ftScoreAway.setText(String.valueOf(ServiceGame.getCom().getScore_away()));
    
// TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException{
    
             if ( event.getSource()== btValider ){
            UpdateScore();
           FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/competition.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("competition");
            stage.setScene(new Scene(root1));  
            stage.show();
            btValider.getScene().getWindow().hide();
          
        }
        else if (event.getSource()== btAnnuler){
        
         FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/competition.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("competition");
            stage.setScene(new Scene(root1));  
            stage.show();
            btValider.getScene().getWindow().hide();
        
        }
    
    
    }

    
    private void UpdateScore() throws SQLException {
       int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        ServiceGame st = new ServiceGame();
        Gamef g = new Gamef();
        g=ServiceGame.getCom();
        g.setScore_home(Integer.valueOf(ftScoreHome.getText()));
        g.setScore_away(Integer.valueOf(ftScoreAway.getText()));
       
                st.UpdateScore(g);

        ServiceClassement serviceClassement = new ServiceClassement();
          List<Classement> classementstest = new ArrayList<Classement>(); // Create an ArrayList object
        classementstest =serviceClassement.AfficherClassement(idc,idp);
        
        for (Classement c: classementstest){
            if (c.getId_team()==g.getId_team_home()){
                if(g.getScore_home()>g.getScore_away()){
                c.setNbr_pts(c.getNbr_pts()+2);
                c.setNbr_pts_P(c.getNbr_pts_P()+1);
                c.setNbr_pts_G(c.getNbr_pts_G()+1);
                serviceClassement.UpdateClassement(c);
                }
                if(g.getScore_home()<g.getScore_away()){
                c.setNbr_pts(c.getNbr_pts()+1);
                c.setNbr_pts_P(c.getNbr_pts_P()+1);
                c.setNbr_pts_D(c.getNbr_pts_D()+1);
                serviceClassement.UpdateClassement(c);
                }
            }
            else if (c.getId_team()==g.getId_team_away()){
                if(g.getScore_home()>g.getScore_away()){
                    c.setNbr_pts(c.getNbr_pts()+1);
                c.setNbr_pts_P(c.getNbr_pts_P()+1);
                c.setNbr_pts_D(c.getNbr_pts_D()+1);
                    serviceClassement.UpdateClassement(c);
               
                }
                if(g.getScore_home()<g.getScore_away()){
                 c.setNbr_pts(c.getNbr_pts()+2);
                c.setNbr_pts_P(c.getNbr_pts_P()+1);
                c.setNbr_pts_G(c.getNbr_pts_G()+1);
                serviceClassement.UpdateClassement(c);
                }
                
            System.out.println("usm etranger");}
            else System.out.println("usm not here");
            }
    }
}
