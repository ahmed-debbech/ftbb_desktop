/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Competition;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IServiceCompetition  {
  public void AddCompetition (Competition c) throws SQLException;
  public void UpdateCompetition (Competition c) throws SQLException ;
  public List<Competition> AfficherCompetition()throws SQLException ;
      
}
