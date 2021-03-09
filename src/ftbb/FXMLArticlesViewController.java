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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class FXMLArticlesViewController implements Initializable {

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
        // TODO
    }    

    @FXML
    private void addArticle(ActionEvent event) {
    }

    @FXML
    private void refreshArticle(ActionEvent event) {
        ServiceArticle sa = new ServiceArticle();
            this.articles =  sa.showArticle();
            int row = 1, cl =0;
            try{
                for(Article a : this.articles){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXMLPostsAdmin.fxml"));
                    Node postbox = loader.load();
                    //PostsAdminController pc = loader.getController();
                    //pc.setData(a);
                    this.vbox_articles.getChildren().add(postbox);
                }
            }catch(IOException e){
                   e.printStackTrace();
            }
    }
    
}
