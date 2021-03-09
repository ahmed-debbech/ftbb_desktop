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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ServiceArticle sa = new ServiceArticle();
            this.articles =  sa.showArticle();
            int row = 1, cl =0;
            try{
                for(Article a : this.articles){
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
                System.out.println("soierjf");
                   e.printStackTrace();
            }
    }    
    
}
