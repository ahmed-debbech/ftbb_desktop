/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.ftbb;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import report_galerie.src.Entites.Feedback;
import report_galerie.src.Services.ServiceFeedback;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class FXMLfeedbackviewController implements Initializable {

    @FXML
    private Label feedback_id;
    @FXML
    private Label date;
    @FXML
    private Label mail;
    @FXML
    private Label client_id;
    @FXML
    private Label topic;
    @FXML
    private Label type;
    @FXML
    private Label text;
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Feedback f, AnchorPane parent){
        
        this.parent = parent;
        this.feedback_id.setText(String.valueOf(f.getFeedback_id()));
        this.client_id.setText(String.valueOf(f.getClient_id()));
        this.text.setText(f.getText());
        this.topic.setText(f.getTopic());
        this.type.setText(f.getType());
        this.mail.setText(f.getEmail());
        this.date.setText((f.getFeedback_date().toString()));
    }

    @FXML
    private void Modifyf(ActionEvent event) {
    
        TextField email = (TextField)parent.getChildren().get(2);
        TextField topic = (TextField)parent.getChildren().get(5);
        TextField type = (TextField)parent.getChildren().get(7);
        TextArea text = (TextArea)parent.getChildren().get(10);
        Label id = (Label)parent.getChildren().get(11);
        email.setText(this.mail.getText());
        topic.setText(this.topic.getText());
        text.setText(this.text.getText());
        type.setText(this.type.getText());
        id.setText(this.feedback_id.getText());
    }

    @FXML
    private void Deletef(ActionEvent event) {
        
        
         Feedback f = new Feedback();
        f.setFeedback_id(Integer.parseInt(this.feedback_id.getText()));
        ServiceFeedback sf = new ServiceFeedback();
            sf.DeleteFeedback(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Report Deleted Successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
    }
    
}
