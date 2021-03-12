/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Article;
import Service.ServiceArticle;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import utils.Passable;
import utils.Utilities;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class PostsAdminController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setStyle("-fx-border-color: black; -fx-border-width: 1px 1px 1px 1px");
    }    

    @FXML
    private void showComments(ActionEvent event) throws IOException {
        Passable p = Passable.getInstance();
            p.setTextData(String.valueOf(ref.getArticle_id()));
            System.out.println("iddsw" + p.getTextData() );
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("FXMLComments.fxml").openStream());
            Stage stage = new Stage();
            stage.setTitle("Comments of article: " + ref.getArticle_id());
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void modArticle(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("FXMLArticlesEditor.fxml").openStream());
            Passable p = Passable.getInstance();
            p.setAnyData(this.ref);
            p.setNumberData(1);
            Stage stage = new Stage();
            stage.setTitle("Article Editor");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void delArticle(ActionEvent event) {
           ServiceArticle sa = new ServiceArticle();
           if(sa.delArticle(this.ref) == true){
               Utilities.alert("SUCCESS", "Element deleted successfully");
            }
    }
    
    
    public void setData(Article a){
        File file = new File(a.getPhoto_url());
        Image im = null;
        if(file.exists()){
                 im = new Image(file.toURI().toString());
        }else{
            im = new Image("resources/default-article.jpg");
        }
        this.image.setImage(im);
        this.text.setText(a.getText());
        this.title.setText(a.getTitle());
        this.date.setText(a.getDate().toString());
        this.num_likes.setText("0" + " likes");
        this.ref = a;
    }
}
