/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Product;
import Service.ServiceProduct;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField cat_p;
    @FXML
    private TextField name_p;
    @FXML
    private TextField price_p;
    @FXML
    private TextField details_p;
    @FXML
    private TextField idAdmin_p;
    @FXML
    private TextField add_date_p;
    private Label label_show_p;
    @FXML
    private TextField stock_p;
    @FXML
    private TableView<Product> lproduct;
    @FXML
    private Button show_but;
    @FXML
    private Label cat;
    @FXML
    private Label stock;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Label details;
    @FXML
    private Label idadmin;
    @FXML
    private Label adddate;
    
    private int test=0;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           TableColumn t1 = new TableColumn("Reference");
        t1.setCellValueFactory(new PropertyValueFactory<Product, String>("ref_product"));
        TableColumn t2 = new TableColumn("Category");
        t2.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        TableColumn t3 = new TableColumn("Stock");
        t3.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        TableColumn t4 = new TableColumn("Name");
        t4.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        TableColumn t5 = new TableColumn("Price");
        t5.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        TableColumn t6 = new TableColumn("Details");
        t6.setCellValueFactory(new PropertyValueFactory<Product, String>("details"));
        TableColumn t7 = new TableColumn("ID Admin");
        t7.setCellValueFactory(new PropertyValueFactory<Product, String>("id_admin"));
        TableColumn t8 = new TableColumn("ADD date");
        t8.setCellValueFactory(new PropertyValueFactory<Product, String>("add_date"));
        
        lproduct.getColumns().addAll(t1,t2,t3,t4,t5,t6,t7,t8);
        
    }    
    
    
    /******** AFFICHER *************************************************************************************************************/
    @FXML
    private void showProduct(ActionEvent event) {
        ServiceProduct sp = new ServiceProduct();
        List<Product> l = sp.ShowProduct();
        
      /*  TableColumn t1 = new TableColumn("Reference");
        t1.setCellValueFactory(new PropertyValueFactory<Product, String>("ref_product"));
        TableColumn t2 = new TableColumn("Category");
        t2.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        TableColumn t3 = new TableColumn("Stock");
        t3.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        TableColumn t4 = new TableColumn("Name");
        t4.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        TableColumn t5 = new TableColumn("Price");
        t5.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));
        TableColumn t6 = new TableColumn("Details");
        t6.setCellValueFactory(new PropertyValueFactory<Product, String>("details"));
        TableColumn t7 = new TableColumn("ID Admin");
        t7.setCellValueFactory(new PropertyValueFactory<Product, String>("id_admin"));
        TableColumn t8 = new TableColumn("ADD date");
        t8.setCellValueFactory(new PropertyValueFactory<Product, String>("add_date"));
        
        lproduct.getColumns().addAll(t1,t2,t3,t4,t5,t6,t7,t8);*/
        ObservableList<Product> data=FXCollections.observableArrayList(l);
        lproduct.setItems(data);
    }
    
    
     /*@FXML
    public void showProduct() {
        ServiceProduct sp = new ServiceProduct();
        List<Product> list  = sp.ShowProduct();
        colref.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ref_product"));
        colcat.setCellValueFactory(new PropertyValueFactory<Product,String>("category"));
        colstock.setCellValueFactory(new PropertyValueFactory<Product,Integer>("stock"));
        colname.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        colprice.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
        coldet.setCellValueFactory(new PropertyValueFactory<Product,String>("details"));
        colidad.setCellValueFactory(new PropertyValueFactory<Product,Integer>("id_admin"));
        coladddate.setCellValueFactory(new PropertyValueFactory<Product,date>("add_date"));
        
        lproduct.setItems(list);
    }*/
    

    /******** AJOUTER *****************************************************************************************************************************************************/
    @FXML
    private void insertPrduct(ActionEvent event) {
        ServiceProduct sp =new ServiceProduct();
        Product p = new Product();//taadit mil controller lil model (architecture)
        
        if(verifierChamps()){
            if(cat_p.getText().matches("^[0-9]+$"))
                {
                   JOptionPane.showMessageDialog(null, "CHECK CATEGORY,ONLY STRINGS ARE SUPPORTED ! ");
                   test = 1 ;
                }
            if (stock_p.getText().matches(("^[A-Z a-z]+$")))
                {
                   JOptionPane.showMessageDialog(null, "CHECK STOCK,ONLY NUMBERS ARE SUPPORTED ! ");
                   test = 1 ;
                }
            if(name_p.getText().matches("^[0-9]+$"))
                {
                   JOptionPane.showMessageDialog(null, "CHECK NAME,ONLY STRINGS ARE SUPPORTED ! ");
                   test = 1 ;
                }
            if (price_p.getText().matches(("^[A-Z a-z]+$")))
                {
                   JOptionPane.showMessageDialog(null, "CHECK PRICE,ONLY NUMBERS ARE SUPPORTED ! ");
                   test = 1 ;
                }
            if(details_p.getText().matches("^[0-9]+$"))
                {
                   JOptionPane.showMessageDialog(null, "CHECK DETAILS,ONLY STRINGS ARE SUPPORTED ! ");
                   test = 1 ;
                }
            if (idAdmin_p.getText().matches(("^[A-Z a-z]+$")))
                {
                   JOptionPane.showMessageDialog(null, "CHECK ID ADMIN,ONLY NUMBERS ARE SUPPORTED ! ");
                   test = 1 ;
                }
        if (test == 0)
        { 
        p.setCategory(cat_p.getText());
        p.setStock(Integer.parseInt(stock_p.getText()));//Integer.parseInt --> hedhy fonction de convertion tbaddel el entier li string
        p.setName(name_p.getText());
        p.setPrice(Integer.parseInt(price_p.getText()));
        p.setDetails(details_p.getText());
        p.setId_admin(Integer.parseInt(idAdmin_p.getText()));
        //p.setAdd_date(add_date_p.getText());
        sp.AddProduct(p);
        
        //aleeeeeeeeeeeeeeeert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS INSERT");
        alert.setHeaderText(null);
        alert.setContentText("PRODUCT INSERTED !");
        alert.showAndWait();
        cat_p.setText("");
        stock_p.setText("");
        name_p.setText("");
        price_p.setText("");
        details_p.setText("");
        idAdmin_p.setText("");
        add_date_p.setText("");
    }}}
     
    private void test(ActionEvent event) {
         System.out.println("HELLLLLOOOOOO !!!");
    }
    
    public boolean verifierChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
        cat_p.setStyle(styledefault);
        stock_p.setStyle(styledefault);
        name_p.setStyle(styledefault);
        price_p.setStyle(styledefault);
        details_p.setStyle(styledefault);
        idAdmin_p.setStyle(styledefault);
 
 

        if (cat_p.getText().equals("")) {
            cat_p.setStyle(style);
            verif = 1;
        }
       
        if (stock_p.getText().equals("")) {
            stock_p.setStyle(style);
            verif = 1;
        }
        if (name_p.getText().equals("")) {
            name_p.setStyle(style);
            verif = 1;
        }
        if (price_p.getText().equals("")) {
            price_p.setStyle(style);
            verif = 1;
        }
        if (details_p.getText().equals("")) {
            details_p.setStyle(style);
            verif = 1;
        }
        if (idAdmin_p.getText().equals("")) {
            idAdmin_p.setStyle(style);
            verif = 1;
        }
        if (verif == 0) {
            return true;
        }
        JOptionPane.showMessageDialog(null, " TEXTFIELD EMPTY !!");
        return false;
    }
    
    /******** HANDLE BUTTON MOUSE *************************************************************************************************************/
     @FXML
    private void handleMouseButton(MouseEvent event) {
       /* ref_p.setVisible(true);
        labelref.setVisible(true);*/
        Product p = lproduct.getSelectionModel().getSelectedItem();
        //labelref.setText(""+p.getRef_product());
        cat_p.setText(p.getCategory());
        stock_p.setText("" +p.getStock());
        name_p.setText(p.getName());
        price_p.setText("" +p.getPrice());
        details_p.setText(p.getDetails());
        idAdmin_p.setText("" +p.getId_admin());
        add_date_p.setText("" +p.getAdd_date());
        /*String stock=Integer.toString(p.getStock());
        stock_p.setText(stock);
        String price=Integer.toString(p.getPrice());
        price_p.setText(price);
        String id_admin=Integer.toString(p.getId_admin());
        idAdmin_p.setText(id_admin);*/
    }
    
    /************ SUPPRIMER ******************************************************************************************************************************/
    @FXML
    private void deleteProduct(ActionEvent event) {
        ServiceProduct sp = new ServiceProduct(); //intance service el couche illi bech tahki maa el BD mil controller
        Product p1 =new Product(); // instance model produit illi bech yettebaath lil BD aan tari9 el service
        Product p =lproduct.getSelectionModel().getSelectedItem(); //khdhina selected item mil table view trajaalik product
        p1.setRef_product(p.getRef_product());//bech naabi el model te3i el p1 bech nhottou feha el p
        
        //alerrrrrrrrrt
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("ALERT DELETE");
         alert.setHeaderText(null);
         alert.setContentText(" ARE YOU SURE YOU WANT TO DELETE THIS PRODUCT ?");
         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK)
         {
               sp.deleteProduct(p);
               lproduct.getItems().remove(lproduct.getSelectionModel().getSelectedItem());
               JOptionPane.showMessageDialog(null, " PRODUCT DELETED ");
         } else {
               JOptionPane.showMessageDialog(null, " PRODUCT NOT DELETED");
         }

        cat_p.setText("");
        name_p.setText("");
        stock_p.setText("");
        price_p.setText("");
        details_p.setText("");
        idAdmin_p.setText("");
        add_date_p.setText("");
        
        
    }
    
    
    
    /*********** MODIFIER **********************************************************************************************************************/
    @FXML
    private void updateProduct(ActionEvent event) {
        
        ServiceProduct sp = new ServiceProduct();
        Product p1 = new Product();
        Product p =lproduct.getSelectionModel().getSelectedItem();
        p1.setRef_product(p.getRef_product()); 
        
        System.out.println("id: "+p1.getRef_product());
        p1.setCategory(cat_p.getText());
        System.out.println("cat: "+p1.getCategory());
        p1.setStock(Integer.parseInt(stock_p.getText()));
        System.out.println("stock: "+p1.getStock());
        p1.setName(name_p.getText());
        System.out.println("name: "+p1.getName());
        p1.setPrice(Integer.parseInt(price_p.getText()));
        System.out.println("price: "+p1.getPrice());
        p1.setDetails(details_p.getText());
        System.out.println("details: "+p1.getDetails());
        p1.setId_admin(Integer.parseInt(idAdmin_p.getText()));
        System.out.println("id_admin: "+p1.getId_admin());
        //mazzelet madakhalltech el date 
        
        
        
        //alerrrrrrrrrt*******************************************************************************************
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success of UPDATE");
        alert.setHeaderText(null);
        alert.setContentText("PRODUCT UPDATED !");
        alert.showAndWait();
     
    
        cat_p.setText("");
        stock_p.setText("");
        name_p.setText("");
        price_p.setText("");
        details_p.setText("");
        idAdmin_p.setText("");
        add_date_p.setText("");
        
        sp.updateProduct(p1);
        this.showProduct(null);
     
    }

   
}
