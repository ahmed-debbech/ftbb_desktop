/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Report;
import IServices.IServicesAdminReports;
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
public class ServiceAdminReport implements IServicesAdminReports {

    Connection cnx;
    
    public ServiceAdminReport(){

    cnx=SqlConnexion.getInstance().getConnection();
} 
    
    
    @Override
    public List<Report> ShowReport() throws SQLException {
         Statement stm=cnx.createStatement();
        String query="SELECT * FROM report";
        ResultSet rst=stm.executeQuery(query);
        List<Report>Reports=new ArrayList<>();
        while(rst.next())
        {
            Report r= new Report();
            r.setReport_id(rst.getInt("report_id"));
            r.setClient_id(rst.getInt("client_id"));
            r.setCommand_id(rst.getInt("command_id"));
            r.setReport_date(rst.getDate("report_date").toString());
            r.setEmail(rst.getString("email"));
            r.setDescription(rst.getString("description"));
            Reports.add(r);
        }
        return Reports;
    }
    
}
