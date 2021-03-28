/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Vote;
import java.sql.SQLException;
import java.util.List;

public interface IServiceVote {
    
      public void AddVote(Vote v, int option_id);
      public void INCVote(int option_id);
      public int VoteNbr (int option_id);
}
