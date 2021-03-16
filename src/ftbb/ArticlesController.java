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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import utils.Passable;
import utils.Utilities;

/**
 *
 * @author Ahmed
 */
public class ArticlesController implements Initializable {
    
    @FXML
    private Button add_but;
    @FXML
    private TextArea text;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private Label photo_dir;
    @FXML
    private TableView<Article> listarticle;
    @FXML
    private ComboBox<String> category;
    private TextField video_dir;
    @FXML
    private Button show_but;
    @FXML
    private Button mod_but;
    @FXML
    private Button del_but;
    @FXML
    private Label article_id;
    @FXML
    private AnchorPane pane1;
    @FXML
    private Button browse_photo;
    
    @FXML
    private void addArticle(ActionEvent event) {
        ServiceArticle sp = new ServiceArticle();
        Article a = new Article(title.getText(), text.getText(), author.getText(), photo_dir.getText(), category.getSelectionModel().getSelectedItem());
        sp.addArticle(a);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showArticle(null);
        category.getItems().add(Article.toStringCatTypes(Article.getBREAKING_NEWS()));
        category.getItems().add(Article.toStringCatTypes(Article.getHOT()));
        category.getItems().add(Article.toStringCatTypes(Article.getANNOUNCE()));
        category.getItems().add(Article.toStringCatTypes(Article.getMISC()));
        TableColumn t1 = new TableColumn("ID ");
        t1.setCellValueFactory(new PropertyValueFactory<Article, String>("article_id"));
        TableColumn t2 = new TableColumn("Admin name");
        t2.setCellValueFactory(new PropertyValueFactory<Article, String>("admin_name"));
        TableColumn t3 = new TableColumn("Title");
        t3.setCellValueFactory(new PropertyValueFactory<Article, String>("title"));
        TableColumn t4 = new TableColumn("Text");
        t4.setCellValueFactory(new PropertyValueFactory<Article, String>("text"));
        TableColumn t5 = new TableColumn("Author");
        t5.setCellValueFactory(new PropertyValueFactory<Article, String>("author"));
        TableColumn t6 = new TableColumn("Date");
        t6.setCellValueFactory(new PropertyValueFactory<Article, Date>("date"));
        TableColumn t7 = new TableColumn("Photo URL");
        t7.setCellValueFactory(new PropertyValueFactory<Article, String>("photo_url"));
        TableColumn t8 = new TableColumn("Video URL");
        t8.setCellValueFactory(new PropertyValueFactory<Article, String>("video_url"));
        TableColumn t9 = new TableColumn("Category");
        t9.setCellValueFactory(new PropertyValueFactory<Article, String>("category"));
        listarticle.getColumns().addAll(t1,t2,t3,t4,t5,t6,t7,t8,t9);
    }    

    @FXML
    private void showArticle(ActionEvent event) {
        ServiceArticle sp = new ServiceArticle();
        List<Article> l = sp.showArticle();
        if(l != null){
            ObservableList<Article> data =FXCollections.observableArrayList(l);
            listarticle.setItems(data);
        }
    }

    @FXML
    private void modArticle(ActionEvent event) {
        if(mod_but.getText() == "Submit"){
            ServiceArticle sa = new ServiceArticle();
            Article a = new Article(Integer.parseInt(article_id.getText()), title.getText(), text.getText(), author.getText(), photo_dir.getText(), category.getSelectionModel().getSelectedItem());
            sa.modArticle(a);
            
            mod_but.setText("Modify");
            text.setText("");
            title.setText("");
            author.setText("");
            photo_dir.setText("");
            video_dir.setText("");
        }else{
            mod_but.setText("Submit");
            Article a = listarticle.getSelectionModel().getSelectedItem();
            if(a == null){
                Utilities.alert("WARNING!", "Please select an article from the list!");
            }else{
                article_id.setText(String.valueOf(a.getArticle_id()));
                text.setText(a.getText());
                title.setText(a.getTitle());
                author.setText(a.getAuthor());
                photo_dir.setText(a.getPhoto_url());
                switch(a.getCategory()){
                    case 0:
                        category.setValue(Article.toStringCatTypes(a.getCategory()));
                        break;
                    case 1:
                        category.setValue(Article.toStringCatTypes(a.getCategory()));
                        break;
                    case 2:
                        category.setValue(Article.toStringCatTypes(a.getCategory()));
                        break;
                    case 3:
                        category.setValue(Article.toStringCatTypes(a.getCategory()));
                        break;
                }
            }
        }
    }

    @FXML
    private void delArticle(ActionEvent event) {
        Article a = listarticle.getSelectionModel().getSelectedItem();
        if(a == null){
            Utilities.alert("WARNING!", "Please select an article from the list!");
        }else{
           ServiceArticle sa = new ServiceArticle();
           Article aa = new Article(a.getArticle_id());
           sa.delArticle(aa);
        }
    }

    @FXML
    private void showComments(ActionEvent event) throws IOException {
        Article a = listarticle.getSelectionModel().getSelectedItem();
        if(a == null){ 
           Utilities.alert("WARNING!", "Please select an article from the list!");
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane root1 =  fxmlLoader.load(getClass().getResource("FXMLComments.fxml").openStream());
            Passable p = Passable.getInstance();
            p.setTextData(String.valueOf(a.getArticle_id()));
            Stage stage = new Stage();
            stage.setTitle("Comments of article: " + a.getArticle_id());
            stage.setScene(new Scene(root1));  
            stage.show();
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