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

public class ItemController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(product);
    }

    private Product product;
    private MyListener myListener;

    public void setData(Product product, MyListener myListener) {
        this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getName());
        priceLable.setText(product.getPrice()+ Gestion_store.CURRENCY );
        Image image = new Image(getClass().getResourceAsStream(product.getPhoto()));
        img.setImage(image);
    }
}
