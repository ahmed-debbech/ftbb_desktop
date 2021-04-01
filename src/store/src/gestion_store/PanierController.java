
package store.src.gestion_store;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import news.src.utils.Utilities;
import store.src.Entities.Product;
import store.src.IService.MyListener;
import store.src.Service.ServiceCart;
import store.src.Service.ServiceCommand;
import user.src.Entities.Client;
import user.src.Service.ServiceClient;

public class PanierController implements Initializable {
    @FXML
    private VBox chosenProductCard;

    @FXML
    private Label productNameLable;

   
    @FXML
    private ImageView productImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private VBox grid;

    private List<Product> products = new ArrayList<>();
    private Image image;
    private MyListener myListener;
    @FXML
    private Label productPriceLabel;
   
       @FXML
    private Label tprice;
    @FXML
    private TextField qty;
    @FXML
    private Button plus;
    @FXML
    private Button moins;

    private List<AnchorPane> anchoritems;


    private void setChosenProduct(Product product) {
        productNameLable.setText(product.getName());
        productPriceLabel.setText(product.getPrice()+Gestion_store.CURRENCY );
        qty.getText();
        image = new Image(getClass().getResourceAsStream(product.getPhoto()));
        productImg.setImage(image);
        chosenProductCard.setStyle("-fx-background-color: #" + product.getDetails() + ";\n" +
                "-fx-background-radius: 30;");
         
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        qty.setText("1");
      tprice.setText("-Click here-");
        anchoritems = new ArrayList<AnchorPane>();
        //products.addAll(getData());
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
       ServiceCart sc = new ServiceCart();
       ServiceClient ss =new ServiceClient();
       Client c = ss.getA();
            List<Product> l =  sc.getCartProducts(c.getId());
            try{
                for(Product product : l){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/store/src/gestion_store/itempanier.fxml"));
                    Node postbox = loader.load();
                    ItempanierController pc = loader.getController();
                    pc.setData(product, myListener, this.chosenProductCard);
                    this.grid.getChildren().add(postbox);
                    this.anchoritems.add(pc.getNode());
                }
            }catch(IOException e){
                System.out.println("no load for product in client");
                   e.printStackTrace();
            }
    }


    @FXML
    private void det(ActionEvent event) {
    }

    private int parse(String text){
        String count = "";
        for(int i=0; i<= text.length()-1; i++){
                if((text.charAt(i) <= '9')  && (text.charAt(i) >= '0')){
                   count += text.charAt(i);
                }
        }
        System.out.println("parse : " + count);
        return Integer.parseInt(count);
    }
    @FXML
    private void passercommande(ActionEvent event) {
        ServiceCart sc = new ServiceCart();
        ServiceClient ss = new ServiceClient();
        Client c = ss.getA();
        List<Product> list = sc.getCartProducts(c.getId());
        ServiceCommand scom = new ServiceCommand();
        int rans = Utilities.generatedId("command", "command_id");
        System.out.println("eirh;g");
        scom.addCommand(c.getId(), parse(tprice.getText()), rans);
        scom.transProduct(list, c.getId(), rans);
        sc.removeAll(c.getId());
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

    @FXML
    private void update(MouseEvent event) {
        int sum = 0;
        for(AnchorPane ap : anchoritems){
            VBox v = (VBox)ap.getChildren().get(0);
            HBox h = (HBox) v.getChildren().get(0);
            VBox vv = (VBox) h.getChildren().get(4);
            Label tot = (Label) vv.getChildren().get(1);
            sum += Integer.parseInt(tot.getText());
        }
        tprice.setText("Total Price: " + sum+ "DT");
    }

}
