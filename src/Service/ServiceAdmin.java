/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Admin;
import Entities.Client;
import Entities.Password;
import Utils.AES256;
import Utils.SqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yassine
 */
public class ServiceAdmin implements IService.IServiceAdmin {
private static Admin a =new Admin();

    
    private List<Admin> admins = new ArrayList<>();
    private ObservableList<Admin> data = FXCollections.observableArrayList();

    public ObservableList<Admin> getData() {
        return data;
    }

    public List<Admin> getAdmins() {
        return admins;
    }
    
   public static Admin getA() {
        return a;
    }

    public static void setA(Admin a) {
        ServiceAdmin.a = a;
    }
    Connection cnx;
    
    public ServiceAdmin() {
        cnx = SqlConnection.getInstance().getConnection();

    }

    @Override
    public void AddPasswordAdmin(Password a) {

        try {
            Statement stm = cnx.createStatement();
            String rq = "INSERT INTO `password`(`password_id`, `pwd`) VALUES"
                    + " (" + a.getPassword_id() + ",'" +AES256.encrypt(a.getPwd())+ "')";
            stm.executeUpdate(rq);
            System.out.println("cree pass réussi");
        } catch (SQLException ex) {
            System.out.println("erreur de cree pass");
            ex.printStackTrace();

        }

    }

    @Override
    public void AddAdmin(Admin a) {
        try {
            Statement stm = cnx.createStatement();
            String rq = "INSERT INTO `admin`(`id`, `name`, `surname`, `email`, `number`, `sex`, `password_id`, `photo_url`, `role`) VALUES (" + a.getId() + ",'" + a.getName() + "','" + a.getSurname() + "','" + a.getEmail() + "'," + a.getNumber() + ",'" + a.getSex() + "'," + a.getPassword_id() + ",'" + a.getPhoto_url() + "'," + a.getRole() + ")";
            stm.executeUpdate(rq);
            System.out.println("ajout réussi");
        } catch (SQLException ex) {
            System.out.println("erreur d'ajout");
            ex.printStackTrace();
        }
    }

    @Override
    public void UpdateAdmin(Admin a) {
        try {
            Statement stm = cnx.createStatement();

            String rq = "UPDATE `admin` SET `name`='" + a.getName()
                    + "',`surname`='" + a.getSurname() + "',`email`='" + a.getEmail() + "'," + "`number`=" + a.getNumber()
                    + ",`sex`='" + a.getSex() + "',`photo_url`='" + a.getPhoto_url() + "',`role`=" + a.getRole() + " WHERE `id`=" + a.getId();
            stm.executeUpdate(rq);
            System.out.println("modication réussi");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
        }
    }

    @Override
    public Admin SelectAdmin(int id) {
        Admin a = new Admin();
        try {
            Statement stm = cnx.createStatement();
            String rq = "SELECT `id`, `name`, `surname`, `email`, `number`, `birthday`, `sex`, `password_id`, `photo_url`, `role` FROM `admin` WHERE `id`=" + id;
            ResultSet rst = stm.executeQuery(rq);
while (rst.next()) {
            a.setId(rst.getInt("id"));
            a.setName(rst.getString("name"));
            a.setSurname(rst.getString("surname"));
            a.setEmail(rst.getString("email"));
            a.setNumber(rst.getInt("number"));
//                a.setBirthday(rst.getDate("birthday"));
            a.setSex(rst.getString("sex"));
            a.setPassword_id(rst.getInt("password_id"));
            a.setPhoto_url(rst.getString("photo_url"));
            a.setRole(rst.getInt("role"));
            
}
            System.out.println("select réussi");

        } catch (SQLException ex) {
            System.out.println("erreur select");
        }
        return a;
    }
    public Admin SelectAdmin(String mail) {
        Admin a = new Admin();
        try {
            Statement stm = cnx.createStatement();
            String rq = "SELECT `id`, `name`, `surname`, `email`, `number`, `birthday`, `sex`, `password_id`, `photo_url`, `role` FROM `admin` WHERE `email`='" +mail+"'";
            ResultSet rst = stm.executeQuery(rq);
while (rst.next()) {
            a.setId(rst.getInt("id"));
            a.setName(rst.getString("name"));
            a.setSurname(rst.getString("surname"));
            a.setEmail(rst.getString("email"));
            a.setNumber(rst.getInt("number"));
//                a.setBirthday(rst.getDate("birthday"));
            a.setSex(rst.getString("sex"));
            a.setPassword_id(rst.getInt("password_id"));
            a.setPhoto_url(rst.getString("photo_url"));
            a.setRole(rst.getInt("role"));
}
            System.out.println("select réussi");

        } catch (SQLException ex) {
            System.out.println("erreur select");
        }
        return a;
    }

