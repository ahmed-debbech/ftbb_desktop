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
            String query = "select * from `comment` where article_id="+id+";";
            ResultSet rst = stm.executeQuery(query);
            while(rst.next()){
                Comment a = new Comment();
                a.setId(rst.getInt("id"));
                a.setContent(rst.getString("content"));
                a.setClient_id(rst.getInt("client_id"));
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
    
}
