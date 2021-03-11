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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class StoreController implements Initializable {

    @FXML
    private TableColumn<Product, Integer> colref;
    @FXML
    private TableColumn<Product, String> colcat;
    @FXML
    private TableColumn<Product, Integer> colstock;
    @FXML
    private TableColumn<Product, String> colname;
    @FXML
    private TableColumn<Product, String> coldetails;
    @FXML
    private TableColumn<Product, Integer> colprice;
    @FXML
    private TableColumn<Product, Integer> colidadmin;
    @FXML
    private TableColumn<Product, Date> coladddate;
    @FXML
    private TableColumn<Product, String> colphoto;
    @FXML
    private TableView<Product> ldetails;

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
       
        ServiceProduct sp = new ServiceProduct();
        List<Product> l = sp.ShowProduct();
        ObservableList<Product> list  = FXCollections.observableArrayList(l);
        colref.setCellValueFactory(new PropertyValueFactory<>("ref_product"));       
        colcat.setCellValueFactory(new PropertyValueFactory<>("category"));        
        colstock.setCellValueFactory(new PropertyValueFactory<>("stock"));        
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));        
        colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        coldetails.setCellValueFactory(new PropertyValueFactory<>("details"));       
        colidadmin.setCellValueFactory(new PropertyValueFactory<>("id_admin"));        
        coladddate.setCellValueFactory(new PropertyValueFactory<>("add_date"));        
        colphoto.setCellValueFactory(new PropertyValueFactory<>("photo"));    
           
     
        ldetails.setItems(list);
        this.showProduct(null);
    }

    @FXML
    private void btnaddtocart(ActionEvent event) {
       
       
    }

    @FXML
    private void handleMouseButton(MouseEvent event) {
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
