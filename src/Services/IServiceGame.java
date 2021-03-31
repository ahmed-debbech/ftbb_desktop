/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Game;
import java.sql.SQLException;
import java.util.List;
/**

/**
 *
 * @author Lenovo
 */
public interface IServiceGame {
     public void AddGame(Game g)throws SQLException;
  public List<Game> AfficherGame()throws SQLException ;
  public List<Game> AfficherGame(int idc, int idp , int idw)throws SQLException ;
  public List<Game> GetGameByIdCompetition(int id )throws SQLException ;
  public List<Game> GetGameByIdPhase(int id )throws SQLException ;
  public List<Game> GetGameByIdWeek(int id )throws SQLException ;
}
