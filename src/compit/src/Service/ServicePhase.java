/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.Service;

import compit.src.Entites.Phase;
import compit.src.Services.IServicePhase;
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
public class ServicePhase implements IServicePhase {
    Connection cnx;

    public ServicePhase() {
        cnx=Maconnexion.getInstance().getConnection();
        
    }
    
    @Override
    public void AddPhase(Phase p) {
        try {
            Statement stm = cnx.createStatement();
            
        
        String query="INSERT INTO `phase`(`name`) VALUES ('"+p.getName()+"')";
        stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServicePhase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList <Phase> AfficherPhase()throws SQLException {
        
            Statement stm = cnx.createStatement();
        
       String query ="select * from `phase` ";
       ResultSet rst = stm.executeQuery(query);
       List <Phase>phases = new ArrayList<> ();
            while (rst.next()){
                Phase P = new Phase ();
                P.setId(rst.getInt("id"));
                P.setName(rst.getString("name"));
                

                phases.add(P);
            }
       
        return (ArrayList<Phase>) phases;
    }
    public void UpdatePhase(Phase c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "UPDATE `phase` SET `name`='" + c.getName() + "' WHERE `id`= " + c.getId();
            stm.executeUpdate(query);
            System.out.println("valide");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePhase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void DeletePhase(Phase c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "DELETE FROM `phase` WHERE `id`=" + c.getId();
            stm.executeUpdate(query);
            System.out.println("validesp");
        } catch (SQLException ex) {
            Logger.getLogger(ServicePhase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Phase com = new Phase();

    public static Phase getCom() {
        return com;
    }

    public static void setCom(Phase com) {
        ServicePhase.com = com;
    }
    
}
