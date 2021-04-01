/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.src.IService;

import java.util.List;
import user.src.Entities.Admin;
import user.src.Entities.Client;
import user.src.Entities.Password;



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
    public void UpdateAdminPass(String pass);
}
