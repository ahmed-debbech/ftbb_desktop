/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Report;
import Services.ServiceAdminReport;
import Services.ServiceReport;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class AdminReportsController implements Initializable {

    @FXML
    private TableView<Report> listreports;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showreports(ActionEvent event) throws SQLException {
        ServiceAdminReport sr=new ServiceAdminReport();
        List<Report> r = sr.ShowReport();
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
        listreports.getColumns().addAll(t1,t2,t3,t4,t5,t6);
        ObservableList<Report> data =FXCollections.observableArrayList(r);
        listreports.setItems(data);
    }

    @FXML
    private void respondreport(ActionEvent event) throws IOException {
        Report r =listreports.getSelectionModel().getSelectedItem();
        if(r == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Report from the list.");
            Optional<ButtonType> result = alert.showAndWait();
        }else{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Respondreport.fxml"));
            Parent root = loader.load();
            RespondreportController cr = loader.getController();
            cr.pass(r.getEmail());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
    
}
}
