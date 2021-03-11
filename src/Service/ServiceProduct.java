/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// service el couche hedhy naamlouha bech nahkiw maa el base de donnée

package Service;

import Entities.Product;
import IService.IServiceProduct;
import Utils.SqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author PC
 */
public class ServiceProduct implements IServiceProduct{

    Connection cnx;

    public ServiceProduct() {
        cnx=SqlConnection.getInstance().getConnection();
    }
    
    @Override
    public void AddProduct(Product p) {
        try {
            Statement stm=cnx.createStatement();
            String query="INSERT INTO `product`(`ref_product`, `category`, `stock`, `name`, `price`, `details`, `id_admin`, `add_date`, `photo`) VALUES ("+p.getRef_product()+", '"+p.getCategory()+"' , "+p.getStock()+" , '"+p.getName()+"' , "+p.getPrice()+" , '"+p.getDetails()+"' , "+p.getId_admin()+" ,sysdate(),'"+p.getPhoto()+"');";
            stm.executeUpdate(query);
        } 
        catch (SQLException ex) 
         {
            Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
         }            
        }

    @Override
    public List<Product> ShowProduct()
    {
        List<Product> products = new ArrayList<>();
        try{
             Statement stm = cnx.createStatement();
             String query="select * from `product`; ";
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

    public void deleteProduct(Product p) {
        try {
            Statement stm = cnx.createStatement();
            String query = "DELETE FROM `product` WHERE ref_product="+p.getRef_product()+"";
            stm.executeUpdate(query);
           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateProduct(Product p){
        try {
            Statement stm = cnx.createStatement();
            String query = "UPDATE product SET category='"+p.getCategory()+"',stock="+p.getStock()+",name='"+p.getName()+"',price="+p.getPrice()+",details='"+p.getDetails()+"',id_admin="+p.getId_admin()+",photo='"+p.getPhoto()+"' WHERE ref_product="+p.getRef_product()+";";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("COULD NOT UPDATE");
        }
        
    }
    
    



}
