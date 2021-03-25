/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Command;
import Entities.Product;
import Service.ServiceCommand;
import Service.ServiceProduct;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PdfViewController implements Initializable {

    @FXML
    private Pane corpsrapport;
    @FXML
    private AnchorPane report;
    @FXML
    private Button export;
    @FXML
    private TableView tab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       TableColumn t1 = new TableColumn("command_id");
        t1.setCellValueFactory(new PropertyValueFactory<Command, Integer>("command_id"));
        TableColumn t2 = new TableColumn("id_client");
        t2.setCellValueFactory(new PropertyValueFactory<Command, Integer>("id_client"));
        TableColumn t3 = new TableColumn("date_command");
        t3.setCellValueFactory(new PropertyValueFactory<Command, Date>("date_command"));
        TableColumn t4 = new TableColumn("status");
        t4.setCellValueFactory(new PropertyValueFactory<Command, Integer>("status"));
        TableColumn t5 = new TableColumn("total_price");
        t5.setCellValueFactory(new PropertyValueFactory<Command, Integer>("total_price"));
        tab.getColumns().addAll(t1,t2,t3,t4,t5);
          export.setOnAction(event ->{pdf();});
    }    
    @FXML
    private void pdf() {
        
         System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
             Window primaryStage = null;
             job.showPrintDialog(primaryStage); 
            
             Node root = this.report ;    
              job.printPage(root);
              job.endJob();
  }
    
}
    
    

}
