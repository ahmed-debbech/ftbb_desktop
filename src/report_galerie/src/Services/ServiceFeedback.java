/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.Services;

import report_galerie.src.Utils.SqlConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import report_galerie.src.Entites.Feedback;

/**
 *
 * @author narug
 */
public class ServiceFeedback {
     Connection cnx;
    
    public ServiceFeedback(){

    cnx=SqlConnexion.getInstance().getConnection();
}    




    public List<Feedback> ShowFeedback() throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT * FROM feedback";
        ResultSet rst=stm.executeQuery(query);
        List<Feedback>Feedbacks=new ArrayList();
        while(rst.next())
        {
            Feedback f= new Feedback();
            f.setFeedback_id(rst.getInt("feedback_id"));
            f.setClient_id(rst.getInt("client_id"));
            f.setFeedback_date(rst.getString("feedback_date"));
            f.setEmail(rst.getString("email"));
            f.setTopic(rst.getString("topic"));
            f.setText(rst.getString("text"));
            f.setType(rst.getString("type"));
            Feedbacks.add(f);
        
        }
        return Feedbacks;
    }
 public List<Feedback> ShowFeedback(int client) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT * FROM feedback where client_id=" +client+ ";";
        ResultSet rst=stm.executeQuery(query);
        List<Feedback>Feedbacks=new ArrayList();
        while(rst.next())
        {
            Feedback f= new Feedback();
            f.setFeedback_id(rst.getInt("feedback_id"));
            f.setClient_id(rst.getInt("client_id"));
            f.setFeedback_date(rst.getString("feedback_date"));
            f.setEmail(rst.getString("email"));
            f.setTopic(rst.getString("topic"));
            f.setText(rst.getString("text"));
            f.setType(rst.getString("type"));
            Feedbacks.add(f);
        
        }
        return Feedbacks;
    }
    public void AddFeedback(Feedback f) {
        try {
            Statement stm=cnx.createStatement();
            String query ="INSERT INTO `feedback`(`feedback_id`, `client_id`, `feedback_date`, `email`, `topic`, `text`, `type`) VALUES ("+f.getFeedback_id()+","+f.getClient_id()+","+"sysdate(), '"+f.getEmail()+"','"+f.getTopic()+"','"+f.getText()+"','"+f.getType()+"');";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }


    public void ModifyFeedback(Feedback f ){
     try {
            Statement stm=cnx.createStatement();
            String query ="UPDATE feedback SET text='"+f.getText()+"' WHERE feedback_id="+f.getFeedback_id()+";";
            
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public void DeleteFeedback(Feedback f){
        try {
            Statement stm=cnx.createStatement();
            String query ="DELETE FROM feedback where feedback_id ="+f.getFeedback_id()+";";
            
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    }


