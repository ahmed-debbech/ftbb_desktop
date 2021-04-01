package store.src.gestion_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import store.src.Entities.Product;
import store.src.IService.MyListener;
import store.src.Service.ServiceCart;
import store.src.Service.ServiceProduct;
import user.src.Entities.Client;
import user.src.Service.ServiceClient;

public class MarketController implements Initializable {
    
    @FXML
    private VBox chosenProductCard;

    @FXML
    private Label productNameLable;

   

    @FXML
    private ImageView productImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Product> products = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private Label productPriceLabel;
   
    @FXML
    private Label id;
    
     @FXML
    private TextField qty;
    @FXML
    private AnchorPane parent;
    @FXML
    private Label boy;


    private void setChosenProduct(Product product) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/store/src/gestion_store/item.fxml"));
        //Node postbox = loader.load();
        ItemController pc = loader.getController();
        pc.setData(product, myListener, this.chosenProductCard);
        productNameLable.setText(product.getName());
        productPriceLabel.setText(product.getPrice()+Gestion_store.CURRENCY );
        image = new Image(getClass().getResourceAsStream(product.getPhoto()));
        productImg.setImage(image);
        chosenProductCard.setStyle("-fx-background-color: red" + product.getDetails() + ";\n" +
                "    -fx-background-radius: 30;");
  
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      qty.setText("1");
        // products.addAll(getData());
        if (products.size() > 0) {
            setChosenProduct(products.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Product product) {
                    setChosenProduct(product);
                }
            };
        }

        grid.getChildren().clear();
        ServiceProduct sp = new ServiceProduct();
        List<Product> l = sp.ShowProduct();
        System.out.println("items " + l.toString());
        int row = 1, cl =0;
            try{
                for(Product product : l){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/store/src/gestion_store/item.fxml"));
                    Node postbox = loader.load();
                    ItemController pc = loader.getController();
                    pc.setData(product, myListener, this.chosenProductCard);
                    if(cl== 3){
                         cl= 0;
                         row++;
                    }
                    this.grid.add(postbox, cl++, row);
                }
            }catch(IOException e){
                System.out.println("no load for product in client");
                   e.printStackTrace();
            }
    }
  

    @FXML
    private void panier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/store/src/gestion_store/panier.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    private void det(ActionEvent event) {
    }

    @FXML
    private void AddToCart(ActionEvent event) {
        ServiceCart s = new ServiceCart();
        Product p = new Product();
        p.setRef_product(Integer.parseInt(id.getText()));
        ServiceClient sss  = new ServiceClient();
        Client c = sss.getA();
        s.addToCart(c.getId(),p);
    }
  
    @FXML
    private void increment(ActionEvent event) {
        String i=qty.getText();
        int k=Integer.parseInt(i);
        k++;
        if(k<100){
             qty.setText(String.valueOf(k));
        }
       
    }

    @FXML
    private void decrement(ActionEvent event) {
        String i=qty.getText();
        int k=Integer.parseInt(i);
        k--;
        if(k>0){
         qty.setText(String.valueOf(k));
        }
    }

}
