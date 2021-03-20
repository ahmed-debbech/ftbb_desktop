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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
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
    @FXML
    private ComboBox<String> filter;
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
           this.date.setText(Utilities.timestampConverter(ref.getDate()));
           this.text.setText(ref.getText());
           this.filter.getItems().add("None");
        this.filter.getItems().add("New");
        this.filter.getItems().add("Hot");

        Image im = null;
        try {
            URL imageUrl = new URL(ref.getPhoto_url());
            InputStream in = imageUrl.openStream();
            BufferedImage image = ImageIO.read(in);
            im = SwingFXUtils.toFXImage(image, null);
            in.close();
        }catch (IOException ioe) {
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
        this.author.setText(ref.getAuthor());
        
        //**** load comments
        loadComments();
    }    
    private void loadComments(){
        this.comlist.getChildren().clear();
        ServiceComment sc = new ServiceComment();
           List<Comment> list =  sc.showComment(String.valueOf(ref.getArticle_id()));
            try{
                int pos = 0;
                for(Comment a : list){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXMLCommentView.fxml"));
                    Node postbox = loader.load();
                    CommentViewController pc = loader.getController();
                    pc.setData(a, pos, this.comlist);
                    this.comlist.getChildren().add(postbox);
                    pos++;
                }
            }catch(IOException e){
                  System.out.println("error");
            }
    }
    private void loadComments(List<Comment> li){
        this.comlist.getChildren().clear();
        ServiceComment sc = new ServiceComment();
           List<Comment> list =  li;
            try{
                int pos = 0;
                for(Comment a : list){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXMLCommentView.fxml"));
                    Node postbox = loader.load();
                    CommentViewController pc = loader.getController();
                    pc.setData(a, pos, this.comlist);
                    this.comlist.getChildren().add(postbox);
                    pos++;
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
        ServiceArticle ss = new ServiceArticle();
        if(this.filter.getSelectionModel().getSelectedIndex() == 1){
            System.out.println("first is selected");
            List<Comment> list = ss.sortByNew(ref.getArticle_id());
            loadComments(list);
        }else{  
            if(this.filter.getSelectionModel().getSelectedIndex() == 2){
                System.out.println("second is selected");
                List<Comment> list = ss.sortByHot(ref.getArticle_id());
                loadComments(list);
            }
        }
        this.comment_content.setText("");
    }

    @FXML
    private void itemChanged(ActionEvent event) {
        ServiceArticle sc = new ServiceArticle();
        
        if(this.filter.getSelectionModel().getSelectedIndex() == 1){
            System.out.println("first is selected");
            List<Comment> list = sc.sortByNew(ref.getArticle_id());
            loadComments(list);
        }else{  
            if(this.filter.getSelectionModel().getSelectedIndex() == 2){
                System.out.println("second is selected");
                List<Comment> list = sc.sortByHot(ref.getArticle_id());
                loadComments(list);
            }
        }
    }
    
}
