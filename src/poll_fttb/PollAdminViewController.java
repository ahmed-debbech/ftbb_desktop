/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll_fttb;

import Entities.Poll;
import Service.ServicePoll;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class PollAdminViewController implements Initializable {

    private List<Poll> list;
    @FXML
    private VBox adminVbox;
    @FXML
    private TextField SearchBar;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ServicePoll sa = new ServicePoll();
        this.adminVbox.getChildren();
            this.list =  sa.ViewPoll();
            try{
                for(Poll p : this.list){
                    if(p.getStatus().equals("Active")){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ActivePoll.fxml"));
                    Node postbox = loader.load();
                    ActivePollController pc = loader.getController();
                    pc.setData(p);
                   
                    this.adminVbox.getChildren().add(postbox);
                    
                    
                    }
              
                }
            }catch(IOException e){
                   e.printStackTrace();
            }
            
            SearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            ServicePoll s = new ServicePoll();
            List<Poll> l ;
            l =  s.searchPoll(newValue);
             this.adminVbox.getChildren().clear();
            this.list =  s.ViewPoll();
            try{
                for(Poll p : l){
                    if(p.getStatus().equals("Active")){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ActivePoll.fxml"));
                    Node postbox = loader.load();
                    ActivePollController pc = loader.getController();
                    pc.setData(p);
                   
                    this.adminVbox.getChildren().add(postbox);
                    
                    
                    }
              
                }
            }catch(IOException e){
                   e.printStackTrace();
            }
        });
        
    }    

    @FXML
    private void ctrlpanel(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root =  fxmlLoader.load(getClass().getResource("FXMLDocument.fxml").openStream());
           
            Stage stage = new Stage();
            stage.setTitle("Admin Poll Control Panel");
            stage.setScene(new Scene(root));  
            stage.show();
    }

    @FXML
    private void ActivePoll(ActionEvent event) throws IOException {
        Parent root =  FXMLLoader.load(getClass().getResource("PollAdminView.fxml"));
            Scene scene = new Scene(root);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            window.setTitle("Active Poll");
    }

    @FXML
    private void EndedPoll(ActionEvent event) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root1 =  FXMLLoader.load(getClass().getResource("EndedPollAdminView.fxml"));
            Scene scene = new Scene(root1);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            window.setTitle("Ended Poll");
            
    }
    
}
