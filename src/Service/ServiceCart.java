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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import user.src.Utils.Utilities;

/**
 *
 * @author PC
 */
public class ServiceCart implements IServiceCart{
Connection cnx;

    public ServiceCart() {
        cnx=SqlConnection.getInstance().getConnection();
    }
    
    private boolean checkProdAdded(int client, int product){
        try {
            ResultSet rst;
            Statement stm = cnx.createStatement();
            String query = "";
            query = "select * from cart where id_client="+client+" and ref_product="+product+";";
            rst = stm.executeQuery(query);         
            if(rst.next()){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Could not generate an id due to the loss of connection.");
        }
        return false;
    }
    @Override
    public void addToCart(int client, Product p) {
        if(checkProdAdded(client, p.getRef_product()) == false){
            System.out.println("not there");
            try {
                Statement stm=cnx.createStatement();
                String query="INSERT INTO `cart`(`cart_id`, `id_client`, `num_products`, `total_price`, `ref_product`, `addition_id`) VALUES ("+client+", "+client+", "+1+", "+p.getPrice()+", "+p.getRef_product()+", " +Utilities.generatedId("cart", "addition_id")+");";

                stm.executeUpdate(query);
            } 
            catch (SQLException ex) 
             {
                 Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
             }  
        }else{
            System.out.println("dont add tio cart");
        }
    }

    @Override
    public List<Product> getCartProducts(int client) {
        List<Product> products = new ArrayList<Product>();
        try{
             Statement stm = cnx.createStatement();
            // String query="select * from `cart` where id_client="+client+"; ";
            String query = "SELECT cart.*, product.* FROM `cart` inner join product on cart.ref_product=product.ref_product where cart.id_client="+client+";";
             ResultSet rst = stm.executeQuery(query);

             while (rst.next())
             {
                 System.out.println(rst.getInt("ref_product"));
                 Product p = new Product();
                 p.setRef_product(rst.getInt("ref_product"));
                 p.setCategory(rst.getString("category"));
                 p.setStock(rst.getInt("stock"));
                 p.setName(rst.getString("name"));
                 p.setPrice(rst.getInt("price"));
                 p.setDetails(rst.getString("details"));
                 p.setId_admin(rst.getInt("id_admin"));
                 p.setAdd_date(rst.getDate("add_date"));
                 p.setPhoto(rst.getString("photo"));
                 

                 products.add(p);
             }
        }catch(SQLException s){
            System.out.println("not showed");
        }
        return products;
    }
    public void removeAll(int client){
        try {
            Statement stm = cnx.createStatement();
            String query = "DELETE FROM `cart` WHERE id_client="+client+"";
            stm.executeUpdate(query);
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
