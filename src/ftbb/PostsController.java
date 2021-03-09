/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Enitity.Article;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class PostsController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Text title;
    @FXML
    private HBox likes;
    @FXML
    private Label like_number;
    @FXML
    private HBox comments;
    @FXML
    private Label com_number;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Article a){
           this.title.setText(a.getTitle());
           this.date.setText(a.getDate().toString());
    }
}
