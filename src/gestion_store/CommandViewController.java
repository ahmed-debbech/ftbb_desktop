/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Command;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void detailsPressed(ActionEvent event) {
    }
     public void setData(Command a){
        this.id.setText(String.valueOf(a.getCommand_id()));
        this.id_client.setText(String.valueOf(a.getId_client()));
        this.status.setText(String.valueOf(a.getStatus()));
        this.date.setText(a.getDate_command().toString());
    }
}
