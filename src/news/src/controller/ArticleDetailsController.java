/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.src.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import news.src.Enitity.Article;
import news.src.Enitity.Comment;
import news.src.Enitity.Like;
import news.src.Service.ServiceArticle;
import news.src.Service.ServiceComment;
import news.src.Service.ServiceLikes;
import utils.Passable;
import news.src.utils.Utilities;
import user.src.Entities.Client;
import user.src.Service.ServiceClient;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ArticleDetailsController implements Initializable {
    
    public static String CLIENT_ID = String.valueOf(Utilities.getClient().getId());
    
    private Article ref;
    @FXML
    private ImageView image;
    @FXML
    private Label title;
    @FXML
    private Text text;
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
    @FXML
    private Button sharefb;
    @FXML
    private Button sharetw;
    @FXML
    private TitledPane tiledpane;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceArticle sa = new ServiceArticle();
        int id= Passable.getInstance().getNumberData();
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
            System.out.println("edeed");
           im = new Image("/news/src/resources/default-article.jpg");
        }
        this.image.setImage(im);
        
        ServiceLikes sl = new ServiceLikes();
        if(sl.getLike(ref.getArticle_id(), -1, Integer.parseInt(CLIENT_ID)) == true){
            this.like_icon.setImage(new Image("/news/src/resources/like-press.png"));
        }else{
           this.like_icon.setImage(new Image("/news/src/resources/like.png"));
        }
        int nm = sl.countLikes(ref.getArticle_id(), -1);
        this.like_number.setText(String.valueOf(nm));
        this.author.setText(ref.getAuthor());
        ServiceComment ser = new ServiceComment();
        int nm_cm = ser.countComments(String.valueOf(ref.getArticle_id()));
        this.com_number.setText(String.valueOf(nm_cm));
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
                    loader.setLocation(getClass().getResource("/news/src/view/FXMLCommentClientView.fxml"));
                    Node postbox = loader.load();
                    CommentClientViewController pc = loader.getController();
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
                    loader.setLocation(getClass().getResource("/news/src/view/FXMLCommentClientView.fxml"));
                    Node postbox = loader.load();
                    CommentClientViewController pc = loader.getController();
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
        Like l = new Like(ref.getArticle_id(), -1,Integer.parseInt(CLIENT_ID));
        if(!sl.isExisted(l)){
            // add the like
            sl.add(l);
            this.like_icon.setImage(new Image("/news/src/resources/like-press.png"));
            int x= Integer.parseInt(this.like_number.getText());
            x++;
            this.like_number.setText(String.valueOf(x));
        }else{
            //else remove it
            sl.remove(l);
            this.like_icon.setImage(new Image("/news/src/resources/like.png"));
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
        a.setClient_id(Integer.parseInt(CLIENT_ID));
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
            }else{
                loadComments();
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

    @FXML
    private void shareFacebook(ActionEvent event) {
         try {
             String u  = "https://www.facebook.com/dialog/share?" +
"app_id=1155678251616612" +
"&display=popup" +
                     "&quote=" +URLEncoder.encode(this.title.getText(),  StandardCharsets.UTF_8.toString())+ 
"&href=https%3A%2F%2Fwww.ftbb.org.tn%2F";
            URI uri= new URI(u);

            java.awt.Desktop.getDesktop().browse(uri);
             System.out.println("Web page opened in browser");

       } catch (Exception e) {

        e.printStackTrace();
       }
    }

    @FXML
    private void shareTwitter(ActionEvent event) {
         try {

             String k = "http://twitter.com/share?text="+URLEncoder.encode(this.title.getText(),  StandardCharsets.UTF_8.toString())+"%0A&url=https%3A%2F%2Fwww.ftbb.org.tn%2F";
            URI uri= new URI(k);

            java.awt.Desktop.getDesktop().browse(uri);
             System.out.println("Web page opened in browser");

       } catch (Exception e) {

        e.printStackTrace();
       }
    }
    
}
