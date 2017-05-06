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
 * This class is used to organize command "Booking"
 * @author Maria
 */
public class BookingCommand implements Command {

    private boolean flag;
    private TypeRoomDAO dao;
    private ApplicationDAO daoApp;
    private Application application;
    final static Logger log = Logger.getLogger(BookingCommand.class);

    /**
     * Constructor with parameters
     * @param flag - show that would be create two different commands
     */
    public BookingCommand(boolean flag) {
        this.flag = flag;
        application = new Application();
    }

    /**
     * Constructor without parameters
     */
    public BookingCommand() {
        application = new Application();
        flag = false;
    }

    /**
     * This method is used to make booking or to make the transition
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
        dao = new TypeRoomDAO();
        if (flag) {

            try {
                request.setAttribute("type", dao.read());
                page = "/application.jsp";
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
                application.setDateFrom(Date.valueOf(dateFrom));
                application.setDateTo(Date.valueOf(dateTo));
                application.setIdUpdate(((User) request.getSession().getAttribute("user")).getId());
                application.setMoneyMax(Integer.parseInt(request.getParameter("price")));
                application.setStatus("Ожидает рассмотрения");
                user.setId(((User) request.getSession().getAttribute("user")).getId());
                application.setUser(user);
                type.setId(Integer.parseInt(request.getParameter("typeNumber")));
                application.setType(type);
                daoApp = new ApplicationDAO();
                try {
                    int i = daoApp.create(application);
                    if (i != 0) {
                        if (request.getSession().getAttribute("locale").equals("ru-RU")
                                || request.getSession().getAttribute("locale").equals("ru")) {
                            message = new LocaleRU().getValue(LocaleRU.BOOKING_RESULT);
                        } else {
                            message = new LocaleEN().getValue(LocaleEN.BOOKING_RESULT);
                        }
                        request.setAttribute("result", message);
                        page = "/resultUser.jsp";
                    } else {
                        request.setAttribute("type", dao.read());
                        if (request.getSession().getAttribute("locale").equals("ru-RU")
                                || request.getSession().getAttribute("locale").equals("ru")) {
                            message = new LocaleRU().getValue(LocaleRU.TRY_AGAIN);
                        } else {
                            message = new LocaleEN().getValue(LocaleEN.TRY_AGAIN);
                        }
                        request.setAttribute("error", message);
                        page = "/application.jsp";
                    }
                } catch (DAOExeption ex) {
                    log.error(ex);
                    
                    return "/error.jsp";
                } catch (ConnectionPollException ex) {
                    log.error(ex);
                    return "/error.jsp";
                }
            } else {
                try {
                    request.setAttribute("type", dao.read());
                } catch (DAOExeption ex) {
                    log.error(ex);
                    return "/error.jsp";
                } catch (ConnectionPollException ex) {
                    log.error(ex);
                    return "/error.jsp";
                }
                if (request.getSession().getAttribute("locale").equals("ru-RU")
                        || request.getSession().getAttribute("locale").equals("ru")) {
                    message = new LocaleRU().getValue(LocaleRU.WARNING);
                } else {
                    message = new LocaleEN().getValue(LocaleEN.WARNING);
                }
                request.setAttribute("error", message);
                page = "/application.jsp";
            }
        }
        return page;
    }
}
