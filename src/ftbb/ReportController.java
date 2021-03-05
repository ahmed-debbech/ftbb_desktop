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
public class ReportController implements Initializable {

    @FXML
    private TableView<Report> listreport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ShowReports(ActionEvent event) throws SQLException {
        
        ServiceReport sr=new ServiceReport();
        List<Report> r = sr.ShowReport();
        TableColumn t1 = new TableColumn("Report ID ");
        t1.setCellValueFactory(new PropertyValueFactory<Report, String>("report_id"));
        TableColumn t2 = new TableColumn("Client ID ");
        t2.setCellValueFactory(new PropertyValueFactory<Report, String>("client_id"));
        TableColumn t3 = new TableColumn("Command ID");
        t3.setCellValueFactory(new PropertyValueFactory<Report, String>("command_id"));
        TableColumn t4 = new TableColumn("Report Date");
        t4.setCellValueFactory(new PropertyValueFactory<Report, String>("report_date"));
        TableColumn t5 = new TableColumn("Description");
        t5.setCellValueFactory(new PropertyValueFactory<Report, String>("description"));
        listreport.getColumns().addAll(t1,t2,t3,t4,t5);
        ObservableList<Report> data =FXCollections.observableArrayList(r);
        listreport.setItems(data);
    }
    
}
