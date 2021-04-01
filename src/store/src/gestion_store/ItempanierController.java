package store.src.gestion_store;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import store.src.Entities.Product;
import store.src.IService.MyListener;

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
    private TextField qty;
    @FXML
    private Label totalpriceLable;
    @FXML
    private Label id;
    @FXML
    private AnchorPane parent;

    private VBox par;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(product);
    }

    private Product product;
    private MyListener myListener;

    public void setData(Product product, MyListener myListener, VBox par) {
       /* this.product = product;
        this.myListener = myListener;
        nameLabel.setText(product.getName());
        priceLable.setText(product.getPrice()+ Gestion_store.CURRENCY );
          totalpriceLable.setText(product.getPrice() + Gestion_store.CURRENCY);
        //quantiteLable.setText("1");
      //  stockLable.setText(Integer(product.getStock()));
        Image image = new Image(getClass().getResourceAsStream(product.getPhoto()));
        img.setImage(image);*/
       this.par = par;
       this.myListener = myListener;
        this.id.setText(String.valueOf(product.getRef_product()));
        this.nameLabel.setText(product.getName());
        priceLable.setText(""+product.getPrice()/*+ Gestion_store.CURRENCY*/ );
        totalpriceLable.setText(String.valueOf(Integer.parseInt(qty.getText())*product.getPrice()));
        File file = new File(product.getPhoto().replace('/' , '\\'));
        Image im = null;
        if(file.exists()){ 
                 im = new Image(file.toURI().toString());
        }else{
            //im = new Image("resources/default-article.jpg"); // this is the defualt photo of the product
        }
         this.img.setImage(im);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void increment(ActionEvent event) {
        String i=qty.getText();
        int k=Integer.parseInt(i);
        k++;
        if(k<100){
             qty.setText(String.valueOf(k));
             String price = this.priceLable.getText();
             int pr = Integer.parseInt(price);    
            int count = pr * k;
            System.out.println("count " + count);
            totalpriceLable.setText(String.valueOf(count));
        }
       
    }

    @FXML
    private void decrement(ActionEvent event) {
        String i=qty.getText();
        int k=Integer.parseInt(i);
        k--;
        if(k>0){
         qty.setText(String.valueOf(k));
         String price = this.priceLable.getText();
             int pr = Integer.parseInt(price);    
            int count = pr * k;
            System.out.println("count " + count);
            totalpriceLable.setText(String.valueOf(count));
        }
    }

    @FXML
    private void onPressed(MouseEvent event) {
        HBox l = (HBox)this.par.getChildren().get(0);
        Label name = (Label)l.getChildren().get(0);
        Label price = (Label)l.getChildren().get(1);
        HBox quant = (HBox) this.par.getChildren().get(2);
        name.setText(this.nameLabel.getText());
        price.setText(this.priceLable.getText());
        ImageView im = (ImageView)this.par.getChildren().get(1);
        im.setImage(this.img.getImage());
        TextField tx = (TextField) quant.getChildren().get(0);
        tx.setText(qty.getText());
    }
    public AnchorPane getNode(){
        return parent;
    }
}

