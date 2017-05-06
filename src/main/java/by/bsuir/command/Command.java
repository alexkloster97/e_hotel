/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface is used to organize pattern Command
 * @author Maria
 */
public interface Command {
    /**
     * Method that would be overridden
     * @param request
     * @param response
     * @return page
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException;
}
