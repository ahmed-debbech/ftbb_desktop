/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Article;
import Service.ServiceArticle;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Passable;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ArticlesViewController implements Initializable {

    @FXML
    private ScrollPane articles_scroller;
    @FXML
    private VBox vbox_articles;
    
    private List<Article> articles;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshArticle(null);
    }    

    @FXML
    private void addArticle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("FXMLArticlesEditor.fxml").openStream());
            Passable p = Passable.getInstance();
            p.setNumberData(0);
            Stage stage = new Stage();
            stage.setTitle("Article Editor");
            stage.setScene(new Scene(root1));  
            stage.show();
    }

    @FXML
    private void refreshArticle(ActionEvent event) {
        ServiceArticle sa = new ServiceArticle();
        this.vbox_articles.getChildren().clear();
            this.articles =  sa.showArticle();
            try{
                for(Article a : this.articles){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXMLPostsAdmin.fxml"));
                    Node postbox = loader.load();
                    PostsAdminController pc = loader.getController();
                    pc.setData(a);
                    this.vbox_articles.getChildren().add(postbox);
                }
            }catch(IOException e){
                   e.printStackTrace();
            }
    }
    
}
