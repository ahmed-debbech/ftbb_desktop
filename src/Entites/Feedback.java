/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author narug
 */
public class Feedback {
    private int feedback_id;
    private int client_id;
    private String feedback_date;
    private String email;
    private String topic;
    private String text;
    private String type;
    
    
    public Feedback(){
    
    }

    /**
     * @return the feedback_id
     */
    public int getFeedback_id() {
        return feedback_id;
    }

    /**
     * @param feedback_id the feedback_id to set
     */
    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    /**
     * @return the client_id
     */
    public int getClient_id() {
        return client_id;
    }

    /**
     * @param client_id the client_id to set
     */
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    /**
     * @return the feedback_date
     */
    public String getFeedback_date() {
        return feedback_date;
    }

    /**
     * @param feedback_date the feedback_date to set
     */
    public void setFeedback_date(String feedback_date) {
        this.feedback_date = feedback_date;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    
}
