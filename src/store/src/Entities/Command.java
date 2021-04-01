/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.src.Entities;

import java.sql.Date;
import javafx.scene.control.Button;

/**
 *
 * @author PC
 */
public class Command {
    private int command_id;
    private int id_client;
    private Date date_command;
    private int status;
    private int total_price;
    private Button button;
    
    public Command(){
        
    }

    public Command(int command_id, int id_client, Date date_command, int status, int total_price, Button button) {
        this.command_id = command_id;
        this.id_client = id_client;
        this.date_command = date_command;
        this.status = status;
        this.total_price = total_price;
        this.button = new Button("action");
    }
    
    

    /**
     * @return the command_id
     */
    public int getCommand_id() {
        return command_id;
    }

    /**
     * @param command_id the command_id to set
     */
    public void setCommand_id(int command_id) {
        this.command_id = command_id;
    }

    /**
     * @return the id_client
     */
    public int getId_client() {
        return id_client;
    }

    /**
     * @param id_client the id_client to set
     */
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    /**
     * @return the date_command
     */
    public Date getDate_command() {
        return date_command;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

   
    
    /**
     * @param date_command the date_command to set
     */
    public void setDate_command(Date date_command) {
        this.date_command = date_command;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the price
     */
    public int getTotal_price() {
        return total_price;
    }

    /**
     * @param price the price to set
     */
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
        
}
