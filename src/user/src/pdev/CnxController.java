/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.pdev;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pidev.LoginController;
import static pidev.Pidev.isSplashLoader;
import user.src.Entities.Admin;
import user.src.Entities.Client;
import user.src.Service.ServiceAdmin;
import user.src.Service.ServiceClient;
import user.src.Service.ServiceCnx;

/**
 *
 * @author Yassine
 */
public class CnxController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpwd;
    @FXML
    private Button Blogin;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane root1;
    @FXML
    private AnchorPane pn_signup;
    @FXML
    private ImageView Exit;
    @FXML
    private AnchorPane pn_signin;
    @FXML
    private DatePicker tdate;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnumber;
    @FXML
    private PasswordField tfpass;
    @FXML
    private RadioButton Rmale;
    @FXML
    private ToggleGroup sexe;
    @FXML
    private RadioButton Rfemme;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfemail1;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if(!isSplashLoader){
            loadSplashScreen();
       } 
       Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });  
        
    }    

    @FXML
    private void signin(ActionEvent event) {
        pn_signin.toFront();
    }

    @FXML
    private void signup(ActionEvent event) {
        pn_signup.toFront();
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
       ServiceAdmin sa =new ServiceAdmin();
       ServiceClient scl =new ServiceClient();
        ServiceCnx sc =new ServiceCnx();
       Admin a= new Admin ();
       
       Client c= new Client ();
       a.setEmail(tfemail.getText());
        if (sc.CheckAdminCnx(tfemail.getText(),tfpwd.getText())==true) {
            ServiceAdmin.setA(sa.SelectAdmin(tfemail.getText()));
            
                    Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource(""));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        }else 
        { if (sc.CheckClientCnx(tfemail.getText(),tfpwd.getText())==true) {{
            c=scl.SelectClient(tfemail.getText());
            ServiceClient.setA(scl.SelectClient(tfemail.getText()));
            if (c.getStatus()==2){
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre compte est banné.");
            Optional<ButtonType> result = alert.showAndWait();
           } else {
            
           
           
               
            Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("/pidev/clienthome.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);}
        }}
        else {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre email ou votre mot de passe est incorect.");
            Optional<ButtonType> result = alert.showAndWait();}

    }}

    @FXML
     private void Inscription(ActionEvent event) throws IOException {
         Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("/user/src/pdev/InscriptionClient.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    private void ResetPassword(ActionEvent event) throws IOException {
     Blogin.getScene().getWindow().hide();
        
     
        Parent root = FXMLLoader.load(getClass().getResource("/user/src/pdev/ResetPassword.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
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

    private void loadSplashScreen(){
        try {
            
            isSplashLoader = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource("/pidev/splash.fxml"));
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
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource("/pidev/Login.fxml"));
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
