/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.ApplicationDAO;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import org.apache.log4j.Logger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Don't accept users application"
 * @author Maria
 */
public class DontAcceptApplication implements Command {

    private ApplicationDAO dao;
    final static Logger log = Logger.getLogger(DontAcceptApplication.class);


    /**
     * This method is used to not accept users application 
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message;
        int id = Integer.parseInt(request.getParameter("application"));
        int idAdmin = ((User) request.getSession().getAttribute("user")).getId();
        dao = new ApplicationDAO();
        try {
            dao.updateStatus(id, idAdmin, "Заявка отклонена");
        } catch (ConnectionPollException ex) {
            log.error(ex);
            return "/error.jsp";
        } catch (DAOExeption ex) {
            log.error(ex);
            return "/error.jsp";
        }
        if (request.getSession().getAttribute("locale").equals("ru-RU")
                || request.getSession().getAttribute("locale").equals("ru")) {
            message = new LocaleRU().getValue(LocaleRU.APPLIC);
        } else {
            message = new LocaleEN().getValue(LocaleEN.APPLIC);
        }
        request.setAttribute("result", message);
        return "/result.jsp";
    }
}
