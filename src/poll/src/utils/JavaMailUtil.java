/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sbs
 */
public class JavaMailUtil {
    
     public static void Send(String reveicer, String desc,String op1, String op2) throws AddressException, MessagingException  {
        JavaMailUtil se = new JavaMailUtil();
		//authentication info
              
                    final String myEmail = "ftbb.store@gmail.com";
                    final String password = "ftbbstore123";
                   

                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(myEmail,password);
                            }
                    });
                    //Start our mail message
                    MimeMessage msg = new MimeMessage(session);
                    
                        msg.setFrom(new InternetAddress(myEmail));
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(reveicer));
                        msg.setSubject("New poll has been Added");
                        msg.setText("Hello dear User, \nthere is a new poll added you can vote now on our App!\n \n    ☉ "+desc+"\n         ✔  "+op1+"\n         ✔  "+op2 );		
                        Transport.send(msg);
                        System.out.println("Email envoyé");
              
	}
     
    public static void SendA(String reveicer, String desc,String op1, String op2) throws AddressException, MessagingException  {
        JavaMailUtil se = new JavaMailUtil();
		//authentication info
              
                    final String myEmail = "ftbb.store@gmail.com";
                    final String password = "ftbbstore123";
                   

                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", "smtp.gmail.com");
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(myEmail,password);
                            }
                    });
                    //Start our mail message
                    MimeMessage msg = new MimeMessage(session);
                    
                        msg.setFrom(new InternetAddress(myEmail));
                        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(reveicer));
                        msg.setSubject("Poll just ended");
                        msg.setText("Hello dear User, \nthe poll below just ended you can check results now on our App!\n \n    ☉ "+desc+"\n         ✔  "+op1+"\n         ✔  "+op2 );		
                        Transport.send(msg);
                        System.out.println("Email envoyé");
              
	}
    
}
