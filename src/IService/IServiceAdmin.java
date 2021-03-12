/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Admin;
import Entities.Client;
import Entities.Password;
import java.util.List;

/**
 *
 * @author Yassine
 */
public interface IServiceAdmin {
     public void UnBanClient(int id_Client) ;
    public Admin SelectAdmin(int id);
    public void AddPasswordAdmin(Password a);
    public void AddAdmin(Admin a);

    public List<Admin> AffichierAdmin();

    public void UpdateAdmin(Admin a);

    public void DeleteAdmin(Admin a);

    public void BanClient(int id_Client);
    
    public void UpdateClient(Client c) ;
}
