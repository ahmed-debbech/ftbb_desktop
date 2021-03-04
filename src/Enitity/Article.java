/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enitity;

import java.sql.Date;
import utils.Utilities;

/**
 *
 * @author Ahmed
 */
public class Article {
    // category types
    public static int BREAKING_NEWS = 0;
    public static int HOT = 1;
    public static int ANNOUNCE = 2;
    public static int MISC = 3;
    
    
    private int article_id;
    private int admin_id;
    private String title;
    private String text;
    private String author;
    private Date date;
    private String photo_url;
    private String video_url;
    private int category;

    public Article(int id){
        this.article_id =id;
    }
    public Article(){
        this.article_id = Utilities.generatedId("article", "article_id");
        this.admin_id=234;
    }
    public Article(String title, String text, String author, String photo, String video, String category){
        this.article_id = Utilities.generatedId("article", "article_id");
        this.admin_id=234;
        this.title = title;
        this.text= text;
        this.author = author;
        this.photo_url = photo;
        this.video_url = video;
        this.category = toIntCatTypes(category);
    }
    
    public Article(int id, String title, String text, String author, String photo, String video, String category){
        this.article_id = id;
        this.admin_id=234;
        this.title = title;
        this.text= text;
        this.author = author;
        this.photo_url = photo;
        this.video_url = video;
        this.category = toIntCatTypes(category);
    }
    
    private int toIntCatTypes(String type){
        int ret = -1;
        switch(type){
            case "Breaking News": ret = 0; break;
            case "Hot": ret = 1; break;
            case "Announce": ret = 2; break;
            case "Misc": ret = 3; break;
        }
        return ret;
    }
    public static String toStringCatTypes(int type){
        String ret = "";
        switch(type){
            case 0: ret = "Breaking News"; break;
            case 1: ret = "Hot"; break;
            case 2: ret = "Announce"; break;
            case 3: ret = "Misc"; break;
        }
        return ret;
    }
    /**
     * @return the article_id
     */
    public int getArticle_id() {
        return article_id;
    }

    /**
     * @param article_id the article_id to set
     */
    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    /**
     * @return the admin_id
     */
    public int getAdmin_id() {
        return admin_id;
    }

    /**
     * @param admin_id the admin_id to set
     */
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
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

    /**
     * @return the video_url
     */
    public String getVideo_url() {
        return video_url;
    }

    /**
     * @param video_url the video_url to set
     */
    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    /**
     * @return the category
     */
    public int getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(int category) {
        this.category = category;
    }
  
}
