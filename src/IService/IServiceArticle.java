/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Enitity.Article;
import Enitity.Comment;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public interface IServiceArticle {
    public void addArticle(Article a);
    public List<Article> showArticle();
    public void modArticle(Article a);
    public boolean delArticle(Article a);
    public Article getArticle(int id);
     public List<Comment> sortByNew(int article_id);
    public List<Comment> sortByHot(int article_id);
    public List<Article> searchArticle(String snap);
}
