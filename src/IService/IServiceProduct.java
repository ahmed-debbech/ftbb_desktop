/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// hiyya interface tetaplika aala les services lkoll 

package IService;

import Entities.Product;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IServiceProduct {
    public void AddProduct(Product p);
    public List<Product> ShowProduct();
}
