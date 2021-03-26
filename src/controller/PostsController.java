/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Enitity.Article;
import Enitity.Like;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.Passable;
import utils.Utilities;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class PostsController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Text title;
    @FXML
    private HBox likes;
    @FXML
    private Label like_number;
    @FXML
    private HBox comments;
    @FXML
    private Label com_number;
    @FXML
    private Label date;
    @FXML
    private ImageView like_icon;
    @FXML
    private Label article_id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setData(Article a){
        System.out.println("eeee: " + a.getArticle_id());
        this.article_id.setText(String.valueOf(a.getArticle_id()));
           this.title.setText(a.getTitle());
           this.date.setText(Utilities.timestampConverter(a.getDate()));
        Image im = null;
        try {
            URL imageUrl = new URL(a.getPhoto_url());
            InputStream in = imageUrl.openStream();
            BufferedImage image = ImageIO.read(in);
            im = SwingFXUtils.toFXImage(image, null);
            in.close();
        }catch (IOException ioe) {
           im = new Image("resources/default-article.jpg");
        }
        this.image.setImage(im);
        ServiceLikes sl = new ServiceLikes();
        if(sl.getLike(Integer.parseInt(article_id.getText()), -1, 122) == true){
            this.like_icon.setImage(new Image("resources/like-press.png"));
        }else{
           this.like_icon.setImage(new Image("resources/like.png"));
        }
        int nm = sl.countLikes(Integer.parseInt(article_id.getText()), -1);
        this.like_number.setText(String.valueOf(nm));
        ServiceComment ser = new ServiceComment();
        int nm_cm = ser.countComments(String.valueOf(a.getArticle_id()));
        this.com_number.setText(String.valueOf(nm_cm));
    }

    @FXML
    private void onClickLike(MouseEvent event) {     
        ServiceLikes sl = new ServiceLikes();
        Like l = new Like(Integer.parseInt(article_id.getText()), -1, 122);
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
    private void passDetails(MouseEvent event) throws IOException {
    Passable p = Passable.getInstance();
            p.setNumberData(Integer.parseInt(this.article_id.getText()));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLArticleDetails.fxml"));
            Pane root1 =  fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(this.title.getText());
            stage.setScene(new Scene(root1));  
            stage.show();
    }
    
}
