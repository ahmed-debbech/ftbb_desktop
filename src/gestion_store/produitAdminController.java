/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

import Entities.Product;
import Service.ServiceProduct;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class produitAdminController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField name_p;
    @FXML
    private TextField price_p;
    @FXML
    private TextField details_p;
    @FXML
    private TextField idAdmin_p;
    
    @FXML
    private TextField photo_p;
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
    
    private int test=0;
 
    
     ObservableList<String> list = FXCollections.observableArrayList("Vétéments","Equipements","Abonnements");
    @FXML
    private ComboBox boxcat;
    
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
    private TextField rechercher;
    @FXML
    private Label name1;
    @FXML
    private Button btnInsertImage;
    String urlInserted;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ImageView photo_view;
    private Image image;
   
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        /*   TableColumn t1 = new TableColumn("Reference");
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
        TableColumn t9 = new TableColumn("Photo");
        t9.setCellValueFactory(new PropertyValueFactory<Product, String>("photo"));
        
        lproduct.getColumns().addAll(t1,t2,t3,t4,t5,t6,t7,t8,t9);*/
      
       
        boxcat.setItems(list);
        
      
        /********** RECHERCHER *****************************************************************************************************************/
         
               
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
           
      
        lproduct.setItems(list);
        
        /*********** RECHERCHE D'UN PRODUIT ***************************************************************************************************************************************************/
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Product> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Product -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Product.getCategory().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Product.getDetails().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (Product.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (String.valueOf(Product.getId_admin()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Product.getPrice()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Product.getStock()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Product.getAdd_date()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
                                else if (String.valueOf(Product.getRef_product()).indexOf(lowerCaseFilter)!=-1){
				     return true;
                                }
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Product> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(lproduct.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		lproduct.setItems(sortedData);
    }
   
    

    /******** AJOUTER *****************************************************************************************************************************************************/
    @FXML
    private void insertPrduct(ActionEvent event) {
        ServiceProduct sp =new ServiceProduct();
        Product p = new Product();//taadit mil controller lil model (architecture)
        
        if(verifierChamps()){
          
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
            if(photo_p.getText().matches("^[0-9]+$"))
                {
                   JOptionPane.showMessageDialog(null, "CHECK DETAILS,ONLY STRINGS ARE SUPPORTED ! ");
                   test = 1 ;
                }
        if (test == 0)
        { 
       
        p.setCategory((String) boxcat.getSelectionModel().getSelectedItem());
        p.setStock(Integer.parseInt(stock_p.getText()));//Integer.parseInt --> hedhy fonction de convertion tbaddel el entier li string
        p.setName(name_p.getText());
        p.setPrice(Integer.parseInt(price_p.getText()));
        p.setDetails(details_p.getText());
        p.setId_admin(Integer.parseInt(idAdmin_p.getText()));
        p.setPhoto(photo_p.getText());
        sp.AddProduct(p);
        
        //aleeeeeeeeeeeeeeeert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("SUCCESS INSERT");
        alert.setHeaderText(null);
        alert.setContentText("PRODUCT INSERTED !");
        alert.showAndWait();
        boxcat.setAccessibleText("");
        stock_p.setText("");
        name_p.setText("");
        price_p.setText("");
        details_p.setText("");
        idAdmin_p.setText("");
        photo_p.setText("");
    }}}
     
    private void test(ActionEvent event) {
         System.out.println("HELLLLLOOOOOO !!!");
    }
    
    public boolean verifierChamps() {
        int verif = 0;
        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

   
       
    
        boxcat.setStyle(styledefault);
        stock_p.setStyle(styledefault);
        name_p.setStyle(styledefault);
        price_p.setStyle(styledefault);
        details_p.setStyle(styledefault);
        idAdmin_p.setStyle(styledefault);
        photo_p.setStyle(styledefault);
 
 

        if (boxcat.getTypeSelector().equals("")) {
            boxcat.setStyle(style);
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
        if (photo_p.getText().equals("")) {
            photo_p.setStyle(style);
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
       
        Product p = lproduct.getSelectionModel().getSelectedItem();
        boxcat.setAccessibleText(""+p.getCategory());
        stock_p.setText("" +p.getStock());
        name_p.setText(p.getName());
        price_p.setText("" +p.getPrice());
        details_p.setText(p.getDetails());
        idAdmin_p.setText("" +p.getId_admin());
        photo_p.setText(p.getPhoto());
       
        
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

        boxcat.setAccessibleText("");
        name_p.setText("");
        stock_p.setText("");
        price_p.setText("");
        details_p.setText("");
        idAdmin_p.setText("");
        photo_p.setText("");
        
        
        
        
    }
    
    
    
    /*********** MODIFIER **********************************************************************************************************************/
    @FXML
    private void updateProduct(ActionEvent event) {
        
        ServiceProduct sp = new ServiceProduct();
        Product p1 = new Product();
        Product p =lproduct.getSelectionModel().getSelectedItem();
        p1.setRef_product(p.getRef_product());
        System.out.println("id: "+p1.getRef_product());
        p1.setCategory((String) boxcat.getSelectionModel().getSelectedItem());
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
        p1.setPhoto(photo_p.getText());
        System.out.println("photo: "+p1.getPhoto());
     
        
        
        
        //alerrrrrrrrrt*******************************************************************************************
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success of UPDATE");
        alert.setHeaderText(null);
        alert.setContentText("PRODUCT UPDATED !");
        alert.showAndWait();
     
    
   
        boxcat.setAccessibleText("");
        stock_p.setText("");
        name_p.setText("");
        price_p.setText("");
        details_p.setText("");
        idAdmin_p.setText("");
        photo_p.setText("");
        
       
        sp.updateProduct(p1);
        this.showProduct(null);
     
    }

    @FXML
    private void uploadimage(ActionEvent event) throws IOException {
    
         /*FileChooser chooser =  new FileChooser();
        chooser.setTitle("ons");
        Stage stage = (Stage)anchorpane.getScene().getWindow();
        File file = chooser.showOpenDialog(stage);
        if(file !=null){
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);*/
         FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg = 
                    new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = 
                    new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = 
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
            
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                photo_view.setImage(image);
                photo_p.setText(file.getAbsolutePath());
            } catch (IOException ex) {
                
            }
        
        }

    @FXML
    private void afficherEvents(InputMethodEvent event) {
    }
    
    @FXML
    private void btnback(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("page1.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        //stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    }





    


