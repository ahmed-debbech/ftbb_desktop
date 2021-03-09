/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entites.Galerie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author narug
 */
public interface IServicesGalerie {
    
    public List<Galerie> ShowPhoto() throws SQLException;
    public void AddPhoto(Galerie g);
    public void ModifyPhoto(Galerie g);
    public void DeletePhoto(Galerie g);
}
