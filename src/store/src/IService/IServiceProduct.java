/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// hiyya interface tetaplika aala les services lkoll 

package store.src.IService;

import java.util.List;
import store.src.Entities.Product;

/**
 *
 * @author PC
 */
public interface IServiceProduct {
    public void AddProduct(Product p);
    public List<Product> ShowProduct();
    public void addToCart(int id);
}
