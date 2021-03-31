/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.pdev;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import user.src.Service.ServiceAdmin;
import user.src.Service.ServiceClient;
import user.src.Service.ServiceCnx;
import user.src.Utils.MailUtils;

/**
 * FXML Controller class
 *
 * @author Yassine
 */
public class ResetPasswordController implements Initializable {

    @FXML
    private TextField tfemail1;
    @FXML
    private Button BTValidEmail;
    @FXML
    private Button BTValideCode;
    @FXML
    private TextField tfCode;
    @FXML
    private Label lCode;

    /**
     * Initializes the controller class.
     */
    Random rand = new Random();
                int nb = rand.nextInt(99999999); 
                int test=0;
    @FXML
    private TextField TfNvpass;
    @FXML
    private Button BTNouvPass;
    @FXML
    private Label lNvPass;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BTValideCode.setVisible(false);
        tfCode.setVisible(false);
        lCode.setVisible(false);
        BTNouvPass.setVisible(false);
        lNvPass.setVisible(false);
        BTNouvPass.setVisible(false);
               TfNvpass.setVisible(false);
            }    

    @FXML
    private void ValiderEmailReset(ActionEvent event) throws MessagingException {
        ServiceCnx sc =new ServiceCnx();
        
        if (sc.CheckAdminCnx(tfemail1.getText())==true){
            MailUtils.sendMail(tfemail1.getText(), nb);
            BTValideCode.setVisible(true);
        tfCode.setVisible(true);
        lCode.setVisible(true);
        BTValidEmail.setVisible(false);
        test=1;
        }else if (sc.CheckClientCnx(tfemail1.getText())==true) {
            MailUtils.sendMail(tfemail1.getText(), nb);
            BTValideCode.setVisible(true);
        tfCode.setVisible(true);
        lCode.setVisible(true);
        BTValidEmail.setVisible(false);
        test=2;
        } else 
            {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre email  est incorect.");
            Optional<ButtonType> result = alert.showAndWait();}
    }

    @FXML
    private void ValiderCodeReset(ActionEvent event) {
        if (nb==Integer.parseInt(tfCode.getText())){ 
            BTValideCode.setVisible(false);
             BTNouvPass.setVisible(true);
        lNvPass.setVisible(true);
        BTNouvPass.setVisible(true);
        TfNvpass.setVisible(true);
        }
        else{
            {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("WARNING!");
            alert.setHeaderText(null);
            alert.setContentText("votre Code  est incorect.");
            Optional<ButtonType> result = alert.showAndWait();} 
        }
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
         BTValidEmail.getScene().getWindow().hide();      
     
        Parent root = FXMLLoader.load(getClass().getResource("Cnx.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    private void ValiderNouveauPw(ActionEvent event) throws IOException {
        if (test==1) {
            ServiceAdmin ad =new ServiceAdmin(); 
            ad.UpdateAdminPass(TfNvpass.getText());
            this.Retour(event);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Password Modifier");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            ServiceClient cl =new ServiceClient(); 
            cl.UpdateClientPass(TfNvpass.getText());
            this.Retour(event);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Password Modifier");
            Optional<ButtonType> result = alert.showAndWait();
            
        }
    }
    
}
