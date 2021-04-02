/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.poll_fttb;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import poll.src.Entities.Poll;
import poll.src.Service.ServiceOption;
import poll.src.Service.ServicePoll;
import poll.src.Service.ServiceVote;

/**
 * FXML Controller class
 *
 * @author sbs
 */
public class EndedPollController implements Initializable {

    @FXML
    private TextArea polldescription;
    @FXML
    private TextField option1;
    @FXML
    private TextField option2;
    @FXML
    private ProgressBar loadbar1;
    @FXML
    private Label percent1;
    @FXML
    private Label percent2;
    @FXML
    private ProgressBar loadbar2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    polldescription.setEditable(false);
        option1.setEditable(false);
        option2.setEditable(false);
    }  
    
     public void setData(Poll p){
        ServiceOption so = new ServiceOption();
        ServiceVote sv = new ServiceVote();
        ServicePoll sp = new ServicePoll();
        
           this.polldescription.setText(p.getDescription());
           this.option1.setText(so.displayoption(p.getPoll_id(),0));
           this.option2.setText(so.displayoption(p.getPoll_id(),1));
           
        int VoteNbr1=0;
        int VoteNbr2=0;
        int totalvote=0;
        int rslt1=0;
        int rslt2=0;
        
       
        VoteNbr1 = sv.VoteNbr(so.optId(p.getPoll_id(),0));
        VoteNbr2 = sv.VoteNbr(so.optId(p.getPoll_id(),1));
        totalvote = VoteNbr1 + VoteNbr2;
        rslt1 = (VoteNbr1 *100)/(totalvote);
        rslt2 = (VoteNbr2 *100)/(totalvote);
        this.percent1.setText(String.valueOf(rslt1+"%"));
        this.percent2.setText(String.valueOf(rslt2+"%"));
        
        double pb1 = rslt1;
        double pb2 = rslt2;
        
           this.loadbar1.setProgress(pb1/100);
           this.loadbar2.setProgress(pb2/100);
           
      
    }
}
