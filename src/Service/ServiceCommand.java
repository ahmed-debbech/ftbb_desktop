/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Command;
import Entities.Product;
import IService.IServiceCommand;
import Utils.SqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ServiceCommand implements IServiceCommand{
    Connection cnx;

    public ServiceCommand() {
        cnx=SqlConnection.getInstance().getConnection();
    }
    

    @Override
    public List<Command> showCommand() {
        List<Command> commands = new ArrayList<>();
        try{
             Statement stm = cnx.createStatement();
             String query="select * from `command`; ";
             ResultSet rst = stm.executeQuery(query);
           
             while (rst.next())
             {
                 Command co = new Command();
                 co.setCommand_id(rst.getInt("command_id"));
                 co.setId_client(rst.getInt("id_client"));
                 co.setDate_command(rst.getDate("date_command"));
                 co.setStatus(rst.getInt("status"));
                 co.setTotal_price(rst.getInt("total_price"));
              
                 
                 commands.add(co);
             }
        }catch(SQLException s){
            System.out.println("not showed");
        }
        return commands;
    }

    @Override
    public void updateCommand(Command c, int v) {
        try {
            Statement stm = cnx.createStatement();
            String query = "UPDATE command SET status="+v+" WHERE command_id="+c.getCommand_id()+";";
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("COULD NOT UPDATE");
        }
    }
}
