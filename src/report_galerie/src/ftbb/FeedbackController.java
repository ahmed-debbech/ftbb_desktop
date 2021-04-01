/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.ftbb;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import report_galerie.src.Entites.Feedback;
import report_galerie.src.Services.ServiceFeedback;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class FeedbackController implements Initializable {

    @FXML
    private VBox listfeedbacks;
    @FXML
    private TextField tfemail;
    @FXML
    private TextArea tftext;
    @FXML
    private TextField tftopic;
    @FXML
    private TextField tftype;
    @FXML
    private Label fdb;
    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void ShowFeedbacks(ActionEvent event) throws SQLException, IOException {
        
        this.listfeedbacks.getChildren().clear();
        ServiceFeedback sf = new ServiceFeedback();
           List<Feedback> list =  sf.ShowFeedback();
            try{
                
                for(Feedback f : list){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/report_galerie/src/ftbb/FXMLfeedbackview.fxml"));
                    Node postbox = loader.load();
                    FXMLfeedbackviewController pc = loader.getController();
                    pc.setData(f, parent);
                    this.listfeedbacks.getChildren().add(postbox);
                    
                }
            }catch(IOException e){
                  System.out.println("error");
            }
    }

    @FXML
    private void addfeed(ActionEvent event) {
        Feedback f =new Feedback();
            f.setEmail(tfemail.getText());
            f.setTopic(tftopic.getText());
            f.setText(tftext.getText());
            f.setType(tftype.getText());
            
       if((tfemail.getText().equals("")) || (tftopic.getText().equals("")) || (tftext.getText().equals("")) || (tftype.getText().equals(""))){
           
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please Type!.");
            Optional<ButtonType> result = alert.showAndWait();
            
        }else if((tfemail.getText().indexOf('@') == -1)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Email !.");
            Optional<ButtonType> result = alert.showAndWait();
            
        }else{ServiceFeedback sf=new ServiceFeedback();
           sf.AddFeedback(f);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Feedback added successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
    }
    }
   
    


    

    

    @FXML
    private void sub(ActionEvent event) {
        ServiceFeedback sf=new ServiceFeedback();
        Feedback f = new Feedback(Integer.parseInt(fdb.getText()),tftext.getText());
        sf.ModifyFeedback(f);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Feedback  modified successfully.");
            Optional<ButtonType> result = alert.showAndWait();
    }
}
