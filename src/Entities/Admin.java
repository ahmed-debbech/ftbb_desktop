/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import Utils.Utilities;

/**
 *
 * @author Yassine
 */
public class Admin {
    
    private int id,number,password_id,role;
    private String name,surname,email,photo_url,sex;
    private Date birthday ;

    public Admin() {
        this.id = Utilities.generatedId("client","id");
    }

    public Admin(String name, String surname, int password_id,Date birthday ,int number,  String photo_url, int role, String email, String sex) {
       this.id = Utilities.generatedId("admin","id");
        this.number = number;
        this.password_id = password_id;
        this.photo_url = photo_url;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.sex = sex;
        this.birthday=birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPassword_id(int password_id) {
        this.password_id = password_id;
    }

    public int getPassword_id() {
        return password_id;
    }

  

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Admin{" +" id="+id+", name=" + name + ", "
                + "surname=" + surname + ", email=" + email +",\n birthday="+birthday + ", number=" + number + ", password_id=" + password_id + ""
                + ", photo_url=" + photo_url + ", role=" + role +  ", sex=" + sex + "}\n";
    }

    
            
}
