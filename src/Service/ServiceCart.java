/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Product;
import IService.IServiceCart;
import Utils.SqlConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class ServiceCart implements IServiceCart{
Connection cnx;

    public ServiceCart() {
        cnx=SqlConnection.getInstance().getConnection();
    }
    
    @Override
    public void addToCart(int client, Product p) {
        try {
            Statement stm=cnx.createStatement();
            String query="INSERT INTO `cart`(`cart_id`, `id_client`, `num_products`, `total_price`, `ref_product`) VALUES ("+client+", "+client+", "+1+", "+p.getPrice()+", "+p.getRef_product()+");";
            stm.executeUpdate(query);
        } 
        catch (SQLException ex) 
         {
             Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
         }  
    }
    
}
