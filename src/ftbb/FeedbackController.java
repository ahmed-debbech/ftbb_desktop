/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Feedback;
import Services.ServiceFeedback;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class FeedbackController implements Initializable {

    @FXML
    private TableView<Feedback> listfeedbacks;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn t1 = new TableColumn("Feedback ID ");
        t1.setCellValueFactory(new PropertyValueFactory<Feedback, String>("feedback_id"));
        TableColumn t2 = new TableColumn("Client ID ");
        t2.setCellValueFactory(new PropertyValueFactory<Feedback, String>("client_id"));
        TableColumn t3 = new TableColumn("Feedback Date");
        t3.setCellValueFactory(new PropertyValueFactory<Feedback, String>("feedback_date"));
        TableColumn t4 = new TableColumn("E-mail");
        t4.setCellValueFactory(new PropertyValueFactory<Feedback, String>("email"));
        TableColumn t5 = new TableColumn("Topic");
        t5.setCellValueFactory(new PropertyValueFactory<Feedback, String>("topic"));
        TableColumn t6 = new TableColumn("Text");
        t6.setCellValueFactory(new PropertyValueFactory<Feedback, String>("text"));
        TableColumn t7 = new TableColumn("Type");
        t7.setCellValueFactory(new PropertyValueFactory<Feedback, String>("type"));
        
        listfeedbacks.getColumns().addAll(t1,t2,t3,t4,t5,t6,t7);
    }    

    @FXML
    private void ShowFeedbacks(ActionEvent event) throws SQLException {
        
        ServiceFeedback sf=new ServiceFeedback();
        List<Feedback> f = sf.ShowFeedback();
        ObservableList<Feedback> data =FXCollections.observableArrayList(f);
        listfeedbacks.setItems(data);
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
            
        }else{
           ServiceFeedback sf=new ServiceFeedback();
           sf.AddFeedback(f);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Feedback added successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
    }

   
    
}

    @FXML
    private void modifyfeed(ActionEvent event) {
        
        Feedback f =listfeedbacks.getSelectionModel().getSelectedItem();
        if(f == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Feedback title from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
        fdb.setText(String.valueOf(f.getFeedback_id()));
        tftext.setText(f.getText());
        }
    }

    @FXML
    private void deletefeed(ActionEvent event) {
        
        Feedback f =listfeedbacks.getSelectionModel().getSelectedItem();
          if(f == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Feedback title from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
           
           ServiceFeedback sf= new ServiceFeedback();
           sf.DeleteFeedback(f);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Feedback Deleted Successfully!.");
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
