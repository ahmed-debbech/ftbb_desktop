/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.controllers;


import compit.src.Entites.Competition;
import compit.src.Entites.Team;
import compit.src.Service.ServiceCompetition;
import compit.src.Service.ServiceTeam;
import java.io.IOException;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class UpdateTeamController implements Initializable {
private  ServiceCompetition serviceCompetition = new ServiceCompetition();
  ArrayList<Competition> competitions = new ArrayList<Competition>(); // Create an ArrayList object
    @FXML
    private ChoiceBox<Competition> ListCompetition = new ChoiceBox<Competition>();
    
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
        try {
            competitions = serviceCompetition.AfficherCompetition();
            
                for (Competition c : competitions) {
           
            ListCompetition.getItems().add(c);
         ListCompetition.getValue();
        }
                
                
                               
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
       ServiceCompetition scc = new ServiceCompetition();
        ftName.setText(ServiceTeam.getCom().getName());
        Competition com =null; ;
    try {
        com = scc.GetCompetitionById(ServiceTeam.getCom().getId_competition());
    } catch (SQLException ex) {
        Logger.getLogger(UpdateTeamController.class.getName()).log(Level.SEVERE, null, ex);
    }
        System.err.println(com);
      ListCompetition.setValue(com);
   
    }    

    @FXML
    private void handleButtonAction(ActionEvent event)throws SQLException, IOException{
    
             if ( event.getSource()== btValider ){
            UpdateTeam();
          Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/compit/src/view/team.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
        else if (event.getSource()== btAnnuler){
        
       Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/compit/src/view/team.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    
    
    }
    
    private void UpdateTeam() throws SQLException {
        
        ServiceTeam st = new ServiceTeam();
        Team t = new Team();
        t=ServiceTeam.getCom();
        t.setName(ftName.getText());
        t.setId_competition(ListCompetition.getSelectionModel().getSelectedIndex());
        st.UpdateTeam(t);
    }
}
