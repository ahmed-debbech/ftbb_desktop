/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.src.IService;
import java.util.List;

import store.src.Entities.Command;

/**
 *
 * @author PC
 */
public interface IServiceCommand {
    public List<Command> showCommand();
    public void updateCommand(Command c, int v);
    public List<Command> showClientCommands(int cl);
}
