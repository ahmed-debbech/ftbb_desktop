/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Classement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface IServiceClassement {
    public void AddClassement(Classement c)throws SQLException;
    public List<Classement> AfficherClassement(int idc, int idp)throws SQLException ;
    public List<Classement> AfficherClassement()throws SQLException ;
}
