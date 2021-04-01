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
import report_galerie.src.Entites.Feedback;
import report_galerie.src.IServices.IServicesAdminFeedbacks;

/**
 *
 * @author narug
 */
public class ServiceAdminFeedback implements IServicesAdminFeedbacks {

       Connection cnx;
    
    public ServiceAdminFeedback(){

    cnx=SqlConnexion.getInstance().getConnection();
}
    @Override
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
