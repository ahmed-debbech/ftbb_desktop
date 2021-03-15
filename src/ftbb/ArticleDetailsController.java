/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Article;
import Enitity.Comment;
import Enitity.Like;
import Service.ServiceArticle;
import Service.ServiceComment;
import Service.ServiceLikes;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Passable;
import utils.Utilities;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ArticleDetailsController implements Initializable {

    private Article ref;
    @FXML
    private ImageView image;
    @FXML
    private Label title;
    @FXML
    private Label text;
    @FXML
    private HBox likes;
    @FXML
    private ImageView like_icon;
    @FXML
    private Label like_number;
    @FXML
    private HBox comments;
    @FXML
    private Label com_number;
    @FXML
    private Label author;
    @FXML
    private Label date;
    @FXML
    private ScrollPane scroller;
    @FXML
    private VBox comlist;
    @FXML
    private TextField comment_content;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceArticle sa = new ServiceArticle();
        int id= Passable.getInstance().getNumberData();
        Passable.getInstance().erase();
         this.ref = sa.getArticle(id);
         System.out.println("ldld" + this.ref.getArticle_id());
           this.title.setText(ref.getTitle());
           this.date.setText(ref.getDate().toString());
           this.text.setText(ref.getText());
           File file = new File(ref.getPhoto_url());
        Image im = null;
        if(file.exists()){
                 im = new Image(file.toURI().toString());
        }else{
            im = new Image("resources/default-article.jpg");
        }
        this.image.setImage(im);
        
        ServiceLikes sl = new ServiceLikes();
        if(sl.getLike(ref.getArticle_id(), -1, 122) == true){
            this.like_icon.setImage(new Image("resources/like-press.png"));
        }else{
           this.like_icon.setImage(new Image("resources/like.png"));
        }
        int nm = sl.countLikes(ref.getArticle_id(), -1);
        this.like_number.setText(String.valueOf(nm));
        
        //**** load comments
        this.comlist.getChildren().clear();
        ServiceComment sc = new ServiceComment();
           List<Comment> list =  sc.showComment(String.valueOf(ref.getArticle_id()));
            try{
                for(Comment a : list){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXMLCommentView.fxml"));
                    Node postbox = loader.load();
                    CommentViewController pc = loader.getController();
                    pc.setData(a);
                    this.comlist.getChildren().add(postbox);
                }
            }catch(IOException e){
                  System.out.println("error");
            }
    }    

    @FXML
    private void passDetails(MouseEvent event) {
    }

    @FXML
    private void onClickLike(MouseEvent event) {
         ServiceLikes sl = new ServiceLikes();
        Like l = new Like(ref.getArticle_id(), -1, 122);
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

    @FXML
    private void comment(ActionEvent event) {
        String comment = this.comment_content.getText();
        ServiceComment sc = new ServiceComment();
        Comment a = new Comment();
        a.setClient_id(122);
        a.setArticle_id(this.ref.getArticle_id());
        a.setContent(comment);
        a.setId(Utilities.generatedId("comment", "id"));
        sc.addComment(a);
    }
    
}
