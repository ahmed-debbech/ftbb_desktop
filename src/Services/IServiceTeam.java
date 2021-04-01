/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Team;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IServiceTeam {
    public void AddTeam (Team c) throws SQLException;
  public void UpdateTeam (Team c) throws SQLException ;
  public List<Team> AfficherTeam()throws SQLException ;
  public List<Team> AfficherTeam(int idc)throws SQLException ;
  public Team GetTeamById (int id )throws SQLException ;
    
}
