/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compit.src.Entites;

/**
 *
 * @author Lenovo
 */
public class Classement {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_phase() {
        return id_phase;
    }

    public void setId_phase(int id_phase) {
        this.id_phase = id_phase;
    }

    public int getId_competition() {
        return id_competition;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
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

    public int getNbr_pts() {
        return nbr_pts;
    }

    public void setNbr_pts(int nbr_pts) {
        this.nbr_pts = nbr_pts;
    }

    public Classement() {
    }
    private int id,id_phase,id_competition,id_team,nbr_pts_P,nbr_pts_G,nbr_pts_D,pts_diff,nbr_pts ;
}
