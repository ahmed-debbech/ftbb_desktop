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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import news.src.Enitity.Article;
import news.src.Service.ServiceArticle;
import news.src.Service.ServiceComment;
import news.src.Service.ServiceLikes;
import utils.Passable;
import news.src.utils.Utilities;
import pidev.AdminhomeController;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ArticleAdminViewController implements Initializable {

    private Article ref;
    @FXML
    private Label title;
    @FXML
    private Label text;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label date;
    @FXML
    private Label num_likes;
    @FXML
    private Button com_but;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setStyle("-fx-border-color: #e6e6e6; -fx-border-width: 0.5px 0.5px 0.5px 0.5px");
    }    

    @FXML
    private void showComments(ActionEvent event) throws IOException {
        Passable p = Passable.getInstance();
            p.setTextData(String.valueOf(ref.getArticle_id()));
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("/news/src/view/FXMLCommentsAdmin.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Comments of article: " + ref.getArticle_id());
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void modArticle(ActionEvent event) throws IOException {
            Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            Passable.getInstance().setNumberData(1);
                    Passable.getInstance().setAnyData(ref);

            node = (Node)FXMLLoader.load(getClass().getResource("/news/src/view/FXMLArticlesEditor.fxml"));
            AnchorPane pane = (AnchorPane)Passable.getInstance().getContainer();
            pane.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(AdminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void delArticle(ActionEvent event) {
           ServiceArticle sa = new ServiceArticle();
           if(sa.delArticle(this.ref) == true){
               Utilities.alert("SUCCESS", "Element deleted successfully");
            }
    }
    
    
    public void setData(Article a){
        Image im = null;
        try {
            URL imageUrl = new URL(a.getPhoto_url());
            InputStream in = imageUrl.openStream();
            BufferedImage image = ImageIO.read(in);
            im = SwingFXUtils.toFXImage(image, null);
            in.close();
        }catch (IOException ioe) {
           im = new Image("/news/src/resources/default-article.jpg");
        }
        this.image.setImage(im);
        this.text.setText(a.getText());
        this.title.setText(a.getTitle());
        this.date.setText(Utilities.timestampConverter(a.getDate()));
        ServiceLikes sl = new ServiceLikes();
        int nm = sl.countLikes(a.getArticle_id(), -1);
        this.num_likes.setText(String.valueOf(nm)+ " likes");
        ServiceComment sc = new ServiceComment();
        int count = sc.countComments(String.valueOf(a.getArticle_id()));
        this.com_but.setText(count + " comments");
        this.ref = a;
    }
}
