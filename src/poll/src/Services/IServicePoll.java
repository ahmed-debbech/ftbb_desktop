/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.Services;


import java.util.List;
import poll.src.Entities.Poll;


/**
 *
 * @author Slim
 */
public interface IServicePoll {
    
    public void AddPoll(Poll p);
    public List<Poll> ViewPoll() ;
    public void DeletePoll(Poll p);
     public void DeleteAll ();
    public void swapstatus(int poll_id);
    public List<Poll> searchPoll(String Text);
    
    
    
    
}



