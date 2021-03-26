/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author PC
 */
    public class ClienthomeController implements Initializable {

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;
    
    
    @FXML
    private ComboBox box;
   
    ObservableList<String> list = FXCollections.observableArrayList("Votre Compte","Vos commandes","Vos envis");
    
    @FXML
    private ImageView logo;
    @FXML
    private AnchorPane linker;
    @FXML
    private Text title;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        //exit button
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        //menu
        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });
        
        //hedhy ta3 el combobox ta3 el client illi fil menu mil fou9
        box.setValue("Bonjour CLIENT !");
        box.setItems(list);
        
        //houni kif tenzel aala el logo yhezzek lil page d'acceuil
        /*logo.setOnMouseClicked(event -> {
            logo.
        }   */   
   }

    @FXML
    private void btnActualiteCl(ActionEvent event) throws IOException {
         Node node;
            try {
                title.setText("Fédération Tunisienne de Basket-Ball"+"-Actualié");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/view/FXMLClientArticles.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @FXML
    private void btnSondageCl(ActionEvent event) throws IOException {
         Node node;
            try {
                title.setText("Fédération Tunisienne de Basket-Ball"+"-Sondage");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("slim.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @FXML
    private void btnStoreCl(ActionEvent event) throws IOException {
         Node node;
            try {
                title.setText("Fédération Tunisienne de Basket-Ball"+"-Store");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("aandiEna.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @FXML
    private void btnGalerieCl(ActionEvent event) throws IOException {
         Node node;
            try {
                title.setText("Fédération Tunisienne de Basket-Ball"+"-Galerie");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("amine.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    }

    @FXML
    private void btnReportCl(ActionEvent event) throws IOException {
        Node node;
            try {
                    title.setText("Fédération Tunisienne de Basket-Ball"+"-Report");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("amine.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void btnCompteCl(ActionEvent event) throws IOException {
       Node node;
            try {
                title.setText("Fédération Tunisienne de Basket-Ball"+"-Compte");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("yassine.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void btnCompetitionCl(ActionEvent event) throws IOException {
        Node node;
            try {
                 title.setText("Fédération Tunisienne de Basket-Ball"+"-Competition");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("ali.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }

    @FXML
    private void btncart(ActionEvent event) throws IOException {
        Node node;
            try {
               title.setText("Fédération Tunisienne de Basket-Ball"+"-Votre panier");  
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("aandiEna.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btnhelp(ActionEvent event) throws IOException {
        Node node;
            try {
            title.setText("Fédération Tunisienne de Basket-Ball"+"-Aide");     
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("aandiEna.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    @FXML
    private void btnactualitelogo(ActionEvent event) throws IOException {
        title.setText("Fédération Tunisienne de Basket-Ball"+"-Actualité");  
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clienthome.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();
    }
}

      
    

