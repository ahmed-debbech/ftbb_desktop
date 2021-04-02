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
import compit.src.utils.Utilities;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


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
    @FXML
    private TextField path;

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
            Pane root1 =  fxmlLoader.load(getClass().getResource("/compit/src/view/team.fxml"));
            Stage stage = new Stage();
            stage.setTitle("team");
            stage.setScene(new Scene(root1));  
            stage.show();
            btValider.getScene().getWindow().hide();
        
        }
    
    }
    
    private void AddTeam() throws Exception {
        
        ServiceTeam st = new ServiceTeam();
        Team t = new Team ();
        t.setName(ftName.getText());
        t.setId_competition(ListCompetition.getValue().getId());
        t.setLogo(Utilities.pathToUrl(path.getText()));
        st.AddTeam(t);
        btValider.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/compit/src/view/team.fxml"));
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

    @FXML
    private void onBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("C:\\xampp\\htdocs\\uploads"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                path.setText(file.getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
    }
    
}
