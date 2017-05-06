/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.UserDAO;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.entity.UserGroup;
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
 * This class is used to organize command "Block admin"
 * @author Maria
 */
public class BlockAdminCommand implements Command {

    private User user;
    private UserGroup group;
    private UserDAO dao;
    private boolean flag;
    final static Logger log = Logger.getLogger(BlockAdminCommand.class);

    /**
     * Constructor with parameters
     * @param flag - show that would be create two different commands
     */
    public BlockAdminCommand(boolean flag) {
        this.flag = flag;
        user = new User();
        group = new UserGroup();
    }

    /**
     * Constructor without parameters
     */
    public BlockAdminCommand() {
        flag = false;
        user = new User();
        group = new UserGroup();
    }

    /**
     * This method is used to block admin or to make the transition
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        List<User> users = new ArrayList<User>();
        String message;
        if (flag) {
            int idUser = ((User) request.getSession().getAttribute("user")).getId();
            int idGroup = ((User) request.getSession().getAttribute("user")).getGroup().getId();
            dao = new UserDAO();
            try {
                users = dao.findAdmin(idGroup, idUser);
                if (users.size() != 0) {
                    request.setAttribute("typeAdmin", users);
                } else {
                    if (request.getSession().getAttribute("locale").equals("ru-RU")
                            || request.getSession().getAttribute("locale").equals("ru")) {
                        message = new LocaleRU().getValue(LocaleRU.NONE_ADMIN);
                    } else {
                        message = new LocaleEN().getValue(LocaleEN.NONE_ADMIN);
                    }
                    request.setAttribute("none", message);
                }
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }

        } else {
            int idUser = ((User) request.getSession().getAttribute("user")).getId();
            int idGroup = ((User) request.getSession().getAttribute("user")).getGroup().getId();
            int id = Integer.parseInt(request.getParameter("user"));
            System.out.println(id);
            dao = new UserDAO();
            try {
                User delete = (User) dao.getByID(id);
                int i = dao.delete(delete);
                if (i != 0) {
                    users = dao.findAdmin(idGroup, idUser);
                    if (users.size() != 0) {
                        request.setAttribute("typeAdmin", users);
                    } else {
                        if (request.getSession().getAttribute("locale").equals("ru-RU")
                                || request.getSession().getAttribute("locale").equals("ru")) {
                            message = new LocaleRU().getValue(LocaleRU.NONE_ADMIN);
                        } else {
                            message = new LocaleEN().getValue(LocaleEN.NONE_ADMIN);
                        }
                        request.setAttribute("none", message);
                    }
                }
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
        }
        page = "/deleteAdmin.jsp";
        return page;
    }
}
