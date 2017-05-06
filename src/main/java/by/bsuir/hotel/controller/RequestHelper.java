/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.hotel.controller;

import by.bsuir.command.AddNewAdmin;
import by.bsuir.command.AddNewUser;
import by.bsuir.command.AddNumberCommand;
import by.bsuir.command.BlockAdminCommand;
import by.bsuir.command.BookingCommand;
import by.bsuir.command.ClientsApplicationCommand;
import by.bsuir.command.Command;
import by.bsuir.command.DeleteApplicationCommand;
import by.bsuir.command.DeleteNumberCommand;
import by.bsuir.command.DontAcceptApplication;
import by.bsuir.command.EditApplicationCommand;
import by.bsuir.command.EditNumberCommand;
import by.bsuir.command.EditUserCommand;
import by.bsuir.command.EnterUserCommand;
import by.bsuir.command.Exit;
import by.bsuir.command.FindNumberByType;
import by.bsuir.command.SetLocaleCommand;
import by.bsuir.command.ShowBillCommand;
import by.bsuir.command.ShowUserApplication;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 * This class is used to help organize activities of servlet
 * @author Maria
 */
public class RequestHelper {
    private static RequestHelper instance = null;
    HashMap<String, Command> commands = new HashMap<String, Command>();

    
    
    private RequestHelper() {
        commands.put("addNewUser", new AddNewUser());
        commands.put("addNewAdmin", new AddNewAdmin());
        commands.put("EnterUser", new EnterUserCommand());
        commands.put("ExitUser", new Exit());
        commands.put("EditUser", new EditUserCommand());
        commands.put("FindByType", new FindNumberByType());
        commands.put("DeleteNumber", new DeleteNumberCommand());
        commands.put("EditNumber", new EditNumberCommand(true));
        commands.put("SaveEditNumber", new EditNumberCommand());
        commands.put("FindAdmin", new BlockAdminCommand(true));
        commands.put("DeleteAdmin", new BlockAdminCommand());
        commands.put("AddNumber", new AddNumberCommand(true));
        commands.put("SaveNumber", new AddNumberCommand());
        commands.put("Booking", new BookingCommand());
        commands.put("NewBooking", new BookingCommand(true));
        commands.put("UserApplication", new ShowUserApplication());
        commands.put("EditApplication", new EditApplicationCommand(true));
        commands.put("SaveEditApplication", new EditApplicationCommand(false));
        commands.put("DeleteApplication", new DeleteApplicationCommand());
        commands.put("SeeBill", new ShowBillCommand());
        commands.put("SeeClientsApplication", new ClientsApplicationCommand(true));
        commands.put("AcceptApplicaion", new ClientsApplicationCommand(false));
        commands.put("DontAcceptApplication", new DontAcceptApplication());
        commands.put("SetLocale", new SetLocaleCommand());
    }
    
    /**
     * This method is used to get command
     * @param request - request
     * @return Command
     */
    public Command getCommand(HttpServletRequest request){
        Command  c = null;
        String action = request.getParameter("command");
        System.out.println(action);
        c = commands.get(action);
        return c;
    }
    
    /**
     * This method is used to create object of class RequestHelper
     * @return object of class RequestHelper
     */
    public static RequestHelper getInstance(){
        if(instance == null){
            instance = new RequestHelper();
        }
        return instance;
    }
    
}
