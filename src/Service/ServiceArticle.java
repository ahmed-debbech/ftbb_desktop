/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Enitity.Article;
import Enitity.Comment;
import IService.IServiceArticle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.SqlConnection;
import utils.Utilities;

/**
 *
 * @author Ahmed
 */
public class ServiceArticle implements IServiceArticle {

    Connection cnx;
    
    public ServiceArticle(){
        cnx= SqlConnection.getInstance().getConnection();
    }
    
    @Override
    public void addArticle(Article a) {
         if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return;
        }
        try {
            Statement stm = cnx.createStatement();
            String query = "insert into article (article_id, admin_id,"
                    + "title, text, author, date, photo_url, category) values ("
                    +String.valueOf(a.getArticle_id())+","
                    +String.valueOf(a.getAdmin_id())+",'"
                    +a.getTitle()+"','"
                    +a.getText()+"','"
                    +a.getAuthor()+"', sysdate(),'"
                    +a.getPhoto_url()+"',"
                    +a.getCategory()+");";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("could not add new article");
        }
    } 

    @Override
    public List<Article> showArticle() {
        if(cnx == null){
            Utilities.alert("ERROR", "could not connect to database.");
            return null;
        }
        List<Article> list = new ArrayList<>();
        try{
            Statement stm = this.cnx.createStatement();
            String query = "SELECT article.*, admin.name FROM `article` inner join admin on article.admin_id=admin.id;";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()){
                Article a = new Article();
                a.setArticle_id(rst.getInt("article_id"));
                a.setAdmin_id(rst.getInt("admin_id"));
                a.setAdmin_name(rst.getString("name"));
                a.setTitle(rst.getString("title"));
                a.setText(rst.getString("text"));
                a.setAuthor(rst.getString("author"));
                a.setDate(rst.getDate("date"));
                a.setPhoto_url(rst.getString("photo_url"));
                a.setCategory(rst.getInt("category"));
                list.add(a);
            }
        }catch(SQLException ex){
            System.out.println("Could not show articles");
            list = null;
        }
        return list;
    }

    @Override
    public void modArticle(Article a) {
         if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return;
        }
        try{
            Statement stm = cnx.createStatement();
            String query = "update article set "
                    + "title = '"+a.getTitle()+"',"
                    + "text = '"+a.getText()+"',"
                    + "author = '"+a.getAuthor()+"',"
                    + "photo_url = '" + a.getPhoto_url() + "',"
                    + "category = " + a.getCategory() + " "
                    + "where article_id=" + a.getArticle_id() +";";
            stm.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("Could not modify article");
        }
    }

    @Override
    public boolean delArticle(Article a) {
         if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return false;
        }
        try{
            Statement stm = cnx.createStatement();
            String query = "delete from article where article_id="+a.getArticle_id()+";";
            stm.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("Could not delete article");
            return false;
        }
        return true;
    }

    @Override
    public Article getArticle(int id) {
        if(cnx == null){
            Utilities.alert("ERROR", "could not connect to database.");
            return null;
        }
        try{
            Statement stm = this.cnx.createStatement();
            String query = "select * from article where article_id="+id+";";
            ResultSet rst = stm.executeQuery(query);
            if(rst.next()){
                Article a = new Article();
                a.setArticle_id(rst.getInt("article_id"));
                a.setAdmin_id(rst.getInt("admin_id"));
                a.setTitle(rst.getString("title"));
                a.setText(rst.getString("text"));
                a.setAuthor(rst.getString("author"));
                a.setDate(rst.getDate("date"));
                a.setPhoto_url(rst.getString("photo_url"));
                a.setCategory(rst.getInt("category"));
                System.out.println();
                return a;
            }else{
                return null;
            }
        }catch(SQLException ex){
            System.out.println("Could not show articles");
        }
        return null;
    }

    @Override
    public List<Comment> sortByNew(int article_id) {
         List<Comment> list = new ArrayList<>();
        try{
            Statement stm = this.cnx.createStatement();
            System.out.println("xxxx " + article_id);
            String query = "SELECT comment.*, client.name, client.surname FROM `comment` inner join client on comment.client_id=client.id where article_id="+article_id+" order by date DESC;";
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
    public List<Comment> sortByHot(int article_id) {
        List<Comment> list = new ArrayList<>();
        try{
            Statement stm = this.cnx.createStatement();
            System.out.println("xxxx " + article_id);
            String query = "SELECT comment.*, count(likes.id_like) as likes_count, client.name, client.surname from `comment` left join likes on likes.id_comment = comment.id inner join client on client.id = comment.client_id where article_id="+article_id+" group by comment.id order by likes_count DESC;";
            //String query = "SELECT comment.*, client.name, client.surname FROM `comment` inner join client on comment.client_id=client.id where article_id="+article_id+";";
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
    
}
