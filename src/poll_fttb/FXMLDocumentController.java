 /* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll_fttb;

import Entities.Option;
import Entities.Poll;
import Entities.Vote;
import Service.ServicePoll;
import Service.ServiceOption;
import Service.ServiceVote;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.MessagingException;
import utils.JavaMailUtil;
import utils.Utilities;


/**
 *
 * @author Slim
 */
public class FXMLDocumentController implements Initializable {
    
   
    @FXML
    private TextArea PollDescription;
    @FXML
    private TextField OptionDescription2;
    @FXML
    private TextField OptionDescription1;
   
    private Label Lshow;
    @FXML
    private TableView<Poll> ListPoll;
    @FXML
    private TextArea Descriptiondisplay;
    @FXML
    private TextField Option1display;
    @FXML
    private TextField Option2display;
    @FXML
    private ProgressBar resultbar1;
     @FXML
    private ProgressBar resultbar2;
    @FXML
    private Label result1;
    @FXML
    private Label result2;
    @FXML
    private TableColumn<Poll, String> t1;
    @FXML
    private TableColumn<Poll, String> t2;
    @FXML
    private TableColumn<Poll, String> t4;
    @FXML
    private TableColumn<Poll, String> t3;
    @FXML
    private TableColumn<?, ?> t11;
   

   
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        Lshow.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewPoll(null);
//        TableColumn t1 = new TableColumn("Poll ID");
        t1.setCellValueFactory(new PropertyValueFactory<Poll, String>(String.valueOf("poll_id")));
//        TableColumn t2 = new TableColumn("Description");
        t2.setCellValueFactory(new PropertyValueFactory<Poll, String>("description"));
//        TableColumn t3 = new TableColumn("Creation Date");
        t3.setCellValueFactory(new PropertyValueFactory<Poll, String>("creation_date"));
//        TableColumn t4 = new TableColumn("Status");
        t4.setCellValueFactory(new PropertyValueFactory<Poll, String>("status"));
     
           
        Descriptiondisplay.setEditable(false);
        Option1display.setEditable(false);
        Option2display.setEditable(false);
        
    }    

    @FXML
    private void AddPoll(ActionEvent event) throws Exception {
        ServicePoll sp = new ServicePoll();
            ServiceOption so = new ServiceOption();
                ServiceVote sv = new ServiceVote();
                
        
        
        if((PollDescription.getText().equalsIgnoreCase("") )||(OptionDescription1.getText().equalsIgnoreCase(""))||(OptionDescription2.getText().equalsIgnoreCase(""))){
            Utilities.alert("WARNING!", "Please enter all the required information!");
        }else{
            Poll p = new Poll();
            p.setDescription(PollDescription.getText()); 
    
            Option o = new Option();
            o.setDescription(OptionDescription1.getText());
                Vote v = new Vote();
            
            Option op = new Option();
            op.setDescription(OptionDescription2.getText());
                Vote vt = new Vote();
                
        sp.AddPoll(p);
            so.AddOption(o,p.getPoll_id());
                sv.AddVote(v, o.getOption_id());
            so.AddOption(op,p.getPoll_id());
                sv.AddVote(vt,op.getOption_id());
                
                ViewPoll(null);
                JavaMailUtil.Send("slim.jaafoura@esprit.tn",PollDescription.getText(),OptionDescription1.getText(),OptionDescription2.getText());
        }
    }
        

    public boolean tt;

    @FXML
    private void ViewPoll(ActionEvent event) {
         ServicePoll tp = new ServicePoll();
        List<Poll> p = tp.ViewPoll();

        ObservableList<Poll> data =FXCollections.observableArrayList(p);
        
         ListPoll.setItems(data);   
        TableView<Poll> table;
       tt = p.isEmpty();
    }

    

    @FXML
    private void DeletePoll(ActionEvent event) {
        
                Poll p = ListPoll.getSelectionModel().getSelectedItem();
                
        if(p == null){
            Utilities.alert("WARNING!", "Please select a Poll from the list!");
        }else{
           ServicePoll sp = new ServicePoll();
           sp.DeletePoll(p);
           Utilities.alert("INFO Message", "Poll deleted successfully");
           
           ViewPoll(null);
        }
      
    }

    @FXML
    private void view1poll(ActionEvent event) {
        
        Poll p = ListPoll.getSelectionModel().getSelectedItem();
                
        if(p == null){
            Utilities.alert("WARNING!", "Please select a Poll from the list!");
        }else{
           if(p.getStatus().equals("Ended")){   
        ServiceOption so = new ServiceOption();
        ServiceVote sv = new ServiceVote();
        ServicePoll sp = new ServicePoll();
        
        Descriptiondisplay.setText(ListPoll.getSelectionModel().getSelectedItem().getDescription());
//        System.out.println(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id()));
       Option1display.setText(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),0));
       Option2display.setText(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),1));
        int VoteNbr1=0;
        int VoteNbr2=0;
        int totalvote=0;
        int rslt1=0;
        int rslt2=0;
       
        VoteNbr1 = sv.VoteNbr(so.optId(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),0));
        VoteNbr2 = sv.VoteNbr(so.optId(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),1));
        totalvote = VoteNbr1 + VoteNbr2;
        rslt1 = (VoteNbr1 *100)/(totalvote);
        rslt2 = (VoteNbr2 *100)/(totalvote);
        
        
        result1.setText(String.valueOf(rslt1+"%"));
        result2.setText(String.valueOf(rslt2+"%"));
        
       
       double pb1 = rslt1;
       double pb2 = rslt2;        
        resultbar1.setProgress(pb1/100);
        resultbar2.setProgress(pb2/100);
           }
           else{
           ServiceOption so = new ServiceOption();
//        Option o = new Option();
        Descriptiondisplay.setText(ListPoll.getSelectionModel().getSelectedItem().getDescription());
//        System.out.println(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id()));
       Option1display.setText(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),0));
       Option2display.setText(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),1));
       
       resultbar1.setProgress(0);
       resultbar2.setProgress(0);
       result1.setText("0%");
        result2.setText("0%");
       
        }
      
      
    }}
    

    @FXML
    private void EndPoll(ActionEvent event) throws MessagingException {
        
        Poll p = ListPoll.getSelectionModel().getSelectedItem();
                
        if(p == null){
            Utilities.alert("WARNING!", "Please select a Poll from the list!");
        }else{
            if(p.getStatus().equals("Ended")){ Utilities.alert("WARNING!", "This Poll already Ended!");}
            else {
        ServiceOption so = new ServiceOption();
        ServiceVote sv = new ServiceVote();
        ServicePoll sp = new ServicePoll();
        
        int VoteNbr1=0;
        int VoteNbr2=0;
        int totalvote=0;
        int rslt1=0;
        int rslt2=0;
       
        VoteNbr1 = sv.VoteNbr(so.optId(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),0));
        VoteNbr2 = sv.VoteNbr(so.optId(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),1));
        totalvote = VoteNbr1 + VoteNbr2;
        rslt1 = (VoteNbr1 *100)/(totalvote);
        rslt2 = (VoteNbr2 *100)/(totalvote);
        
        
        result1.setText(String.valueOf(rslt1+"%"));
        result2.setText(String.valueOf(rslt2+"%"));
        
       
       double pb1 = rslt1;
       double pb2 = rslt2;        
        resultbar1.setProgress(pb1/100);
        resultbar2.setProgress(pb2/100);
  
//        Option o = new Option();
        Descriptiondisplay.setText(ListPoll.getSelectionModel().getSelectedItem().getDescription());
//        System.out.println(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id()));
       Option1display.setText(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),0));
       Option2display.setText(so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),1));
       
        sp.swapstatus(ListPoll.getSelectionModel().getSelectedItem().getPoll_id());
        
        JavaMailUtil.SendA("slim.jaafoura@esprit.tn",
                ListPoll.getSelectionModel().getSelectedItem().getDescription(),
                so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),0),
                so.displayoption(ListPoll.getSelectionModel().getSelectedItem().getPoll_id(),1)
                );
        
        view1poll(null);
        ViewPoll(null);
        
        }
       
    }


    }

    @FXML
    private void DeleteAll(ActionEvent event) {
        if(tt ){
            Utilities.alert("WARNING!", "there is no polls to delete");
        }else{
         ServicePoll sp = new ServicePoll();
           sp.DeleteAll();
           Utilities.alert("INFO Message", "all the polls deleted successfully");
           
           ViewPoll(null);
    }
    }
}
