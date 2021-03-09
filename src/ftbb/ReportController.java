 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Report;
import Services.ServiceReport;
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
import javafx.scene.control.ButtonType;
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
public class ReportController implements Initializable {

    @FXML
    private TableView<Report> listreport;
    @FXML
    private TextField tfcmd;
    @FXML
    private TextField tfemail;
    @FXML
    private TextArea tfdesc;
    @FXML
    private Label rld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn t1 = new TableColumn("Report ID ");
        t1.setCellValueFactory(new PropertyValueFactory<Report, String>("report_id"));
        TableColumn t2 = new TableColumn("Client ID ");
        t2.setCellValueFactory(new PropertyValueFactory<Report, String>("client_id"));
        TableColumn t3 = new TableColumn("Command ID");
        t3.setCellValueFactory(new PropertyValueFactory<Report, String>("command_id"));
        TableColumn t4 = new TableColumn("Report Date");
        t4.setCellValueFactory(new PropertyValueFactory<Report, String>("report_date"));
         TableColumn t5 = new TableColumn("Email");
        t5.setCellValueFactory(new PropertyValueFactory<Report, String>("email"));
        TableColumn t6 = new TableColumn("Description");
        t6.setCellValueFactory(new PropertyValueFactory<Report, String>("description"));
        listreport.getColumns().addAll(t1,t2,t3,t4,t5,t6);
    }    

    @FXML
    private void ShowReports(ActionEvent event) throws SQLException {
        
        ServiceReport sr=new ServiceReport();
        List<Report> r = sr.ShowReport();
        ObservableList<Report> data =FXCollections.observableArrayList(r);
        listreport.setItems(data);
    }

    @FXML
    private void modifyreport(ActionEvent event) {
        
        Report r =listreport.getSelectionModel().getSelectedItem();
        if(r == null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Photo title from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
        rld.setText(String.valueOf(r.getReport_id()));
        tfdesc.setText(r.getDescription());
        }
    }

    @FXML
    private void deletereport(ActionEvent event) {
        Report r =listreport.getSelectionModel().getSelectedItem();
          if(r == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Report title from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
           
           ServiceReport sr= new ServiceReport();
           sr.DeleteReport(r);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Report Deleted Successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
    }
    }

    @FXML
    private void subdes(ActionEvent event) {
        ServiceReport sr=new ServiceReport();
        Report r = new Report(Integer.parseInt(rld.getText()),tfdesc.getText());
        sr.ModifyReport(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Report modified successfully.");
            Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    private void addreport(ActionEvent event) {
        Report r =new Report();
        String cmdid = tfcmd.getText();
        int cmdid2= Integer.parseInt(cmdid);
            r.setCommand_id(cmdid2);
            r.setEmail(tfemail.getText());
            r.setDescription(tfdesc.getText());
            
       if((tfcmd.getText().equals("")) || (tfemail.getText().equals("")) || (tfdesc.getText().equals(""))){
           
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please Type!.");
            Optional<ButtonType> result = alert.showAndWait();
            
        }else{
           ServiceReport sr=new ServiceReport();
           sr.AddReport(r);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Report added successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        
    }
    
    
    
    
}
