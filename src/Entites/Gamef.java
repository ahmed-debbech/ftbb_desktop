/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class Gamef {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore_home() {
        return score_home;
    }

    public void setScore_home(int score_home) {
        this.score_home = score_home;
    }

    public int getScore_away() {
        return score_away;
    }

    public void setScore_away(int score_away) {
        this.score_away = score_away;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTeam_home() {
        return team_home;
    }

    public void setTeam_home(String team_home) {
        this.team_home = team_home;
    }

    public String getTeam_away() {
        return team_away;
    }

    public void setTeam_away(String team_away) {
        this.team_away = team_away;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getStatistique() {
        return statistique;
    }

    public void setStatistique(String statistique) {
        this.statistique = statistique;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    
    public String toString(int str) {
        return "str";
    }

    

    public Gamef() {
    }
    private int id,score_home,score_away,status;
    private String team_home,team_away,salle,statistique;
    private Date time ;
    
}
