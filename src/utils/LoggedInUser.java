/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import user.src.Entities.Client;

/**
 *
 * @author Ahmed
 */
public class LoggedInUser {

    /**
     * @return the model
     */
    public Client getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Client model) {
        this.model = model;
    }
    private Client model;
    
    private static LoggedInUser instance;
    
    private LoggedInUser(){
    }

    public static LoggedInUser getInstance(){
        if(instance == null){
            instance = new LoggedInUser();
        }
        return instance;
    }
}
