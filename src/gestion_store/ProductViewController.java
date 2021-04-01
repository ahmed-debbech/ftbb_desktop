/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Product;
import Service.ServiceCart;
import Service.ServiceProduct;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class ProductViewController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddToCart(ActionEvent event) {
        ServiceCart s = new ServiceCart();
        Product p = new Product();
        p.setRef_product(Integer.parseInt(id.getText()));
        s.addToCart(2,p);
    }
    
     public void setData(Product a){
        this.id.setText(String.valueOf(a.getRef_product()));
           this.name.setText(a.getName());
           File file = new File(a.getPhoto().replace('/' , '\\'));
        Image im = null;
        if(file.exists()){
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.image.setImage(im);
    }
    
}
