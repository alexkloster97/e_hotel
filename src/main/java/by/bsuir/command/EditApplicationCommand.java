/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsuir.command;

import by.bsuir.hotel.dao.ApplicationDAO;
import by.bsuir.hotel.dao.TypeRoomDAO;
import by.bsuir.hotel.entity.Application;
import by.bsuir.hotel.entity.TypeOfRoom;
import by.bsuir.hotel.entity.User;
import by.bsuir.hotel.exeption.ConnectionPollException;
import by.bsuir.hotel.exeption.DAOExeption;
import by.bsuir.hotel.resource.LocaleEN;
import by.bsuir.hotel.resource.LocaleRU;
import by.bsuir.hotel.resource.ResourcesBundle;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Date;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to organize command "Edit application"
 * @author Maria
 */
public class EditApplicationCommand implements Command {

    private boolean flag;
    private ApplicationDAO dao;
    private TypeRoomDAO typedao;
    private Application app;
    final static Logger log = Logger.getLogger(EditApplicationCommand.class);


    /**
     * Constructor with parameters
     * @param flag - show that would be create two different commands
     */
    public EditApplicationCommand(boolean flag) {
        this.flag = flag;
        app = new Application();
    }

    /**
     * This method is used to edit applications or to make the transition
     * @param request - request
     * @param response - response
     * @return page which would be made the transition
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String message;
        if (flag) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao = new ApplicationDAO();
            typedao = new TypeRoomDAO();
            try {
                app = (Application) dao.getByID(id);
                request.getSession().setAttribute("myApplication", app);
                request.getSession().setAttribute("type", typedao.read());
                page = "/editApplication.jsp";
            } catch (DAOExeption ex) {
                log.error(ex);
                return "/error.jsp";
            } catch (ConnectionPollException ex) {
                log.error(ex);
                return "/error.jsp";
            }
        } else {
            if (Pattern.matches(new ResourcesBundle().getValue(ResourcesBundle.PRICE), request.getParameter("price"))) {
                String dateFrom = String.valueOf(request.getParameter("dateFrom"));
                String dateTo = String.valueOf(request.getParameter("dateTo"));
                User user = new User();
                TypeOfRoom type = new TypeOfRoom();
                app.setId(((Application) request.getSession().getAttribute("myApplication")).getId());
                app.setDateFrom(Date.valueOf(dateFrom));
                app.setDateTo(Date.valueOf(dateTo));
                app.setIdUpdate(((User) request.getSession().getAttribute("user")).getId());
                app.setMoneyMax(Integer.parseInt(request.getParameter("price")));
                app.setStatus("Ожидает рассмотрения");
                user.setId(((User) request.getSession().getAttribute("user")).getId());
                app.setUser(user);
                type.setId(Integer.parseInt(request.getParameter("typeNumber")));
                app.setType(type);
                dao = new ApplicationDAO();
                int i;
                try {
                    i = dao.update(app);
                    if (i != 0) {
                        if (request.getSession().getAttribute("locale").equals("ru-RU")
                                || request.getSession().getAttribute("locale").equals("ru")) {
                            message = new LocaleRU().getValue(LocaleRU.EDIT_APPLIC);
                        } else {
                            message = new LocaleEN().getValue(LocaleEN.EDIT_APPLIC);
                        }
                        request.setAttribute("result", message);
                        page = "/resultUser.jsp";
                    } else {
                        page = "/userApplication.jsp";

                    }
                } catch (DAOExeption ex) {
                    log.error(ex);
                    return "/error.jsp";
                } catch (ConnectionPollException ex) {
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
                page = "/editApplication.jsp";
            }
        }
        return page;
    }
}
