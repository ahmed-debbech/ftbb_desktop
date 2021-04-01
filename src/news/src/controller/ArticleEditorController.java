/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.src.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import news.src.Enitity.Article;
import news.src.Service.ServiceArticle;
import news.src.Service.UploadService;
import utils.Passable;
import news.src.utils.Utilities;


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
    private TextField photo_dir;
    @FXML
    private Button browse_photo;
    @FXML
    private Label article_id;
    
    private int mode; // 0 for add , 1 for modify
    private Article whatToMod;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.mode = Passable.getInstance().getNumberData();
        if(this.mode == 1){
            this.whatToMod = (Article) Passable.getInstance().getAnyData();
        }else{
            this.whatToMod = null;
        }
        
        System.out.println("mode ==" + Passable.getInstance().getNumberData());
        category.getItems().add(Article.toStringCatTypes(Article.getBREAKING_NEWS()));
        category.getItems().add(Article.toStringCatTypes(Article.getHOT()));
        category.getItems().add(Article.toStringCatTypes(Article.getANNOUNCE()));
        category.getItems().add(Article.toStringCatTypes(Article.getMISC()));
        if(this.mode == 0){
            this.add_but.setText("Add New");
        }else{
            this.add_but.setText("Change");
            if(whatToMod != null){
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


    private boolean controlFields(){
        if(this.title.getText().equals("") || 
                this.text.getText().equals("") || 
                this.author.getText().equals("") || 
                this.photo_dir.getText().equals("") ||
                this.category.getSelectionModel().getSelectedItem() .equals("Select Category")){
            Utilities.alert("Error", "All field are mendatory!");
            return false;
        }
        return true;
    }
    @FXML
    private void modArticle(ActionEvent event) {
        if(!controlFields()){
            return;
        }
        if(this.mode == 0){
              ServiceArticle sp = new ServiceArticle();
              UploadService.uploadImage(photo_dir.getText());
              String dir = Utilities.pathToUrl(photo_dir.getText());
                Article a = new Article(title.getText(), text.getText(), author.getText(), dir, category.getSelectionModel().getSelectedItem());
                sp.addArticle(a);
        }else{
            ServiceArticle sa = new ServiceArticle();
            String dir = Utilities.pathToUrl(photo_dir.getText());
            Article a = new Article(Integer.parseInt(article_id.getText()), title.getText() , text.getText(), author.getText(), dir, category.getSelectionModel().getSelectedItem());
            sa.modArticle(a);
        }
        Stage stage = (Stage) add_but.getScene().getWindow();
        //stage.close();
    }

    @FXML
    private void browseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a banner file!");
        fileChooser.setInitialDirectory(new File("C:\\xampp\\htdocs\\uploads"));
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
