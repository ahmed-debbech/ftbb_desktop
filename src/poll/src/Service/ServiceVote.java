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
import poll.src.Entities.Vote;
import poll.src.Services.IServiceVote;
import utils.SqlConnection;


/**
 *
 * @author sbs
 */
public class ServiceVote implements IServiceVote {
    
     Connection cnx;

    public ServiceVote() {
        cnx = SqlConnection.getInstance().getConnection();
    }

    
    @Override
    public void AddVote(Vote v, int option_id) {
         try {
         Statement stm = cnx.createStatement();
     
        //the options that are added needs to belgon to the POLL with poll_id if poll_id is deleted then the option will to be deleted too SCOPE_IDENTITY / IDENT_CURRENT  /@@IDENTITY
        String query = "INSERT INTO `vote`(`vote_id`, `option_id`)  VALUES ("+v.getOption_id()+","+option_id+");";
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
         Logger.getLogger(ServiceOption.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    @Override
    public void INCVote(int option_id) {
        
        try {
         Statement stm = cnx.createStatement();
     
        //the options that are added needs to belgon to the POLL with poll_id if poll_id is deleted then the option will to be deleted too SCOPE_IDENTITY / IDENT_CURRENT  /@@IDENTITY
        String query = "UPDATE vote set vote_nbr = vote_nbr+1  WHERE option_id ="+option_id+";";
        stm.executeUpdate(query);
        
        } catch (SQLException ex) {
            System.out.println("select a Poll");
     }
        
    }

    @Override
    public int VoteNbr(int option_id) {
        int votenbr=0;
         try {
            Statement stm = cnx.createStatement();
            String query = "SELECT vote_nbr from vote WHERE option_id="+ option_id +";";
            ResultSet rst = stm.executeQuery(query);
            
            List <Integer> nbr = new ArrayList <>();
            while (rst.next()){
                    nbr.add(rst.getInt("vote_nbr"));
             }
      votenbr= nbr.get(0);
//      optionid= id.get(n);
//            votenbr=rst.getInt("vote_nbr");

             
      
//      s= id.get(n);
             
        } catch (SQLException ex) {
            System.out.println("Select the poll that you wish to END!");
     }
         return votenbr;
         
         
       
    }

}
