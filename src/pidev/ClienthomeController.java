/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Passable;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ClienthomeController implements Initializable {

    @FXML
    private ImageView Exit;
    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView menu;
    @FXML
    private AnchorPane pane2;
    @FXML
    private Button parametre;
    @FXML
    private AnchorPane pane3;
    @FXML
    private  AnchorPane linker;
    @FXML
    private ComboBox box;
    ObservableList<String> list = FXCollections.observableArrayList("Votre Compte","Vos commandes","Vos envis");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("bbbbbbb");
           
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        pane1.setVisible(false);
        
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        System.out.println("vvvvv");
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition.setByX(-1200);
        translateTransition.play();
        System.out.println("555555");
        
        menu.setOnMouseClicked(event -> {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();
            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
            if(pane2.getTranslateX() == -1200.0){
                translateTransition1.setByX(+1200);
            }else{
                if(pane2.getTranslateX() == 0.0){
                    translateTransition1.setByX(-1200);
                }
            }
             translateTransition1.play();
            

        });
        
        System.out.println("eeeeewwww");
        pane1.setOnMouseClicked(event ->{
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition1.setFromValue(0.15);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
        
        fadeTransition1.setOnFinished(event1 -> {
            pane1.setVisible(false);
        });
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane2);
        translateTransition1.setByX(-600);
        translateTransition1.play();
        });
        
        
        /*******************************************************************************************************************/
        
        pane1.setVisible(false);
        
        FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition1.setFromValue(1);
        fadeTransition1.setToValue(0);
        fadeTransition1.play();
        
        TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),pane3);
        translateTransition1.setByX(-2400);
        translateTransition1.play();
        
        parametre.setOnMouseClicked(event2 -> {
             TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
             if(pane3.getTranslateX() == -2400.0){
                translateTransition2.setByX(+2400.0);
             }else{
                 if(pane2.getTranslateX() == 0.0){
                    translateTransition2.setByX(-2400.0);
                 }
             }
        translateTransition2.play();
        
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition2.setFromValue(0);
        fadeTransition2.setToValue(0.15);
        fadeTransition2.play();
        });
        
        pane1.setOnMouseClicked(event2 ->{
        FadeTransition fadeTransition2 = new FadeTransition(Duration.seconds(0.5),pane1);
        fadeTransition2.setFromValue(0.15);
        fadeTransition2.setToValue(0);
        fadeTransition2.play();
        
        fadeTransition2.setOnFinished(event3 -> {
            pane1.setVisible(false);
        });
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(0.5),pane3);
        translateTransition2.setByX(-1200);
        translateTransition2.play();
        });
        //hedhy ta3 el combobox ta3 el client illi fil menu mil fou9
        box.setValue("Bonjour CLIENT !");
        box.setItems(list);
        Passable.getInstance().setContainer(linker);
    }    

    @FXML
    private void btnactualite(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/news/src/view/FXMLClientArticles.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btnsondage(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("slim.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btncompetition(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("ali.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btnreport(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("amine.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btngalerie(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("amine.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btnstore(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/store/src/gestion_store/market.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btnprofil(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("/user/src/pdev/ProfileUtil.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

  
    @FXML
    private void btncart(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("ena.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void btnretour(ActionEvent event) {
         Node node;
         //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek
         node = null;
         linker.getChildren().clear();
    }

    @FXML
    private void btnaide(ActionEvent event) {
         Node node;
            try {
            //thott houni fil 'getResources' esm el fichier illi khadem fih el crud mte3ek  
            node = (Node)FXMLLoader.load(getClass().getResource("Help.fxml"));
            linker.getChildren().setAll(node);

            } catch (IOException ex) {
            Logger.getLogger(ClienthomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }


    @FXML
    private void btnparametre(ActionEvent event) {
    }

    @FXML
    private void btngestion(MouseEvent event) {
    }

   

}
