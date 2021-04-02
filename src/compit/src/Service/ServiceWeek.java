/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.Service;

import compit.src.Entites.Week;
import compit.src.Services.IServiceWeek;
import compit.src.utils.Maconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Lenovo
 */
public class ServiceWeek implements IServiceWeek {
    Connection cnx; 
    public ServiceWeek() {
        cnx=Maconnexion.getInstance().getConnection();
        
    }
    
    @Override
    public void AddWeek(Week w) {
        try {
            Statement stm = cnx.createStatement();
            
        
        String query="INSERT INTO `week`(`Name_week`) VALUES ('"+w.getName_week()+"')";
        stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePhase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void DeleteWeek(Week c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "DELETE FROM `week` WHERE `id`=" + c.getId();
            stm.executeUpdate(query);
            System.out.println("validesp");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceWeek.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void UpdateWeek(Week c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "UPDATE `week` SET `Name_week`='" + c.getName_week()+ "' WHERE `id`= " + c.getId();
            stm.executeUpdate(query);
            System.out.println("valide");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePhase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList <Week> AfficherWeek()throws SQLException {
        
            Statement stm = cnx.createStatement();
        
       String query ="select * from `week` ";
       ResultSet rst = stm.executeQuery(query);
       List <Week>weeks = new ArrayList<> ();
            while (rst.next()){
                Week W = new Week ();
                W.setId(rst.getInt("id"));
                W.setName_week(rst.getString("Name_week"));
                

                weeks.add(W);
            }
       
        return (ArrayList<Week>) weeks;
    }
    private static Week com = new Week();

    public static Week getCom() {
        return com;
    }

    public static void setCom(Week com) {
        ServiceWeek.com = com;
    }
}
