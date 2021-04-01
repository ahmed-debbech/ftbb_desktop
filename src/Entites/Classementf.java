/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Lenovo
 */

public class Classementf implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbr_pts_P() {
        return nbr_pts_P;
    }

    public void setNbr_pts_P(int nbr_pts_P) {
        this.nbr_pts_P = nbr_pts_P;
    }

    public int getNbr_pts_G() {
        return nbr_pts_G;
    }

    public void setNbr_pts_G(int nbr_pts_G) {
        this.nbr_pts_G = nbr_pts_G;
    }

    public int getNbr_pts_D() {
        return nbr_pts_D;
    }

    public void setNbr_pts_D(int nbr_pts_D) {
        this.nbr_pts_D = nbr_pts_D;
    }

    public int getPts_diff() {
        return pts_diff;
    }

    public void setPts_diff(int pts_diff) {
        this.pts_diff = pts_diff;
    }

    public String getName_team() {
        return name_team;
    }

    public void setName_team(String name_team) {
        this.name_team = name_team;
    }

    public String getLogo_team() {
        return logo_team;
    }

    public void setLogo_team(String logo_team) {
        this.logo_team = logo_team;
    }

    

    public Classementf() {
    }
    private int id,nbr_pts_P,nbr_pts_G,nbr_pts_D,pts_diff,nbr_pts ;
    private String name_team,logo_team;

    public int getNbr_pts() {
        return nbr_pts;
    }

    public void setNbr_pts(int nbr_pts) {
        this.nbr_pts = nbr_pts;
    }
    
    
}
