/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_store;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author PC
 */
    public class adminhomeController implements Initializable {

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuClose;

    @FXML
    private AnchorPane slider;
    @FXML
    private ImageView buttonvueclient;
    @FXML
    private ImageView logo;
    @FXML
   private AnchorPane linker;
    @FXML
    private Text title;

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
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
      /*  this.buttonvueclient.setOnMouseClicked(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clienthome.fxml"));
        Parent root1 = null;
            try {
                root1 = (Parent) fxmlLoader.load();
            } catch (IOException ex) {
                Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        });*/
    }

    @FXML
    private void btnActualiteAd(ActionEvent event) throws IOException {
      Node node;
            try {
                  title.setText("Fédération Tunisienne de Basket-Ball"+"-Actualié");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("ahmed.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
    }

    @FXML
    private void btnSondageAd(ActionEvent event) throws IOException {
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
    private void btnStoreAd(ActionEvent event) throws IOException {
         Node node;
            try {
            title.setText("Fédération Tunisienne de Basket-Ball"+"-Store");
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("page1.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(adminhomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }

    @FXML
    private void btnGalerieAd(ActionEvent event) throws IOException {
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
    private void btnReportAd(ActionEvent event) throws IOException {
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
    private void btnCompteAd(ActionEvent event) throws IOException {
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
    private void btnCompetitionAd(ActionEvent event) throws IOException {
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
    
    //mrigla
    @FXML
    private void btnvueclient(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("page2c.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();

    }
    
    //mrigla
    @FXML
    private void btnactualitelogo(ActionEvent event) throws IOException {
          title.setText("Fédération Tunisienne de Basket-Ball"+"-Actualité");  
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminhome.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED); 
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    }

   



