/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.MD5.HashingMD5;
import by.bsuir.hotel.dao.UserDAO;
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
 * This class is used to organize command "Enter user"
 * @author Maria
 */
public class EnterUserCommand implements Command {

    private User user;
    private UserDAO dao;
    final static Logger log = Logger.getLogger(EnterUserCommand.class);


    /**
     *Constructor without parameters
     */
    public EnterUserCommand() {
        user = new User();
    }

    /**
     * This method is used to enter user
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user.setLogin(request.getParameter("login"));
        String password;
        try {
            password = HashingMD5.hashing(request.getParameter("password"));
        } catch (Exception ex) {
            log.error(ex);
            return "/error.jsp";
        }
        user.setPassword(password);
        User daoUser = null;
        String page = null;
        String message;
        try {
            dao = new UserDAO();
            daoUser = dao.findByLogin(user.getLogin());
            if (daoUser == null) {
                if (request.getSession().getAttribute("locale").equals("ru-RU")
                        || request.getSession().getAttribute("locale").equals("ru")) {
                    message = new LocaleRU().getValue(LocaleRU.WRONG_LOGIN);
                } else {
                    message = new LocaleEN().getValue(LocaleEN.WRONG_LOGIN);
                }
                request.setAttribute("userError", message);
                page = "/index.jsp";
            } else {
                if (daoUser.getPassword().equals(user.getPassword())) {
                    request.getSession().setAttribute("user", daoUser);
                    if ("admin".equals(daoUser.getGroup().getGroup())) {
                        page = "/adminPage.jsp";
                    }
                    if ("user".equals(daoUser.getGroup().getGroup())) {
                        page = "/userPage.jsp";
                    }
                } else {
                    if (request.getSession().getAttribute("locale").equals("ru-RU")
                            || request.getSession().getAttribute("locale").equals("ru")) {
                        message = new LocaleRU().getValue(LocaleRU.WRONG_PASSWORD);
                    } else {
                        message = new LocaleEN().getValue(LocaleEN.WRONG_PASSWORD);
                    }
                    request.setAttribute("userError", message);
                    page = "/index.jsp";
                }

            }
        } catch (ConnectionPollException ex) {
            log.error(ex);
            return "/error.jsp";
        } catch (DAOExeption ex) {
            log.error(ex);
            return "/error.jsp";
        }

        return page;
    }
}
