/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftbb;

import Utils.Utilities;
//import java.net.Authenticator;
import java.net.PasswordAuthentication;
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

         private static void sendMail(String name, String receiver_email, String comment){
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "ftbb.store@gmail.com";
        //Your gmail password
        String password = "ftbbstore123";

       
        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {});
        
        //Prepare email message
         Message message = new MimeMessage(session);
        try {
             message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver_email));
            message.setSubject("Report answer!");
             String htmlCode = "<h1><b> "+name+" </b> </h1> <h2>has liked your comment: </h2> </br> <b>"+comment+"</b>";
            message.setContent(htmlCode, "text/html");
        } catch (MessagingException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //Send mail
            Transport.send(message);
            System.out.println("Message sent successfully");
        } catch (MessagingException ex) {
                System.out.println("Could not send email!");
        }
    }

    @FXML
    private void respondreport(ActionEvent event) {
       sendMail("x", this.tfce.getText(), this.tfan.getText());
    }
    
}
