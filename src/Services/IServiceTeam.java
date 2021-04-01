/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Team;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Lenovo
 */
public interface IServiceTeam {
    public void AddTeam (Team c) throws SQLException;
  public void UpdateTeam (Team c) throws SQLException ;
  public ArrayList<Team> AfficherTeam()throws SQLException ;
  public ArrayList<Team> AfficherTeam(int idc)throws SQLException ;
  public Team GetTeamById (int id )throws SQLException ;
  public Team GetTeamByName (String str )throws SQLException ;
    
}
