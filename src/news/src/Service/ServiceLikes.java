/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.src.Service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import news.src.Enitity.Like;
import news.src.IService.IServiceLikes;
import news.src.utils.Utilities;
import user.src.Utils.SqlConnection;


/**
 *
 * @author Ahmed
 */
public class ServiceLikes implements IServiceLikes {
    Connection cnx;
    
    public ServiceLikes(){
        cnx= SqlConnection.getInstance().getConnection();
    }

    @Override
    public void add(Like k) {
        if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return;
        }
        try {
            Statement stm = cnx.createStatement();
            String query = "insert into likes (id_like, id_article,"
                    + "id_comment, id_client) values ("
                    +String.valueOf(k.getId_like())+","
                    +String.valueOf(k.getId_article())+","
                    +String.valueOf(k.getId_comment())+","
                    +String.valueOf(k.getId_client())+");";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("could not add new like");
        }
    }

    @Override
    public void remove(Like k) {
        if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return;
        }
        try{
            Statement stm = cnx.createStatement();
            String query = "delete from likes where id_client="+k.getId_client()+" and id_article="+k.getId_article()+";";
            stm.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("Could not delete like");
        }
    }

    @Override
    public boolean isExisted(Like k) {
        if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return false;
        }
        try {
            ResultSet rst;
            Statement stm = cnx.createStatement();
            String query = "";
            if(k.getId_comment() == -1){
                query = "select * from likes where id_client="+k.getId_client()+" and id_article="+k.getId_article()+";";
            }else{
                 query = "select * from likes where id_client="+k.getId_client()+" and id_comment="+k.getId_comment()+";";
            }
            rst = stm.executeQuery(query);         
            if(rst.next()){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Could not generate an id due to the loss of connection.");
        }
        return false;
    }

    @Override
    public boolean getLike(int art, int cmt, int cl) {
        if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return false;
        }
        try {
            ResultSet rst;
            Statement stm = cnx.createStatement();
            String query = "";
            if(cmt == -1){
                query = "select * from likes where id_client="+cl+" and id_article="+art+";";
            }else{
                 query = "select * from likes where id_client="+cl+" and id_comment="+cmt+";";
            }
            rst = stm.executeQuery(query);       
            if(rst.next()){
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Could not return the like.");
        }
        return false;
    }

    @Override
    public int countLikes(int art, int cmt) {
        if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return -1;
        }
        try {
            ResultSet rst;
            Statement stm = cnx.createStatement();
            String query = "";
            if(cmt == -1){
                query = "select count(*) as co from likes where id_article="+art+";";
            }else{
                 query = "select count(*) as co from likes where id_comment="+cmt+";";
            }
            rst = stm.executeQuery(query); 
            rst.last();
            return  rst.getInt("co");
        } catch (SQLException ex) {
            System.out.println("Could not return the like count.");
        }
        return -1;
    }
    public String getClientName(String id){
        if(cnx == null){
            Utilities.alert("ERROR", "could not connect to database.");
            return null;
        }
        try{
            Statement stm = this.cnx.createStatement();
            String query = "select name from client where id="+id+";";
            ResultSet rst = stm.executeQuery(query);
            if(rst.next()){
                 String a = null;
                 a = rst.getString("name");
                return a;
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("no name");
        }
        return null;
    }
    public String getClientEmail(String id){
        if(cnx == null){
            Utilities.alert("ERROR", "could not connect to database.");
            return null;
        }
        try{
            Statement stm = this.cnx.createStatement();
            String query = "select email from client where id="+id+";";
            ResultSet rst = stm.executeQuery(query);
            if(rst.next()){
                 String a = null;
                 a = rst.getString("email");
                return a;
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("no email");
        }
        return null;
    }
}
