/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Product;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ProductViewClientController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label quantite;
    @FXML
    private Label prixunitaire;
    @FXML
    private Label prixtotal;
    @FXML
    private Label title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setData(Product a){
        File file = new File(a.getPhoto());
        Image im = null;
        if(file.exists()){
                 im = new Image(file.toURI().toString());
        }else{
            im = new Image("resources/cart.png");
        }
        this.image.setImage(im);
        this.title.setText(a.getName());
        this.quantite.setText("1");
        this.prixunitaire.setText(String.valueOf(a.getPrice()));
        this.prixtotal.setText(String.valueOf(Integer.parseInt(this.quantite.getText()) * Integer.parseInt(this.prixunitaire.getText())));
        
      }
}
