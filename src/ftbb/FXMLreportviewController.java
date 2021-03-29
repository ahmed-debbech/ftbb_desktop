/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Entites.Report;
import Services.ServiceReport;
import Utils.Utilities;
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

/**
 * FXML Controller class
 *
 * @author narug
 */
public class FXMLreportviewController implements Initializable {

    @FXML
    private Label command_id;
    @FXML
    private Label report_id;
    @FXML
    private Label mail;
    @FXML
    private Label client_id;
    @FXML
    private Label descr;
    @FXML
    private Label date;
    
    private AnchorPane parent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Report r, AnchorPane parent){
        
        this.parent = parent;
        this.command_id.setText(String.valueOf(r.getCommand_id()));
        this.report_id.setText(String.valueOf(r.getReport_id()));
        this.client_id.setText(String.valueOf(r.getClient_id()));
        this.descr.setText(r.getDescription());
        this.mail.setText(r.getEmail());
        this.date.setText((r.getReport_date().toString()));
    }

    @FXML
    private void ModifyReports(ActionEvent event) {
        TextField cmdid = (TextField)parent.getChildren().get(2);
        TextField email = (TextField)parent.getChildren().get(4);
        TextArea desc = (TextArea)parent.getChildren().get(6);
        Label id = (Label)parent.getChildren().get(9);
        cmdid.setText(this.command_id.getText());
        email.setText(this.mail.getText());
        desc.setText(this.descr.getText());
        id.setText(this.report_id.getText());
    }

     @FXML
    private void onDelete(ActionEvent event) {
        Report r = new Report();
        r.setReport_id(Integer.parseInt(this.report_id.getText()));
        ServiceReport sr = new ServiceReport();
            sr.DeleteReport(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Note!");
            alert.setHeaderText(null);
            alert.setContentText("Report Deleted Successfully!.");
            Optional<ButtonType> result = alert.showAndWait();
    }
}
