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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class TeamController implements Initializable {
    
    
    ArrayList <Team >listTeam= new ArrayList<Team>();
    ServiceTeam st = new ServiceTeam();
    @FXML
    private ChoiceBox<Competition> ListCompetition= new ChoiceBox<Competition>();
    @FXML
    private TextField ftSearch;
    @FXML
    private Button btDeleteTeam;
    @FXML
    private Button btEditTeam;
    @FXML
    private Button btAddTeam;
    @FXML
    private Button btDashboard;
    
  private  ServiceCompetition serviceCompetition = new ServiceCompetition();
  ArrayList<Competition> competitions = new ArrayList<Competition>(); // Create an ArrayList object
    @FXML
    private TableView<Team> table_team= new TableView<>();
    @FXML
    private TableColumn<Team, String> logo_team;
    @FXML
    private TableColumn<Team, String> name_team;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListCompetition.setOnAction(this::selectedCompetition);
        try {
            competitions = serviceCompetition.AfficherCompetition();
            
                for (Competition c : competitions) {
           
            ListCompetition.getItems().add(c);
         ListCompetition.getValue();
        }
                ListCompetition.getSelectionModel().selectFirst();
                
                               
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        affichertable();
        table_team.getSelectionModel().selectFirst();
        
        
        
        // TODO
    }    

    
    private void selectedCompetition(ActionEvent event) {
        
           Competition c = new Competition();
        ServiceCompetition sc = new ServiceCompetition();
        c= ListCompetition.getValue();
        System.out.println(c.getName());
        ServiceCompetition.setCom(c);
        System.out.println(c.getName());
           ServiceTeam st = new ServiceTeam();
           int idc = ServiceCompetition.getCom().getId();
       
        try {
            listTeam = (ArrayList<Team>) st.AfficherTeam(idc);
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       affichertable(); 
        
    }
    
    private void affichertable() {
        int idc = ServiceCompetition.getCom().getId();
        ServiceTeam st = new ServiceTeam();
        
        try {
           
            listTeam = (ArrayList<Team>) st.AfficherTeam(idc);
            
            name_team.setCellValueFactory(new PropertyValueFactory<>("name"));
            logo_team.setCellValueFactory(new PropertyValueFactory<>("logo"));
            
            table_team.setItems(st.getData());
            
            
            
            
            

        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    @FXML
    private void AddTeam(ActionEvent event)throws IOException {
        
        URL url;
        url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\AddTeam.fxml").toURI().toURL();
        Stage stage = new Stage ();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("Add Team");
        stage.show();
        btAddTeam.getScene().getWindow().hide();

    }
    
    @FXML
    private void UpdateTeam(ActionEvent event) throws IOException {
        
        Team t=table_team.getSelectionModel().getSelectedItems().get(0);
         ServiceTeam.setCom(t);
         Stage stage = new Stage ();
         URL url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\UpdateTeam.fxml").toURI().toURL();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("Update Team");
        stage.show();
         btEditTeam.getScene().getWindow().hide();

    }
    @FXML
    private void DeleteTeam(ActionEvent event) throws IOException, SQLException {
      
        
        ServiceTeam sc = new ServiceTeam();
        Team t = new Team();
      ObservableList allTeam,Single;
      allTeam=table_team.getItems();
      Single=table_team.getSelectionModel().getSelectedItems();
      t=table_team.getSelectionModel().getSelectedItems().get(0);
      sc.DeleteTeam(t);
        System.out.println(t);
        System.out.println(Single);
      Single.forEach(allTeam::remove);
            
            
//             ListCompetition.getItems().remove(ServiceCompetition.getCom());
//             competitions = serviceCompetition.AfficherCompetition();
        
        
      
            
        
        
      

             
             
    }
    
    
     
        
         

    @FXML
    private void Back(ActionEvent event) throws IOException {
        btDashboard.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/ftbb/Ftbb.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("FTBB Application");
            stage.setScene(new Scene(root1));  
            stage.show();
            
    }
        
        
    
}
