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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class EndedPollClientViewController implements Initializable {

    @FXML
    private VBox adminVbox;
    private List<Poll> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServicePoll sa = new ServicePoll();
        this.adminVbox.getChildren().clear();
            this.list =  sa.ViewPoll();
            try{
                for(Poll a : this.list){
                    if(a.getStatus().equals("Ended")){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("EndedPoll.fxml"));
                    Node postbox = loader.load();
                    EndedPollController pc = loader.getController();
                    pc.setData(a);
                    this.adminVbox.getChildren().add(postbox);
                }}
            }catch(IOException e){
                   e.printStackTrace();
    }    
    } 

  

    @FXML
    private void EndedPoll(ActionEvent event) throws IOException {
        Parent root1 =  FXMLLoader.load(getClass().getResource("EndedPollClientView.fxml"));
            Scene scene = new Scene(root1);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            window.setTitle("Ended Poll");
    }

    @FXML
    private void ActivePoll(ActionEvent event) throws IOException {
         Parent root1 =  FXMLLoader.load(getClass().getResource("PollCientView.fxml"));
            Scene scene = new Scene(root1);
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
            window.setTitle("Active Poll");
    }
    
}