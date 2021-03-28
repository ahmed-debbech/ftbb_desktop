/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Admin;
import Entities.Client;
import Entities.Password;
import IService.IServiceCnx;
import Utils.AES256;
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
                             
                a.setPwd(AES256.decrypt(rst.getString("pwd")));
                a.setPassword_id(rst.getInt("password_id"));
                
               
                System.out.println("get info  from password");
            }
           
        } catch (SQLException ex) {
            System.out.println("erreur sur checkpass");
        }
        return (pwd.equals(a.getPwd()));
    }
    @Override
    public boolean CheckAdminCnx(String mail, String pwd) {
      boolean test=false;
       Admin a= new Admin ();
       try{
      ServiceAdmin sa = new ServiceAdmin();
      a= sa.SelectAdmin(mail);
                System.out.println("get info  from mail");
            
            if (a.getEmail() != null){
               test=this.checkPass(a.getPassword_id(), pwd);
             
            }
            
        } catch (Exception ex) {
            System.out.println("erreur sur check");
        }
       
      return test;
    }
     @Override
    public boolean CheckClientCnx(String mail, String pwd) {
      boolean test=false;
       Client a= new Client ();
       try{
      ServiceClient sa = new ServiceClient();
      a= sa.SelectClient(mail);
                System.out.println("get info  from mail");
            
            if (a.getEmail() != null){
               test=this.checkPass(a.getPassword_id(), pwd);
             
            }
            
        } catch (Exception ex) {
            System.out.println("erreur sur check");
        }
       
      return test;
    }
     @Override
    public boolean CheckAdminCnx(String mail) {
      boolean test=false;
       Admin a= new Admin ();
       try{
      ServiceAdmin sa = new ServiceAdmin();
      a= sa.SelectAdmin(mail);
                System.out.println("get info  from mail");
        
            
            if (a.getEmail() != null){
               test=true;
              ServiceAdmin.setA(a);
            }
            
        } catch (Exception ex) {
            System.out.println("erreur sur check");
        }
       
      return test;
    }
     @Override
    public boolean CheckClientCnx(String mail) {
      boolean test=false;
       Client a= new Client ();
       try{
      ServiceClient sa = new ServiceClient();
      a= sa.SelectClient(mail);
                System.out.println("get info  from mail");
            
            if (a.getEmail() != null){
               test=true;
             ServiceClient.setA(a);
            }
            
        } catch (Exception ex) {
            System.out.println("erreur sur check");
        }
       
      return test;
    }
}
