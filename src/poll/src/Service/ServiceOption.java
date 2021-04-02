/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.Service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import poll.src.Entities.Option;
import poll.src.Services.IServiceOption;
import utils.SqlConnection;

/**
 *
 * @author sbs
 */
public class ServiceOption implements IServiceOption {
    
    Connection cnx;

    public ServiceOption() {
        cnx = SqlConnection.getInstance().getConnection();
    }

    
    @Override
    public void AddOption(Option o, int poll_id) {
         try {
         Statement stm = cnx.createStatement();
     
        //the options that are added needs to belgon to the POLL with poll_id if poll_id is deleted then the option will to be deleted too SCOPE_IDENTITY / IDENT_CURRENT  /@@IDENTITY
        String query = "INSERT INTO `options`(`option_id`, `description`, `poll_id`)  VALUES ("+o.getOption_id()+",'"+o.getDescription()+"',"+poll_id+");";
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
         Logger.getLogger(ServiceOption.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    
    @Override
    public List<Option> ViewOptions() throws SQLException {
        List <Option> Options = new ArrayList <>();
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from options";
            ResultSet rst = stm.executeQuery(query);
       
             
             
             
        } catch (SQLException ex) {
         Logger.getLogger(ServicePoll.class.getName()).log(Level.SEVERE, null, ex);
     }
         
         
         
        return Options;
    }

//    @Override
//    public void DeleteOptions(String poll_id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    
    
    @Override
    public String displayoption(int poll_id , int n) {
      String s="";
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT description from options WHERE Poll_id="+ poll_id +";";
            ResultSet rst = stm.executeQuery(query);
            
            List <String> Desc = new ArrayList <>();
            while (rst.next()){
                    Desc.add(rst.getString("description"));
             }
      
      s= Desc.get(n);
             
        } catch (SQLException ex) {
            System.out.println("Select poll");
     }
         return s;
         
         
       
    }
    
    @Override
    public int optId(int poll_id , int n) {
      int optionid=0;
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT option_id from options WHERE Poll_id="+ poll_id +";";
            ResultSet rst = stm.executeQuery(query);
            
            List <Integer> id = new ArrayList <>();
            while (rst.next()){
                    id.add(rst.getInt("option_id"));
             }
      
      optionid= id.get(n);
             
        } catch (SQLException ex) {
            System.out.println("Select poll");
     }
         return optionid;
         
         
       
    }

  
    
}


//@Override
//    public String displayoption(int poll_id) {
//      String s="";
//         try {
//            Statement stm = cnx.createStatement();
//            String query = "SELECT description from options WHERE Poll_id="+ poll_id +";";
//            
//            ResultSet rst = stm.executeQuery(query);
//       rst.next();
//       s=rst.getString("description");
//             
//        } catch (SQLException ex) {
//            System.out.println("Select poll");
//     }
//         return s;
//         
//         
//       
//    }