/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Admin;
import Entities.Client;




/**
 *
 * @author Yassine
 */
public interface IServiceCnx {
   public boolean CheckAdminCnx(String mail,String pwd);
    public boolean checkPass(int id ,String pwd);
   public boolean CheckClientCnx(String mail, String pwd);
}
