package gestion_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import gestion_store.Gestion_store;
import IService.MyListener;
import Entities.Product;
import Service.ServiceCart;
import Service.ServiceProduct;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

  /*  private List<Product> getData() {
        List<Product> products = new ArrayList<>();
        Product product;

        product = new Product();
        product.setName("Koura");
        product.setPrice(2);
        product.setPhoto("/resources/koura.jpg");
        product.setDetails("6A7324");
        products.add(product);

        product = new Product();
        product.setName("tenue");
        product.setPrice(3);
        product.setPhoto("/resources/tenue.jpg");
        product.setDetails("A7745B");
        products.add(product);

        product = new Product();
        product.setName("chaussures");
        product.setPrice(1);
        product.setPhoto("/resources/chaussures.jpg");
        product.setDetails("F16C31");
        products.add(product);

        product = new Product();
        product.setName("pa");
        product.setPrice(2);
        product.setPhoto("/resources/pa.jpg");
        product.setDetails("291D36");
        products.add(product);

        product = new Product();
        product.setName("panier");
        product.setPrice(4);
        product.setPhoto("/resources/panier.jpg");
        product.setDetails("22371D");
        products.add(product);

        product = new Product();
        product.setName("maillot");
        product.setPrice(2);
        product.setPhoto("/resources/maillot.jpg");
        product.setDetails("FB5D03");
        products.add(product);

        product = new Product();
        product.setName("pull");
        product.setPrice(5);
        product.setPhoto("/resources/pull.jpg");
        product.setDetails("80080C");
        products.add(product);

        product = new Product();
        product.setName("pull");
        product.setPrice(5);
        product.setPhoto("/resources/pull.jpg");
        product.setDetails("FFB605");
        products.add(product);

        product = new Product();
        product.setName("bavette");
        product.setPrice(6);
        product.setPhoto("/resources/bavette.jpg");
        product.setDetails("5F060E");
        products.add(product);

        product = new Product();
        product.setName("pa");
        product.setPrice(1);
        product.setPhoto("/resources/pa.jpg");
        product.setDetails("E7C00F");
        products.add(product);

        return products;
    }*/

    private void setChosenProduct(Product product) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("item.fxml"));
        //Node postbox = loader.load();
        ItemController pc = loader.getController();
        pc.setData(product, myListener);
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
        int row = 1, cl =0;
            try{
                for(Product product : l){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("item.fxml"));
                    Node postbox = loader.load();
                    ItemController pc = loader.getController();
                    pc.setData(product, myListener);
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
        /*int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(products.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    

    @FXML
    private void panier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("panier.fxml"));
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
        s.addToCart(2,p);
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
