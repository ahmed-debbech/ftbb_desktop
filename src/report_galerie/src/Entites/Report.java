/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package report_galerie.src.Entites;

import news.src.utils.Utilities;

/**
 *
 * @author narug
 */
public class Report {
     private int report_id;
    private int client_id;
    private int command_id;
    private String report_date;
    private String email;
    private String description;
    
    
    public Report(int x,String y){
        this.report_id=x;
        this.description=y;
    }
    
    public Report(){
       this.report_id=Utilities.generatedId("report", "report_id");
       this.client_id=999;
    
    }

    /**
     * @return the report_id
     */
    public int getReport_id() {
        return report_id;
    }

    /**
     * @param report_id the report_id to set
     */
    public void setReport_id(int report_id) {
        this.report_id = report_id;
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
     * @return the command_id
     */
    public int getCommand_id() {
        return command_id;
    }

    /**
     * @param command_id the command_id to set
     */
    public void setCommand_id(int command_id) {
        this.command_id = command_id;
    }

    /**
     * @return the report_date
     */
    public String getReport_date() {
        return report_date;
    }

    /**
     * @param report_date the report_date to set
     */
    public void setReport_date(String report_date) {
        this.report_date = report_date;
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
    
    
    
    
}
