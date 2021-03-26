/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Enitity.Comment;
import Service.ServiceLikes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.Utilities;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class CommentAdminViewController implements Initializable {

    @FXML
    private Label client_name;
    @FXML
    private Label content;
    @FXML
    private Label likes;
    @FXML
    private Label date;
    @FXML
    private Label comment_id;
    @FXML
    private CheckBox ban;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Comment c){
        System.out.println("teser eeee");
        this.comment_id.setText(String.valueOf(c.getId()));
        this.client_name.setText(c.getClient_name());
        this.content.setText(c.getContent());
        this.date.setText(Utilities.timestampConverter(c.getDate()));
        ServiceLikes sl = new ServiceLikes();
        int nm = sl.countLikes(-1, Integer.parseInt(comment_id.getText()));
        this.likes.setText(String.valueOf(nm) + " likes");
    }
    public AnchorPane getNode(){
        return this.anchor;
    }
}
