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
import report_galerie.src.Entites.Report;
import report_galerie.src.IServices.IServicesReport;

/**
 *
 * @author narug
 */
public class ServiceReport implements IServicesReport {
    
    Connection cnx;
    
    public ServiceReport(){

    cnx=SqlConnexion.getInstance().getConnection();
}    
    
    
    @Override
    public List<Report> ShowReport() throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT * FROM report";
        ResultSet rst=stm.executeQuery(query);
        List<Report>Reports=new ArrayList();
        while(rst.next())
        {
            Report r= new Report();
            r.setReport_id(rst.getInt("report_id"));
            r.setClient_id(rst.getInt("client_id"));
            r.setCommand_id(rst.getInt("command_id"));
            r.setReport_date(rst.getString("report_date"));
            r.setEmail(rst.getString("email"));
            r.setDescription(rst.getString("description"));
            Reports.add(r);
        
        }
        return Reports;
    }

    public List<Report> ShowReport(int client) throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT * FROM report where client_id=" +client+ ";";
        ResultSet rst=stm.executeQuery(query);
        List<Report>Reports=new ArrayList();
        while(rst.next())
        {
            Report r= new Report();
            r.setReport_id(rst.getInt("report_id"));
            r.setClient_id(rst.getInt("client_id"));
            r.setCommand_id(rst.getInt("command_id"));
            r.setReport_date(rst.getString("report_date"));
            r.setEmail(rst.getString("email"));
            r.setDescription(rst.getString("description"));
            Reports.add(r);
        
        }
        return Reports;
    }
    @Override
    public void AddReport(Report r) {
      try {
            Statement stm=cnx.createStatement();
            String query ="INSERT INTO `report`(`report_id`, `client_id`, `command_id`, `report_date`, `email`, `description`) VALUES ("+r.getReport_id()+","+r.getClient_id()+","+r.getCommand_id()+","+"sysdate(), '"+r.getEmail()+"','"+r.getDescription()+"');";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ModifyReport(Report r) {
        try {
            Statement stm=cnx.createStatement();
            String query ="UPDATE report SET description='"+r.getDescription()+"' WHERE report_id="+r.getReport_id()+";";
            
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DeleteReport(Report r) {
         try {
            Statement stm=cnx.createStatement();
            String query ="DELETE FROM report where report_id ="+r.getReport_id()+";";
            
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

}
