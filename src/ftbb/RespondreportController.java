/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Utils.Utilities;
//import java.net.Authenticator;
import javax.mail.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;


/**
 * FXML Controller class
 *
 * @author narug
 */
public class RespondreportController implements Initializable {

    @FXML
    private TextField tfce;
    @FXML
    private TextArea tfan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void pass(String x){
        tfce.setText(x);
    
    }

        

    @FXML
    private void respondreport(ActionEvent event) {
       Utilities.sendMail("x", this.tfce.getText(), this.tfan.getText());
    }
    
}
