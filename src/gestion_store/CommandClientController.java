/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Command;
import Service.ServiceCommand;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CommandClientController implements Initializable {

    @FXML
    private VBox listpro;
    @FXML
    private ScrollPane scroller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.listpro.getChildren().clear();
            ServiceCommand sc = new ServiceCommand();
            List<Command> l =  sc.showClientCommands(2);
            try{
                for(Command a : l){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("CommandView.fxml"));
                    Node postbox = loader.load();
                    CommandViewController pc = loader.getController();
                    pc.setData(a);
                    this.listpro.getChildren().add(postbox);
                }
            }catch(IOException e){
                   e.printStackTrace();
            }
    }    

    
}
