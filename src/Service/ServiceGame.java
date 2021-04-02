/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entites.Game;
import Entites.Gamef;
import Services.IServiceGame;
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
public class ServiceGame implements IServiceGame {
    Connection cnx;
    private ObservableList<Game> data = FXCollections.observableArrayList();
    public ObservableList<Gamef> data1 = FXCollections.observableArrayList();  
    
    public ObservableList<Game> getData() {
        return data;
    }
    

    public ObservableList<Gamef> getData1() {
        return data1;
    }

    public ServiceGame() {
        cnx=Maconnexion.getInstance().getConnection();
        
    }
    @Override
    public void AddGame(Game g) throws SQLException {
                
      
        
        try {
            Statement stm = cnx.createStatement();
            String query = "insert into game (`id_competition`, `id_phase`, `id_week`, `id_team_home`, `id_team_away`, `salle`,`score_home`,`score_away`,`status`, `time`) values ('" + g.getId_competition()+"','" + g.getId_phase()+"','" + g.getId_week() +"','" + g.getId_team_home() +"','" + g.getId_team_away() +"','" + g.getSalle() +"',0,0,0, '"+g.getTime()+"')";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList <Game> AfficherGame()throws SQLException {
        
            Statement stm = cnx.createStatement();
        
       String query ="select * from `game` ";
       ResultSet rst = stm.executeQuery(query);
       ArrayList <Game>games = new ArrayList<> ();
            while (rst.next()){
                Game G = new Game ();
                G.setId(rst.getInt("id"));
                G.setId_team_home(rst.getInt("id_team_home"));
                G.setId_team_away(rst.getInt("id_team_away"));
                G.setScore_home(rst.getInt("score_home"));
                G.setScore_away(rst.getInt("score_away"));
                G.setTime(rst.getDate("time"));
                G.setSalle(rst.getString("salle"));
                G.setStatus(rst.getInt("status"));
                games.add(G);
                data.add(G);
            }
       
        return  games;
    }

    @Override
    public List<Game> GetGameByIdCompetition(int id) throws SQLException {
        
        Statement stm = cnx.createStatement();
        
       
       String query = "select * from `game` where id_competition="+id+";";
       ResultSet rst = stm.executeQuery(query);
       List <Game>games = new ArrayList<> ();
            while (rst.next()){
                Game G = new Game ();
                G.setId(rst.getInt("id"));
                G.setId_team_home(rst.getInt("id_team_home"));
                G.setId_team_away(rst.getInt("id_team_away"));
                G.setScore_home(rst.getInt("score_home"));
                G.setScore_away(rst.getInt("score_away"));
                G.setTime(rst.getDate("time"));
                G.setSalle(rst.getString("salle"));
G.setStatus(rst.getInt("status"));
                games.add(G);
                data.add(G);
            }
       
        return (ArrayList<Game>) games;
        
        
        
        
    }

    @Override
    public List<Game> GetGameByIdPhase(int id) throws SQLException {
        
        Statement stm = cnx.createStatement();
        
       
       String query = "select * from `game` where id_phase="+id+";";
       ResultSet rst = stm.executeQuery(query);
       List <Game>games = new ArrayList<> ();
            while (rst.next()){
                Game G = new Game ();
                G.setId(rst.getInt("id"));
                G.setId_team_home(rst.getInt("id_team_home"));
                G.setId_team_away(rst.getInt("id_team_away"));
                G.setScore_home(rst.getInt("score_home"));
                G.setScore_away(rst.getInt("score_away"));
                G.setStatus(rst.getInt("status"));
                G.setSalle(rst.getString("salle"));

                games.add(G);
                data.add(G);
            }
       
        return (ArrayList<Game>) games;
    }

    @Override
    public List<Game> GetGameByIdWeek(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        
       
       String query = "select * from `game` where id_week="+id+";";
       ResultSet rst = stm.executeQuery(query);
       List <Game>games = new ArrayList<> ();
            while (rst.next()){
                Game G = new Game ();
                G.setId(rst.getInt("id"));
                G.setId_team_home(rst.getInt("id_team_home"));
                G.setId_team_away(rst.getInt("id_team_away"));
                G.setScore_home(rst.getInt("score_home"));
                G.setScore_away(rst.getInt("score_away"));
                G.setStatus(rst.getInt("status"));
                G.setSalle(rst.getString("salle"));

                games.add(G);
                data.add(G);
            }
       
        return (ArrayList<Game>) games;
    }

    @Override
    public List<Game> AfficherGame(int idc, int idp, int idw) throws SQLException {
        Statement stm = cnx.createStatement();
        
       
       String query = "select * from `game` where id_week='" + idw + "'and id_phase='" + idp + "'and id_competition='" + idc + "'";
       ResultSet rst = stm.executeQuery(query);
       List <Game>games = new ArrayList<> ();
            while (rst.next()){
                Game G = new Game ();
                G.setId(rst.getInt("id"));
                G.setId_team_home(rst.getInt("id_team_home"));
                G.setId_team_away(rst.getInt("id_team_away"));
                G.setScore_home(rst.getInt("score_home"));
                G.setScore_away(rst.getInt("score_away"));
                G.setStatus(rst.getInt("status"));
                G.setSalle(rst.getString("salle"));
                G.setTime(rst.getDate("time"));
                games.add(G);
                data.add(G);
            }
       
        return (ArrayList<Game>) games;
    
    
   
}
    
    
    private static Gamef com = new Gamef();

    public static Gamef getCom() {
        return com;
    }

    public static void setCom(Gamef com) {
        ServiceGame.com = com;
    }
    private static Game comf = new Game();

    public static Game getComf() {
        return comf;
    }

    public static void setComf(Game com) {
        ServiceGame.comf = com;
    }
    
    
    
    public void UpdateScore(Gamef g) throws SQLException {

        try {

            Statement stm = cnx.createStatement();
            String query;
            query = "UPDATE `game` SET `status` = 1, `score_home`='" + g.getScore_home()+ "',`score_away`='" + g.getScore_away()+ "' WHERE `id`= " + g.getId();
            stm.executeUpdate(query);
            System.out.println("valide");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
