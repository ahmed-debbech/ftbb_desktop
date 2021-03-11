/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Product;
import Service.ServiceProduct;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class StoreController implements Initializable {

    private TableColumn<Product, Integer> colref;
    private TableColumn<Product, String> colcat;
    private TableColumn<Product, Integer> colstock;
    private TableColumn<Product, String> colname;
    private TableColumn<Product, String> coldetails;
    private TableColumn<Product, Integer> colprice;
    private TableColumn<Product, Integer> colidadmin;
    private TableColumn<Product, Date> coladddate;
    private TableColumn<Product, String> colphoto;
    @FXML
    private GridPane ldetails;
    @FXML
    private ScrollPane scroller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*TableColumn t2 = new TableColumn("Reference");
        t2.setCellValueFactory(new PropertyValueFactory<Product, String>("ref_product"));
        TableColumn t3 = new TableColumn("Category");
        t3.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        TableColumn t4 = new TableColumn("Stock");
        t4.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        TableColumn t5 = new TableColumn("Name");
        t5.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        TableColumn t6 = new TableColumn("Price");
        t6.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        TableColumn t7 = new TableColumn("Details");
        t7.setCellValueFactory(new PropertyValueFactory<Product, String>("details"));
        TableColumn t8 = new TableColumn("ID Admin");
        t8.setCellValueFactory(new PropertyValueFactory<Product, String>("id_admin"));
        TableColumn t9 = new TableColumn("ADD date");
        t9.setCellValueFactory(new PropertyValueFactory<Product, String>("add_date"));
        TableColumn t1 = new TableColumn("Photo");
        t1.setCellValueFactory(new PropertyValueFactory<Product, String>("photo"));
        
        ldetails.getColumns().addAll(t1,t2,t3,t4,t5,t6,t7,t8,t9);*/
        
        
    }    
    
    
    /******** AFFICHER *************************************************************************************************************/
    @FXML
    private void showProduct(ActionEvent event) {
       ldetails.getChildren().clear();
        ServiceProduct sp = new ServiceProduct();
        List<Product> l = sp.ShowProduct();
        int row = 1, cl =0;
            try{
                for(Product a : l){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ProductView.fxml"));
                    Node postbox = loader.load();
                    ProductViewController pc = loader.getController();
                    pc.setData(a);
                    if(cl== 3){
                         cl= 0;
                         row++;
                    }
                    this.ldetails.add(postbox, cl++, row);
                }
            }catch(IOException e){
                System.out.println("no load for product in client");
                   e.printStackTrace();
            }
    }

    @FXML
    private void btnaddtocart(ActionEvent event) {
       
       
    }


    @FXML
    private void panier(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("panierClient.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();
    }
        
   
}
