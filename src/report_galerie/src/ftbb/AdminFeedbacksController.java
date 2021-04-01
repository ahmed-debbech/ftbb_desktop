/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.ftbb;


import ftbb.RespondfeedbackController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidev.AdminhomeController;
import report_galerie.src.Entites.Feedback;
import report_galerie.src.Services.ServiceAdminFeedback;
import utils.Passable;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class AdminFeedbacksController implements Initializable {

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
            
            Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/report_galerie/src/ftbb/respondfeedback.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
        }
    }
    
}
