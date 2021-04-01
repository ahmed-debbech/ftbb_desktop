/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.src.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import news.src.Enitity.Article;
import news.src.Enitity.Like;
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
            System.out.println("img " + a.getPhoto_url());
            URL imageUrl = new URL(a.getPhoto_url());
            InputStream in = imageUrl.openStream();
            BufferedImage image = ImageIO.read(in);
            im = SwingFXUtils.toFXImage(image, null);
            in.close();
        }catch (IOException ioe) {
           im = new Image("/news/src/resources/default-article.jpg");
        }
        this.image.setImage(im);
        ServiceLikes sl = new ServiceLikes();
        ServiceClient si = new ServiceClient();
        Client  ccc = si.getA();
        if(sl.getLike(Integer.parseInt(article_id.getText()), -1, ccc.getId()) == true){
            this.like_icon.setImage(new Image("/news/src/resources/like-press.png"));
        }else{
           this.like_icon.setImage(new Image("/news/src/resources/like.png"));
        }
        int nm = sl.countLikes(Integer.parseInt(article_id.getText()), -1);
        this.like_number.setText(String.valueOf(nm));
        ServiceComment ser = new ServiceComment();
        int nm_cm = ser.countComments(String.valueOf(a.getArticle_id()));
        this.com_number.setText(String.valueOf(nm_cm));
    }

    @FXML
    private void onClickLike(MouseEvent event) {     
        ServiceClient si = new ServiceClient();
        Client  ccc = si.getA();
        ServiceLikes sl = new ServiceLikes();
        Like l = new Like(Integer.parseInt(article_id.getText()), -1, ccc.getId());
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
    private void passDetails(MouseEvent event) throws IOException {
    Passable p = Passable.getInstance();
            p.setNumberData(Integer.parseInt(this.article_id.getText()));
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/news/src/view/FXMLArticleDetails.fxml"));
            AnchorPane linker = (AnchorPane) Passable.getInstance().getContainer();
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(pidev.ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
