/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Comment;
import Enitity.Like;
import Service.ServiceComment;
import Service.ServiceLikes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Utilities;

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
    @FXML
    private ImageView delete;
    @FXML
    private Label client_id;
    @FXML
    private Label item_pos;
    
    private VBox parent_list;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickLike(MouseEvent event) {
        ServiceLikes sl = new ServiceLikes();
        Like l = new Like(-1, Integer.parseInt(comment_id.getText()), 122);
        if(!sl.isExisted(l)){
            // add the like
            sl.add(l);
            this.like_icon.setImage(new Image("resources/like-press.png"));
            int x= Integer.parseInt(this.like_number.getText());
            x++;
            this.like_number.setText(String.valueOf(x));
        }else{
            //else remove it
            sl.remove(l);
            this.like_icon.setImage(new Image("resources/like.png"));
            int x= Integer.parseInt(this.like_number.getText());
            x--;
            this.like_number.setText(String.valueOf(x));
        }
    }
    public void setData(Comment c, int pos, VBox parent){
        this.parent_list = parent;
        this.item_pos.setText(String.valueOf(pos));
         this.client_id.setText(String.valueOf(c.getClient_id()));
        if(this.client_id.getText().equals("122")){
                this.delete.setVisible(true);
        }else{
            this.delete.setVisible(false);
        }
        this.client_name.setText(c.getClient_name());
        this.content.setText(c.getContent());
        this.date.setText(Utilities.timestampConverter(c.getDate()));
        this.comment_id.setText(String.valueOf(c.getId()));
         ServiceLikes sl = new ServiceLikes();
        if(sl.getLike(-1, Integer.parseInt(comment_id.getText()), 122) == true){
            this.like_icon.setImage(new Image("resources/like-press.png"));
        }else{
           this.like_icon.setImage(new Image("resources/like.png"));
        }
        int nm = sl.countLikes(-1, Integer.parseInt(comment_id.getText()));
        this.like_number.setText(String.valueOf(nm));
    }

    @FXML
    private void deleteComment(MouseEvent event) {
        if(Utilities.alertConfirmation("Warning", "Are you sure you want to delete this comment?") == true){
            ServiceComment sc = new ServiceComment();
            Comment a = new Comment();
            a.setId(Integer.parseInt(this.comment_id.getText()));
            sc.delComment(a);
            this.parent_list.getChildren().remove(Integer.parseInt(this.item_pos.getText()));
        }
    }
}
