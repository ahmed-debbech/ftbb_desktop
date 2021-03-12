/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Enitity.Comment;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public interface IServiceComment {
    public List<Comment> showComment(String id);
    public void delComment(Comment a);
    public void addComment(Comment a);
}
