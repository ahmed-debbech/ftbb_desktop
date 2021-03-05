/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Feedback;
import Utils.SqlConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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






}