/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import Utils.Utilities;

/**
 *
 * @author narug
 */
public class Galerie {
    private int galerie_id;
    private int admin_id;
    private String photo_title;
    private String photo_url;
    private String description;
    
    
    public Galerie(int x,String y,String z){
        this.galerie_id=x;
        this.photo_title=y;
        this.description=z;
    }
    
   
    public Galerie(){
     this.galerie_id=Utilities.generatedId("galerie", "galerie_id");
     this.admin_id=444;
    }

    /**
     * @return the galerie_id
     */
    public int getGalerie_id() {
        return galerie_id;
    }

    /**
     * @param galerie_id the galerie_id to set
     */
    public void setGalerie_id(int galerie_id) {
        this.galerie_id = galerie_id;
    }

    /**
     * @return the photo_id
     */
    public int getAdmin_id() {
        return admin_id;
    }

    /**
     * @param admin_id the photo_id to set
     */
    public void setAdmin_id(int photo_id) {
        this.admin_id = admin_id;
    }

    /**
     * @return the photo_title
     */
    public String getPhoto_title() {
        return photo_title;
    }

    /**
     * @param photo_title the photo_title to set
     */
    public void setPhoto_title(String photo_title) {
        this.photo_title = photo_title;
    }

    /**
     * @return the photo_url
     */
    public String getPhoto_url() {
        return photo_url;
    }

    /**
     * @param photo_url the photo_url to set
     */
    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public String toString(){
        return "Galerie{"+"galerie_id"+galerie_id+",admin_id"+admin_id+"pohto_title"+photo_title+",photo_url"+photo_url+"}\n";  
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
