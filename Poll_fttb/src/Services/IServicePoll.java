/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Option;
import Entities.Poll;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Slim
 */
public interface IServicePoll {
    
    public void AddPoll(Poll p);
    public List<Poll> ViewPoll() ;
    public void DeletePoll(Poll p);
    public void swapstatus(int poll_id);

    
    
    
}



