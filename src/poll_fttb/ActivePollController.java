/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll_fttb;

import Entities.Poll;
import Service.ServiceOption;
import Service.ServiceVote;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class ActivePollController implements Initializable {

    @FXML
    private VBox activepollmodel;
    @FXML
    private TextArea polldescription;
    @FXML
    private TextField option1;
    @FXML
    private TextField option2;
    private int poll_id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        polldescription.setEditable(false);
        option1.setEditable(false);
        option2.setEditable(false);
        
       
        System.out.println(poll_id);
    }    

    @FXML
    private void voteoption1(ActionEvent event) {
        ServiceOption so = new ServiceOption();
        ServiceVote sv = new ServiceVote();
        sv.INCVote(so.optId(poll_id,0));
    }

    @FXML
    private void voteoption2(ActionEvent event) {
        ServiceOption so = new ServiceOption();
        ServiceVote sv = new ServiceVote();
        sv.INCVote(so.optId(poll_id,1));
    }
    
    public void setData(Poll p){
        
        ServiceOption so = new ServiceOption();
        ServiceVote sv = new ServiceVote();
           this.polldescription.setText(p.getDescription());
           this.option1.setText(so.displayoption(p.getPoll_id(),0));
           this.option2.setText(so.displayoption(p.getPoll_id(),1));
           poll_id=p.getPoll_id();
           
    }
}
