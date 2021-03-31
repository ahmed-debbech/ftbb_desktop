/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sbs
 */
public class JavaMailUtil {
    public static void sendMail(String recipient) throws Exception{
    Properties properties = new Properties();
    
    properties.put("mail.stmp.auth", "true");
    properties.put("mail.stmp.starttls.enable", "true");
    properties.put("mail.stmp.host", "smtp.gmail.com");
    properties.put("mail.stmp.port", "587");
    
    String myAccountEmail = "slim.jaafoura@esprit.tn";
    String password = "sirano123";
    
        Session session = Session.getInstance(properties,new javax.mail.Authenticator() {
        @Override
        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(myAccountEmail, password);
        }
        });
        
       Message message = prepareMessage(session, myAccountEmail, recipient );
        Transport.send(message);
    }
       
  

    private static Message prepareMessage(Session session, String myAccountEmail,String recipient) {
     
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            
            message.setSubject("new poll has been created");
            message.setText("Hello dear user, \n a new poll has been created on the poll section you can vote now!");
            
            return message;
            
            
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
