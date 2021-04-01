/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Competition;
import Entites.Team;
import Services.IServiceTeam;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Maconnexion;

/**
 *
 * @author Lenovo
 */
public class ServiceTeam implements IServiceTeam {
    Connection cnx;
private ObservableList<Team> data = FXCollections.observableArrayList();
public ObservableList<Team> getData() {
        return data;
    }
    public ServiceTeam() {
        cnx = Maconnexion.getInstance().getConnection();

    }
    @Override
    public void AddTeam(Team c) throws SQLException {
        String query;
        query = "INSERT INTO `team`(`name`,`id_competition`) VALUES('" + c.getName() + "','" + c.getId_competition() + "')";
        try {

            Statement stm = cnx.createStatement();

            stm.executeUpdate(query);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   @Override
    public ArrayList<Team> AfficherTeam() throws SQLException {

        Statement stm = cnx.createStatement();

        String query = "select * from `team` ";
        ResultSet rst = stm.executeQuery(query);
        List<Team> teams = new ArrayList<>();
        while (rst.next()) {
            Team C = new Team();
            C.setId(rst.getInt("id"));
            C.setLogo(rst.getString("logo"));
            C.setName(rst.getString("name"));
            C.setId_competition(rst.getInt("id_competition"));
            

            teams.add(C);
            data.add(C);
        }

        return   (ArrayList<Team>) teams;
    }
    
   @Override
    public void UpdateTeam(Team c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "UPDATE `team` SET `name`='" + c.getName() + "' WHERE `id`= " + c.getId();
            stm.executeUpdate(query);
            System.out.println("valide");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTeam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Team SelectedTeam(String str) throws SQLException {
        Team c = new Team();
        Statement stm = cnx.createStatement();

        String query = "select * from `team` where `name`=' " + str + "'";
        ResultSet rst = stm.executeQuery(query);
while (rst.next()) {
        c.setId(rst.getInt("id"));
        c.setName(rst.getString("name"));
        c.setLogo(rst.getString("logo"));
        c.setId_competition(rst.getInt("id_competition"));
       
        
}
System.out.println("select ss");
        return c;
    }
    
    private static Team com = new Team();

    public static Team getCom() {
        return com;
    }

    public static void setCom(Team com) {
        ServiceTeam.com = com;
    }
   
    public void DeleteTeam(Team c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "DELETE FROM `team` WHERE `id`=" + c.getId();
            stm.executeUpdate(query);
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTeam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Team GetTeamById(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        
       
       String query = "select `id`, `name`, `id_competition`, `logo` from `team` where id="+id+";";
       ResultSet rst = stm.executeQuery(query);
       Team t = new Team();
       while (rst.next()) {
       t.setId(rst.getInt("id"));
       t.setName(rst.getString("name"));
       t.setLogo(rst.getString("logo"));
       t.setId_competition(rst.getInt("id_competition"));
       }
                   
        return  t;
    }
    @Override
    public Team GetTeamByName(String str) throws SQLException {
        Statement stm = cnx.createStatement();
        
       
       String query = "select `id`, `name`, `id_competition`, `logo` from `team` where name="+str+";";
       ResultSet rst = stm.executeQuery(query);
       Team t = new Team();
       while (rst.next()) {
       t.setId(rst.getInt("id"));
       t.setName(rst.getString("name"));
       t.setLogo(rst.getString("logo"));
       t.setId_competition(rst.getInt("id_competition"));
       }
                   
        return  t;
    }
    
    @Override
    public ArrayList<Team> AfficherTeam(int idc) throws SQLException {
        Statement stm = cnx.createStatement();
        
       
       String query = "select * from `team` where id_competition='" + idc + "'";
       ResultSet rst = stm.executeQuery(query);
          List<Team> teams = new ArrayList<>();
        while (rst.next()) {
            Team C = new Team();
            C.setId(rst.getInt("id"));
            C.setLogo(rst.getString("logo"));
            C.setName(rst.getString("name"));
            C.setId_competition(rst.getInt("id_competition"));
            

            teams.add(C);
            data.add(C);
        }

        return  (ArrayList<Team>) teams;
}
}
