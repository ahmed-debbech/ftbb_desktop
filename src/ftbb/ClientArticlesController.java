/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Article;
import Service.ServiceArticle;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ClientArticlesController implements Initializable {

    @FXML
    private ScrollPane scroller;
    @FXML
    private GridPane article_grid;
    
    List<Article> articles;
    @FXML
    private TextField search;
    @FXML
    private ComboBox<String> sort;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sort.getItems().add("Newest");
        sort.getItems().add("Top in past 6 hours");
        sort.getItems().add("Top in past day");
        sort.getItems().add("Top of all time");
        sort.getSelectionModel().select("Newest");
            ServiceArticle sa = new ServiceArticle();
            this.articles =  sa.showArticle();
            loadArticles(this.articles);
    }    
    private void loadArticles(List<Article> art){
        article_grid.getChildren().clear();
            int row = 1, cl =0;
            try{
                for(Article a : art){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("FXMLPosts.fxml"));
                    Node postbox = loader.load();
                    PostsController pc = loader.getController();
                    pc.setData(a);
                    if(cl== 3){
                         cl= 0;
                         row++;
                    }
                    article_grid.add(postbox, cl++, row);
                }
            }catch(IOException e){
                System.out.println("no load for articles in client");
                   e.printStackTrace();
            }
    }
    @FXML
    private void onSortSelected(ActionEvent event) {
         ServiceArticle sa = new ServiceArticle();
        int i = sort.getSelectionModel().getSelectedIndex();
        switch(i){
            case 0:
                this.articles =  sa.showArticle();
                loadArticles(this.articles);
                break;
            case 1:
                this.articles = sa.getTopLatest(0);
                loadArticles(this.articles);
                break;
            case 2:
                this.articles = sa.getTopLatest(1);
                loadArticles(this.articles);
                break;
            case 3:
                this.articles = sa.getTopLatest(2);
                loadArticles(this.articles);
                break;
            case 4:
                this.articles = sa.getTopLatest(3);
                loadArticles(this.articles);
                break;
        }
    }
    
}
