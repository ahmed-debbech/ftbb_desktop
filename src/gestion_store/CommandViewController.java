/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Command;
import Entities.Product;
import Service.ServiceCommand;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CommandViewController implements Initializable {

    @FXML
    private Label id;
    @FXML
    private Label id_client;
    @FXML
    private Label status;
    @FXML
    private Label date;
    @FXML
    private AnchorPane parent;
    @FXML
    private Label total;
    @FXML
    private TitledPane titledpane;
    @FXML
    private TableView<Product> listprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

     public void setData(Command a){
        this.id.setText("Command id:" + String.valueOf(a.getCommand_id()));
        this.id_client.setText("Client id:" + String.valueOf(a.getId_client()));
        if(a.getStatus() == 1){
        this.status.setText("Status: valid");
        }else{
            this.status.setText("Status: not valid yet");
        }
        this.date.setText(a.getDate_command().toString());
        this.total.setText("Total price" + String.valueOf(a.getTotal_price()) + " DT");
        this.titledpane.setExpanded(false);
        TableColumn t1 = new TableColumn("Name ");
        t1.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        TableColumn t2 = new TableColumn("Price ");
        t2.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        TableColumn t3 = new TableColumn("Details");
        t3.setCellValueFactory(new PropertyValueFactory<Product, String>("details"));
        ServiceCommand sc = new ServiceCommand();
        listprod.getColumns().addAll(t1,t2,t3);
        List<Product> list = sc.getProductCommand(a.getCommand_id(), a.getId_client());
        ObservableList<Product> data =FXCollections.observableArrayList(list);
            listprod.setItems(data);
    }

    @FXML
    private void showprod(ActionEvent event) {
        this.titledpane.setExpanded(true);
    }
}
