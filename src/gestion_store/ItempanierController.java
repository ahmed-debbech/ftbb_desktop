package gestion_store;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import gestion_store.Gestion_store;
import IService.MyListener;
import Entities.Product;

public class ItempanierController implements Initializable {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;
    @FXML
    private Label stockLable;
    @FXML
    private Label quantiteLable;
    @FXML
    private Label totalpriceLable;

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
          totalpriceLable.setText(product.getPrice() + Gestion_store.CURRENCY);
        //quantiteLable.setText("1");
      //  stockLable.setText(Integer(product.getStock()));
        Image image = new Image(getClass().getResourceAsStream(product.getPhoto()));
        img.setImage(image);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

