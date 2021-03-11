/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.Utilities;

/**
 *
 * @author PC
 */
public class CommandProduct {
    private int id_cp;
    private int ref_product;
    private int id_client;
    private int command_id;

    public CommandProduct(int ref, int cl, int cmd){
        this.id_cp = Utilities.generatedId("command_product", "id_cp");
        this.ref_product = ref;
        this.id_client = cl;
        this.command_id = cmd;
    }
    /**
     * @return the id_cp
     */
    public int getId_cp() {
        return id_cp;
    }

    /**
     * @param id_cp the id_cp to set
     */
    public void setId_cp(int id_cp) {
        this.id_cp = id_cp;
    }

    /**
     * @return the ref_product
     */
    public int getRef_product() {
        return ref_product;
    }

    /**
     * @param ref_product the ref_product to set
     */
    public void setRef_product(int ref_product) {
        this.ref_product = ref_product;
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
}
