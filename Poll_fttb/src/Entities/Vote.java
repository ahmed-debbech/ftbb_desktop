/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import utils.Utilities;

/**
 *
 * @author sbs
 */
public class Vote {
    
   private int vote_id;
   private int vote_nbr ;
   private int option_id;
   private int client_id;
   
   
    public Vote() {
        this.option_id = Utilities.generatedId("options","option_id");
    }

    public Vote(int vote_id, int vote_nbr, int option_id) {
        this.vote_id = vote_id;
        this.vote_nbr = vote_nbr;
        this.option_id = option_id;
    }

    public int getVote_id() {
        return vote_id;
    }

    public int getVote_nbr() {
        return vote_nbr;
    }

    public int getOption_id() {
        return option_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }

    public void setVote_nbr(int vote_nbr) {
        this.vote_nbr = vote_nbr;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }
    


    
   
    
}
