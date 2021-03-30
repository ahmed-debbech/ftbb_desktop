/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Competition;
import Services.IServiceCompetition;
import com.sun.applet2.preloader.event.ConfigEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import utils.Maconnexion;

/**
 *
 * @author Lenovo
 */
public class ServiceCompetition implements IServiceCompetition {

    Connection cnx;

    public ServiceCompetition() {
        cnx = Maconnexion.getInstance().getConnection();

    }

    @Override
    public void AddCompetition(Competition c) throws SQLException {
        String query;
        query = "INSERT INTO `competition`(`name`) VALUES('" + c.getName() + "')";
        try {

            Statement stm = cnx.createStatement();

            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Competition> AfficherCompetition() throws SQLException {

        Statement stm = cnx.createStatement();

        String query = "select * from `competition` ";
        ResultSet rst = stm.executeQuery(query);
        List<Competition> competitions = new ArrayList<>();
        while (rst.next()) {
            Competition C = new Competition();
            C.setId(rst.getInt("id"));
            C.setName(rst.getString("name"));
            C.setCalendar(rst.getString("calendar"));

            competitions.add(C);
        }

        return (ArrayList<Competition>) competitions;
    }

    @Override
    public void UpdateCompetition(Competition c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "UPDATE `competition` SET `name`='" + c.getName() + "' WHERE `id`= " + c.getId();
            stm.executeUpdate(query);
            System.out.println("valide");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Competition SelectedCompetition(String str) throws SQLException {
        Competition c = new Competition();
        Statement stm = cnx.createStatement();

        String query = "select * from `competition` where `name`=' " + str + "'";
        ResultSet rst = stm.executeQuery(query);
while (rst.next()) {
        c.setId(rst.getInt("id"));
        c.setName(rst.getString("name"));
        c.setCalendar(rst.getString("calendar"));
        
}
System.out.println("select ss");
        return c;
    }
    
    
private static Competition com = new Competition();

    public static Competition getCom() {
        return com;
    }

    public static void setCom(Competition com) {
        ServiceCompetition.com = com;
    }
    
    
     
    public void DeleteCompetition(Competition c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "DELETE FROM `competition` WHERE `id`=" + c.getId();
            stm.executeUpdate(query);
            System.out.println("validesp");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
