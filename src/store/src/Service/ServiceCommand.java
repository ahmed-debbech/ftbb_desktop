/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.src.Service;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import news.src.utils.Utilities;
import store.src.Entities.Command;
import store.src.Entities.Product;
import store.src.IService.IServiceCommand;
import user.src.Utils.SqlConnection;
/**
 *
 * @author PC
 */
public class ServiceCommand implements IServiceCommand{
    Connection cnx;

    public ServiceCommand() {
        cnx=SqlConnection.getInstance().getConnection();
    }
    
    public void addCommand(int client, int total, int commandid){
         try {
                Statement stm=cnx.createStatement();
                String query="INSERT INTO `command` (`command_id`, `id_client`, `date_command`, `status`, `total_price`) VALUES (" +
                        commandid+", "+client+", NOW(), "+0+", "+total+");";

                stm.executeUpdate(query);
             
            } 
            catch (SQLException ex) 
             {
                 Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
             }  
    }
    public void transProduct(List<Product> list, int client, int commandid){
         try {
             for(Product p : list){
                Statement stm=cnx.createStatement();
                String query="INSERT INTO `command_product` (`id_cp`, `ref_product`, `id_client`, `command_id`) VALUES ("+Utilities.generatedId("command_product", "id_cp")+", " +p.getRef_product()+", "+client+", "+commandid+");";

                stm.executeUpdate(query);
             }
            } 
            catch (SQLException ex) 
             {
                 Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
             }  
    }

    @Override
    public List<Command> showCommand() {
        List<Command> commands = new ArrayList<>();
        try{
             Statement stm = cnx.createStatement();
             String query="select * from `command`; ";
             ResultSet rst = stm.executeQuery(query);
           
             while (rst.next())
             {
                 Command co = new Command();
                 co.setCommand_id(rst.getInt("command_id"));
                 co.setId_client(rst.getInt("id_client"));
                 co.setDate_command(rst.getDate("date_command"));
                 co.setStatus(rst.getInt("status"));
                 co.setTotal_price(rst.getInt("total_price"));
              
                 
                 commands.add(co);
             }
        }catch(SQLException s){
            System.out.println("not showed");
        }
        return commands;
    }

    @Override
    public void updateCommand(Command c, int v) {
        try {
            Statement stm = cnx.createStatement();
            String query = "UPDATE command SET status="+v+" WHERE command_id="+c.getCommand_id()+";";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("COULD NOT UPDATE");
        }
    }
    
    /************ MAILING **********************************************************************************************************************************************************************************************************************************/
     public List<String> getEmailList(int command_id){
       
       List<String> list = null ;
       int i=0;
       String query = "select email from client where id_client in (select id_client from command where command_id = "+command_id+");";
       Statement stm ;
       ResultSet rst;
       try{
           stm = cnx.createStatement();
           rst = stm.executeQuery(query);
           while (rst.next())
            {
                list.add(rst.getString("email"));
                i++;
            }
           
           }
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
       
        return list;
       
   }
    
    
    // mailiiiing 
    
    public void sendEmail(int Command_id) {
        System.out.println("aaaaaaaa");
       // Sender's email ID needs to be mentioned
      String from = "ftbb.store@gmail.com";
      final String username = "ftbb.store@gmail.com";//change accordingly
      final String password = "ftbbstore123";//change accordingly
       
       Statement stm ;
       ResultSet rst;
       try{ System.out.println("bbbbbb");
      System.out.println("ccccc");
               // Recipient's email ID needs to be mentioned.
                String to = "onskechrid1999@gmail.com";
                  //  String to = "arij.mazigh92@gmail.com";
                   // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("Commande validée !");
	
	   // Now set the actual message
	   message.setText("Cher Client,\n" +"\n"+
"Merci de faire vos achats sur FTBB store ! Votre commande 30325136 a été confirmée avec succès.\n" +
"\n" +
"Elle sera à votre disposition dès que possible. Veuillez noter : Si vous avez depassé une semaine pour récupérer votre commande , elle va être annulée automatiquement.\n" +
"\n" +
"Merci d'avoir fait vos achats sur  FTBB store.");

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
                
            }
           
           
       
       
       catch(Exception ex)
       {
           ex.printStackTrace();
       }
      
   }

    @Override
    public List<Command> showClientCommands(int cl) {
         List<Command> list = new ArrayList<>() ;
       String query = "select * from command where id_client="+cl+";";
       Statement stm ;
       ResultSet rst;
       try{
           stm = cnx.createStatement();
           rst = stm.executeQuery(query);
           while (rst.next())
            {
                Command c = new Command();
                c.setCommand_id(rst.getInt("command_id"));
                c.setDate_command(rst.getDate("date_command"));
                c.setStatus(rst.getInt("status"));
                c.setTotal_price(rst.getInt("total_price"));
                c.setId_client(rst.getInt("id_client"));
                list.add(c);
            }
           return list;
           }
       catch(SQLException ex)
       {
           System.out.println("could not retreive user commands");
       }
       return null;
    }

    public List<Product> getProductCommand(int command_id, int id_client) {
       List<Product> list = new ArrayList<>() ;
       String query = "SELECT product.* FROM `command_product` inner join product on product.ref_product=command_product.ref_product where command_id="+command_id+";";
       Statement stm ;
       ResultSet rst;
       try{
           stm = cnx.createStatement();
           rst = stm.executeQuery(query);
           while (rst.next())
            {
                Product c = new Product();
                c.setName(rst.getString("name"));
                c.setPrice(rst.getInt("price"));
                c.setDetails(rst.getString("details"));
                list.add(c);
            }
           return list;
           }
       catch(SQLException ex)
       {
           System.out.println("could not retreive user commands");
       }
       return null;
    }
    
   
}
