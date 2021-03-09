/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Article;
import Service.ServiceArticle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.Passable;
import utils.Utilities;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class ArticleEditorController implements Initializable {

    @FXML
    private Button add_but;
    @FXML
    private TextArea text;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private ComboBox<String> category;
    @FXML
    private Label photo_dir;
    @FXML
    private Button browse_photo;
    @FXML
    private Label article_id;
    
    private int mode = 0; // 0 for add , 1 for modify
    private Article whatToMod;
    
   @FXML
    private void addArticle(ActionEvent event) {
        ServiceArticle sp = new ServiceArticle();
        Article a = new Article(title.getText(), text.getText(), author.getText(), photo_dir.getText().replace('\\', '/'), category.getSelectionModel().getSelectedItem());
        sp.addArticle(a);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        category.getItems().add(Article.toStringCatTypes(Article.getBREAKING_NEWS()));
        category.getItems().add(Article.toStringCatTypes(Article.getHOT()));
        category.getItems().add(Article.toStringCatTypes(Article.getANNOUNCE()));
        category.getItems().add(Article.toStringCatTypes(Article.getMISC()));
        if(mode == 0){
            this.add_but.setText("Add New");
        }else{
            this.add_but.setText("Change");
            if(whatToMod == null){
                    Utilities.alert("WARNING!", "Please select an article from the list!");
                }else{
                    article_id.setText(String.valueOf(whatToMod.getArticle_id()));
                    text.setText(whatToMod.getText());
                    title.setText(whatToMod.getTitle());
                    author.setText(whatToMod.getAuthor());
                    photo_dir.setText(whatToMod.getPhoto_url());
                    switch(whatToMod.getCategory()){
                        case 0: 
                            category.setValue(Article.toStringCatTypes(whatToMod.getCategory()));
                            break;
                        case 1:
                            category.setValue(Article.toStringCatTypes(whatToMod.getCategory()));
                            break;
                        case 2:
                            category.setValue(Article.toStringCatTypes(whatToMod.getCategory()));
                            break;
                        case 3:
                            category.setValue(Article.toStringCatTypes(whatToMod.getCategory()));
                            break;
                    }
                }
        }
    }    


    private void modArticle(ActionEvent event) {
        if(this.mode == 0){
              ServiceArticle sp = new ServiceArticle();
             Article a = new Article(title.getText(), text.getText(), author.getText(), photo_dir.getText(), category.getSelectionModel().getSelectedItem());
             sp.addArticle(a);
        }else{
            ServiceArticle sa = new ServiceArticle();
            Article a = new Article(Integer.parseInt(article_id.getText()), title.getText(), text.getText(), author.getText(), photo_dir.getText(), category.getSelectionModel().getSelectedItem());
            sa.modArticle(a);
        }
    }

    @FXML
    private void browseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        File file = fileChooser.showOpenDialog(stage);
        try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_dir.setText(file.getAbsolutePath());
            } catch (IOException ex) {
                System.out.println("could not get the image");
            }
    }
    
    
}
