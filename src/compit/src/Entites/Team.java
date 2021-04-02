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
public class Team {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Team() {
    }
    private int id,id_competition ;
    private String name,logo;

    @Override
    public String toString() {
        return name;
    }
    
}
