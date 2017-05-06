/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.MD5.HashingMD5;
import by.bsuir.hotel.dao.UserDAO;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.entity.UserGroup;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import by.bsuir.hotel.resource.ResourcesBundle;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Add new admin"
 * @author Maria
 */
public class AddNewAdmin implements Command {

    private User admin;
    private UserGroup group;
    private UserDAO dao;
    final static Logger log = Logger.getLogger(AddNewAdmin.class);


    /**
     * Constructor without parameters
     */
    public AddNewAdmin() {
        admin = new User();
        group = new UserGroup();
    }

    /**
     * This method is used to create new admin
     * @param request - request
     * @param response - response
     * @return page
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao = new UserDAO();
        String message;
        String page = null;
        if (Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.NAME), request.getParameter("name"))
                && Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.NAME), request.getParameter("surname"))
                && Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.LOGIN), request.getParameter("login"))) {
            admin.setName(request.getParameter("name"));
            admin.setSurname(request.getParameter("surname"));
            admin.setLogin(request.getParameter("login"));
            String password = request.getParameter("password");
            try {
                password = HashingMD5.hashing(request.getParameter("password"));
            } catch (Exception ex) {
                log.error(ex);
            }
            admin.setPassword(password);
            group.setId(1);
            admin.setGroup(group);
            try {
                User tmp = dao.findByLogin(admin.getLogin());
                if (tmp != null) {
                    if (request.getSession().getAttribute("locale").equals("ru-RU") 
                            || request.getSession().getAttribute("locale").equals("ru")) {
                        message = new LocaleRU().getValue(LocaleRU.USER_EXIST);
                    } else {
                        message = new LocaleEN().getValue(LocaleEN.USER_EXIST);
                    }

                    request.setAttribute("error", message);
                    page = "/addNewAdmin.jsp";
                } else {
                    int i = dao.create(admin);
                    if (i != 0) {
                        if (request.getSession().getAttribute("locale").equals("ru-RU")
                                || request.getSession().getAttribute("locale").equals("ru")) {
                            message = new LocaleRU().getValue(LocaleRU.ADMIN_ADD_SUCCSES);
                        } else {
                            message = new LocaleEN().getValue(LocaleEN.ADMIN_ADD_SUCCSES);
                        }
                        request.setAttribute("result", message);
                        page = "/result.jsp";
                    }
                }
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";

            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            }
        } else {
            if (request.getSession().getAttribute("locale").equals("ru-RU") 
                    || request.getSession().getAttribute("locale").equals("ru")) {
                message = new LocaleRU().getValue(LocaleRU.WARNING);
            } else {
                message = new LocaleEN().getValue(LocaleEN.WARNING);
            }
            request.setAttribute("error", message);
            page = "/addNewAdmin.jsp";
        }

        return page;
    }
}
