/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Enitity.Article;
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
            System.out.println("of: " +String.valueOf(a.getArticle_id()) 
            +String.valueOf(a.getAdmin_id())
            + a.getTitle()
            + a.getText()
            + a.getAuthor());
            String query = "insert into article (article_id, admin_id,"
                    + "title, text, author, date, photo_url, video_url, category) values ("
                    +String.valueOf(a.getArticle_id())+","
                    +String.valueOf(a.getAdmin_id())+",'"
                    +a.getTitle()+"','"
                    +a.getText()+"','"
                    +a.getAuthor()+"', sysdate(),'"
                    +a.getPhoto_url()+"','"
                    +a.getVideo_url()+"',"
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
            String query = "select * from `article`;";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()){
                Article a = new Article();
                a.setArticle_id(rst.getInt("article_id"));
                a.setAdmin_id(rst.getInt("admin_id"));
                a.setTitle(rst.getString("title"));
                a.setText(rst.getString("text"));
                a.setAuthor(rst.getString("author"));
                a.setDate(rst.getDate("date"));
                a.setPhoto_url(rst.getString("photo_url"));
                a.setVideo_url(rst.getString("video_url"));
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
                    + "video_url = '" + a.getVideo_url() + "',"
                    + "category = " + a.getCategory() + " "
                    + "where article_id=" + a.getArticle_id() +";";
            stm.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("Could not modify article");
        }
    }

    @Override
    public void delArticle(Article a) {
         if(cnx == null){
             Utilities.alert("ERROR", "could not connect to database.");
            return;
        }
        try{
            Statement stm = cnx.createStatement();
            String query = "delete from article where article_id="+a.getArticle_id()+";";
            stm.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println("Could not delete article");
        }
    }
    
}
