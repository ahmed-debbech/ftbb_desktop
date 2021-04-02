/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.Service;


import compit.src.Entites.Classement;
import compit.src.Entites.Classementf;
import compit.src.Services.IServiceClassement;
import compit.src.utils.Maconnexion;
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

/**
 *
 * @author Lenovo
 */
public class ServiceClassement implements IServiceClassement {
    Connection cnx;
    private ObservableList<Classement> data = FXCollections.observableArrayList();
    public ObservableList<Classementf> data1 = FXCollections.observableArrayList();  
    
    public ObservableList<Classement> getData() {
        return data;
    }
    

    public ObservableList<Classementf> getData1() {
        return data1;
    }
    
    public ServiceClassement() {
        cnx=Maconnexion.getInstance().getConnection();
        
    }
    
    @Override
    public void AddClassement(Classement c) throws SQLException {
        
        try {
            Statement stm = cnx.createStatement();
            String query = "insert into classement (`id_competition`, `id_phase`,`id_team`) values ('" + c.getId_competition()+"','" + c.getId_phase()+"','" + c.getId_team() +"')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public List<Classement> AfficherClassement(int idc, int idp) throws SQLException {
        Statement stm = cnx.createStatement();
        
       
       String query = "select * from `classement` where id_phase='" + idp + "'and id_competition='" + idc + "'";
       ResultSet rst = stm.executeQuery(query);
       List <Classement>classements = new ArrayList<> ();
            while (rst.next()){
                Classement c = new Classement();
                c.setId(rst.getInt("id"));
                c.setId_competition(rst.getInt("id_competition"));
                c.setId_phase(rst.getInt("id_phase"));
                c.setId_team(rst.getInt("id_team"));
                c.setNbr_pts(rst.getInt("nbr_pts"));
                c.setNbr_pts_D(rst.getInt("nbr_pts_D"));
                c.setNbr_pts_G(rst.getInt("nbr_pts_G"));
                c.setNbr_pts_P(rst.getInt("nbr_pts_P"));
                c.setPts_diff(rst.getInt("pts_diff"));
                

                classements.add(c);
                data.add(c);
            }
       
        return (ArrayList<Classement>) classements;
    
    
   
}
    @Override
    public List<Classement> AfficherClassement() throws SQLException {
        Statement stm = cnx.createStatement();
        
       
       String query = "select * from `classement`";
       ResultSet rst = stm.executeQuery(query);
       List <Classement>classements = new ArrayList<> ();
            while (rst.next()){
                Classement c = new Classement();
                c.setId(rst.getInt("id"));
                c.setId_competition(rst.getInt("id_competition"));
                c.setId_phase(rst.getInt("id_phase"));
                c.setId_team(rst.getInt("id_team"));
                c.setNbr_pts(rst.getInt("nbr_pts"));
                c.setNbr_pts_D(rst.getInt("nbr_pts_D"));
                c.setNbr_pts_G(rst.getInt("nbr_pts_G"));
                c.setNbr_pts_P(rst.getInt("nbr_pts_P"));
                c.setPts_diff(rst.getInt("pts_diff"));
                

                classements.add(c);
                data.add(c);
            }
       
        return (ArrayList<Classement>) classements;
    
    
   
}
    
    private static Classementf com = new Classementf();

    public static Classementf getCom() {
        return com;
    }

    public static void setCom(Classementf com) {
        ServiceClassement.com = com;
    }
    
    
    @Override
    public void UpdateClassement(Classement c) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            
            query = "UPDATE `classement` SET `nbr_pts_P`='" + c.getNbr_pts_P()+"',`nbr_pts_G`='"+c.getNbr_pts_G()+"',`nbr_pts_D`='"+c.getNbr_pts_D()+"',`nbr_pts`='"+c.getNbr_pts()+  "' WHERE `id`= " + c.getId();
            stm.executeUpdate(query);
            System.out.println("valide");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
