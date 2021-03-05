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
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ShowCommandsController implements Initializable {

    @FXML
    private TableView<Command> lcommand;

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
                lcommand.getColumns().addAll(t1,t2,t3,t4,t5);

    }    

    @FXML
    private void refreshButton(ActionEvent event) {
        ServiceCommand sc = new ServiceCommand();
        List<Command> l = sc.showCommand();
        
        ObservableList<Command> data=FXCollections.observableArrayList(l);
        lcommand.setItems(data);
    }

    @FXML
    private void onValid(ActionEvent event) {
        Command c  = lcommand.getSelectionModel().getSelectedItem();
        ServiceCommand sc = new ServiceCommand();
        sc.updateCommand(c, 1);
        
    }

    @FXML
    private void onCancel(ActionEvent event) {
        Command c  = lcommand.getSelectionModel().getSelectedItem();
        ServiceCommand sc = new ServiceCommand();
        sc.updateCommand(c, 2);
    }
    
}
