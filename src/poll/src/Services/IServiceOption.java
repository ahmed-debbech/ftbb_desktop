/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.Services;




import java.sql.SQLException;
import java.util.List;
import poll.src.Entities.Option;
/**
 *
 * @author sbs
 */
public interface IServiceOption {
    
    public void AddOption(Option o, int poll_id);
    
    public List<Option> ViewOptions() throws SQLException;
    
    public String displayoption (int poll_id, int n);
    
    public int optId(int poll_id , int n);

}