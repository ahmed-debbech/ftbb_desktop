/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitity;

import utils.Utilities;

/**
 *
 * @author Ahmed
 */
public class Like {
    private int id_like;
    private int id_article;
    private int id_comment;
    private int id_client;

    
    public Like(int like, int art, int cmt, int cl){
        this.id_like =like;
        this.id_article = art;
        this.id_comment = cmt;
        this.id_client = cl;
    }
    public Like(int art, int cmt, int cl){
        this.id_like = Utilities.generatedId("likes", "id_like");
        this.id_article = art;
        this.id_comment = cmt;
        this.id_client = cl;
    }
    /**
     * @return the id_like
     */
    public int getId_like() {
        return id_like;
    }

    /**
     * @param id_like the id_like to set
     */
    public void setId_like(int id_like) {
        this.id_like = id_like;
    }

    /**
     * @return the id_article
     */
    public int getId_article() {
        return id_article;
    }

    /**
     * @param id_article the id_article to set
     */
    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    /**
     * @return the id_comment
     */
    public int getId_comment() {
        return id_comment;
    }

    /**
     * @param id_comment the id_comment to set
     */
    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
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
    
}
