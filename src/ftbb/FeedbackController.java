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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class FeedbackController implements Initializable {

    @FXML
    private TableView<Feedback> listfeedbacks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ShowFeedbacks(ActionEvent event) throws SQLException {
        
        ServiceFeedback sf=new ServiceFeedback();
        List<Feedback> f = sf.ShowFeedback();
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
        ObservableList<Feedback> data =FXCollections.observableArrayList(f);
        listfeedbacks.setItems(data);
    }
    
}