    @Override
    public void DeleteAdmin(Admin a) {
        try {
            Statement stm = cnx.createStatement();
            String rq = "DELETE FROM `admin` WHERE `id`=" + a.getId();
            stm.executeUpdate(rq);
            System.out.println("suppression réussi");
        } catch (SQLException ex) {
            System.out.println("erreur sur suppression");
        }
    }

    @Override
    public void BanClient(int id_Client) {
        try {
            int ban = 2;
            Statement stm = cnx.createStatement();
            String rq = "UPDATE `client` SET `status`=" + ban
                    + " WHERE `id`=" + id_Client;
            stm.executeUpdate(rq);
            System.out.println("ban reussi");
        } catch (SQLException ex) {
            System.out.println("erreur sur ban");
        }
    }
     @Override
    public void UnBanClient(int id_Client) {
        try {
            int ban = 0;
            Statement stm = cnx.createStatement();
            String rq = "UPDATE `client` SET `status`=" + ban
                    + " WHERE `id`=" + id_Client;
            stm.executeUpdate(rq);
            System.out.println("unban reussi");
        } catch (SQLException ex) {
            System.out.println("erreur sur unban");
        }
    }

    @Override
    public void UpdateClient(Client c) {
        try {
            Statement stm = cnx.createStatement();

            String rq = "UPDATE `client` SET `id`=" + c.getId() + ",`name`='" + c.getName() + "',`surname`='" + c.getSurname() + "',`email`='" + c.getEmail() + "',"
                    + "`number`=" + c.getNumber() + ",`birthday`=" + c.getBirthday() + ",`sex`='" + c.getSex() + "',`creation_date`=" + c.getCreation_date() + ","
                    + "`password_id`=" + c.getPassword_id() + ",`photo_url`='" + c.getPhoto_url() + "',`id_cart`=" + c.getId_cart() + ","
                    + "`status`='" + c.getStatus() + "' WHERE `id`=" + c.getId();
            stm.executeUpdate(rq);
            System.out.println("modication réussi");
        } catch (SQLException ex) {
            System.out.println("erreur de modification");
        }
    }

    @Override
    public List<Admin> AffichierAdmin() {
        try {
            Statement stm = cnx.createStatement();
            String rq = "select * from `admin` ";
            ResultSet rst = stm.executeQuery(rq);
            while (rst.next()) {
                Admin a = new Admin();
                a.setId(rst.getInt("id"));
                a.setName(rst.getString("name"));
                a.setSurname(rst.getString("surname"));
                a.setEmail(rst.getString("email"));
                a.setNumber(rst.getInt("number"));
//                a.setBirthday(rst.getDate("birthday"));
                a.setSex(rst.getString("sex"));
                a.setPassword_id(rst.getInt("password_id"));
                a.setPhoto_url(rst.getString("photo_url"));
                a.setRole(rst.getInt("role"));
                admins.add(a);
                data.add(a);
                System.out.println("aff réussi");
            }
        } catch (SQLException ex) {
            System.out.println("erreur sur affichage");
        }
        return admins;
    }
}
