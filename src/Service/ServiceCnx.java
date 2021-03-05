/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Admin;
import Entities.Password;
import IService.IServiceCnx;
import Utils.SqlConnection;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Yassine
 */
public class ServiceCnx implements IServiceCnx{
  Connection cnx;
private Admin b= new Admin (); 

    public Admin getAdmin() {
        return b;
    }

    public ServiceCnx() {
        cnx = SqlConnection.getInstance().getConnection();

    }
    public boolean checkPass(int password_id ,String pwd){
       Password a =new Password() ;
        try{
      String rq="SELECT `password_id`, `pwd` FROM `password` WHERE `password_id`="+password_id;
      Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(rq);
            while (rst.next()) {
                             
                a.setPwd(rst.getString("pwd"));
                a.setPassword_id(rst.getInt("password_id"));
                
               
                System.out.println("get info  from password");
            }
           
        } catch (SQLException ex) {
            System.out.println("erreur sur checkpass");
        }
        return (pwd.equals(a.getPwd()));
    }
    @Override
    public Admin CheckAdminCnx(String mail, String pwd) {
       
       Admin a= new Admin ();
       try{
      String rq="SELECT `id`, `name`, `surname`, `email`, `number`, `birthday`, `sex`,"
              + " `password_id`, `photo_url`, `role` FROM `admin` WHERE `email`="+mail;
      Statement stm = cnx.createStatement();
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
               
                System.out.println("get info  from mail");
            }
            if (a.getEmail() != null){
             boolean  test=this.checkPass(a.getPassword_id(), pwd);
             if (test==true)
                 b=a;
            }
            
        } catch (SQLException ex) {
            System.out.println("erreur sur check");
        }
       
      return b;
    }
    
}
