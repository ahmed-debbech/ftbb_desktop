/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.Utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Ahmed
 */
/**
 * a function that generates an ID of 8 numbers based on your table and the id attribute 
 * ver: 0.1
 * @author Ahmed
 */
public class Utilities {
    public static int generatedId(String table_name, String attribute_name){
        Connection cnx = SqlConnexion.getInstance().getConnection();
        int generated = 0;
        try {
            ResultSet rst;
            do{
                Statement stm = cnx.createStatement();
                Random rand = new Random();
                generated = rand.nextInt(99999999); 
                //System.out.println("[Utlities.generatedId()] the random id is " + generated);
                String query = "select * from `"+table_name+"` where "+attribute_name+" = " + generated + ";";
                rst = stm.executeQuery(query);
            }while(rst.next());
            
        } catch (SQLException ex) {
            System.out.println("Could not generate an id due to the loss of connection.");
            generated = -1;
        }
        return generated;
    }

    public static String timestampConverter(String report_date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static String pathToUrl(String str){
            Path p = Paths.get(str);
            String file = p.getFileName().toString();
            String s=(new StringBuilder()).append("http://127.0.0.1/uploads/").append(file).toString();  
            return s;
    }
   
    public static void sendMail(String name, String receiver_email, String comment){
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
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        
        //Prepare email message
         Message message = new MimeMessage(session);
        try {
             message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver_email));
            message.setSubject("Report answer!");
             String htmlCode = "<h1><b> "+name+" </b> </h1> <h2>Admin answered you : </h2> </br> <b>"+comment+"</b>";
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

}
