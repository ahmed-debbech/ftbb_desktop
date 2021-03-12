/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Comment;
import Service.ServiceLikes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class CommentViewController implements Initializable {

    @FXML
    private Label client_name;
    @FXML
    private Label content;
    @FXML
    private HBox likes;
    @FXML
    private ImageView like_icon;
    @FXML
    private Label like_number;
    @FXML
    private Label date;
    @FXML
    private Label comment_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickLike(MouseEvent event) {
    }
    public void setData(Comment c){
        this.client_name.setText(c.getClient_name());
        this.content.setText(c.getContent());
        this.date.setText(c.getDate().toString());
       // ServiceLikes sl = new ServiceLikes();
        //int nm = sl.countLikes(-1,Integer.parseInt(this.comment_id.getText()));
        //this.like_number.setText(String.valueOf(nm));
    }
}
