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
public class Game {

    public static String NOT_FINISHED = "not finished";
    public static String FINISHED = "finished";
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_competition() {
        return id_competition;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    public int getId_phase() {
        return id_phase;
    }

    public void setId_phase(int id_phase) {
        this.id_phase = id_phase;
    }

    public int getId_week() {
        return id_week;
    }

    public void setId_week(int id_week) {
        this.id_week = id_week;
    }

    public int getId_team_home() {
        return id_team_home;
    }

    public void setId_team_home(int id_team_home) {
        this.id_team_home = id_team_home;
    }

    public int getId_team_away() {
        return id_team_away;
    }

    public void setId_team_away(int id_team_away) {
        this.id_team_away = id_team_away;
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

    public int getId_statistique() {
        return id_statistique;
    }

    public void setId_statistique(int id_statistique) {
        this.id_statistique = id_statistique;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Game() {
    }
    private int id,id_competition,id_phase,id_week,id_team_home,id_team_away,score_home,score_away,id_statistique,status;
    private String salle ;
    private Date time ;
    
}
