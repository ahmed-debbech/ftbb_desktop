package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import Entites.Competition;
import Entites.Game;
import Entites.Gamef;
import Entites.Phase;
import Entites.Team;
import Entites.Week;
import Service.ServiceCompetition;
import Service.ServiceGame;
import Service.ServicePhase;
import Service.ServiceTeam;
import Service.ServiceWeek;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class CompetitionController implements Initializable {
    

    ArrayList <Game> listGame= new ArrayList<Game>();
    ArrayList <Gamef> listGamef= new ArrayList<Gamef>();
    ServiceGame sg = new ServiceGame();
    ServiceTeam st = new ServiceTeam();

    
    @FXML
    private ChoiceBox<Competition> ListCompetition = new ChoiceBox<Competition>();
    @FXML
    private ChoiceBox<Phase> ListPhase = new ChoiceBox<Phase>();
    @FXML
    private ChoiceBox<Week> ListWeek = new ChoiceBox<Week>();
    
  private  ServiceCompetition serviceCompetition = new ServiceCompetition();
  ArrayList<Competition> competitions = new ArrayList<Competition>(); // Create an ArrayList object

  private  ServicePhase servicePhase = new ServicePhase();
  ArrayList<Phase> phases = new ArrayList<Phase>(); // Create an ArrayList object
  
  private  ServiceWeek serviceWeek = new ServiceWeek();
  ArrayList<Week> weeks = new ArrayList<Week>(); // Create an ArrayList object
  
   private  ServiceGame serviceGame = new ServiceGame();
  ArrayList<Game> games = new ArrayList<Game>(); // Create an ArrayList object
    @FXML
    private TableView<Gamef> table_game = new TableView<Gamef>();
    @FXML
    private TableColumn<Gamef, String> name_home = new TableColumn<>("");;
    @FXML
    private TableColumn<Gamef, String> score_home;
    @FXML
    private TableColumn<Gamef, String> score_away;
    @FXML
    private TableColumn<Gamef, String> name_away;
    @FXML
    private TableColumn<Gamef, String> hall;
    @FXML
    private TableColumn<Gamef, Integer> statuts;
    @FXML
    private TableColumn<?, ?> logo_home;
    @FXML
    private TableColumn<?, ?> logo_away;
    @FXML
    private TableColumn<Gamef, String> time;
    @FXML
    private Button btAddCompetition;
    @FXML
    private Button btDashboard;
    @FXML
    private Button Classement;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                System.out.println("333333333333333333333333333");

        ListCompetition.setOnAction(this::selectedCompetition);
        ListPhase.setOnAction(this::selectedPhase);
        ListWeek.setOnAction(this::selectedWeek);
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
        
        try {
            phases = servicePhase.AfficherPhase();
                for (Phase p : phases) {
           
            ListPhase.getItems().add(p);
            ListPhase.getValue();
        }
                ListPhase.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        try {
            weeks = serviceWeek.AfficherWeek();
                for (Week w : weeks) {
           
            ListWeek.getItems().add(w);
            ListWeek.getValue();
        }
                ListWeek.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
       affichertable();
       table_game.getSelectionModel().selectFirst();
        
        
    }    
    

public void affichertable (){
   
        int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        int idw =ServiceWeek.getCom().getId();
        
        ServiceGame sg = new ServiceGame ();
        
        
        try {
           
            games = (ArrayList<Game>) sg.AfficherGame(idc, idp, idw);
            
            try {
            for (Game g : games) {
           Gamef gf = new Gamef();
           
           
           
           gf.setId(g.getId());
           gf.setTeam_home(st.GetTeamById(g.getId_team_home()).getName());
           gf.setTeam_away(st.GetTeamById(g.getId_team_away()).getName());
           gf.setScore_home(g.getScore_home());
           gf.setScore_away(g.getScore_away());
           gf.setSalle(g.getSalle());
           gf.setTime(g.getTime());
           if(g.getStatus() == 1){
           gf.setStatus(Game.FINISHED);
           }else{
               gf.setStatus(Game.NOT_FINISHED);
           }
           gf.setId_team_home(g.getId_team_home());
           gf.setId_team_away(g.getId_team_away());
           listGamef.add(gf);
           sg.data1.add(gf);
           
          
           
            
                
            
        }
                
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            name_home.setCellValueFactory(new PropertyValueFactory<>("team_home"));
            name_away.setCellValueFactory(new PropertyValueFactory<>("team_away"));
            score_home.setCellValueFactory(new PropertyValueFactory<>("score_home"));
            score_away.setCellValueFactory(new PropertyValueFactory<>("score_away"));
            hall.setCellValueFactory(new PropertyValueFactory<>("salle"));
            statuts.setCellValueFactory(new PropertyValueFactory<>("status"));
            time.setCellValueFactory(new PropertyValueFactory<>("time"));
            table_game.setItems(sg.getData1());
            
            
            
            
            

        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }



    @FXML
    private void AddPhase(ActionEvent event) throws MalformedURLException, IOException {
          URL url;
        url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\AddPhase.fxml").toURI().toURL();
        Stage stage = new Stage ();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("AddPhase");
        stage.show();
        btAddCompetition.getScene().getWindow().hide();
        
        
    }

    @FXML
    private void AddWeek(ActionEvent event) throws MalformedURLException, IOException {
          URL url;
        url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\AddWeek.fxml").toURI().toURL();
        Stage stage = new Stage ();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("AddWeek");
        stage.show();
        btAddCompetition.getScene().getWindow().hide();
        
        
    }

    @FXML
    private void AddGame(ActionEvent event) throws IOException {
        Stage stage = new Stage ();
         URL url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\AddGame.fxml").toURI().toURL();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("AddGame");
        stage.show();
        btAddCompetition.getScene().getWindow().hide();
    }

    @FXML
    private void UpdateCompetition(ActionEvent event) throws IOException {
         Stage stage = new Stage ();
         URL url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\UpdateCompetition.fxml").toURI().toURL();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("Update Competition");
        stage.show();
         btAddCompetition.getScene().getWindow().hide();

    }

    @FXML
    private void DeleteCompetition(ActionEvent event) throws IOException, SQLException {
       
        ServiceCompetition sc = new ServiceCompetition();
        sc.DeleteCompetition(ServiceCompetition.getCom());
      
            
            
             ListCompetition.getItems().remove(ServiceCompetition.getCom());
             competitions = serviceCompetition.AfficherCompetition();
            
//        Stage stage = new Stage ();
//         URL url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\DeleteCompetition.fxml").toURI().toURL();
//        Parent root =  FXMLLoader.load(url);
//        stage.setScene(new Scene(root));
//        stage.setTitle("Delete Competition");
//        stage.show();
    }

    @FXML
    private void UpdatePhase(ActionEvent event) throws IOException {
         Stage stage = new Stage ();
         URL url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\UpdatePhase.fxml").toURI().toURL();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("Update Phase");
        stage.show();
         btAddCompetition.getScene().getWindow().hide();

    }


    @FXML
    private void DeletePhase(ActionEvent event)throws IOException, SQLException {
       
        ServicePhase sc = new ServicePhase();
        sc.DeletePhase(ServicePhase.getCom());
      
             phases = servicePhase.AfficherPhase();
            
             ListPhase.getItems().remove(ServicePhase.getCom());}

    @FXML
    private void UpdateWeek(ActionEvent event) throws IOException {
         Stage stage = new Stage ();
         URL url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\UpdateWeek.fxml").toURI().toURL();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("Update Week");
        stage.show();
         btAddCompetition.getScene().getWindow().hide();

    }

    @FXML
    private void DeleteWeek(ActionEvent event)throws IOException, SQLException {
       
        ServiceWeek sc = new ServiceWeek();
        sc.DeleteWeek(ServiceWeek.getCom());
      
             weeks = serviceWeek.AfficherWeek();
            
             ListWeek.getItems().remove(ServiceWeek.getCom());}

    @FXML
    private void AddCompetition(ActionEvent event)throws IOException {
        
        URL url;
        url = new File("C:\\Users\\Lenovo\\Documents\\ftbb\\src\\view\\AddCompetition.fxml").toURI().toURL();
        Stage stage = new Stage ();
        Parent root =  FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.setTitle("Add Competition");
        stage.show();
        btAddCompetition.getScene().getWindow().hide();

    }

    private void selectedCompetition(ActionEvent event) {
        
           Competition c = new Competition();
        ServiceCompetition sc = new ServiceCompetition();
        c= ListCompetition.getValue();
        
        ServiceCompetition.setCom(c);
        
           ServiceGame sg = new ServiceGame ();
           int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        int idw =ServiceWeek.getCom().getId();
        try {
            games = (ArrayList<Game>) sg.AfficherGame(idc, idp, idw);
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       affichertable(); 
        
    }
    
    private void selectedPhase(ActionEvent event) {
        
           Phase c = new Phase();
        ServicePhase sc = new ServicePhase();
        c = ListPhase.getValue();
        ServicePhase.setCom(c);
        
        int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        int idw =ServiceWeek.getCom().getId();
           ServiceGame sg = new ServiceGame ();
        try {
            games = (ArrayList<Game>) sg.AfficherGame(idc, idp, idw);
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        affichertable();
    }
    private void selectedWeek(ActionEvent event) {
       
           Week c = new Week();
        ServiceWeek sc = new ServiceWeek();
        c= ListWeek.getValue();
        ServiceWeek.setCom(c);
       
           ServiceGame sg = new ServiceGame ();
           int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        int idw =ServiceWeek.getCom().getId();
        try {
            games = (ArrayList<Game>) sg.AfficherGame(idc, idp, idw);
        } catch (SQLException ex) {
            Logger.getLogger(CompetitionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        affichertable();
        
    }

    @FXML
    private void UpdateScore(ActionEvent event) throws IOException {
        Gamef t = new Gamef();
        
        t=table_game.getSelectionModel().getSelectedItems().get(0);
        serviceGame.setCom(t);
        
        
        btDashboard.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/EditScore.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Edit Score");
            stage.setScene(new Scene(root1));  
            stage.show();
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

    @FXML
    private void Classement(ActionEvent event) throws IOException {
              
        
        btDashboard.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/view/Classement.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Edit Score");
            stage.setScene(new Scene(root1));  
            stage.show();
        
    }


    
}
