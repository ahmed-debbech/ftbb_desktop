/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.src.Services;


import poll.src.Entities.Vote;

public interface IServiceVote {
    
      public void AddVote(Vote v, int option_id);
      public void INCVote(int option_id);
      public int VoteNbr (int option_id);
}
