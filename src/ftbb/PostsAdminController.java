/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class PostsAdminController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label text;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showComments(ActionEvent event) {
        
    }

    @FXML
    private void modArticle(ActionEvent event) {
    }

    @FXML
    private void delArticle(ActionEvent event) {
    }
    
}
