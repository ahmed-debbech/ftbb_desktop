/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.Services;

import compit.src.Entites.Week;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public interface IServiceWeek {
    public void AddWeek (Week w);
  public List<Week> AfficherWeek()throws SQLException ;
}
