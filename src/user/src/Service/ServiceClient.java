/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.Service;


import user.src.Utils.AES256;
import user.src.Utils.SqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import user.src.Entities.Client;
import user.src.IService.IServiceClient;

/**
 *
 * @author Yassine
 */
public class ServiceClient implements IServiceClient{
    Connection cnx;
     private List<Client> Clients = new ArrayList<>();
     private ObservableList<Client> data = FXCollections.observableArrayList();
private static Client a =new Client();
    public ObservableList<Client> getData() {
        return data;
    }
    public List<Client> getAdmins() {
        return Clients;
    }

    public static Client getA() {
        return a;
    }

    public static void setA(Client a) {
        ServiceClient.a = a;
    }

   
public ServiceClient() {
        cnx = SqlConnection.getInstance().getConnection();

    }
    @Override
    public void AddClient(Client c) {
     try {
            Statement stm = cnx.createStatement();
            String rq ="INSERT INTO `client`(`id`, `name`, `surname`, `email`, `number`, `sex`,  `password_id`, `photo_url`, `id_cart`, `status`, `birthday`) VALUES ("+c.getId()+",'"+c.getName()+"','"+c.getSurname()+"','"+c.getEmail()+"',"+c.getNumber()+",'"+c.getSex()+"',"+c.getPassword_id()+",'"+c.getPhoto_url()+"',"+c.getId_cart()+",'"+c.getStatus()+"','"+c.getBirthday()+"')";
            stm.executeUpdate(rq);
            System.out.println("ajout réussi");
        } catch (SQLException ex) {
            System.out.println("erreur d'ajout");
            ex.printStackTrace();
        }    }

    @Override
    public List<Client> AffichierClient() {
    try {
            Statement stm = cnx.createStatement();
            String rq = "select * from `client` ";
            ResultSet rst = stm.executeQuery(rq);
            while (rst.next()) {
                Client a = new Client();
                a.setId(rst.getInt("id"));
                a.setName(rst.getString("name"));
                a.setSurname(rst.getString("surname"));
                a.setEmail(rst.getString("email"));
                a.setNumber(rst.getInt("number"));
                a.setBirthday(rst.getDate("birthday"));
                a.setSex(rst.getString("sex"));
                a.setPassword_id(rst.getInt("password_id"));
                a.setPhoto_url(rst.getString("photo_url"));
                a.setCreation_date(rst.getDate("creation_date"));
                a.setId_cart(rst.getInt("id_cart"));
                a.setStatus(rst.getInt("status"));
                Clients.add(a);
                data.add(a);
                System.out.println("aff réussi");
                 
            }
        } catch (SQLException ex) {
            System.out.println("erreur sur affichage");
        }
        return Clients;
    }

    @Override
    public void UpdateClient(Client c) {
        try {
            Statement stm = cnx.createStatement();

            String rq = "UPDATE `client` SET `name`='" + c.getName() + "',`surname`='" + c.getSurname() + "',`email`='" + c.getEmail() + "',"
                    + "`number`=" + c.getNumber() 
                    + ",`birthday`='" + c.getBirthday() 
                    + "',`sex`='" + c.getSex() + "',"
                    + "`password_id`=" + c.getPassword_id() + ",`photo_url`='" + c.getPhoto_url() + "',`id_cart`=" + c.getId_cart() + ","
                    + "`status`='" + c.getStatus() + "' WHERE `id`=" + c.getId();
            stm.executeUpdate(rq);
            System.out.println("modication réussi");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
            ex.printStackTrace();
        }
    }
      public Client SelectClient(String mail) {
        Client a = new Client();
        try {
            Statement stm = cnx.createStatement();
            String rq = "SELECT `id`, `name`, `surname`, `email`, `number`, `birthday`, `sex`, `password_id`, `photo_url`,`creation_date`,`id_cart`, `status` FROM `client` WHERE `email`='" +mail+"'";
            ResultSet rst = stm.executeQuery(rq);
while (rst.next()) {
            a.setId(rst.getInt("id"));
            a.setName(rst.getString("name"));
            a.setSurname(rst.getString("surname"));
            a.setEmail(rst.getString("email"));
            a.setNumber(rst.getInt("number"));
            a.setBirthday(rst.getDate("birthday"));
            a.setSex(rst.getString("sex"));
            a.setPassword_id(rst.getInt("password_id"));
            a.setPhoto_url(rst.getString("photo_url"));
            a.setId_cart(rst.getInt("id_cart"));
            a.setCreation_date(rst.getDate("creation_date"));
            a.setStatus(rst.getInt("status"));
}
            System.out.println("select réussi");

        } catch (SQLException ex) {
            System.out.println("erreur select");
        }
        return a;
    }
       @Override
    public void UpdateClientPass(String pass) {
        Client ad=new Client ();
        ad= ServiceClient.getA();
      try {
            Statement stm = cnx.createStatement();
//            Date date = new Date(System.currentTimeMillis());
//            String rq = "UPDATE `password` SET `last_change`="+date+",`previous_pwd`=`pwd` WHERE `password_id`= "+a.getPassword_id();
//            stm.executeUpdate(rq);
            String rq1 ="UPDATE `password` SET `pwd`='"+AES256.encrypt(pass)+"' WHERE `password_id`="+ad.getPassword_id();
            stm.executeUpdate(rq1);
            System.out.println("modication réussi");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
        }
    }
    }
    

  
