/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;



import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import static pidev.Pidev.isSplashLoader;
/**
 * FXML Controller class
 *
 * @author PC
 */
    public class LoginController implements Initializable {

    @FXML
    private AnchorPane pn_signup;
    @FXML
    private AnchorPane pn_signin;
    @FXML
    private ImageView Exit;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane root1;


    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        
       if(!isSplashLoader){
            loadSplashScreen();
       } 
       Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });  
        
    }
    
    /*@FXML
    private handleButtonAction(ActionEvent event){
        
    }*/
    

    @FXML
    private void signin(ActionEvent event) {
        pn_signin.toFront();
    }

    @FXML
    private void signup(ActionEvent event) {
        pn_signup.toFront();
    }

    @FXML
    private void signup_google(ActionEvent event) {
    }

    @FXML
    private void signup_fb(ActionEvent event) {
    }

    @FXML
    private void signin_google(ActionEvent event) {
    }

    @FXML
    private void signin_fb(ActionEvent event) {
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("adminhome.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
      private void loadSplashScreen(){
        try {
            
            isSplashLoader = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource("splash.fxml"));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3),pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3),pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            
            fadeIn.play();
            
           fadeIn.setOnFinished((e)->{
                fadeOut.play();
            });
            
            fadeOut.setOnFinished((e)->{
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    root.setBackground(Background.EMPTY);
                    root.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            

 }

    
    



