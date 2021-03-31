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
import Entites.Phase;
import Entites.Week;
import Service.ServiceCompetition;
import Service.ServiceGame;
import Service.ServicePhase;
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
    ServiceGame sg = new ServiceGame();
    
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
    private TableView<Game> table_game = new TableView<Game>();
    @FXML
    private TableColumn<Game, String> name_home = new TableColumn<>("");;
    @FXML
    private TableColumn<Game, String> score_home;
    @FXML
    private TableColumn<Game, String> score_away;
    @FXML
    private TableColumn<Game, String> name_away;
    @FXML
    private TableColumn<Game, String> hall;
    @FXML
    private TableColumn<Game, String> statuts;
    @FXML
    private TableColumn<?, ?> logo_home;
    @FXML
    private TableColumn<?, ?> logo_away;
    @FXML
    private TableColumn<?, ?> time;
    @FXML
    private Button btAddCompetition;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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
        
        
    }    
    

public void affichertable (){
        int idc = ServiceCompetition.getCom().getId();
        int idp =ServicePhase.getCom().getId();
        int idw =ServiceWeek.getCom().getId();
        
        ServiceGame sg = new ServiceGame ();
        
        
        try {
            games = (ArrayList<Game>) sg.AfficherGame(idc, idp, idw);
            name_home.setCellValueFactory(new PropertyValueFactory<>("id_team_home"));
            name_away.setCellValueFactory(new PropertyValueFactory<>("id_team_away"));
            score_home.setCellValueFactory(new PropertyValueFactory<>("score_home"));
            score_away.setCellValueFactory(new PropertyValueFactory<>("score_away"));
            hall.setCellValueFactory(new PropertyValueFactory<>("salle"));
            statuts.setCellValueFactory(new PropertyValueFactory<>("statuts"));
            table_game.setItems(sg.getData());
            
            
            
            
            

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
      
            competitions = serviceCompetition.AfficherCompetition();
            
             ListCompetition.getItems().remove(ServiceCompetition.getCom());
            
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

    @FXML
    private void selectedCompetition(ActionEvent event) {
        System.out.println("tttt");
           Competition c = new Competition();
        ServiceCompetition sc = new ServiceCompetition();
        c= ListCompetition.getValue();
        System.out.println(c.getName());
        ServiceCompetition.setCom(c);
        System.out.println(c.getName());
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
    private void selectedPhase(ActionEvent event) {
        System.out.println("tttt");
           Phase c = new Phase();
        ServicePhase sc = new ServicePhase();
        c = ListPhase.getValue();
        ServicePhase.setCom(c);
        System.out.println(c.getName());
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
    @FXML
    private void selectedWeek(ActionEvent event) {
        System.out.println("tttt");
           Week c = new Week();
        ServiceWeek sc = new ServiceWeek();
        c= ListWeek.getValue();
        ServiceWeek.setCom(c);
        System.out.println(c.getName_week());
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


    
}
