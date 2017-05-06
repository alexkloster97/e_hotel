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
 * This class is used to organize command "Set locale"
 * @author Maria
 */
public class SetLocaleCommand implements Command {

    /**
     * This method is used to set locale 
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String) request.getParameter("locales");
        if (locale.equals("en_EN")) {
            request.getSession().setAttribute("locale", "en-EN");
        } else {
            if (locale.equals("ru_RU")) {
                request.getSession().setAttribute("locale", "ru-RU");
            } else {
                request.getSession().setAttribute("locale", "ru-RU");
            }

        }
        if (((User) request.getSession().getAttribute("user")) == null) {
            return "/index.jsp";
        } else {
            if (((User) request.getSession().getAttribute("user")).getGroup().getId() == 2) {
                return "/userPage.jsp";
            } else {
                return "/adminPage.jsp";
            }
        }
    }
}
