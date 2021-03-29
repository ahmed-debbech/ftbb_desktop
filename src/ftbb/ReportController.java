/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Report;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author narug
 */
public class ReportController implements Initializable {

    @FXML
    private TextField tfcmd;
    @FXML
    private TextField tfemail;
    @FXML
    private TextArea tfdesc;
    @FXML
    private Label rld;
    @FXML
    private VBox listreport;
    @FXML
    private AnchorPane parent;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void ShowReports(ActionEvent event) throws SQLException, IOException {

        this.listreport.getChildren().clear();
        ServiceReport sr = new ServiceReport();
           List<Report> list =  sr.ShowReport();
            try{
                
                for(Report r : list){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXMLreportview.fxml"));
                    Node postbox = loader.load();
                    FXMLreportviewController pc = loader.getController();
                    pc.setData(r, parent);
                    this.listreport.getChildren().add(postbox);
                    
                }
            }catch(IOException e){
                  System.out.println("error");
            }
    }

        
    

    

    @FXML
    private void subdes(ActionEvent event) {
        ServiceReport sr = new ServiceReport();
        Report r = new Report(Integer.parseInt(rld.getText()), tfdesc.getText());
        sr.ModifyReport(r);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Note!");
        alert.setHeaderText(null);
        alert.setContentText("Report modified successfully.");
        Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    private void addreport(ActionEvent event) {

        Report r = new Report();

        if (tfcmd.getText().equals("") || tfemail.getText().equals("") || tfdesc.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Please Type!.");
            Optional<ButtonType> result = alert.showAndWait();

        } else if (tfemail.getText().indexOf('@') == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("Wrong Email !.");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (!tfcmd.getText().matches("\\d*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("numbers only  !.");
            Optional<ButtonType> result = alert.showAndWait();

        } else {
            Integer holder = Integer.parseInt(tfcmd.getText());
            r.setCommand_id(holder);
            r.setEmail(tfemail.getText());
            r.setDescription(tfdesc.getText());
            ServiceReport sr = new ServiceReport();
            sr.AddReport(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Report added successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

}
