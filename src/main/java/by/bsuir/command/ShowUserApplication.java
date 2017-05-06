/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.ApplicationDAO;
import by.bsuir.hotel.entity.Application;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Show users application"
 * @author Maria
 */
public class ShowUserApplication implements Command {

    final static Logger log = Logger.getLogger(ShowUserApplication.class);
    private List<Application> applications;
    private ApplicationDAO dao;

    /**
     * Constructor without parameters
     */
    public ShowUserApplication() {
        applications = new ArrayList<Application>();
    }

    /**
     * This method is used to show information about users applications 
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message;
        int idUser = ((User) request.getSession().getAttribute("user")).getId();
        dao = new ApplicationDAO();
        try {
            applications = dao.findApplicationByIdUser(idUser);
            if (applications.size() != 0) {
                request.getSession().setAttribute("application", applications);
                request.getSession().setAttribute("result", null);
            } else {
                if (request.getSession().getAttribute("locale").equals("ru-RU")
                        || request.getSession().getAttribute("locale").equals("ru")) {
                    message = new LocaleRU().getValue(LocaleRU.NO_APPLIC);
                } else {
                    message = new LocaleEN().getValue(LocaleEN.NO_APPLIC);
                }
                request.getSession().setAttribute("result", message);
            }
        } catch (DAOExeption ex) {
            log.error(ex);
            return "/error.jsp";
        } catch (ConnectionPollException ex) {
            log.error(ex);
            return "/error.jsp";
        }

        return "/userApplication.jsp";
    }
}
