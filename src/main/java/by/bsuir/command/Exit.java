/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Exit"
 * @author Maria
 */
public class Exit implements Command{
    private User user;

    /**
     * Constructor without parameters
     */
    public Exit() {
        user = new User();
    }

    /**
     * This method is used to exit
     * @param request - request
     * @param response -  response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("locale");
        return "/index.jsp";
    }
    
}
