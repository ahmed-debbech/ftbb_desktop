/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Client;
import java.util.List;

/**
 *
 * @author Yassine
 */
public interface IServiceClient {
    public void AddClient(Client c);

    public List<Client> AffichierClient();

    public void UpdateClient(Client c);
    public void UpdateClientPass(String pass);
    
}
