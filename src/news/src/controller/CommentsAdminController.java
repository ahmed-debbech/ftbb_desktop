/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.src.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import news.src.Enitity.Comment;
import news.src.Service.ServiceComment;
import news.src.utils.Passable;
import news.src.utils.Utilities;


/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class CommentsAdminController implements Initializable {

    @FXML
    private VBox listcomments;
    @FXML
    private Button ref_but;
    @FXML
    private Button ban_but;
   
    @FXML
    private AnchorPane panecom;
    
    List<AnchorPane> children;
    private String article_id;
    @FXML
    private TextField filter;
    @FXML
    private CheckBox checks;
    @FXML
    private Label title;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         article_id = Passable.getInstance().getTextData();
         System.out.println("comt id " + article_id);
         Passable.getInstance().erase();
         refreshComment(null);
         filter.textProperty().addListener((observable, oldValue, newValue) -> {
            ServiceComment sa = new ServiceComment();
            List<Comment> l ;
            l =  sa.searchComment(Integer.parseInt(article_id), newValue);
             loadComments(l);
        });
    }    

    private void loadComments(List<Comment> list){
        this.listcomments.getChildren().clear();
           children = new ArrayList<>();
            try{
                for(Comment a : list){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/news/src/view/FXMLCommentAdminView.fxml"));
                    Node postbox = loader.load();
                    CommentAdminViewController pc = loader.getController();
                    pc.setData(a);
                    children.add(pc.getNode());
                    this.listcomments.getChildren().add(postbox);
                     this.listcomments.setSpacing(5);
                }
            }catch(IOException e){
                  System.out.println("error");
            }
    }
    
    @FXML
    private void refreshComment(ActionEvent event) {
        ServiceComment sc = new ServiceComment();
           List<Comment> list =  sc.showComment(article_id);
           loadComments(list);
    }

    @FXML
    private void banComment(ActionEvent event) {
        List<String> ids = new ArrayList<>();
       for(AnchorPane ap : children){
           HBox hb = (HBox)ap.getChildren().get(0);
           VBox vb = (VBox)hb.getChildren().get(1);
           CheckBox chb = (CheckBox)vb.getChildren().get(3);
           Label lab = (Label)vb.getChildren().get(2);
           if(chb.isSelected()){
               ids.add(lab.getText());
           }
       }
       if(ids.size() == 0){
             Utilities.alert("WARNING!", "Please select a comment from the list.");
        }else{
            ServiceComment sc = new ServiceComment();
            for(String id : ids){
                Comment ccc = new Comment();
                ccc.setId(Integer.parseInt(id));
                sc.delComment(ccc);
                refreshComment(null);
            }
        }
    }

    @FXML
    private void onSelectAll(ActionEvent event) {
        for(AnchorPane ap : children){
        HBox hb = (HBox)ap.getChildren().get(0);
           VBox vb = (VBox)hb.getChildren().get(1);
           CheckBox chb = (CheckBox)vb.getChildren().get(3);
           if(this.checks.isSelected() == true){
           chb.setSelected(true);
            }else{
               chb.setSelected(false);
           }
        }
    }

    
    
}
