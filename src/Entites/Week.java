/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Lenovo
 */
public class Week {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public Week() {
    }
    private int id ;
    private String Name_week;

    public String getName_week() {
        return Name_week;
    }

    public void setName_week(String Name_week) {
        this.Name_week = Name_week;
    }

    @Override
    public String toString() {
        return Name_week ;
    }

    

    
    
    
}
