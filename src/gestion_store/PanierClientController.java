/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Product;
import Service.ServiceCart;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage; 
/**
 * FXML Controller class
 *
 * @author PC
 */
public class PanierClientController implements Initializable {

    @FXML
    private AnchorPane empty;
    @FXML
    private ScrollPane scroller;
    @FXML
    private VBox prodlist;
    @FXML
    private Label prixcommande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //hna bech t7ot test doub matet7al el page mta3 panier tasti ken fer8a tafichi el anchorpane (empty.setVisible(true);
        //sinon affichi el scroller scroller.setVisible(true); 
        ServiceCart sc = new ServiceCart();
            List<Product> list = sc.getCartProducts(2);
        if(list.isEmpty()){
            empty.setVisible(true);
            scroller.setVisible(false);
        }else{
            empty.setVisible(false);
            scroller.setVisible(true);
            //as long as we have something in our cart then we should retreive it
           loadProducts();
           
        }
    }    
    private void loadProducts(){
      
        this.prodlist.getChildren().clear();
        ServiceCart sc = new ServiceCart();
            List<Product> products =  sc.getCartProducts(2);
            try{
                for(Product a : products){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ProductViewClient.fxml"));
                    Node postbox = loader.load();
                    ProductViewClientController pc = loader.getController();
                    pc.setData(a);
                    this.prodlist.getChildren().add(postbox);
               }
            }catch(IOException e){
                   System.out.println("could not load");
            }
            
    }
    @FXML
    private void startshopping(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("store.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    private void btnback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("store.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    private void passercommande(ActionEvent event) {
         
     }
    }
    
