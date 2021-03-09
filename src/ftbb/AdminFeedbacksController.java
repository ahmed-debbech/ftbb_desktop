/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Feedback;
import Services.ServiceAdminFeedback;
import Services.ServiceFeedback;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class AdminFeedbacksController implements Initializable {

    @FXML
    private TableView<Feedback> listfeedbacks;
    @FXML
    private Label clem;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showfeedbacks(ActionEvent event) throws SQLException {
        
         ServiceAdminFeedback sf=new ServiceAdminFeedback();
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

    @FXML
    private void respondfeedbacks(ActionEvent event) throws IOException {
        Feedback f =listfeedbacks.getSelectionModel().getSelectedItem();
        if(f == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Feedback from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("respondfeedback.fxml"));
            Parent root = loader.load();
            RespondfeedbackController cf = loader.getController();
            cf.pass(f.getEmail());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        
       
        }
    }
    
}
