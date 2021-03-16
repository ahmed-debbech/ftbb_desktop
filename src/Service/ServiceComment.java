/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Enitity.Article;
import Enitity.Comment;
import IService.IServiceComment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.SqlConnection;
import utils.Utilities;

/**
 *
 * @author Ahmed
 */
public class ServiceComment implements IServiceComment{
      Connection cnx;
    
    public ServiceComment(){
        cnx= SqlConnection.getInstance().getConnection();
    }


    @Override
    public List<Comment> showComment(String id) {
        List<Comment> list = new ArrayList<>();
        try{
            Statement stm = this.cnx.createStatement();
            System.out.println("xxxx " + id);
            String query = "SELECT comment.*, client.name, client.surname FROM `comment` inner join client on comment.client_id=client.id where article_id="+id+";";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()){
                Comment a = new Comment();
                a.setId(rst.getInt("id"));
                a.setContent(rst.getString("content"));
                a.setClient_id(rst.getInt("client_id"));
                a.setClient_name(rst.getString("name") + rst.getString("surname"));
                a.setArticle_id(rst.getInt("article_id"));
                a.setDate(rst.getDate("date"));
                list.add(a);
            }
        }catch(SQLException ex){
            System.out.println("Could not show comments");
        }
        return list;
    }

    @Override
    public void delComment(Comment a) {
        try{
            Statement stm = cnx.createStatement();
            String query = "delete from comment where id="+a.getId()+";";
            stm.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("Could not delete comment");
        }
    }

    @Override
    public void addComment(Comment a) {
        if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return;
        }
        try {
            Statement stm = cnx.createStatement();
            String query = "insert into comment (id, content,"
                    + "client_id, article_id, date) values ("
                    +String.valueOf(a.getId())+",'"
                    +String.valueOf(a.getContent())+"',"
                    +a.getClient_id()+","
                    +a.getArticle_id()+","+" sysdate()"+");";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("could not add new comment");
        }
    }

    @Override
    public int countComments(String art_id) {
         if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return -1;
        }
        try {
            ResultSet rst;
            Statement stm = cnx.createStatement();
            String query = "select count(*) as co from comment where article_id="+art_id+";";
            rst = stm.executeQuery(query); 
            rst.last();
            return  rst.getInt("co");
        } catch (SQLException ex) {
            System.out.println("Could not return the comments count.");
        }
        return -1;
    }
    
}