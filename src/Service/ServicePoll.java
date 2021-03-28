/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Option;
import Entities.Poll;
import Services.IServiceOption;
import Services.IServicePoll;
import java.awt.image.RescaleOp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SqlConnection;

/**
 *
 * @author Slim
 */
public class ServicePoll implements IServicePoll{
    
 Connection cnx;

    public ServicePoll() {
        cnx = SqlConnection.getInstance().getConnection();
    }
 
    
    
    @Override
    public void AddPoll(Poll p) {
     
     try {
         Statement stm = cnx.createStatement();
 
        String query = "INSERT INTO poll(poll_id, description, creation_date) VALUES ("+p.getPoll_id()+",'"+p.getDescription()+"', sysdate())";
        stm.executeUpdate(query);
  
        } catch (SQLException ex) {
         Logger.getLogger(ServicePoll.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    
    
    
    @Override
    public List<Poll> ViewPoll(){
        List <Poll> Polls = new ArrayList <>();
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from poll;";
            ResultSet rst = stm.executeQuery(query);
       
             while (rst.next()){
                    Poll p = new Poll();
                    p.setPoll_id(rst.getInt("poll_id"));
                    p.setDescription(rst.getString("description"));
                    p.setCreation_date(rst.getTimestamp("creation_date"));
                    p.setStatus(rst.getString("status"));
                    Polls.add(p);
                    
             }
             
             
        } catch (SQLException ex) {
         System.out.println("Couldnt show Polls");
     }
         
         Collections.sort(Polls, new SortByDate());
        return Polls;
    }

   @Override
    public void DeletePoll(Poll p) {
     try {
         Statement stm = cnx.createStatement();
     
        
        String query = "DELETE FROM poll WHERE Poll_id='"+ p.getPoll_id() + "'";
        stm.executeUpdate(query);
        
       
        } catch (SQLException ex) {
         Logger.getLogger(ServicePoll.class.getName()).log(Level.SEVERE, null, ex);
     }
    }



    @Override
    public void swapstatus(int poll_id) {
        try {
         Statement stm = cnx.createStatement();
      
        String query = "UPDATE poll set status = 'Ended'  WHERE poll_id ="+poll_id+";";
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            System.out.println("select a Poll");
     }
    }

    @Override
    public void DeleteAll() {
        try {
         Statement stm = cnx.createStatement();
     
        
        String query = "DELETE FROM poll";
        stm.executeUpdate(query);
        
       
        } catch (SQLException ex) {
         Logger.getLogger(ServicePoll.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

   

  public static class SortByDate implements Comparator<Poll> {
      

        @Override
        public int compare(Poll a, Poll b) {
            return b.getCreation_date().compareTo(a.getCreation_date());
        }
    }
}

  