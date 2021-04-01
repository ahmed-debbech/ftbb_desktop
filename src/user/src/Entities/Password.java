/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.Entities;

import java.sql.Date;
import java.time.LocalDate;
import user.src.Utils.Utilities;

/**
 *
 * @author Yassine
 */
public class Password {

    private int password_id;
    private String pwd;
    private Date last_change;
    private String previous_pwd;
    
 public Password(String pass) {
        this.password_id = Utilities.generatedId("password","password_id");
        this.last_change = Date.valueOf(LocalDate.now()) ;
        this.pwd=pass;
        this.previous_pwd = null;
         
    }
    public Password() {
        this.password_id = Utilities.generatedId("password","password_id");
        this.last_change = Date.valueOf(LocalDate.now()) ;
    }
    

    public Password(int password_id, String pwd, Date last_change, String previous_pwd) {
        this.password_id = Utilities.generatedId("password","password_id");
        this.pwd = pwd;
        this.last_change = Date.valueOf(LocalDate.now()) ;
        this.previous_pwd = null;
     
    }

    public int getPassword_id() {
        return password_id;
    }

    public void setPassword_id(int password_id) {
        this.password_id =password_id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getLast_change() {
        return last_change;
    }

    public void setLast_change(Date last_change) {
        this.last_change = last_change;
    }

    public String getPrevious_pwd() {
        return previous_pwd;
    }

    public void setPrevious_pwd(String previous_pwd) {
        this.previous_pwd = previous_pwd;
    }

  
}
