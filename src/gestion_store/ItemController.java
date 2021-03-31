package gestion_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import gestion_store.Gestion_store;
import IService.MyListener;
import Entities.Product;
import java.io.File;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private Label id;
    
    private VBox parent;
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(product);
    }

    private Product product;
    private MyListener myListener;
    
    private void update(){
        //parent.getChildren().get(0)
    }
    public void setData(Product product, MyListener myListener, VBox parent) {
        /*this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getName());
        priceLable.setText(product.getPrice()+ Gestion_store.CURRENCY );
        Image image = new Image(getClass().getResourceAsStream(product.getPhoto()));
        img.setImage(image);*/
       
        this.myListener = myListener;
        this.parent = parent;
        this.id.setText(String.valueOf(product.getRef_product()));
        this.nameLabel.setText(product.getName());
        priceLable.setText(product.getPrice()+ Gestion_store.CURRENCY );
        File file = new File(product.getPhoto().replace('/' , '\\'));
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.img.setImage(im);
    }

    @FXML
    private void onPressed(MouseEvent event) {
        System.out.println("kkkkkkk");
        HBox l = (HBox)this.parent.getChildren().get(0);
        Label name = (Label)l.getChildren().get(0);
        Label price = (Label)l.getChildren().get(2);
        System.out.println("oooooooo");
        name.setText(this.nameLabel.getText());
        price.setText(this.priceLable.getText());
        ImageView im = (ImageView)this.parent.getChildren().get(1);
        im.setImage(this.img.getImage());
    }
}
