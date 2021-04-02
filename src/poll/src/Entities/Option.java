/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.Entities;

import poll.src.utils.Utilities;

/**
 *
 * @author sbs
 */
public class Option  {
    
   private int option_id;
   private String description;
   private int poll_id;
   
    public Option() {
        this.option_id = Utilities.generatedId("options","option_id");
    }

   
    public Option(int option_id, String description) {
        this.option_id = Utilities.generatedId("options","option_id");
        this.description = description;
       
    }

    public int getOption_id() {
        return option_id;
    }

    public String getDescription() {
        return description;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoll_id() {
        return poll_id;
    }

    public void setPoll_id(int poll_id) {
        this.poll_id = poll_id;
    }

//    @Override
//    public String toString() {
//        return "\n Option "+option_id+" : "+ description + "\n";
//    }
//    
    


    
   
   
}
