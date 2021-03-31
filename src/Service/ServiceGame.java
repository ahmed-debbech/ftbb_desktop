/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entites.Game;
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

    public ObservableList<Game> getData() {
        return data;
    }

    public ServiceGame() {
        cnx=Maconnexion.getInstance().getConnection();
        
    }
    @Override
    public void AddGame(Game g) {
//        try {
//            Statement stm = cnx.createStatement();
//            
//        
//        String query="INSERT INTO `game`(`id`, `id_competition`, `id_phase`, `id_week`, `id_team_home`, `id_team_away`, `score_home`, `score_away`, `id_statistique`, `status`, `salle`, `time`) VALUES ("")";
//        stm.executeUpdate(query);
//        } catch (SQLException ex) {
//            Logger.getLogger(ServiceCompetition.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
                
                G.setSalle(rst.getString("salle"));

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
                
                G.setSalle(rst.getString("salle"));

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
                
                G.setSalle(rst.getString("salle"));

                games.add(G);
                data.add(G);
            }
       
        return (ArrayList<Game>) games;
    
    
   
}
}
