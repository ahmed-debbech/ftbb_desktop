/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Galerie;
import IServices.IServicesGalerie;
import Utils.SqlConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author narug
 */
public class ServiceGalerie implements IServicesGalerie {
    Connection cnx;
    
   
public ServiceGalerie(){

    cnx=SqlConnexion.getInstance().getConnection();
}    
    @Override
    public void AddPhoto(Galerie g) {
        try {
            Statement stm=cnx.createStatement();
            String query ="INSERT INTO `galerie`(`galerie_id`, `admin_id`, `photo_url`, `photo_title`, `description`) VALUES ("+g.getGalerie_id()+","+g.getAdmin_id()+",'"+g.getPhoto_url()+"','"+g.getPhoto_title()+"','"+g.getDescription()+"');";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

  

    @Override
    public List<Galerie> ShowPhoto() throws SQLException {
        Statement stm=cnx.createStatement();
        String query="SELECT * FROM galerie";
        ResultSet rst=stm.executeQuery(query);
        List<Galerie>Galeries=new ArrayList();
        while(rst.next())
        {
            Galerie g= new Galerie();
            g.setGalerie_id(rst.getInt("galerie_id"));
            g.setAdmin_id(rst.getInt("admin_id"));
            g.setPhoto_url(rst.getString("photo_url"));
            g.setPhoto_title(rst.getString("photo_title"));
            g.setDescription(rst.getString("description"));
            Galeries.add(g);
        
        }
        return Galeries;
    }

    @Override
    public void ModifyPhoto(Galerie g) {
        try {
            Statement stm=cnx.createStatement();
            String query ="UPDATE galerie SET photo_title='"+g.getPhoto_title()+"', description='"+g.getDescription()+"' WHERE galerie_id="+g.getGalerie_id()+";";
            
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void DeletePhoto(Galerie g) {
        try {
            Statement stm=cnx.createStatement();
            String query ="DELETE FROM galerie where galerie_id ="+g.getGalerie_id()+";";
            
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGalerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
