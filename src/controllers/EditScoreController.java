/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entites.Game;
import Entites.Gamef;
import Service.ServiceGame;
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
        
        ServiceGame st = new ServiceGame();
        Gamef g = new Gamef();
        g=ServiceGame.getCom();
        g.setScore_home(Integer.valueOf(ftScoreHome.getText()));
        g.setScore_away(Integer.valueOf(ftScoreAway.getText()));
        st.UpdateScore(g);
        
    }
}
