/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Product;
import java.util.List;

/**
 *
 * @author PC
 */
public interface IServiceCart {
     public void addToCart(int client, Product p );
     public List<Product> getCartProducts(int client);
}
