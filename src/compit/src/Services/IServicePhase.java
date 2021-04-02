/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.Services;
import compit.src.Entites.Phase;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public interface IServicePhase {
    public void AddPhase (Phase p);
  public List<Phase> AfficherPhase()throws SQLException ;
}
