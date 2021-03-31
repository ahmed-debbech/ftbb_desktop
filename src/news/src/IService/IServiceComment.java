/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.src.IService;


import java.util.List;
import news.src.Enitity.Comment;

/**
 *
 * @author Ahmed
 */
public interface IServiceComment {
    public List<Comment> showComment(String id);
    public void delComment(Comment a);
    public void addComment(Comment a);
    public int countComments(String art_id);
    public List<Comment> searchComment(int article_id, String text);
}
