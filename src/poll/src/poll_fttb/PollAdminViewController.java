/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.poll_fttb;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pidev.AdminhomeController;
import poll.src.Entities.Poll;
import poll.src.Service.ServicePoll;
import utils.Passable;

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
                    loader.setLocation(getClass().getResource("/poll/src/poll_fttb/ActivePoll.fxml"));
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
                    loader.setLocation(getClass().getResource("/poll/src/poll_fttb/ActivePoll.fxml"));
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
            Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/poll/src/poll_fttb/FXMLDocument.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void ActivePoll(ActionEvent event) throws IOException {
        Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/poll/src/poll_fttb/PollAdminView.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void EndedPoll(ActionEvent event) throws IOException {

            Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/poll/src/poll_fttb/EndedPollAdminView.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }
    
}
