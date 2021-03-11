/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import Utils.Utilities;

/**
 *
 * @author PC
 */
public class Product {
    private int ref_product;
    private String category;
    private int stock;
    private String name;
    private int price;
    private String details;
    private int id_admin;
    private Date add_date;
    private String photo;

    public Product() {
        this.ref_product = Utilities.generatedId("Product", "ref_product");; //function of random
    }

    public int getRef_product() {
        return ref_product;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDetails() {
        return details;
    }

    public int getId_admin() {
        return id_admin;
    }

    public Date getAdd_date() {
        return add_date;
    }
    public String getPhoto() {
        return photo;
    }

    public void setRef_product(int ref_product) {
        this.ref_product = ref_product;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public void setAdd_date(Date add_date) {
        this.add_date = add_date;
    }
    
     public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    

    @Override
    public String toString() {
        return "Product{" + "ref_product=" + ref_product + ", category=" + category + ", stock=" + stock + ", name=" + name + ", price=" + price + ", details=" + details + ", id_admin=" + id_admin + ", add_date=" + add_date + ", photo=" + photo +"}\n";
    }
    
    
}
